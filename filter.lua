function BulletList(list)
    for k,v in pairs(list.content) do
        for a,b in pairs(v) do
            process(b.content)
        end
    end
end

function process(order)
    print(#order)
    if (order[1].text == "Click") then
        print("await click('"..order[3].tag.."');")

    elseif (order[1].text == "Type") then
        print("await type('"..order[3].text.."');")

    elseif (order[1].text == "isVisible") then
        print("await isVisible('"..order[3].text.."');")
    end

    print(order[1].text)
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