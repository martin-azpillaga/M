var {join} = require("path");
var {LanguageClient} = require("vscode-languageclient");
var { workspace } = require('vscode');
const { spawn } = require('child_process')

var client;

exports.activate = function (context)
{
    console.log(context.asAbsolutePath("ls.jar"));

    
    var serverOptions =
    {
        run:
        {
            command: "java",
            args:["-jar", context.asAbsolutePath("ls.jar")]
        }
    };

    var clientOptions =
    {
        documentSelector: [{ scheme: 'file', language: 'm' }],
        synchronize:
        {
            fileEvents: workspace.createFileSystemWatcher('**/.m')
        }
    };

    client = new LanguageClient('mserver', 'mserver example', serverOptions, clientOptions);

    client.start();
}
exports.deactivate = function ()
{
    if (!client)
    {
        return undefined;
    }
    client.stop();
}