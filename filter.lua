-- Run as 
-- pandoc test.md --lua-filter filter.lua && 
-- echo "})" >> Code/Theia/test.js

os.remove('Code/Theia/test.js')
file = io.open('Code/Theia/test.js','a')
io.output(file)
io.write("const { click, type, isVisible, openBrowser, closeBrowser, connectToServer, closeContext} = require('./mobot.js')\n")
io.write("\n")

lastParagraph = ""

function Header(h)
    if h.level == 1 then
        title = fullLine(h.content, 1)
        io.write("describe('"..title.."', function()\n");
        io.write("{\n");
        io.write("this.timeout(100000)\n")
        io.write("before(openBrowser)\n")
        io.write("beforeEach(connectToServer)\n")
        io.write("afterEach(closeContext)\n")
        io.write("after(closeBrowser)\n")
        io.write("\n")
    end
end

function Para(paragraph)
    lastParagraph = fullLine(paragraph.content, 1)
end

function BulletList(list)

    io.write("it('"..lastParagraph.."', async function()\n")
    io.write("{\n")

    for k,v in pairs(list.content) do
        for a,b in pairs(v) do
            process(b.content)
        end
    end

    io.write("})\n")
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

    if (order[1].text == "Click") then
        
        io.write("await click('"..text.."');\n")
    
    elseif (order[1].text == "Type") then

        io.write("await type('"..text.."');\n")

    elseif (order[1].text == "isVisible") then

        io.write("await isVisible('"..text.."');\n")

    end
end

function CodeBlock(block)

    if block.classes[3] == nil then return end

    local action = block.classes[1]
    local workspace = block.classes[2]
    local file = block.classes[3]
    local path = "GeneratedTests/" .. workspace .. "/" .. file

    if not Directory("GeneratedTests") then
        os.execute("mkdir GeneratedTests")
    end
    if not Directory("GeneratedTests/" .. workspace) then
        os.execute("mkdir " .. "GeneratedTests/" .. workspace)
    end

    if action == "set" then
        file = io.open(path, "w")
        io.output(file)
        io.write(block.text)
        io.close(file)
    elseif action == "append" then
        file = io.open(path, "a")
        io.output(file)
        io.write("\n"..block.text)
        io.close(file)
    elseif action == "check" then
        file = io.open(path, "w")
        io.output(file)
        io.write(block.text)
        io.close(file)
    end
end

function Exists(file)
    local ok, err, code = os.rename(file, file)
    if not ok then
       if code == 13 then
          return true
       end
    end
    return ok, err
 end

function Directory(path)
    return Exists(path.."/")
end