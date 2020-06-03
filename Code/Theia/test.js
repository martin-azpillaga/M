const { click, type, isVisible, openBrowser, closeBrowser, connectToServer, closeContext} = require('./mobot.js')

describe('Authors', function()
{
this.timeout(100000)
before(openBrowser)
beforeEach(connectToServer)
afterEach(closeContext)
after(closeBrowser)

it('The  framework powers the online and standalone textual editors.TypeFox licenses Theia under the .', async function()
{
})
})
describe('What is M', function()
{
this.timeout(100000)
before(openBrowser)
beforeEach(connectToServer)
afterEach(closeContext)
after(closeBrowser)

it('M does not compete with the tools you know and love. It provides you with atool that will improve your development journey.', async function()
{
})
it('Missing a feature? Open an issue.', async function()
{
})
it('You can donate using GitHub’s built-in sponsorship feature. Click the sponsorheart button and set the amount you feel comfortable with.', async function()
{
})
})
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
await click('File');
await click('New File');
await type('Hello.m');
await click('OK');
})
})
