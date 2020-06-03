const puppeteer = require('puppeteer');
const chai = require('chai');
const rimraf = require('rimraf');

chai.should();

let browser;
let context;
let page;

async function type(text)
{
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
    browser = await puppeteer.launch({headless: false, slowMo: 100, defaultViewport: null, args: ['--start-maximized']});
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

module.exports = {click, isVisible, type, browser, page, context, openBrowser, closeBrowser, connectToServer, closeContext}