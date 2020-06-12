const readline = require('readline');
const reader = readline.createInterface
({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

reader.on('line', function(line)
{
    const blocks = JSON.parse(line).blocks
    const out = console.log

    out('const {click, checkWorkspace, checkProblems, createFolder, openFolder, createFile, openFile, type, newFiles, openBrowser, connectToServer, closeContext, closeBrowser} = require("./mobot.js")')
    out('describe(\'tests\', function()')
    out('{')
    out('this.timeout(100000)')
    out('before(openBrowser)')
    out('beforeEach(connectToServer)')
    out('afterEach(closeContext)')
    out('after(closeBrowser)')

    for (block of blocks)
    {
        if (block.t === 'Header' && block.c[0] == 1)
        {
            const title = text(block.c[1])
            out(`it('${title}', async function()`)
            out('{')
            out('await click("Help")')
            out('await click("Help")')
        }
        else if (block.t == 'BulletList')
        {
            for (entry of block.c)
            {
                const command = inline(entry[0].c)
                if (command.startsWith("Create and open folder"))
                {
                    const folder = command.replace("Create and open folder ", "")
                    out(`await createFolder('${folder}')`)
                    out(`await openFolder('${folder}')`)
                }
                else if (command.startsWith("Create and open file"))
                {
                    const file = command.replace("Create and open file ", "")
                    out(`await createFile('${file}')`)
                    out(`await openFile('${file}')`)
                }
                else if (command.startsWith("Expect new files"))
                {
                    const arguments = command.replace("Expect new files in ", "")
                    const folder = arguments.substring(0, arguments.indexOf(":"))
                    const filesWithComma = arguments.substring(arguments.indexOf(":")+2)
                    const files = filesWithComma.split(", ")
                    out(`await newFiles('${folder}', ${files.map(x => `'${x}'`).join(", ")})`)
                }
                else if (command.startsWith("Append to"))
                {
                    const file = command.replace("Append to ", "");
                    out(`await type('${file}',`)
                }
            }
        }
        else if (block.t == 'CodeBlock')
        {
            const code = block.c[1]
            out(`\`${code}\`)`)
        }
    }
    out('await checkWorkspace()')
    out('await checkProblems()')
    out('})')
    out('})')
})

function text(inline)
{
    let result = ""
    for (entry of inline)
    {
        if(typeof(entry) == 'string')
        {
            result += entry
        }
    }
    return result
}

function inline(inline)
{
    let result = ""
    for (entry of inline)
    {
        if (entry.t == "Str")
        {
            result += entry.c
        }
        else if (entry.t == "Space")
        {
            result += " "
        }
    }
    return result
}
// execute by piping pandoc's json output to node
// pandoc file -t json | node reader.js
