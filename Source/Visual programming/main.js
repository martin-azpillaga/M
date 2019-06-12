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

    var blob = new Blob([xml_text],{type: "text/xml"});
    var url = URL.createObjectURL(blob);
    var div = document.createElement("div")
    var anch = document.createElement("a");

    document.body.appendChild(div);
    div.appendChild(anch);

    anch.innerHTML = "&nbsp;";
    anch.href = url;
    anch.download = "blocks";
        
    var ev = new MouseEvent("click",{});
    anch.dispatchEvent(ev);
    document.body.removeChild(div);
});

function blocksToText(event) 
{
    
}