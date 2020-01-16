function Link (element)
    element.target = element.target:gsub(".md",".html")
    return element
end
