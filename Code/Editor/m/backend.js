"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const inversify_1 = require("inversify");
const node_1 = require("@theia/languages/lib/node");
const server_1 = require("vscode-ws-jsonrpc/lib/server");
const path = require("path");
const net = require("net");
exports.default = new inversify_1.ContainerModule(bind => {
    bind(node_1.LanguageServerContribution).to(MContribution);
});
let MContribution = class MContribution extends node_1.BaseLanguageServerContribution {
    constructor() {
        super(...arguments);
        this.id = 'mserver';
        this.name = 'mserver';
    }
    start(clientConnection) {
        console.log("Start called");
        let socketPort = getPort();
        if (socketPort) {
            const socket = new net.Socket();
            const serverConnection = server_1.createSocketConnection(socket, socket, () => {
                socket.destroy();
            });
            this.forward(clientConnection, serverConnection);
            socket.connect(socketPort);
        }
        else {
            console.log("java jarring");
            const jar = path.resolve(__dirname, '../../../../ls.jar');
            console.log("\n\n");
            console.log(jar);
            console.log("\n\n");
            const command = 'java';
            const args = ['-jar', jar];
            const serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    }
};
MContribution = __decorate([
    inversify_1.injectable()
], MContribution);
function getPort() {
    let arg = process.argv.filter(arg => arg.startsWith('--LSP_PORT='))[0];
    if (!arg) {
        return undefined;
    }
    else {
        return Number.parseInt(arg.substring('--LSP_PORT='.length));
    }
}
//# sourceMappingURL=backend.js.map