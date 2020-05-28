const puppeteer = require('puppeteer');
const chai = require('chai');
chai.should();

let browser;

describe('The application', function()
{
    this.timeout(10000);

    before(async function ()
    {
        browser = await puppeteer.launch({headless: false, slowMo: 500});
    })
    it('Opens a page', async function ()
    {
        try
        {
            const pages = await browser.pages();
            const page = pages[0];
            await page.goto('localhost:3000');
            await page.click('#shell-tab-explorer-view-container')
            await page.screenshot({path: 'example.png'});
            await browser.close();
            (2+2).should.equal(4)
        }
        catch (err)
        {
            console.log("\n\n"+err+"\n\n");
            browser.close();
            process.exit(111);
        }
    })
})