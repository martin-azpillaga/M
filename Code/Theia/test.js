const puppeteer = require('puppeteer');
const chai = require('chai');
chai.should();

let browser;
let context;

describe('The application', function()
{
    this.timeout(20000);

    before(async function ()
    {
        browser = await puppeteer.launch({headless: false});
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
        if (!!context)
        {
            await context.close();
        }
    })
    it('Opens a page', async function ()
    {
        try
        {
            const page = await context.newPage();
            await page.goto('localhost:3000');
            var explorer = await page.waitForSelector('#shell-tab-explorer-view-containe')
            await explorer.click()
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