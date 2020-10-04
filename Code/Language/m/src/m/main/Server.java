package m.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

public class Server implements LanguageServer, LanguageClientAware, WorkspaceService, TextDocumentService
{
	LanguageClient client;
	List<Project> projects;
	
	@Override
	public void connect(LanguageClient client)
	{
		this.client = client;
	}
	
	@Override
	public TextDocumentService getTextDocumentService()
	{
		return this;
	}

	@Override
	public WorkspaceService getWorkspaceService()
	{
		return this;
	}

	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params)
	{
        var options = new WorkspaceFoldersOptions();
		options.setSupported(true);
		options.setChangeNotifications(true);
		
		var capabilities = new ServerCapabilities();
		capabilities.setTextDocumentSync(TextDocumentSyncKind.Full);
		capabilities.setWorkspace(new WorkspaceServerCapabilities(options));
		capabilities.setHoverProvider(true);
		capabilities.setCompletionProvider(new CompletionOptions(false, Arrays.asList(".")));
		capabilities.setSignatureHelpProvider(new SignatureHelpOptions(Arrays.asList("(", ",")));
		
		projects = new ArrayList<>();
		
		for (var folder : params.getWorkspaceFolders())
		{
			var uri = folder.getUri();
			projects.add(new Project(uri));
		}
		
		return CompletableFuture.supplyAsync(()->new InitializeResult(capabilities));
	}

	@Override
	public void initialized()
	{
		publishDiagnostics();
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
			projects.add(new Project(uri));
		}
		for (var removed : params.getEvent().getRemoved())
		{
			var uri = removed.getUri();

			var iterator = projects.iterator();

			while(iterator.hasNext())
			{
				var project = iterator.next();
				if (project.root.equals(uri))
				{
					iterator.remove();
				}
			}
		}
		publishDiagnostics();
	}

	@Override
	public void didChangeConfiguration(DidChangeConfigurationParams params)
	{
		// No configuration is sync'ed between server and client
		// All configuration is stored in configuration files like m.project
		// so that the projects are more portable
	}

	@Override
	public void didChangeWatchedFiles(DidChangeWatchedFilesParams params)
	{
		for (var change : params.getChanges())
		{
			var uri = change.getUri();

			if (change.getType() == FileChangeType.Created)
			{
				for (var project : projects)
				{
					project.fileAdded(uri);
				}
			}
			else if (change.getType() == FileChangeType.Deleted)
			{
				for (var project : projects)
				{
					project.fileDeleted(uri);
				}
			}
		}
		publishDiagnostics();
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
		for (var project : projects)
		{
			project.fileChanged(uri, text);
		}
		publishDiagnostics();
	}

	private void publishDiagnostics()
	{
		var diagnostics = new HashMap<String,List<Diagnostic>>();

		for (var project : projects)
		{
			diagnostics.putAll(project.getDiagnostics());
		}

		for (var entry : diagnostics.entrySet())
		{
			client.publishDiagnostics(new PublishDiagnosticsParams(entry.getKey(), entry.getValue()));
		}
	}
	
	
	
	
	
	@Override
	public CompletableFuture<Hover> hover(HoverParams params)
	{
		var hover = new Hover();
		var result = "";

		for (var project : projects)
		{
			result += project.game.hover(params.getTextDocument().getUri(), params.getPosition());
		}

		var contents = new MarkupContent("markdown", result);
		hover.setContents(contents);
		return CompletableFuture.supplyAsync(() -> hover);
	}
	
	@Override
	public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params)
	{
		var path = params.getTextDocument().getUri();
		for (var project : projects)
		{
			if (project.contains(path))
			{
				var result = project.game.completions(path, params.getPosition());

				return CompletableFuture.supplyAsync(() -> Either.forLeft(result));
			}
		}
		return null;
    }
	
	@Override
	public CompletableFuture<SignatureHelp> signatureHelp(SignatureHelpParams params)
	{
		var path = params.getTextDocument().getUri();
		for (var project : projects)
		{
			if (project.contains(path))
			{
				var result = project.game.signature(path, params.getPosition());

				return CompletableFuture.supplyAsync(() -> result);
			}
		}
		return null;
	}
}
