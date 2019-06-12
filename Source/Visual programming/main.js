var blocksPanel = document.getElementById("blocklyDiv");
var textPanel = document.getElementById("textPanel");
var workspace = Blockly.inject('blocklyDiv', {toolbox: document.getElementById('toolbox'), zoom: {controls: true, wheel: true}});
workspace.addChangeListener(blocksToText);

document.getElementById("blocks-button").addEventListener("click", function()
{
    if (blocksPanel.style.display === "inline-block" || blocksPanel.style.display === "")
    {
        blocksPanel.style.display = "none"
        if (textPanel.style.display === "inline-block" || textPanel.style.display==="")
        {
            textPanel.style.width = "100%";
        }
    }
    else if (textPanel.style.display === "none")
    {
        blocksPanel.style.display = "inline-block"
        blocksPanel.style.width = "100%";
        Blockly.svgResize(workspace);
    }
    else
    {
        blocksPanel.style.display = "inline-block"
        blocksPanel.style.width = "50%";
        textPanel.style.width = "49%";
        Blockly.svgResize(workspace);
    }
});
document.getElementById("text-button").addEventListener("click", function()
{
    if (textPanel.style.display === "inline-block" || textPanel.style.display === "")
    {
        textPanel.style.display = "none"
        if (blocksPanel.style.display === "inline-block" || blocksPanel.style.display==="")
        {
            blocksPanel.style.width = "100%";
            Blockly.svgResize(workspace);
        }
    }
    else if (blocksPanel.style.display === "none")
    {
        textPanel.style.display = "inline-block"
        textPanel.style.width = "100%";
    }
    else
    {
        textPanel.style.display = "inline-block"
        textPanel.style.width = "49%";
        blocksPanel.style.width = "50%";
        Blockly.svgResize(workspace);
    }
});

document.getElementById("load-button").addEventListener("click", function()
{
    document.getElementById("uploaded-input").click();
});

document.getElementById("uploaded-input").addEventListener("change", function()
{
    var file = document.getElementById("uploaded-input").files[0];
    var fr = new FileReader();
    fr.onload = function(e)
    {
        var xml = Blockly.Xml.textToDom(e.target.result);
        Blockly.Xml.domToWorkspace(xml, workspace);
    };
    fr.readAsText(file);
});

document.getElementById("save-button").addEventListener("click", function()
{ 
    var xml = Blockly.Xml.workspaceToDom(workspace);
    var xml_text = Blockly.Xml.domToText(xml);

    download(xml_text, "blocks", "text/xml");
});

document.getElementById("download-button").addEventListener("click", function()
{ 
    textPanel.value;

    download(text, "text.m", "text/text");
});

function download(text, name, type)
{
    var blob = new Blob([text],{type: type});
    var url = URL.createObjectURL(blob);
    var div = document.createElement("div")
    var anch = document.createElement("a");

    document.body.appendChild(div);
    div.appendChild(anch);

    anch.innerHTML = "&nbsp;";
    anch.href = url;
    anch.download = name;
        
    var ev = new MouseEvent("click",{});
    anch.dispatchEvent(ev);
    document.body.removeChild(div);
}

function blocksToText(event) 
{
    var result = "";
    var blocks = workspace.getBlocksByType("entity", true);

    for (var i = 0; i < blocks.length; i++)
    {
        var block = blocks[i];

        if (block.getParent() == null)
        {
            result += EntityToCode(block, false) + "\n\n";
        }

    }

    textPanel.value = result;
}

function EntityToCode(block, isSubEntity)
{
    var result = "";
    var name = block.getFieldValue('NAME');
    if (isSubEntity)
    {
        result += " " + name
    }
    else
    {
        result += "entity " + name
    }
    var base = block.getInputTargetBlock('base');
    if (base != null)
    {
        var baseName = base.getFieldValue('NAME');
        result += " based on " + baseName;

        var removedComponent = base.getInputTargetBlock('removed');
        if (removedComponent != null)
        {
            result += " without";
        }
        while (removedComponent != null)
        {
            var name = removedComponent.getFieldValue('NAME');
            result += " " + name;
            removedComponent = removedComponent.getNextBlock();
        }
    }

    var component = block.getInputTargetBlock('components');
    
    if (component != null)
    {
        result += " has";
    }
    while (component != null)
    {
        var componentName = component.getFieldValue('NAME');

        if (component.type == "tag")
        {
            result += " " + componentName;
        }
        else if (component.type == "real1")
        {
            var x = component.getFieldValue('X');
            result += " " + componentName + " " + x;
        }
        else if (component.type == "real2")
        {
            var x = component.getFieldValue('X');
            var y = component.getFieldValue('Y');
            result += " " + componentName + " " + x + " " + y;
        }
        component = component.getNextBlock();
    }

    var subEntity = block.getInputTargetBlock('subEntities');
    
    if (subEntity != null)
    {
        result += " contains";
    }
    while (subEntity != null)
    {
        result += EntityToCode(subEntity, true);
        subEntity = subEntity.getNextBlock();
    }
    result += '.';

    return result;
}