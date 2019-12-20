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

--- Check if a file or directory exists in this path
function Exists(file)
    local ok, err, code = os.rename(file, file)
    if not ok then
       if code == 13 then
          -- Permission denied, but it exists
          return true
       end
    end
    return ok, err
 end
 
 --- Check if a directory exists in this path
 function Directory(path)
    -- "/" works on both Unix and Windows
    return Exists(path.."/")
 end