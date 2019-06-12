function onFileLoad(elementId, event) 
{
    var xml = Blockly.Xml.textToDom(event.target.result);
    Blockly.Xml.domToWorkspace(xml, workspace);
}

function onChooseFile(event, onLoadFileHandler) {
    if (typeof window.FileReader !== 'function')
        throw ("The file API isn't supported on this browser.");
    let input = event.target;
    if (!input)
        throw ("The browser does not properly implement the event object");
    if (!input.files)
        throw ("This browser does not support the `files` property of the file input.");
    if (!input.files[0])
        return undefined;
    let file = input.files[0];
    let fr = new FileReader();
    fr.onload = onLoadFileHandler;
    fr.readAsText(file);
}

var workspace = Blockly.inject('blocklyDiv', {toolbox: document.getElementById('toolbox'), scrollbars: false, zoom: {controls: true, wheel: true}});
function myUpdateFunction(event) 
{
//alert("I am an alert box!");
}
function load()
{
alert("Alerta")
}
function save()
{
var xml = Blockly.Xml.workspaceToDom(workspace);
var xml_text = Blockly.Xml.domToText(xml);
var blob = new Blob([xml_text],{type: "text/xml"});
var url = URL.createObjectURL(blob),
            div = document.createElement("div"),
            anch = document.createElement("a");

        document.body.appendChild(div);
        div.appendChild(anch);

        anch.innerHTML = "&nbsp;";
        div.style.width = "0";
        div.style.height = "0";
        anch.href = url;
        anch.download = "workspace";
        
        var ev = new MouseEvent("click",{});
        anch.dispatchEvent(ev);
        document.body.removeChild(div);

}
workspace.addChangeListener(myUpdateFunction);