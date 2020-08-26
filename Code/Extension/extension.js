var {
    LanguageClient
} = require("vscode-languageclient");
var {
    workspace
} = require('vscode');

var client;

exports.activate = function(context) {
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