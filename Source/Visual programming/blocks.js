Blockly.Blocks['entity'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("entity")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.appendValueInput("base")
        .setCheck("base");
    this.appendStatementInput("components")
        .setCheck("component")
        .appendField("has");
    this.appendStatementInput("subEntities")
        .setCheck("entity")
        .appendField("contains");
    this.setInputsInline(false);
    this.setPreviousStatement(true, "entity");
    this.setNextStatement(true, "entity");
    this.setColour(210);
 this.setTooltip("entity");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real2'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField(": (")
        .appendField(new Blockly.FieldNumber(0), "X")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Y")
        .appendField(")");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("real2");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real1'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField(":")
        .appendField(new Blockly.FieldNumber(0), "X");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("real1");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real4'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField(": (")
        .appendField(new Blockly.FieldNumber(0), "X")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Y")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Z")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "W")
        .appendField(")");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("real4");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['base'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("based on")
        .appendField(new Blockly.FieldTextInput("entity"), "NAME");
    this.appendStatementInput("removed")
        .setCheck("componentName")
        .appendField("without");
    this.setOutput(true, "base");
    this.setColour(210);
 this.setTooltip("base");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['componentname'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component name"), "NAME");
    this.setPreviousStatement(true, "componentName");
    this.setNextStatement(true, "componentName");
    this.setColour(230);
 this.setTooltip("componentName");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['system'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldImage("https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-ios7-gear-outline-256.png", 25, 25, "*"))
        .appendField("system")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.appendStatementInput("commands")
        .setCheck("command")
        .appendField("commands");
    this.setColour(135);
 this.setTooltip("system");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['break'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("break");
    this.setPreviousStatement(true, "command");
    this.setColour(230);
 this.setTooltip("break");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['declaration'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("declare variable")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("declaration");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['loop'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("for all entity")
        .appendField(new Blockly.FieldTextInput("name"), "GROUP");
    this.appendStatementInput("constraints")
        .setCheck("constraint")
        .appendField("with");
    this.appendStatementInput("commands")
        .setCheck("command")
        .appendField("do");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("loop");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['tagged'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldCheckbox("TRUE"), "negated")
        .appendField("tag")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(315);
 this.setTooltip("constraint");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['timeout'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("timed out")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(330);
 this.setTooltip("timeout");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['triggered'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("triggered")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(330);
 this.setTooltip("triggered");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['detection'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["detecting an entry","ENTER"], ["detecting an stay","STAY"], ["detecting an exit","EXIT"]]), "NAME");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(330);
 this.setTooltip("enter");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['mouse'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["mouse down","DOWN"], ["mouse click","UP"], ["mouse enter","ENTER"], ["mouse exit","EXIT"]]), "NAME");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(330);
 this.setTooltip("mouse");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['if'] = {
  init: function() {
    this.appendValueInput("condition")
        .setCheck("Boolean")
        .setAlign(Blockly.ALIGN_CENTRE)
        .appendField("if");
    this.appendStatementInput("ifCommands")
        .setCheck("command")
        .appendField("then");
    this.appendStatementInput("elseCommands")
        .setCheck("command")
        .appendField("else");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['while'] = {
  init: function() {
    this.appendValueInput("condition")
        .setCheck("Boolean")
        .appendField("while");
    this.appendStatementInput("commands")
        .setCheck("command")
        .appendField("do");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("while");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['variable_assignment'] = {
  init: function() {
    this.appendValueInput("NAME")
        .setCheck("Number")
        .appendField(new Blockly.FieldDropdown([["set","SET"], ["intialize","INITIALIZE"], ["increase","INCREASE"], ["decrease","DECREASE"], ["multiply","MULTIPLY"], ["divide","DIVIDE"], ["modulus","MODULUS"]]), "assignmentType")
        .appendField(new Blockly.FieldTextInput("variable"), "NAME");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("variable assignment");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['component_assignment'] = {
  init: function() {
    this.appendValueInput("expression")
        .setCheck("Number")
        .appendField(new Blockly.FieldDropdown([["set","SET"], ["increase","INCREASE"], ["decrease","DECREASE"]]), "assignmentType")
        .appendField(new Blockly.FieldTextInput("component"), "COMPONENT")
        .appendField("of")
        .appendField(new Blockly.FieldTextInput("entity"), "ENTITY");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("component assignment");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['subrutine_call'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("call")
        .appendField(new Blockly.FieldTextInput("subrutine"), "SUBRUTINE");
    this.appendStatementInput("PARAMETERS")
        .setCheck(null)
        .appendField("with");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("subrutine call");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['access'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "COMPONENT")
        .appendField("of")
        .appendField(new Blockly.FieldTextInput("entity"), "ENTITY");
    this.setOutput(true, "Number");
    this.setColour(230);
 this.setTooltip("access");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['pop'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("value of")
        .appendField(new Blockly.FieldTextInput("variable"), "VARIABLE");
    this.setOutput(true, "Number");
    this.setColour(230);
 this.setTooltip("pop");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['call'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("process")
        .appendField(new Blockly.FieldTextInput("function"), "FUNCTION");
    this.appendStatementInput("NAME")
        .setCheck(null)
        .appendField("with");
    this.setOutput(true, "Number");
    this.setColour(230);
 this.setTooltip("call");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['real3'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField(":")
        .appendField("(")
        .appendField(new Blockly.FieldNumber(0), "X")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Y")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Z")
        .appendField(")");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['timer'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField(new Blockly.FieldNumber(0), "SECONDS")
        .appendField("seconds");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("timer");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['reference'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("entity")
        .appendField(new Blockly.FieldTextInput("name"), "ENTITY");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("reference");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['enumeration'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME");
    this.appendStatementInput("values")
        .setCheck("String")
        .appendField("includes");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("enumeration");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['enumeration_value'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setPreviousStatement(true, "String");
    this.setNextStatement(true, "String");
    this.setColour(230);
 this.setTooltip("enumeration value");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['trigger'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField(new Blockly.FieldDropdown([["gamepad start","START"], ["gamepad select","SELECT"], ["gamepad south","SOUTH"], ["gamepad west","WEST"], ["gamepad east","EAST"], ["gamepad north","NORTH"], ["gamepad left stick X","LEFTX"], ["gamepad left stick Y","LEFTY"], ["gamepad right stick X","RIGHTX"], ["gamepad right stick Y","RIGHTY"]]), "VALUE");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("trigger");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['sensor'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("detects");
    this.appendStatementInput("NAME")
        .setCheck("String")
        .appendField("categories");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("sensor");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['sprite'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("sprite")
        .appendField(new Blockly.FieldTextInput("name"), "SPRITE");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("sprite");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['audio'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("audio clip")
        .appendField(new Blockly.FieldTextInput("name"), "AUDIO");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("sprite");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['mesh'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("mesh")
        .appendField(new Blockly.FieldTextInput("name"), "MESH");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("sprite");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['range'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("gamepad stick")
        .appendField(new Blockly.FieldDropdown([["left X","LEFTX"], ["left Y","LEFTY"], ["right X","RIGHTX"], ["right Y","RIGHTY"]]), "VALUE");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("range");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['vertical_access'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "COMPONENT")
        .appendField("of")
        .appendField(new Blockly.FieldTextInput("entity"), "ENTITY");
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(230);
 this.setTooltip("vertical access");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['vertical_pop'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("value of")
        .appendField(new Blockly.FieldTextInput("variable"), "VARIABLE");
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(230);
 this.setTooltip("vertical pop");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['vertical_call'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("process")
        .appendField(new Blockly.FieldTextInput("function"), "FUNCTION");
    this.appendStatementInput("NAME")
        .setCheck(null)
        .appendField("with");
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(230);
 this.setTooltip("vertical call");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['tag'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("tag")
        .appendField(new Blockly.FieldTextInput("component"), "NAME");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("tag");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['multiplication'] = {
  init: function() {
    this.appendValueInput("a")
        .setCheck("Number");
    this.appendValueInput("b")
        .setCheck("Number")
        .appendField("x");
    this.setInputsInline(true);
    this.setOutput(true, "Number");
    this.setColour(230);
 this.setTooltip("multiplication");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['textcomponent'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField(":")
        .appendField(new Blockly.FieldTextInput("text"), "VALUE");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(230);
 this.setTooltip("text");
 this.setHelpUrl("");
  }
};