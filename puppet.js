const puppeteer = require('puppeteer');

let browser;

(async () =>
{
        browser = await puppeteer.launch({headless: false, slowMo: 500});
        const pages = await browser.pages();
        const page = pages[0];
        await page.goto('localhost:3000');
        await page.click('#shell-tab-explorer-view-container')
        await page.screenshot({path: 'example.png'});
        await browser.close();
    
})().catch(err => browser.close());