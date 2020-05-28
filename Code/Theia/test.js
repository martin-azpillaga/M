const puppeteer = require('puppeteer');
const chai = require('chai');
chai.should();

let browser;
let context;

describe('The application', function()
{
    this.timeout(10000);

    before(async function ()
    {
        browser = await puppeteer.launch({headless: false, slowMo: 500});
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
    })
    afterEach(async function()
    {
        await context.close();
    })
    it('Opens a page', async function ()
    {
        try
        {
            const page = await context.newPage();
            await page.goto('localhost:3000');
            await page.click('#shell-tab-explorer-view-container')
            await page.screenshot({path: 'example.png'});
            
            (2+2).should.equal(4)
        }
        catch (err)
        {
            console.log("\n\n"+err+"\n\n");
            context.close();
            process.exit(111);
        }
    })
})