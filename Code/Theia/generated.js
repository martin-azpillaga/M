const { click, type, openBrowser, closeBrowser, connectToServer, closeContext} = require('./mobot.js')

describe('test', function()
{
    this.timeout(100000)
    before(openBrowser)
    beforeEach(connectToServer)
    afterEach(closeContext)
    after(closeBrowser)

    it('test', async function()
    {
        await click("File");
        await click("New File");
        await type("Hello.m");
        await click("OK");
    })
})