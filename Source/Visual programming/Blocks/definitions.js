[{
  "type": "entity",
  "message0": "entity %1 %2 %3 has %4 contains %5",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "name"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_value",
      "name": "base",
      "check": "base"
    },
    {
      "type": "input_statement",
      "name": "components",
      "check": "component"
    },
    {
      "type": "input_statement",
      "name": "subEntities",
      "check": "entity"
    }
  ],
  "inputsInline": false,
  "previousStatement": "entity",
  "nextStatement": "entity",
  "colour": 210,
  "tooltip": "entity",
  "helpUrl": ""
},
{
  "type": "real2",
  "message0": "%1 : ( %2 , %3 )",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_number",
      "name": "X",
      "value": 0
    },
    {
      "type": "field_number",
      "name": "Y",
      "value": 0
    }
  ],
  "inputsInline": true,
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "real2",
  "helpUrl": ""
},
{
  "type": "real1",
  "message0": "%1 : %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_number",
      "name": "X",
      "value": 0
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "real1",
  "helpUrl": ""
},
{
  "type": "real4",
  "message0": "%1 : ( %2 , %3 , %4 , %5 )",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_number",
      "name": "X",
      "value": 0
    },
    {
      "type": "field_number",
      "name": "Y",
      "value": 0
    },
    {
      "type": "field_number",
      "name": "Z",
      "value": 0
    },
    {
      "type": "field_number",
      "name": "W",
      "value": 0
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "real4",
  "helpUrl": ""
},
{
  "type": "base",
  "message0": "based on %1 %2 without %3",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "entity"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "removed",
      "check": "componentName"
    }
  ],
  "output": "base",
  "colour": 210,
  "tooltip": "base",
  "helpUrl": ""
},
{
  "type": "componentname",
  "message0": "%1",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component name"
    }
  ],
  "previousStatement": "componentName",
  "nextStatement": "componentName",
  "colour": 230,
  "tooltip": "componentName",
  "helpUrl": ""
},
{
  "type": "system",
  "message0": "%1 system %2 %3 commands %4",
  "args0": [
    {
      "type": "field_image",
      "src": "https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-ios7-gear-outline-256.png",
      "width": 25,
      "height": 25,
      "alt": "*",
      "flipRtl": false
    },
    {
      "type": "field_input",
      "name": "NAME",
      "text": "name"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "commands",
      "check": "command"
    }
  ],
  "colour": 135,
  "tooltip": "system",
  "helpUrl": ""
},
{
  "type": "break",
  "message0": "break",
  "previousStatement": "command",
  "colour": 230,
  "tooltip": "break",
  "helpUrl": ""
},
{
  "type": "declaration",
  "message0": "declare variable %1",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "name"
    }
  ],
  "previousStatement": "command",
  "nextStatement": "command",
  "colour": 230,
  "tooltip": "declaration",
  "helpUrl": ""
},
{
  "type": "loop",
  "message0": "for all entity %1 %2 with %3 do %4",
  "args0": [
    {
      "type": "field_input",
      "name": "GROUP",
      "text": "name"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "constraints",
      "check": "constraint"
    },
    {
      "type": "input_statement",
      "name": "commands",
      "check": "command"
    }
  ],
  "previousStatement": "command",
  "nextStatement": "command",
  "colour": 230,
  "tooltip": "loop",
  "helpUrl": ""
},
{
  "type": "tagged",
  "message0": "%1 tag %2",
  "args0": [
    {
      "type": "field_checkbox",
      "name": "negated",
      "checked": true
    },
    {
      "type": "field_input",
      "name": "NAME",
      "text": "name"
    }
  ],
  "previousStatement": "constraint",
  "nextStatement": "constraint",
  "colour": 315,
  "tooltip": "constraint",
  "helpUrl": ""
},
{
  "type": "timeout",
  "message0": "timed out %1",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "name"
    }
  ],
  "previousStatement": "constraint",
  "nextStatement": "constraint",
  "colour": 330,
  "tooltip": "timeout",
  "helpUrl": ""
},
{
  "type": "triggered",
  "message0": "triggered %1",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "name"
    }
  ],
  "previousStatement": "constraint",
  "nextStatement": "constraint",
  "colour": 330,
  "tooltip": "triggered",
  "helpUrl": ""
},
{
  "type": "detection",
  "message0": "%1",
  "args0": [
    {
      "type": "field_dropdown",
      "name": "NAME",
      "options": [
        [
          "detecting an entry",
          "ENTER"
        ],
        [
          "detecting an stay",
          "STAY"
        ],
        [
          "detecting an exit",
          "EXIT"
        ]
      ]
    }
  ],
  "previousStatement": "constraint",
  "nextStatement": "constraint",
  "colour": 330,
  "tooltip": "enter",
  "helpUrl": ""
},
{
  "type": "mouse",
  "message0": "%1",
  "args0": [
    {
      "type": "field_dropdown",
      "name": "NAME",
      "options": [
        [
          "mouse down",
          "DOWN"
        ],
        [
          "mouse click",
          "UP"
        ],
        [
          "mouse enter",
          "ENTER"
        ],
        [
          "mouse exit",
          "EXIT"
        ]
      ]
    }
  ],
  "previousStatement": "constraint",
  "nextStatement": "constraint",
  "colour": 330,
  "tooltip": "mouse",
  "helpUrl": ""
},
{
  "type": "if",
  "message0": "if %1 then %2 else %3",
  "args0": [
    {
      "type": "input_value",
      "name": "condition",
      "check": "Boolean",
      "align": "CENTRE"
    },
    {
      "type": "input_statement",
      "name": "ifCommands",
      "check": "command"
    },
    {
      "type": "input_statement",
      "name": "elseCommands",
      "check": "command"
    }
  ],
  "previousStatement": "command",
  "nextStatement": "command",
  "colour": 230,
  "tooltip": "",
  "helpUrl": ""
},
{
  "type": "while",
  "message0": "while %1 do %2",
  "args0": [
    {
      "type": "input_value",
      "name": "condition",
      "check": "Boolean"
    },
    {
      "type": "input_statement",
      "name": "commands",
      "check": "command"
    }
  ],
  "previousStatement": "command",
  "nextStatement": "command",
  "colour": 230,
  "tooltip": "while",
  "helpUrl": ""
},
{
  "type": "variable_assignment",
  "message0": "%1 %2 %3",
  "args0": [
    {
      "type": "field_dropdown",
      "name": "assignmentType",
      "options": [
        [
          "set",
          "SET"
        ],
        [
          "intialize",
          "INITIALIZE"
        ],
        [
          "increase",
          "INCREASE"
        ],
        [
          "decrease",
          "DECREASE"
        ],
        [
          "multiply",
          "MULTIPLY"
        ],
        [
          "divide",
          "DIVIDE"
        ],
        [
          "modulus",
          "MODULUS"
        ]
      ]
    },
    {
      "type": "field_input",
      "name": "NAME",
      "text": "variable"
    },
    {
      "type": "input_value",
      "name": "NAME",
      "check": "Number"
    }
  ],
  "previousStatement": "command",
  "nextStatement": "command",
  "colour": 230,
  "tooltip": "variable assignment",
  "helpUrl": ""
},
{
  "type": "component_assignment",
  "message0": "%1 %2 of %3 %4",
  "args0": [
    {
      "type": "field_dropdown",
      "name": "assignmentType",
      "options": [
        [
          "set",
          "SET"
        ],
        [
          "increase",
          "INCREASE"
        ],
        [
          "decrease",
          "DECREASE"
        ]
      ]
    },
    {
      "type": "field_input",
      "name": "COMPONENT",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "ENTITY",
      "text": "entity"
    },
    {
      "type": "input_value",
      "name": "expression",
      "check": "Number"
    }
  ],
  "previousStatement": "command",
  "nextStatement": "command",
  "colour": 230,
  "tooltip": "component assignment",
  "helpUrl": ""
},
{
  "type": "subrutine_call",
  "message0": "call %1 %2 with %3",
  "args0": [
    {
      "type": "field_input",
      "name": "SUBRUTINE",
      "text": "subrutine"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "PARAMETERS"
    }
  ],
  "previousStatement": "command",
  "nextStatement": "command",
  "colour": 230,
  "tooltip": "subrutine call",
  "helpUrl": ""
},
{
  "type": "access",
  "message0": "%1 of %2",
  "args0": [
    {
      "type": "field_input",
      "name": "COMPONENT",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "ENTITY",
      "text": "entity"
    }
  ],
  "output": "Number",
  "colour": 230,
  "tooltip": "access",
  "helpUrl": ""
},
{
  "type": "pop",
  "message0": "value of %1",
  "args0": [
    {
      "type": "field_input",
      "name": "VARIABLE",
      "text": "variable"
    }
  ],
  "output": "Number",
  "colour": 230,
  "tooltip": "pop",
  "helpUrl": ""
},
{
  "type": "call",
  "message0": "process %1 %2 with %3",
  "args0": [
    {
      "type": "field_input",
      "name": "FUNCTION",
      "text": "function"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "NAME"
    }
  ],
  "output": "Number",
  "colour": 230,
  "tooltip": "call",
  "helpUrl": ""
},
{
  "type": "real3",
  "message0": "%1 : ( %2 , %3 , %4 )",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_number",
      "name": "X",
      "value": 0
    },
    {
      "type": "field_number",
      "name": "Y",
      "value": 0
    },
    {
      "type": "field_number",
      "name": "Z",
      "value": 0
    }
  ],
  "colour": 230,
  "tooltip": "",
  "helpUrl": ""
},
{
  "type": "timer",
  "message0": "%1 %2 seconds",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_number",
      "name": "SECONDS",
      "value": 0
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "timer",
  "helpUrl": ""
},
{
  "type": "reference",
  "message0": "%1 entity %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "ENTITY",
      "text": "name"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "reference",
  "helpUrl": ""
},
{
  "type": "enumeration",
  "message0": "%1 %2 includes %3",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "values",
      "check": "String"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "enumeration",
  "helpUrl": ""
},
{
  "type": "enumeration_value",
  "message0": "%1",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "name"
    }
  ],
  "previousStatement": "String",
  "nextStatement": "String",
  "colour": 230,
  "tooltip": "enumeration value",
  "helpUrl": ""
},
{
  "type": "trigger",
  "message0": "%1 %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_dropdown",
      "name": "VALUE",
      "options": [
        [
          "gamepad start",
          "START"
        ],
        [
          "gamepad select",
          "SELECT"
        ],
        [
          "gamepad south",
          "SOUTH"
        ],
        [
          "gamepad west",
          "WEST"
        ],
        [
          "gamepad east",
          "EAST"
        ],
        [
          "gamepad north",
          "NORTH"
        ],
        [
          "gamepad left stick X",
          "LEFTX"
        ],
        [
          "gamepad left stick Y",
          "LEFTY"
        ],
        [
          "gamepad right stick X",
          "RIGHTX"
        ],
        [
          "gamepad right stick Y",
          "RIGHTY"
        ]
      ]
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "trigger",
  "helpUrl": ""
},
{
  "type": "sensor",
  "message0": "%1 detects %2 categories %3",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "NAME",
      "check": "String"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "sensor",
  "helpUrl": ""
},
{
  "type": "sprite",
  "message0": "%1 sprite %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "SPRITE",
      "text": "name"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "sprite",
  "helpUrl": ""
},
{
  "type": "audio",
  "message0": "%1 audio clip %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "AUDIO",
      "text": "name"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "sprite",
  "helpUrl": ""
},
{
  "type": "mesh",
  "message0": "%1 mesh %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "MESH",
      "text": "name"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "sprite",
  "helpUrl": ""
},
{
  "type": "range",
  "message0": "%1 gamepad stick %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_dropdown",
      "name": "VALUE",
      "options": [
        [
          "left X",
          "LEFTX"
        ],
        [
          "left Y",
          "LEFTY"
        ],
        [
          "right X",
          "RIGHTX"
        ],
        [
          "right Y",
          "RIGHTY"
        ]
      ]
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "range",
  "helpUrl": ""
},
{
  "type": "vertical_access",
  "message0": "%1 of %2",
  "args0": [
    {
      "type": "field_input",
      "name": "COMPONENT",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "ENTITY",
      "text": "entity"
    }
  ],
  "previousStatement": null,
  "nextStatement": null,
  "colour": 230,
  "tooltip": "vertical access",
  "helpUrl": ""
},
{
  "type": "vertical_pop",
  "message0": "value of %1",
  "args0": [
    {
      "type": "field_input",
      "name": "VARIABLE",
      "text": "variable"
    }
  ],
  "previousStatement": null,
  "nextStatement": null,
  "colour": 230,
  "tooltip": "vertical pop",
  "helpUrl": ""
},
{
  "type": "vertical_call",
  "message0": "process %1 %2 with %3",
  "args0": [
    {
      "type": "field_input",
      "name": "FUNCTION",
      "text": "function"
    },
    {
      "type": "input_dummy"
    },
    {
      "type": "input_statement",
      "name": "NAME"
    }
  ],
  "previousStatement": null,
  "nextStatement": null,
  "colour": 230,
  "tooltip": "vertical call",
  "helpUrl": ""
},
{
  "type": "tag",
  "message0": "tag %1",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "tag",
  "helpUrl": ""
},
{
  "type": "multiplication",
  "message0": "%1 x %2",
  "args0": [
    {
      "type": "input_value",
      "name": "a",
      "check": "Number"
    },
    {
      "type": "input_value",
      "name": "b",
      "check": "Number"
    }
  ],
  "inputsInline": true,
  "output": "Number",
  "colour": 230,
  "tooltip": "multiplication",
  "helpUrl": ""
},
{
  "type": "textcomponent",
  "message0": "%1 : %2",
  "args0": [
    {
      "type": "field_input",
      "name": "NAME",
      "text": "component"
    },
    {
      "type": "field_input",
      "name": "VALUE",
      "text": "text"
    }
  ],
  "previousStatement": "component",
  "nextStatement": "component",
  "colour": 230,
  "tooltip": "text",
  "helpUrl": ""
}]