package m.main;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
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

import m.generator.IO;

public class Server implements LanguageServer, WorkspaceService, TextDocumentService
{
	LanguageClient client;
	Map<String,Project> projects;

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
		this.projects = new HashMap<>();

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
			var uri = folder.getUri();
			projects.put(uri, new Project(uri));
		}

		return CompletableFuture.supplyAsync(() -> new InitializeResult(capabilities));
	}

	@Override
	public void initialized(InitializedParams params)
	{
		for (var project : projects.values())
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
		//System.exit(0);
	}




	@Override
	public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params)
	{
		for (var added : params.getEvent().getAdded())
		{
			var uri = added.getUri();
			var project = new Project(uri);
			projects.put(uri,project);

			var diagnostics = project.initialize();
			publishDiagnostics(diagnostics);
		}
		for (var removed : params.getEvent().getRemoved())
		{
			var uri = removed.getUri();

			projects.remove(uri);
		}
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params)
	{
		for (var change : params.getChanges())
		{
			var file = change.getUri();

			if (change.getType() == FileChangeType.Created)
			{
				var text = IO.read(file);

				for (var project : projectsContaining(file))
				{
					var diagnostics = project.modify(file, text);
					publishDiagnostics(diagnostics);
				}
			}
			else if (change.getType() == FileChangeType.Deleted)
			{
				for (var project : projectsContaining(file))
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
		modify(params.getTextDocument().getUri(), params.getTextDocument().getText());
	}

	@Override
	public void didChange(DidChangeTextDocumentParams params)
	{
		modify(params.getTextDocument().getUri(), params.getContentChanges().get(0).getText());
	}

	@Override
	public void didClose(DidCloseTextDocumentParams params)
	{

	}

	@Override
	public void didSave(DidSaveTextDocumentParams params)
	{

	}





	@Override
	public CompletableFuture<Hover> hover(HoverParams params)
	{
		var position = params.getPosition();
		var file = params.getTextDocument().getUri();

		var result = "";

		for (var project : projectsContaining(file))
		{
			result += project.hover(file, position);
		}

		var hover = new Hover();
		var contents = new MarkupContent("markdown", result);
		hover.setContents(contents);
		return CompletableFuture.supplyAsync(() -> hover);
	}

	@Override
	public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params)
	{
		var file = params.getTextDocument().getUri();
		var position = params.getPosition();

		var result = new ArrayList<CompletionItem>();

		for (var project : projectsContaining(file))
		{
			result.addAll(project.completions(file, position));
		}

		return CompletableFuture.supplyAsync(() -> Either.forLeft(result));
	}

	@Override
	public CompletableFuture<SignatureHelp> signatureHelp(SignatureHelpParams params)
	{
		var file = params.getTextDocument().getUri();

		var result = new SignatureHelp();

		for (var project : projectsContaining(file))
		{
			result = project.signatures(file, params.getPosition(), params.getContext().getTriggerCharacter());
		}

		var finalResult = result;

		return CompletableFuture.supplyAsync(() -> finalResult);
	}




	private List<Project> projectsContaining(String file)
	{
		var result = new ArrayList<Project>();

		projects.forEach((folder,project) ->
		{
			if (IO.contains(folder,file))
			{
				result.add(project);
			}
		});

		if (result.isEmpty())
		{
			client.showMessage(new MessageParams(MessageType.Info, "Ⲙ file detected outside of workspace folders. Open the file in a workspace folder."));
		}

		return result;
	}

	private void modify(String file, String text)
	{
		for (var project : projectsContaining(file))
		{
			var diagnostics = project.modify(file, text);
			publishDiagnostics(diagnostics);
		}
	}

	private void publishDiagnostics(Map<String,List<Diagnostic>> diagnosticMap)
	{
		diagnosticMap.forEach((uri, diagnostics)->
		{
			var parameters = new PublishDiagnosticsParams(uri, diagnostics);
			client.publishDiagnostics(parameters);
		});
	}
}
