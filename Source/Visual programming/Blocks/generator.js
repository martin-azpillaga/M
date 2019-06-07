Blockly.JavaScript['entity'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var value_base = Blockly.JavaScript.valueToCode(block, 'base', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_components = Blockly.JavaScript.statementToCode(block, 'components');
  var statements_subentities = Blockly.JavaScript.statementToCode(block, 'subEntities');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['real2'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var number_x = block.getFieldValue('X');
  var number_y = block.getFieldValue('Y');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['real1'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var number_x = block.getFieldValue('X');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['real4'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var number_x = block.getFieldValue('X');
  var number_y = block.getFieldValue('Y');
  var number_z = block.getFieldValue('Z');
  var number_w = block.getFieldValue('W');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['base'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var statements_removed = Blockly.JavaScript.statementToCode(block, 'removed');
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['componentname'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['system'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var statements_commands = Blockly.JavaScript.statementToCode(block, 'commands');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['break'] = function(block) {
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['declaration'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['loop'] = function(block) {
  var text_group = block.getFieldValue('GROUP');
  var statements_constraints = Blockly.JavaScript.statementToCode(block, 'constraints');
  var statements_commands = Blockly.JavaScript.statementToCode(block, 'commands');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['tagged'] = function(block) {
  var checkbox_negated = block.getFieldValue('negated') == 'TRUE';
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['timeout'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['triggered'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['detection'] = function(block) {
  var dropdown_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['mouse'] = function(block) {
  var dropdown_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['if'] = function(block) {
  var value_condition = Blockly.JavaScript.valueToCode(block, 'condition', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_ifcommands = Blockly.JavaScript.statementToCode(block, 'ifCommands');
  var statements_elsecommands = Blockly.JavaScript.statementToCode(block, 'elseCommands');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['while'] = function(block) {
  var value_condition = Blockly.JavaScript.valueToCode(block, 'condition', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_commands = Blockly.JavaScript.statementToCode(block, 'commands');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['variable_assignment'] = function(block) {
  var dropdown_assignmenttype = block.getFieldValue('assignmentType');
  var text_name = block.getFieldValue('NAME');
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['component_assignment'] = function(block) {
  var dropdown_assignmenttype = block.getFieldValue('assignmentType');
  var text_component = block.getFieldValue('COMPONENT');
  var text_entity = block.getFieldValue('ENTITY');
  var value_expression = Blockly.JavaScript.valueToCode(block, 'expression', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['subrutine_call'] = function(block) {
  var text_subrutine = block.getFieldValue('SUBRUTINE');
  var statements_parameters = Blockly.JavaScript.statementToCode(block, 'PARAMETERS');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['access'] = function(block) {
  var text_component = block.getFieldValue('COMPONENT');
  var text_entity = block.getFieldValue('ENTITY');
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['pop'] = function(block) {
  var text_variable = block.getFieldValue('VARIABLE');
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['call'] = function(block) {
  var text_function = block.getFieldValue('FUNCTION');
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['real3'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var number_x = block.getFieldValue('X');
  var number_y = block.getFieldValue('Y');
  var number_z = block.getFieldValue('Z');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['timer'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var number_seconds = block.getFieldValue('SECONDS');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['reference'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var text_entity = block.getFieldValue('ENTITY');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['enumeration'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var statements_values = Blockly.JavaScript.statementToCode(block, 'values');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['enumeration_value'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['trigger'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var dropdown_value = block.getFieldValue('VALUE');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['sensor'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['sprite'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var text_sprite = block.getFieldValue('SPRITE');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['audio'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var text_audio = block.getFieldValue('AUDIO');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['mesh'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var text_mesh = block.getFieldValue('MESH');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['range'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var dropdown_value = block.getFieldValue('VALUE');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['vertical_access'] = function(block) {
  var text_component = block.getFieldValue('COMPONENT');
  var text_entity = block.getFieldValue('ENTITY');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['vertical_pop'] = function(block) {
  var text_variable = block.getFieldValue('VARIABLE');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['vertical_call'] = function(block) {
  var text_function = block.getFieldValue('FUNCTION');
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['tag'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['multiplication'] = function(block) {
  var value_a = Blockly.JavaScript.valueToCode(block, 'a', Blockly.JavaScript.ORDER_ATOMIC);
  var value_b = Blockly.JavaScript.valueToCode(block, 'b', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['textcomponent'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var text_value = block.getFieldValue('VALUE');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};