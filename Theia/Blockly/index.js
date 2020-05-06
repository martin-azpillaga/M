
var workspace = Blockly.inject('blocklyDiv', {toolbox: document.getElementById('toolbox'), zoom: {controls: true, wheel: true}});

Blockly.Blocks['function'] = {
  init: function() {
    this.appendValueInput("parameters")
        .setCheck('parameter')
        .appendField(new Blockly.FieldTextInput("function"), "name");
    this.appendStatementInput("statements")
        .setCheck("statement");
    this.appendValueInput("result")
        .setCheck(["expression", "application", "atom"])
        .appendField("result");
    this.setColour(260);
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