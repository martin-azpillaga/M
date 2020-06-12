const puppeteer = require('puppeteer');
const chai = require('chai');
const rimraf = require('rimraf');

chai.should();

let browser;
let context;
let page;

let workspace = {folders: {}, files: []}
let problems = {}

async function type(text)
{
    text = text.split("    ").join("");
    var lines = text.split("\n");
    for (line of lines)
    {
        await page.keyboard.type(line);
        if (line !== lines[lines.length-1])
        {
            await page.keyboard.press("Escape");
            await page.keyboard.type("\n");
        }
    }
}
const openBrowser = async function ()
{
    browser = await puppeteer.launch({headless: false, slowMo: 50, defaultViewport: null, args: ['--start-maximized', '--no-sandbox']});
}
const closeBrowser = async function ()
{
    if (!!browser)
    {
        browser.close();
    }
}
const connectToServer = async function()
{
    rimraf.sync('workspace/*');
    context = await browser.createIncognitoBrowserContext();
    page = await context.newPage();
    await page.goto('localhost:3000')
}
const closeContext = async function()
{
    if (!!context)
    {
        await context.close();
    }
}

async function click(path)
{
    const fullPath = '//*[text()="'+path+'"]'
    await page.waitForXPath(fullPath);
    var elements = await page.$x(fullPath);
    if (elements.length == 1)
    {
        const element = elements[0];
        await element.click();
    }
    else
    {
        throw new Error(elements.length + " elements found with path "+path);
    }
}

async function isVisible(path)
{
    const fullPath = '//*[text()="'+path+'"]'
    var element = await page.waitForXPath(fullPath, {timeout: 5000});
    return !!element
}

async function press(hotkey)
{
    var keys = hotkey.split("+");
    for (key of keys)
    {
        await page.keyboard.down(key);
    }
    for (key of keys)
    {
        await page.keyboard.up(key);
    }
}

async function command(text)
{
    await press("Control+Shift+P");
    await type(text);
    await click(text);
}

async function noProblems()
{
    await press("Control+Shift+P");
    await type("Problems");
    await press("Enter");
    await isVisible("No problems have been detected in the workspace so far.")
}


async function createFolder(name)
{
    await command("File: New Folder")
    await isVisible("OK")
    await type(name)
    await press("Enter")
}

async function openFolder(name)
{
    await command("File: Open Workspace...")
    await click(name)
    await click("Open")
}


async function createFile(path)
{
    await command("File: New File")
    await isVisible("New File")
    await page.keyboard.type(path)
    await press("Enter")
    await newFile(path)
}

async function openFile(path)
{

}

async function append(path, code)
{
    await type(code)
}

async function newFile(path)
{
    const names = path.split('/')
    let directory = workspace
    for (name of names)
    {
        if (name === names[names.length-1])
        {
            if (!(name in directory.files))
            {
                directory.files.push(name)
            }
        }
        else
        {
            if (name in directory.folders)
            {
                directory = directory.folders[name]
            }
            else
            {
                directory.folders[name] = {folders: {}, files: []}
                directory = directory.folders[name]
            }
        }
    }
}

async function wait(time)
{
    await page.waitFor(time)
}

async function checkWorkspace()
{
    try
    {
        for (folder of Object.keys(workspace.folders))
        {
            await isVisible(folder)
        }
        for (file of workspace.files)
        {
            await isVisible(file)
        }
    }
    catch (err)
    {
        await command('Toggle Explorer View')
        for (folder of Object.keys(workspace.folders))
        {
            await isVisible(folder)
        }
        for (file of workspace.files)
        {
            await isVisible(file)
        }
    }
}

async function checkProblems()
{
    
}

async function expectWarning(message)
{

}

async function expectError(message)
{

}

module.exports = {expectWarning, expectError, problems, workspace, checkWorkspace, checkProblems, click, isVisible, type, press, context, openBrowser, closeBrowser, connectToServer, closeContext, command, noProblems, createFolder, openFolder, createFile, openFile, append, newFile}