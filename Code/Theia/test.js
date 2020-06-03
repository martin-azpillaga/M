const { click, type, isVisible, openBrowser, closeBrowser, connectToServer, closeContext} = require('./mobot.js')

describe('Test', function()
{
this.timeout(100000)
before(openBrowser)
beforeEach(connectToServer)
afterEach(closeContext)
after(closeBrowser)

it('Some explanation', async function()
{
await click('File');
await click('New File');
await type('Hello.m');
await click('OK');
await isVisible('Hello.m');
await type('Hello World!');
})
it('Later on', async function()
{
await click('c');
await click('d');
})
})
