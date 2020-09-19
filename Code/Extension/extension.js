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
        commands.registerCommand('m.open', () => {
            const panel = window.createWebviewPanel(
                'mVisual', // Identifies the type of the webview. Used internally
                'M Blocks', // Title of the panel displayed to the user
                ViewColumn.One, // Editor column to show the new webview panel in.
                {
                    enableScripts: true
                } // Webview options. More on these later.
              );
              console.log("0");
              const blocklyPath = Uri.file(
                path.join(context.extensionPath, 'blockly.js')
              );
              const cssPath = Uri.file(path.join(context.extensionPath, 'index.css'));
              console.log(blocklyPath);
              const blockly = panel.webview.asWebviewUri(blocklyPath);
              const blocksPath = Uri.file(
                path.join(context.extensionPath, 'index.js')
              );
              console.log("vscode-resource:"+blocklyPath.path);
              const blocks = panel.webview.asWebviewUri(blocksPath);

            panel.webview.html = blocksHtml("vscode-resource:"+blocklyPath.path, "vscode-resource:"+blocksPath.path, "vscode-resource:"+cssPath);
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

function blocksHtml(blockly, blocks, css)
{
    return `<!DOCTYPE html>
    <html>
    <head>
      <title>M editor</title>
      <link rel="stylesheet" href="index.css">
    </head>
    
    <body>
    
      <section>  
        <p> Random text</p>
        <div id="blocklyDiv"></div>
      </section>
    
      <xml xmlns="https://developers.google.com/blockly/xml" id="toolbox" style="display: none">
        <category name="Statements">
          <block type="system"></block>
          <block type="selection"></block>
          <block type="cell"></block>
          <block type="times"></block>
          <block type="iteration"></block>
          <block type="set"></block>
          <block type="forall">
            <field name="entity">entity</field>
          </block>
        </category>
      </xml>
      <script src="${blockly}"></script>
      <script src="${blocks}"></script>
    </body>
    </html>`;
}