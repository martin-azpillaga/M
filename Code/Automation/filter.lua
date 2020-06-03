firstHeader = true
os.remove('Code/Theia/test.js')
file = io.open('Code/Theia/test.js','a')
io.output(file)
io.write("const { click, type, press, isVisible, noProblems, openBrowser, closeBrowser, connectToServer, closeContext} = require('./mobot.js')\n")
io.write("\n")

lastParagraph = ""
lastTitle = ""
insideDescribe = false

function Header(h)
    if h.level == 1 then
        lastTitle = fullLine(h.content, 1)
        insideDescribe = false
    end
end

function Para(paragraph)
    lastParagraph = fullLine(paragraph.content, 1)
end

function BulletList(list)

    first = list.content[1][1].content[1].text
    if (first == 'Click' or first == 'Press' or first == "Type" or first == "isVisible") then
        
        if not insideDescribe then
            insideDescribe = true
            if not firstHeader then
                firstHeader = false
                io.write('})\n')
            end
            io.write("describe('"..lastTitle.."', function()\n");
            io.write("{\n");
            io.write("this.timeout(100000)\n")
            io.write("before(openBrowser)\n")
            io.write("beforeEach(connectToServer)\n")
            io.write("afterEach(closeContext)\n")
            io.write("after(closeBrowser)\n")
            io.write("\n")
        end
        
        io.write("it('"..lastParagraph.."', async function()\n")
        io.write("{\n")
        
        io.write("await click('Help');\n")
        io.write("await click('Help');\n")

        for k,v in pairs(list.content) do
            for a,b in pairs(v) do
                process(b.content)
            end
        end

        io.write("await noProblems();\n")

        io.write("})\n")
    end
end

function fullLine(inline, start)
    result = ""
    
    for i=start,#inline do
        if inline[i].tag == "Str" then
            result = result .. inline[i].text
        elseif inline[i].tag == "Space" then
            result = result .. " "
        end
    end
    return result
end

function process(order)
    text = fullLine(order,3)

    command = order[1].text

    if (command == "Click") then
        
        io.write("await click('"..text.."');\n")
    
    elseif (command == "Type") then

        io.write("await type('"..text.."');\n")

    elseif (command == "isVisible") then

        io.write("await isVisible('"..text.."');\n")
    
    elseif (command == "Press") then

        io.write("await press('"..text.."');\n")
    
    end
end