const uuid = require('uuid');
const electron = require('electron');
const { join, resolve } = require('path');
const { fork } = require('child_process');
const { app, shell, BrowserWindow, ipcMain, Menu, globalShortcut } = electron;
const { ElectronSecurityToken } = require('@theia/core/lib/electron-common/electron-token');

const electronSecurityToken = { value: uuid.v4() };
let mainPort

app.on('ready', () =>
{
    const mainWindow = new BrowserWindow();

    var menu = Menu.buildFromTemplate(
    [
        {label: 'Blocks', click: ()=>{mainWindow.loadURL('file://' + join(__dirname, 'Blockly/index.html'))}},
        {label: 'Editor', click: ()=>{mainWindow.loadURL('file://' + join(__dirname, 'lib/index.html') + '?port=' + mainPort)}},
        {label: 'Documentation', click: ()=>{mainWindow.loadURL('file://'+join(__dirname,'README.html'))}}
    ]);

    const devMode = process.defaultApp || /node_modules[/]electron[/]/.test(process.execPath);

    const loadMainWindow = (port) => {
        if (!mainWindow.isDestroyed()) {
            mainWindow.webContents.session.cookies.set({
                url: `http://localhost:${port}/`,
                name: ElectronSecurityToken,
                value: JSON.stringify(electronSecurityToken),
            }, error => {
                if (error) {
                    console.error(error);
                } else {
                    mainPort = port
                    mainWindow.loadURL('file://' + join(__dirname, 'lib/index.html') + '?port=' + port);
                }
            });
        }
    };

    process.env.THEIA_APP_PROJECT_PATH = resolve(__dirname);

    // Set the electron version for both the dev and the production mode. (https://github.com/eclipse-theia/theia/issues/3254)
    // Otherwise, the forked backend processes will not know that they're serving the electron frontend.
    const { versions } = process;
    if (versions && typeof versions.electron !== 'undefined') {
        process.env.THEIA_ELECTRON_VERSION = versions.electron;
    }
    const mainPath = join(__dirname, 'src-gen', 'backend', 'main');
    // We need to distinguish between bundled application and development mode when starting the clusters.
    // See: https://github.com/electron/electron/issues/6337#issuecomment-230183287
    if (devMode) {
        process.env[ElectronSecurityToken] = JSON.stringify(electronSecurityToken);
        require(mainPath).then(address => {
            console.log("in");
            loadMainWindow(address.port);
        }).catch((error) => {
            console.error(error);
            app.exit(1);
        });
    } else {
        const childProcess = fork(mainPath, [], { env: Object.assign({
            [ElectronSecurityToken]: JSON.stringify(electronSecurityToken),
        }, process.env) });
        childProcess.on('message', (address) => {
            loadMainWindow(address.port);
        });
        childProcess.on('error', (error) => {
            console.error(error);
            app.exit(1);
        });
        app.on('quit', () => {
            // If we forked the process for the clusters, we need to manually terminate it.
            // See: https://github.com/eclipse-theia/theia/issues/835
            process.kill(childProcess.pid);
        });
    }
    globalShortcut.register('CommandOrControl+M', () => {
        Menu.setApplicationMenu(menu)
      })
});

app.on('window-all-closed', () => {
    console.log("quit")
    app.exit(0);
});
ipcMain.on('create-new-window', (event, url) => {
    createNewWindow(url);
});
ipcMain.on('open-external', (event, url) => {
    shell.openExternal(url);
});