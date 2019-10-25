Blockly.Blocks['entity'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage("https://www.gstatic.com/codesite/ph/images/star_on.gif", 15, 15, "entity"))
        .appendField(new Blockly.FieldTextInput("name"), "name");
    this.appendStatementInput("components")
        .setCheck(["tag", "valued"]);
    this.setInputsInline(true);
    this.setPreviousStatement(true, "entity");
    this.setNextStatement(true, "entity");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['tagcomponent'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("name"), "name");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "tag");
    this.setNextStatement(true, ["tag", "valued"]);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['valuedcomponent'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("name"), "name");
    this.appendValueInput("value")
        .setCheck("value");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "valued");
    this.setNextStatement(true, ["tag", "valued"]);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['system'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage("https://www.gstatic.com/codesite/ph/images/star_on.gif", 15, 15, "entity"))
        .appendField(new Blockly.FieldTextInput("name"), "name");
    this.appendStatementInput("commands")
        .setCheck("command");
    this.setInputsInline(true);
    this.setColour(105);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['loop'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage("https://www.gstatic.com/codesite/ph/images/star_on.gif", 15, 15, "entity"))
        .appendField(new Blockly.FieldTextInput("entity"), "entity");
    this.appendStatementInput("constraints")
        .setCheck("tag");
    this.appendStatementInput("commands")
        .setCheck("command");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(105);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['branch'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck(["access", "expression"])
        .appendField(new Blockly.FieldImage("https://www.gstatic.com/codesite/ph/images/star_on.gif", 15, 15, "entity"));
    this.appendStatementInput("commands")
        .setCheck("command");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(105);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['assignment'] = {
  init: function() {
    this.appendValueInput("access")
        .setCheck("access");
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["=","set"], ["+=","increase"], ["-=","decrease"], ["*=","multiply"], ["/=","divide"]]), "type");
    this.appendValueInput("expression")
        .setCheck(["access", "expression"]);
    this.setInputsInline(true);
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(105);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['access3'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("a"), "a")
        .appendField(".")
        .appendField(new Blockly.FieldTextInput("b"), "b")
        .appendField(".")
        .appendField(new Blockly.FieldTextInput("c"), "c");
    this.setInputsInline(true);
    this.setOutput(true, "access");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['access2'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("a"), "a")
        .appendField(".")
        .appendField(new Blockly.FieldTextInput("b"), "b");
    this.setInputsInline(true);
    this.setOutput(true, "access");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['binaryexpression'] = {
  init: function() {
    this.appendValueInput("left")
        .setCheck(["access", "expression"]);
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["+","plus"], ["-","minus"], ["*","times"], ["/","divide"], ["&&","and"], ["||","or"], ["<","less"], ["<=","lessOrEqual"], ["=","equal"], [">=","greaterOrEqual"], [">","greater"], ["!=","notEqual"]]), "operation");
    this.appendValueInput("right")
        .setCheck(["access", "expression"]);
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['unaryexpression'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["!","not"], ["~","bitwiseNot"], ["++","increment"], ["--","decrement"]]), "operation");
    this.appendValueInput("expression")
        .setCheck(["access", "expression"]);
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['brackets'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("(");
    this.appendValueInput("expression")
        .setCheck(["access", "expression"]);
    this.appendDummyInput()
        .appendField(")");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real1value'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "x");
    this.setInputsInline(true);
    this.setOutput(true, "value");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real4value'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "x")
        .appendField(new Blockly.FieldNumber(0), "y")
        .appendField(new Blockly.FieldNumber(0), "z")
        .appendField(new Blockly.FieldNumber(0), "w");
    this.setInputsInline(true);
    this.setOutput(true, "value");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real3value'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "x")
        .appendField(new Blockly.FieldNumber(0), "y")
        .appendField(new Blockly.FieldNumber(0), "z");
    this.setInputsInline(true);
    this.setOutput(true, "value");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real2value'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "x")
        .appendField(new Blockly.FieldNumber(0), "y");
    this.setInputsInline(true);
    this.setOutput(true, "value");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['wordvalue'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("asset name"), "name");
    this.setInputsInline(true);
    this.setOutput(true, "value");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['access1'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("a"), "a");
    this.setInputsInline(true);
    this.setOutput(true, "access");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['add'] = {
  init: function() {
    this.appendValueInput("component")
        .setCheck(null)
        .appendField("add");
    this.appendValueInput("entity")
        .setCheck(null)
        .appendField("to");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['remove'] = {
  init: function() {
    this.appendValueInput("component")
        .setCheck(null)
        .appendField("remove");
    this.appendValueInput("entity")
        .setCheck(null)
        .appendField("from");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['join3'] = {
  init: function() {
    this.appendValueInput("x")
        .setCheck(null)
        .appendField("join");
    this.appendValueInput("y")
        .setCheck(null)
        .appendField(",");
    this.appendValueInput("z")
        .setCheck(null)
        .appendField(",");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['join2'] = {
  init: function() {
    this.appendValueInput("x")
        .setCheck(null)
        .appendField("join");
    this.appendValueInput("y")
        .setCheck(null)
        .appendField(",");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['join4'] = {
  init: function() {
    this.appendValueInput("x")
        .setCheck(null)
        .appendField("join");
    this.appendValueInput("y")
        .setCheck(null)
        .appendField(",");
    this.appendValueInput("z")
        .setCheck(null)
        .appendField(",");
    this.appendValueInput("w")
        .setCheck(null)
        .appendField(",");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['random'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck(null)
        .appendField("random");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['create'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck(null)
        .appendField("create");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['destroy'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck(null)
        .appendField("destroy");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['mathfunction'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck(null)
        .setAlign(Blockly.ALIGN_CENTRE)
        .appendField(new Blockly.FieldDropdown([["sin","sin"], ["cos","cos"], ["ta","tan"]]), "name");
    this.setInputsInline(true);
    this.setOutput(true, "expression");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['converttocommand'] = {
  init: function() {
    this.appendValueInput("value")
        .setCheck(["access", "expression"])
        .appendField("call");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(105);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['has'] = {
  init: function() {
    this.appendValueInput("entity")
        .setCheck(null)
        .appendField("entity");
    this.appendValueInput("component")
        .setCheck(null)
        .appendField("has");
    this.setInputsInline(true);
    this.setOutput(true, "access");
    this.setColour(150);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};