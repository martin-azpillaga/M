import {ContainerModule, injectable} from 'inversify'
import {BaseLanguageServerContribution, LanguageServerContribution, IConnection} from '@theia/languages/lib/node'
import { createSocketConnection } from 'vscode-ws-jsonrpc/lib/server'
import * as path from 'path';
import * as net from 'net'

console.log("I am backend")

export default new ContainerModule(bind =>
{
    bind(LanguageServerContribution).to(MContribution)
});

@injectable()
class MContribution extends BaseLanguageServerContribution
{
    id = 'mserver';
    name = 'mserver';
    
    start(clientConnection: IConnection): void
    {
        console.log("Start called");
        let socketPort = getPort();
        if (socketPort) {
            const socket = new net.Socket()
            const serverConnection = createSocketConnection(socket, socket, () => {
                socket.destroy()
            });
            this.forward(clientConnection, serverConnection)
            socket.connect(socketPort)
        } else {
            console.log("java jarring");
            const jar = path.resolve(__dirname, '../../ls.jar');
    
            const command = 'java';
            const args: string[] = ['-jar',jar];
            const serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    }
}

function getPort(): number | undefined
{
    let arg = process.argv.filter(arg => arg.startsWith('--LSP_PORT='))[0]
    if (!arg) {
        return undefined
    } else {
        return Number.parseInt(arg.substring('--LSP_PORT='.length))
    }
}