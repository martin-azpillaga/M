
var workspace = Blockly.inject('blocklyDiv', {toolbox: document.getElementById('toolbox'), zoom: {controls: true, wheel: true}, renderer: "zelos", theme: "dark"});

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

Blockly.Blocks['system'] = {
    init: function() {
      this.appendDummyInput()
          .appendField(new Blockly.FieldTextInput("system"), "name");
      this.appendStatementInput("statements")
          .setCheck(["selection", "iteration", "forall", "subprocess"]);
      this.setColour(230);
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };
  
  Blockly.Blocks['selection'] = {
    init: function() {
      this.appendValueInput("condition")
          .setCheck("Number")
          .appendField("select");
      this.appendStatementInput("positiveStatements")
          .setCheck(["selection", "iteration", "forall", "subprocess"]);
      this.setPreviousStatement(true, "selection");
      this.setNextStatement(true, ["selection", "iteration", "forall", "subprocess"]);
      this.setColour(230);
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };
  
  Blockly.Blocks['iteration'] = {
    init: function() {
      this.appendValueInput("condition")
          .setCheck(["Bool", "sda"])
          .appendField("iterate");
      this.appendStatementInput("statements")
          .setCheck(["selection", "iteration", "forall", "subprocess"]);
      this.setPreviousStatement(true, "iteration");
      this.setNextStatement(true, ["selection", "iteration", "forall", "subprocess"]);
      this.setColour(230);
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };
  
  Blockly.Blocks['forall'] = {
    init: function() {
      this.appendDummyInput()
          .appendField("foreach")
          .appendField(new Blockly.FieldTextInput("entity"), "entity");
      this.appendStatementInput("statements")
          .setCheck(["selection", "iteration", "forall", "subprocess"]);
      this.setPreviousStatement(true, "forall");
      this.setNextStatement(true, ["selection", "iteration", "forall", "subprocess"]);
      this.setColour(230);
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };

  Blockly.Blocks['set'] = {
    init: function() {
      this.appendValueInput("expression")
          .appendField("set")
          .appendField(new Blockly.FieldTextInput("entity"), "entity")
          .appendField("to")
          .setCheck(["Bool", "sda"]);
      this.setColour(230);
      this.setPreviousStatement(true, "forall");
      this.setNextStatement(true, ["selection", "iteration", "forall", "subprocess"]);
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
      this.setColour(230);
      this.setOutput("Bool");
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };

  Blockly.Blocks['times'] = {
    init: function() {
        this.appendValueInput("left")
          .setCheck(["Bool", "sda"]);
          this.appendValueInput("right")
          .appendField("*")
          .setCheck(["Bool", "sda"]);
      this.setColour(230);
      this.setOutput("Bool");
      this.setInputsInline(true);
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };