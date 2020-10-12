const { LanguageClient } = require("vscode-languageclient");
const {	window, commands, workspace } = require("vscode");
const { spawn, spawnSync } = require("child_process");
const net = require("net");

var client;

exports.activate = function(context)
{
	context.subscriptions.push(commands.registerCommand("m.restart", () =>
	{
		if (client)
		{
			client.stop();
		}
		start(context);
	}));

	start(context);
}

function start(context)
{
	if (!java8Available()) {
		var selection = window.showInformationMessage("M requires a Java 8+ runtime to execute. Please install and add Java 8+ to the path", "Install Java 8+");
		selection.then(x => open("https://jdk.java.net/14/"));
		return;
	}

	var debugging = process.env.DEBUG === "true";

	var serverOptions;

	if (debugging)
	{
		serverOptions =	() =>
		{
			let socket = net.connect({port: 5007});
			let result =
			{
				writer: socket,
				reader: socket
			};
			return Promise.resolve(result);
		}
	}
	else
	{
		serverOptions =
		{
			run:
			{
				command: "java",
				args: ["-jar", context.asAbsolutePath("ls.jar")]
			}
		};
	}

	var clientOptions =
	{
		documentSelector:
		[{
			scheme: "file",
			language: "m"
		},
		{
			scheme: "file",
			pattern: "**/Ⲙ.json"
		}]
	};

	client = new LanguageClient("m language server", serverOptions, clientOptions);

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


exports.deactivate = function deactivate()
{
	if (!client)
	{
		return undefined;
	}
	client.stop();
}
