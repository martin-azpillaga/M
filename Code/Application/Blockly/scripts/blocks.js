Blockly.Blocks['system'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("system"), "name");
    this.appendStatementInput("statements")
        .setCheck(["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['selection'] = {
  init: function() {
    this.appendValueInput("condition")
        .setCheck("Boolean")
        .appendField("→");
    this.appendStatementInput("positiveStatements")
        .setCheck(["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setPreviousStatement(true, "selection");
    this.setNextStatement(true, ["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['iteration'] = {
  init: function() {
    this.appendValueInput("condition")
        .setCheck("Boolean")
        .appendField("↺");
    this.appendStatementInput("statements")
        .setCheck(["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setPreviousStatement(true, "iteration");
    this.setNextStatement(true, ["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['forall'] = {
  init: function() {
    this.appendValueInput("condition")
        .setCheck("Boolean")
        .appendField("∀")
        .appendField(new Blockly.FieldTextInput("entity"), "entity");
    this.appendStatementInput("statements")
        .setCheck(["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setPreviousStatement(true, "forall");
    this.setNextStatement(true, ["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['assignment'] = {
  init: function() {
    this.appendValueInput("condition")
        .setCheck("Boolean")
        .appendField("∀")
        .appendField(new Blockly.FieldTextInput("entity"), "entity");
    this.appendStatementInput("statements")
        .setCheck(["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setPreviousStatement(true, "forall");
    this.setNextStatement(true, ["selection", "iteration", "forall", "assignment", "subprocess"]);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};