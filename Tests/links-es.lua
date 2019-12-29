function Link (element)
    element.target = element.target:gsub(".md","-es.html")
    return element
end
