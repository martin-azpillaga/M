const { app, BrowserWindow } = require('electron')

function createWindow () 
{
  let win = new BrowserWindow()

  win.loadFile('index.html')
}

app.on('ready', createWindow)