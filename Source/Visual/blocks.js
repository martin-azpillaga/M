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
    this.setColour(165);
 this.setTooltip("loop");
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
    this.setColour(165);
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
    this.setColour(165);
 this.setTooltip("while");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['variable_assignment'] = {
  init: function() {
    this.appendValueInput("NAME")
        .setCheck(null)
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
    this.appendValueInput("FUNCTION")
        .setCheck("function")
        .appendField("call");
    this.setPreviousStatement(true, "command");
    this.setNextStatement(true, "command");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['binary_arithmetic'] = {
  init: function() {
    this.appendValueInput("left")
        .setCheck("Number");
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["+","ADD"], ["-","SUBTRACT"], ["*","MULTIPLY"], ["/","DIVIDE"], ["%","MODULUS"], ["&","AND"], ["|","OR"]]), "OPERATION");
    this.appendValueInput("right")
        .setCheck("Number");
    this.setOutput(true, "Number");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['binary_boolean'] = {
  init: function() {
    this.appendValueInput("left")
        .setCheck(null);
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["and","AND"], ["or","OR"]]), "OPERATION");
    this.appendValueInput("right")
        .setCheck(null);
    this.setOutput(true, "Boolean");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['unary_boolean'] = {
  init: function() {
    this.appendValueInput("NAME")
        .setCheck("Boolean")
        .appendField("not");
    this.setOutput(true, "Boolean");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['binary_arithmetic_boolean'] = {
  init: function() {
    this.appendValueInput("left")
        .setCheck("Number");
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["<","LOWER"], ["<=","LOWEROREQUAL"], ["=","EQUAL"], ["!=","NOTEQUAL"], [">=","GREATEROREQUAL"], [">","GREATER"], ["includes","INCLUDES"]]), "OPERATION");
    this.appendValueInput("right")
        .setCheck("Number");
    this.setOutput(true, "Boolean");
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['constraint_tagged'] = {
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

Blockly.Blocks['constraint_timeout'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldCheckbox("TRUE"), "negated")
        .appendField("timed out")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(330);
 this.setTooltip("timeout");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['constraint_triggered'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldCheckbox("TRUE"), "negated")
        .appendField("triggered")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(330);
 this.setTooltip("triggered");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['constraint_enter'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldCheckbox("TRUE"), "negated")
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("detecting an entry");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(315);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['constraint_stay'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldCheckbox("TRUE"), "negated")
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("detecting an stay");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(315);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['constraint_exit'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldCheckbox("TRUE"), "negated")
        .appendField(new Blockly.FieldTextInput("component"), "NAME")
        .appendField("detecting an exit");
    this.setPreviousStatement(true, "constraint");
    this.setNextStatement(true, "constraint");
    this.setColour(315);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['function1'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("function"), "NAME");
    this.appendValueInput("X")
        .setCheck(null);
    this.setInputsInline(true);
    this.setOutput(true, null);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['function2'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("function"), "NAME");
    this.appendValueInput("X")
        .setCheck(null);
    this.appendValueInput("Y")
        .setCheck(null);
    this.setInputsInline(true);
    this.setOutput(true, null);
    this.setColour(230);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['relation'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("relation")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.appendStatementInput("members")
        .setCheck(["binary_relation", "ternary_relation", "quaternary_relation"]);
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['entity_plus'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldCheckbox("FALSE"), "MAIN")
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
    this.setInputsInline(true);
    this.setPreviousStatement(true, "entity");
    this.setNextStatement(true, "entity");
    this.setColour(210);
 this.setTooltip("entity");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['component'] = {
  init: function() {
    this.appendValueInput("VALUE")
        .setCheck("value")
        .appendField(new Blockly.FieldTextInput("component"), "NAME");
    this.setPreviousStatement(true, "component");
    this.setNextStatement(true, "component");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['binary_relation'] = {
  init: function() {
    this.appendValueInput("X")
        .setCheck("value");
    this.appendValueInput("Y")
        .setCheck("value")
        .appendField("-");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "binary_relation");
    this.setNextStatement(true, "binary_relation");
    this.setColour(240);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['ternary_relation'] = {
  init: function() {
    this.appendValueInput("X")
        .setCheck("value");
    this.appendValueInput("Y")
        .setCheck("value")
        .appendField("-");
    this.appendValueInput("Z")
        .setCheck("value")
        .appendField("-");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "ternary_relation");
    this.setNextStatement(true, "ternary_relation");
    this.setColour(240);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['quaternary_relation'] = {
  init: function() {
    this.appendValueInput("X")
        .setCheck("value");
    this.appendValueInput("Y")
        .setCheck("value")
        .appendField("-");
    this.appendValueInput("Z")
        .setCheck("value")
        .appendField("-");
    this.appendValueInput("W")
        .setCheck("value")
        .appendField("-");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "quaternary_relation");
    this.setNextStatement(true, "quaternary_relation");
    this.setColour(240);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_id'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("id")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_tag'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("tag");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_real1'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "X");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_real2'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "X")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Y");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_real3'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "X")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Y")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Z");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_real4'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "X")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Y")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "Z")
        .appendField(",")
        .appendField(new Blockly.FieldNumber(0), "W");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_sprite'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("sprite")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_mesh'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("mesh")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_material'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("material")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_audio'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("audio")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_text'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("text")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_font'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("font")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_entity'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("entity")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_enumeration'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("enumeration");
    this.appendStatementInput("VALUES")
        .setCheck("enumerationValue")
        .appendField(new Blockly.FieldTextInput("name"), "NAME");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_sensor'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("detect");
    this.appendStatementInput("VALUES")
        .setCheck("enumerationValue")
        .appendField("category");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_timer'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldNumber(0), "VALUE")
        .appendField("seconds");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_trigger'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["gamepad south","SOUTH"], ["gamepad west","WEST"], ["gamepad east","EAST"], ["gamepad north","NORTH"], ["gamepad down","DOWN"], ["gamepad left","LEFT"], ["gamepad right","RIGHT"], ["gamepad up","UP"], ["gamepad left trigger","LEFTTRIGGER"], ["gamepad right trigger","RIGHTTRIGGER"], ["gamepad left stick","LEFTPRESS"], ["gamepad right stick","RIGHTPRESS"], ["gamepad start","START"], ["gamepad select","SELECT"], ["gamepad optional","OPTIONAL"]]), "VALUE");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_range'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["gamepad left x","LEFTX"], ["gamepad left y","LEFTY"], ["gamepad right x","RIGHTX"], ["gamepad right y","RIGHTY"], ["gamepad left shoulder","LEFTSHOULDER"], ["gamepad right shoulder","RIGHTSHOULDER"]]), "VALUE");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};

Blockly.Blocks['value_vector'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldDropdown([["gamepad left stick","LEFTSTICK"], ["gamepad right stick","RIGHTSTICK"], ["gamepad dpad","DPAD"], ["mouse position","MOUSEPOSITION"]]), "VALUE");
    this.setOutput(true, "value");
    this.setColour(285);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};