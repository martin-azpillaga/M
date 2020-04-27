
var workspace = Blockly.inject('blocklyDiv', {toolbox: document.getElementById('toolbox'), zoom: {controls: true, wheel: true}});
/*
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
    var xml_text = Blockly.Xml.domToPrettyText (xml);

    download(xml_text, "blocks", "text/xml");
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
*/
Blockly.Blocks['function'] = {
  init: function() {
    this.appendValueInput("parameters")
        .setCheck("parameter")
        .appendField(new Blockly.FieldTextInput("function"), "name");
    this.appendStatementInput("statements")
        .setCheck("statement");
    this.appendValueInput("result")
        .setCheck(["expression", "application", "atom"])
        .appendField("result");
    this.setColour(225);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['parameter'] = {
  init: function() {
    this.appendValueInput("next")
        .setCheck("parameter")
        .appendField(new Blockly.FieldTextInput("parameter"), "name");
    this.setOutput(true, "parameter");
    this.setColour(225);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['delegation'] = {
  init: function() {
    this.appendValueInput("application")
        .setCheck("application")
        .appendField("");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "statement");
    this.setNextStatement(true, "statement");
    this.setColour(225);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['binary'] = {
  init: function() {
    this.appendValueInput("left")
        .setCheck(["expression", "application", "atom"]);
    this.appendValueInput("right")
        .setCheck(["expression", "application", "atom"])
        .appendField(new Blockly.FieldTextInput("operator"), "operator");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(210);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['unary'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck(["expression", "application", "atom"])
        .appendField(new Blockly.FieldTextInput("operator"), "operator");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(210);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("name"), "name");
    this.setInputsInline(true);
    this.setOutput(true, "atom");
    this.setColour(210);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['cell'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("entity"), "entity")
        .appendField(".")
        .appendField(new Blockly.FieldTextInput("component"), "component");
    this.setInputsInline(true);
    this.setOutput(true, "atom");
    this.setColour(210);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['connector'] = {
  init: function() {
    this.appendValueInput("current")
        .setCheck(["expression", "application", "atom"]);
    this.appendValueInput("next")
        .setCheck(["expression", "application", "atom"]);
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(210);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['application'] = {
  init: function() {
    this.appendValueInput("argument")
        .setCheck(["expression", "application", "atom"])
        .appendField(new Blockly.FieldTextInput("function"), "name");
    this.setOutput(true, "application");
    this.setColour(210);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['block'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck(["expression", "application", "atom"])
        .appendField(new Blockly.FieldTextInput("block"), "name");
    this.appendStatementInput("statements")
        .setCheck("statement");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "statement");
    this.setNextStatement(true, "statement");
    this.setColour(225);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['assignment'] = {
  init: function() {
    this.appendValueInput("atom")
        .setCheck("atom")
        .appendField("set");
    this.appendValueInput("expression")
        .setCheck(["expression", "application", "atom"])
        .appendField("to");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "statement");
    this.setNextStatement(true, "statement");
    this.setColour(225);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};