function CodeBlock(block)

    if block.classes[3] == nil then return end

    local action = block.classes[1]
    local workspace = block.classes[2]
    local file = block.classes[3]
    local path = "Tests/" .. workspace .. "/" .. file

    if not Directory("Tests") then
        os.execute("mkdir Tests")
    end
    if not Directory("Tests/" .. workspace) then
        os.execute("mkdir " .. "Tests/" .. workspace)
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