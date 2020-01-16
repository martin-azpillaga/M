function Link (element)
    element.target = element.target:gsub(".md","-zh.html")
    return element
end
