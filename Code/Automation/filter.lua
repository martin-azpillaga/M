firstHeader = true
os.remove('Code/Theia/test.js')
file = io.open('Code/Theia/test.js','a')
io.output(file)
io.write("const { click, type, press, isVisible, command, noProblems, openBrowser, closeBrowser, connectToServer, closeContext} = require('./mobot.js')\n")
io.write("\n")
inside = false
firstIt = true
lastParagraph = ""
lastTitle = ""
insideDescribe = false

function Header(h)
    if h.level == 1 then
        lastTitle = fullLine(h.content, 1)
        insideDescribe = false
    end
end

function RawInline(raw)
    if raw.format == 'html' and raw.text == '<instructions>' then
        print("inside")
        inside = true
    elseif raw.text == '</instructions>' then
        print("outside")
        inside = false
    end
end

function CodeBlock(block)
    print("code")
    io.write("await type(`\n"..block.text.."`);\n")
end

function Para(paragraph)
    lastParagraph = fullLine(paragraph.content, 1)
end

function BulletList(list)
    print("bullets")
    first = list.content[1][1].content[1].text
    if (first == 'Click' or first == 'Press' or first == "Type" or first == "isVisible" or first == "Command") then
        
        if not insideDescribe then
            insideDescribe = true
            if not firstHeader then
                firstHeader = false
                io.write("await noProblems();\n")
                io.write('})\n')
                io.write('})\n')
            end
            firstIt = true
            io.write("describe('"..lastTitle.."', function()\n");
            io.write("{\n");
            io.write("this.timeout(100000)\n")
            io.write("before(openBrowser)\n")
            io.write("beforeEach(connectToServer)\n")
            io.write("afterEach(closeContext)\n")
            io.write("after(closeBrowser)\n")
            io.write("\n")
        end

        if not firstIt then
            io.write("await noProblems();\n")
            io.write("})\n")
        end

        firstIt = false

        io.write("it('"..lastParagraph.."', async function()\n")
        io.write("{\n")
        
        io.write("await click('Help');\n")
        io.write("await click('Help');\n")

        for k,v in pairs(list.content) do
            for a,b in pairs(v) do
                process(b.content)
            end
        end
        
    end
end

function fullLine(inline, start)
    result = ""
    
    for i=start,#inline do
        if inline[i].tag == "Str" then
            result = result .. inline[i].text
        elseif inline[i].tag == "Space" then
            result = result .. " "
        else
            --print(inline[i].tag)
        end
    end
    return result
end

function process(order)
    if order == nil then
        return
    end

    text = fullLine(order,3)

    command = order[1].text

    print(command)

    if (command == "Click") then
        
        io.write("await click('"..text.."');\n")
    
    elseif (command == "Type") and text ~= "" then

        io.write("await type('"..text.."');\n")

    elseif (command == "isVisible") then

        io.write("await isVisible('"..text.."');\n")
    
    elseif (command == "Press") then

        io.write("await press('"..text.."');\n")
    
    elseif (command == "Command") then

        io.write("await command('"..text.."');\n")

    end
end