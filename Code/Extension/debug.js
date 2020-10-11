const { LanguageClient } = require("vscode-languageclient");
const {	commands, workspace } = require("vscode");
const net = require('net');

var client;

exports.activate = function(context)
{
	context.subscriptions.push(commands.registerCommand('m.restart', () =>
	{
		if (client)
		{
			client.stop();
		}
		start();
	}));

	start();
}

function start()
{
	let serverOptions = () =>
	{
		let socket = net.connect({port: 5007});
		let result =
		{
			writer: socket,
			reader: socket
		};
		return Promise.resolve(result);
	};

	var clientOptions =
	{
		documentSelector:
		[{
			scheme: 'file',
			language: 'm'
		}],
		synchronize:
		{
			fileEvents: [workspace.createFileSystemWatcher('**/*.Ⲙ'), workspace.createFileSystemWatcher('Ⲙ.json')]
		},
	};

	client = new LanguageClient('mserver', 'm language server', serverOptions, clientOptions, true);

	client.start();
}

exports.deactivate = function deactivate()
{
	if (!client)
	{
		return undefined;
	}
	client.stop();
}
