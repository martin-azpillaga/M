const { window } = require("vscode");
const { lookpath } = require("lookpath");

var {
    LanguageClient
} = require("vscode-languageclient");
var {
    workspace
} = require('vscode');

var client;

exports.activate = async function(context) {

    var java = await lookpath("javaccc");
    if (!java)
    {
        window.showInformationMessage("M requires a Java 8+ runtime to execute. Please install and add Java 8+ to the path");
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