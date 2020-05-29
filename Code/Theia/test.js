const puppeteer = require('puppeteer');
const chai = require('chai');
chai.should();

let browser;
let context;
let page;

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
            // Start using waitForXPath instead of $x
            // And create functions for common operations like click
            await page.goto('localhost:3000');
            var explorer = await page.waitForSelector('#shell-tab-explorer-view-container')
            console.log(1);
            await explorer.click();
            var files = await page.$x('//div[text()="File"]')
            var file = files[0];
            console.log(1);
            await file.click();
            var newFiles = await page.$x('//div[text()="New File"]')
            var newFile = newFiles[0];
            console.log(1);
            await newFile.click();
            await page.keyboard.type("Hello.m");
            var oks = await page.$x('//button[text()="OK"]');
            var ok = oks[0];
            console.log("ok")
            await ok.click();
            await page.screenshot({path: 'example.png'});
            
            (2+2).should.equal(4)
        }
        catch (err)
        {
            console.error("\n\n"+err+"\n\n");
            process.exit(111);
        }
    })
})