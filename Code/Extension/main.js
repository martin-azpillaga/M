const {
	window,
	commands,
	workspace
} = require("vscode");

var {
	LanguageClient
} = require("vscode-languageclient");

const { spawn, spawnSync } = require('child_process');

var client;

exports.activate = async function(context) {

	context.subscriptions.push(
		commands.registerCommand('m.restart', () => {
			if (client) {
				client.stop();
			}
			connect(context);
		})
	);

	if (!java8Available()) {
		var selection = window.showInformationMessage("M requires a Java 8+ runtime to execute. Please install and add Java 8+ to the path", "Install Java 8+");
		selection.then(x => open("https://jdk.java.net/14/"));
		return;
	}

	connect(context);
}

exports.deactivate = function() {
	if (!client) {
		return undefined;
	}
	client.stop();
}

function connect(context)
{
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

function java8Available()
{
	var childProcess = spawnSync("java", ["-version"]);
	if (childProcess.error)
	{
		return false;
	}

	var output = childProcess.stderr.toString();
	var version = output.split("\n")[0].split(" ")[2].replace(/"/g,"").split(".").map(x=>parseInt(x));

	if (version[0] == 1)
	{
		return version[1] >= 8;
	}
	else
	{
		return version[0] >= 9;
	}
}

function open(url)
{
	var command = "";

	switch (process.platform)
	{
		case "darwin": command = "open";
		case "win32": command = "explorer.exe";
		case "linux": command = "xdg-open";
	}
	spawn(command, [url]);
}
