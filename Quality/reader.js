const readline = require('readline');
const reader = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

reader.on('line', function(line) {
    const blocks = JSON.parse(line).blocks
    const out = console.log

    out('const mobot = require("./mobot.js")')
    out('describe(\'tests\', function()')
    out('{')
    out('this.timeout(1000000)')
    out('before(mobot.openBrowser)')
    out('beforeEach(mobot.connectToServer)')
    out('afterEach(mobot.closeContext)')
    out('after(mobot.closeBrowser)')

    for (block of blocks) {
        if (block.t === 'Header' && block.c[0] == 1) {
            const title = text(block.c[1])
            out(`it('${title}', async function()`)
            out('{')
        }
        else if (block.t == 'BulletList')
        {
            for (entry of block.c)
            {
                const command = inline(entry[0].c)
                if (command.startsWith("Create and open folder")) {
                    const folder = command.replace("Create and open folder ", "")
                    out(`await mobot.createFolder('${folder}')`)
                    out(`await mobot.openFolder('${folder}')`)
                } else if (command.startsWith("Create and open file")) {
                    const file = command.replace("Create and open file ", "")
                    out(`await mobot.createFile('${file}')`)
                    out(`await mobot.openFile('${file}')`)
                } else if (command.startsWith("Expect new files")) {
                    const arguments = command.replace("Expect new files in ", "")
                    const folder = arguments.substring(0, arguments.indexOf(":"))
                    const filesWithComma = arguments.substring(arguments.indexOf(":") + 2)
                    const files = filesWithComma.split(", ")
                    for (file of files) {
                        out(`await mobot.newFile('${folder}/${file}')`)
                    }
                } else if (command.startsWith("Append to")) {
                    const file = command.replace("Append to ", "");
                    out(`await mobot.append('${file}',`)
                }
            }
        } else if (block.t == 'CodeBlock') {
            const code = block.c[1]
            out(`\`${code}\`)`)
        }
    }
    out('await mobot.checkWorkspace()')
    out('await mobot.checkProblems()')
    out('})')
    out('})')
})

function text(inline) {
    let result = ""
    for (entry of inline) {
        if (typeof(entry) == 'string') {
            result += entry
        }
    }
    return result
}

function inline(inline) {
    let result = ""
    for (entry of inline) {
        if (entry.t == "Str") {
            result += entry.c
        } else if (entry.t == "Space") {
            result += " "
        }
    }
    return result
}