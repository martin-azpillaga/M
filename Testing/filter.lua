function CodeBlock(block)
    local action = block.classes[1]
    local workspace = block.classes[2]
    local file = block.classes[3]
    if action == "set" then
        local path = workspace .. "/" .. file
        os.execute("mkdir " .. workspace)
        file = io.open(path, "w")
        io.output(file)
        io.write(block.text)
        io.close(file)
    elseif action == "check" then
        local path = workspace .. "/check" .. file
        os.execute("mkdir " .. workspace)
        file = io.open(path, "w")
        io.output(file)
        io.write(block.text)
        io.close(file)
    end
    
end