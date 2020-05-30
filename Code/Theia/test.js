const puppeteer = require('puppeteer');
const chai = require('chai');
chai.should();

let browser;
let context;
let page;

async function clickSelector(selector)
{
    var selected = await page.waitForSelector(selector);
    await selected.click();
}

async function clickPath(path)
{
    await page.waitForXPath(path);
    var elements = await page.$x(path);
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
describe('The application', function()
{
    this.timeout(100000);

    before(async function ()
    {
        var slow = process.argv[3] == "slow";
        if (slow)
        {
            browser = await puppeteer.launch({headless: false, slowMo: 100, defaultViewport: null, args: ['--start-maximized']});
        }
        else
        {
            browser = await puppeteer.launch({headless: false});
        }  
    })
    after(async function ()
    {
        if (!!browser)
        {
            browser.close();
        }
    })
    beforeEach(async function()
    {
        context = await browser.createIncognitoBrowserContext();
        page = await context.newPage();
    })
    afterEach(async function()
    {
        if (!!context)
        {
            await context.close();
        }
    })
    it('Opens a page', async function ()
    {
        try
        {
            
            // Connect to localhost:3000/#/workspace-path?
            // Use puppeteer-recorder https://www.npmjs.com/package/puppeteer-recorder
            // to record animations of tests
            await page.goto('localhost:3000');
            await clickSelector('#shell-tab-explorer-view-container')
            await clickPath('//div[text()="File"]')
            await clickPath('//div[text()="New File"]')
            await type("Hello.m");
            await clickPath('//button[text()="OK"]');
            await page.waitForSelector(".view-line");
            await type("control()\n{\nforeach a\n{\na.velocity = a.base*a.inputValue\n}\n}\n");
            await page.waitFor(5000);
        }
        catch (err)
        {
            console.error("\n\n"+err+"\n\n");
            process.exit(111);
        }
    })
})