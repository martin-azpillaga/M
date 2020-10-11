package m.main;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.CompletionOptions;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeTextDocumentParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.DidChangeWorkspaceFoldersParams;
import org.eclipse.lsp4j.DidCloseTextDocumentParams;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.DidSaveTextDocumentParams;
import org.eclipse.lsp4j.FileChangeType;
import org.eclipse.lsp4j.Hover;
import org.eclipse.lsp4j.HoverParams;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.InitializedParams;
import org.eclipse.lsp4j.MarkupContent;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.SignatureHelp;
import org.eclipse.lsp4j.SignatureHelpOptions;
import org.eclipse.lsp4j.SignatureHelpParams;
import org.eclipse.lsp4j.TextDocumentSyncKind;
import org.eclipse.lsp4j.WorkspaceFoldersOptions;
import org.eclipse.lsp4j.WorkspaceServerCapabilities;
import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

public class Server implements LanguageServer, WorkspaceService, TextDocumentService
{
	LanguageClient client;
	List<Project> projects;

	@Override
	public WorkspaceService getWorkspaceService()
	{
		return this;
	}

	@Override
	public TextDocumentService getTextDocumentService()
	{
		return this;
	}


	public void connect(InputStream input, OutputStream output)
	{
		var connection = LSPLauncher.createServerLauncher(this, input, output);
		client = connection.getRemoteProxy();
		connection.startListening();
	}

	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params)
	{
		this.projects = new ArrayList<>();

		var options = new WorkspaceFoldersOptions();
		options.setSupported(true);
		options.setChangeNotifications(true);

		var capabilities = new ServerCapabilities();
		capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
		capabilities.setWorkspace(new WorkspaceServerCapabilities(options));
		capabilities.setHoverProvider(true);
		capabilities.setCompletionProvider(new CompletionOptions(false, Arrays.asList(".")));
		capabilities.setSignatureHelpProvider(new SignatureHelpOptions(Arrays.asList("(", ",")));


		for (var folder : params.getWorkspaceFolders())
		{
			var path = decode(folder.getUri());
			projects.add(new Project(path));
		}

		return CompletableFuture.supplyAsync(() -> new InitializeResult(capabilities));
	}

	@Override
	public void initialized(InitializedParams params)
	{
		for (var project : projects)
		{
			var diagnostics = project.initialize();
			publishDiagnostics(diagnostics);
		}
	}

	@Override
	public CompletableFuture<Object> shutdown()
	{
		return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
	}

	@Override
	public void exit()
	{
		System.exit(0);
	}




	@Override
	public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params)
	{
		for (var added : params.getEvent().getAdded())
		{
			var uri = added.getUri();
			var path = decode(uri);
			var project = new Project(path);
			projects.add(project);
			var diagnostics = project.initialize();
			publishDiagnostics(diagnostics);
		}
		for (var removed : params.getEvent().getRemoved())
		{
			var uri = removed.getUri();
			var path = decode(uri);

			var iterator = projects.iterator();

			while(iterator.hasNext())
			{
				var project = iterator.next();
				if (project.root.equals(path))
				{
					iterator.remove();
				}
			}
		}
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params)
	{
		for (var change : params.getChanges())
		{
			var uri = change.getUri();
			var file = decode(uri);

			if (change.getType() == FileChangeType.Created)
			{
				var text = read(uri);
				for (var project : projects)
				{
					var diagnostics = project.add(file, text);
					publishDiagnostics(diagnostics);
				}
			}
			else if (change.getType() == FileChangeType.Deleted)
			{
				for (var project : projects)
				{
					var diagnostics = project.delete(file);
					publishDiagnostics(diagnostics);
				}
			}
		}
	}

	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params)
	{

	}




	@Override
	public void didOpen(DidOpenTextDocumentParams params)
	{
		onChange(params.getTextDocument().getUri(), params.getTextDocument().getText());
	}

	@Override
	public void didChange(DidChangeTextDocumentParams params)
	{
		onChange(params.getTextDocument().getUri(), params.getContentChanges().get(0).getText());
	}

	@Override
	public void didClose(DidCloseTextDocumentParams params)
	{

	}

	@Override
	public void didSave(DidSaveTextDocumentParams params)
	{

	}

	private void onChange(String uri, String text)
	{
		var path = decode(uri);

		for (var project : projects)
		{
			var diagnostics = project.modify(path, text);
			publishDiagnostics(diagnostics);
		}
	}

	private void publishDiagnostics(Map<String,List<Diagnostic>> diagnostics)
	{
		for (var entry : diagnostics.entrySet())
		{
			var uri = encode(entry.getKey());
			client.publishDiagnostics(new PublishDiagnosticsParams(uri, entry.getValue()));
		}
	}





	@Override
	public CompletableFuture<Hover> hover(HoverParams params)
	{
		var hover = new Hover();
		var result = "";

		var position = params.getPosition();
		var uri = params.getTextDocument().getUri();
		var path = decode(uri);

		for (var project : projects)
		{
			if (project.contains(path))
			{
				result += project.hover(path, position);
			}
		}

		var contents = new MarkupContent("markdown", result);
		hover.setContents(contents);
		return CompletableFuture.supplyAsync(() -> hover);
	}

	@Override
	public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params)
	{
		var uri = params.getTextDocument().getUri();
		var path = decode(uri);

		for (var project : projects)
		{
			if (project.contains(path))
			{
				var result = project.completions(path, params.getPosition());

				return CompletableFuture.supplyAsync(() -> Either.forLeft(result));
			}
		}
		return null;
	}

	@Override
	public CompletableFuture<SignatureHelp> signatureHelp(SignatureHelpParams params)
	{
		var uri = params.getTextDocument().getUri();
		var path = decode(uri);

		for (var project : projects)
		{
			if (project.contains(path))
			{
				var result = project.signature(path, params.getPosition());

				return CompletableFuture.supplyAsync(() -> result);
			}
		}
		return null;
	}



	private String encode(String path)
	{
		return path;
	}

	private String decode(String path)
	{
		var result = "";
		try
		{
			result = URLDecoder.decode(path.replace("file://", ""),"UTF-8");
		}
		catch (UnsupportedEncodingException e){}

		var os = System.getProperty("os.name");

		if (os.startsWith("Win"))
		{
			result = result.substring(1);
		}

		return result;
	}

	private String read(String uri)
	{
		try
		{
			var url = new URI(uri).toURL();
			var inputStream = url.openStream();
			Scanner s = new Scanner(inputStream).useDelimiter("\\A");
			String result = s.hasNext() ? s.next() : "";
			s.close();
			return result;
		} catch (Exception e) {}

		return "";
	}
}
