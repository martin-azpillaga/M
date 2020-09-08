const {
    window,
    commands,
    ViewColumn
} = require("vscode");
const {
    lookpath
} = require("lookpath");

var {
    LanguageClient
} = require("vscode-languageclient");
var {
    workspace
} = require('vscode');

var client;

exports.activate = async function(context) {

    context.subscriptions.push(
        commands.registerCommand('m.open', () => {
            const panel = window.createWebviewPanel(
                'mVisual', // Identifies the type of the webview. Used internally
                'M Blocks', // Title of the panel displayed to the user
                ViewColumn.One, // Editor column to show the new webview panel in.
                {} // Webview options. More on these later.
              );
            panel.webview.html = blocksHtml();
        })
    );

    var java = await lookpath("java");
    if (!java) {
        var selection = window.showInformationMessage("M requires a Java 8+ runtime to execute. Please install and add Java 8+ to the path", "Install Java 8+");
        selection.then(x => require("openurl").open("https://jdk.java.net/14/"));
        return;
    }

    var serverOptions = {
        run: {
            command: "java",
            args: ["-jar", context.asAbsolutePath("ls.jar")]
        }
    };

    var clientOptions = {
        documentSelector: [{
            scheme: 'file',
            language: 'm'
        }],
        synchronize: {
            fileEvents: workspace.createFileSystemWatcher('**/.m')
        }
    };

    client = new LanguageClient('mserver', 'm language server', serverOptions, clientOptions);

    client.start();
}
exports.deactivate = function() {
    if (!client) {
        return undefined;
    }
    client.stop();
}

function blocksHtml()
{
    return `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cat Coding</title>
</head>
<body>
    <img src="https://media.giphy.com/media/JIX9t2j0ZTN9S/giphy.gif" width="300" />
</body>
</html>`;
}