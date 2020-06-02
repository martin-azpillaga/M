const { click, type, isVisible, openBrowser, closeBrowser, connectToServer, closeContext} = require('./mobot.js')

describe('test', function()
{
    // run yarn startBrowser workspace & in mobot.before()
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
        await isVisible("Hello.m");
        await type("Hello World!");
        await click("View");
    })
})