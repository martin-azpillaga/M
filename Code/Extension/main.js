const {
    window,
    commands,
    ViewColumn,
    Uri
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

var path = require('path');

var client;

exports.activate = async function(context) {

    context.subscriptions.push(
        commands.registerCommand('m.restart', () => {
            if (client) {
                client.stop();
            }
            start();
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
            fileEvents: workspace.createFileSystemWatcher('**/*.Ⲙ')
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
