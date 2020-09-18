var {
    LanguageClient
} = require("vscode-languageclient");
const {
    window,
    commands,
    ViewColumn,
    Uri
} = require("vscode");
var {
    workspace
} = require('vscode');

var net = require('net');

var client;

exports.activate = function(context) {

    context.subscriptions.push(
        commands.registerCommand('m.restart', () => {
            if (client)
            {
                client.stop();
            }
            start();
        })
    );

    start();
}
exports.deactivate = function deactivate() {
    if (!client) {
        return undefined;
    }
    client.stop();
}

function start() {
    let connectionInfo = {
        port: 5007
    };
    let serverOptions = () => {
        // Connect to language server via socket
        let socket = net.connect(connectionInfo);
        let result = {
            writer: socket,
            reader: socket
        };
        return Promise.resolve(result);
    };

    var clientOptions = {
        documentSelector: [{
            scheme: 'file',
            language: 'm'
        }],
        synchronize: {
            fileEvents: workspace.createFileSystemWatcher('**/*.m')
        },
    };

    client = new LanguageClient('mserver', 'm language server', serverOptions, clientOptions);

    client.start();
}