"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/*
 * Copyright (C) 2017 TypeFox and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
var inversify_1 = require("inversify");
var browser_1 = require("@theia/languages/lib/browser");
/*var language_contribution_1 = require("./language-contribution");
exports.default = new inversify_1.ContainerModule(function (bind) {
    bind(browser_1.LanguageClientContribution).to(language_contribution_1.DslClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(language_contribution_1.NClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(language_contribution_1.BlocksClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(language_contribution_1.CSharpClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(language_contribution_1.YamlClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(language_contribution_1.JsonClientContribution).inSingletonScope();
});*/
exports.default = new inversify_1.ContainerModule(function (bind) {
    bind(browser_1.LanguageClientContribution).to(DslClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(NClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(BlocksClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(CSharpClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(YamlClientContribution).inSingletonScope();
    bind(browser_1.LanguageClientContribution).to(JsonClientContribution).inSingletonScope();
});
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
var inversify_1 = require("inversify");
var browser_1 = require("@theia/languages/lib/browser");
var DslClientContribution = /** @class */ (function (_super) {
    __extends(DslClientContribution, _super);
    function DslClientContribution(workspace, languages, languageClientFactory) {
        var _this = _super.call(this, workspace, languages, languageClientFactory) || this;
        _this.workspace = workspace;
        _this.languages = languages;
        _this.languageClientFactory = languageClientFactory;
        _this.id = "dsl";
        _this.name = "DSL";
        return _this;
    }
    Object.defineProperty(DslClientContribution.prototype, "globPatterns", {
        get: function () {
            return [
                '**/*.text'
            ];
        },
        enumerable: true,
        configurable: true
    });
    DslClientContribution = __decorate([
        inversify_1.injectable(),
        __param(0, inversify_1.inject(browser_1.Workspace)),
        __param(1, inversify_1.inject(browser_1.Languages)),
        __param(2, inversify_1.inject(browser_1.LanguageClientFactory)),
        __metadata("design:paramtypes", [Object, Object, browser_1.LanguageClientFactory])
    ], DslClientContribution);
    return DslClientContribution;
}(browser_1.BaseLanguageClientContribution));
exports.DslClientContribution = DslClientContribution;
var NClientContribution = /** @class */ (function (_super) {
    __extends(NClientContribution, _super);
    function NClientContribution(workspace, languages, languageClientFactory) {
        var _this = _super.call(this, workspace, languages, languageClientFactory) || this;
        _this.workspace = workspace;
        _this.languages = languages;
        _this.languageClientFactory = languageClientFactory;
        _this.id = "testua";
        _this.name = "TESTUA";
        return _this;
    }
    Object.defineProperty(NClientContribution.prototype, "globPatterns", {
        get: function () {
            return [
                '**/*.testua'
            ];
        },
        enumerable: true,
        configurable: true
    });
    NClientContribution = __decorate([
        inversify_1.injectable(),
        __param(0, inversify_1.inject(browser_1.Workspace)),
        __param(1, inversify_1.inject(browser_1.Languages)),
        __param(2, inversify_1.inject(browser_1.LanguageClientFactory)),
        __metadata("design:paramtypes", [Object, Object, browser_1.LanguageClientFactory])
    ], NClientContribution);
    return NClientContribution;
}(browser_1.BaseLanguageClientContribution));
exports.NClientContribution = NClientContribution;
var BlocksClientContribution = /** @class */ (function (_super) {
    __extends(BlocksClientContribution, _super);
    function BlocksClientContribution(workspace, languages, languageClientFactory) {
        var _this = _super.call(this, workspace, languages, languageClientFactory) || this;
        _this.workspace = workspace;
        _this.languages = languages;
        _this.languageClientFactory = languageClientFactory;
        _this.id = "blocks";
        _this.name = "BLOCKS";
        return _this;
    }
    Object.defineProperty(BlocksClientContribution.prototype, "globPatterns", {
        get: function () {
            return [
                '**/*.blocks'
            ];
        },
        enumerable: true,
        configurable: true
    });
    BlocksClientContribution = __decorate([
        inversify_1.injectable(),
        __param(0, inversify_1.inject(browser_1.Workspace)),
        __param(1, inversify_1.inject(browser_1.Languages)),
        __param(2, inversify_1.inject(browser_1.LanguageClientFactory)),
        __metadata("design:paramtypes", [Object, Object, browser_1.LanguageClientFactory])
    ], BlocksClientContribution);
    return BlocksClientContribution;
}(browser_1.BaseLanguageClientContribution));
exports.BlocksClientContribution = BlocksClientContribution;
var CSharpClientContribution = /** @class */ (function (_super) {
    __extends(CSharpClientContribution, _super);
    function CSharpClientContribution(workspace, languages, languageClientFactory) {
        var _this = _super.call(this, workspace, languages, languageClientFactory) || this;
        _this.workspace = workspace;
        _this.languages = languages;
        _this.languageClientFactory = languageClientFactory;
        _this.id = "csharp";
        _this.name = "CSHARP";
        return _this;
    }
    Object.defineProperty(CSharpClientContribution.prototype, "globPatterns", {
        get: function () {
            return [
                '**/*.cs'
            ];
        },
        enumerable: true,
        configurable: true
    });
    CSharpClientContribution = __decorate([
        inversify_1.injectable(),
        __param(0, inversify_1.inject(browser_1.Workspace)),
        __param(1, inversify_1.inject(browser_1.Languages)),
        __param(2, inversify_1.inject(browser_1.LanguageClientFactory)),
        __metadata("design:paramtypes", [Object, Object, browser_1.LanguageClientFactory])
    ], CSharpClientContribution);
    return CSharpClientContribution;
}(browser_1.BaseLanguageClientContribution));
exports.CSharpClientContribution = CSharpClientContribution;
var YamlClientContribution = /** @class */ (function (_super) {
    __extends(YamlClientContribution, _super);
    function YamlClientContribution(workspace, languages, languageClientFactory) {
        var _this = _super.call(this, workspace, languages, languageClientFactory) || this;
        _this.workspace = workspace;
        _this.languages = languages;
        _this.languageClientFactory = languageClientFactory;
        _this.id = "yaml";
        _this.name = "YAML";
        return _this;
    }
    Object.defineProperty(YamlClientContribution.prototype, "globPatterns", {
        get: function () {
            return [
                '**/*.prefab', '**/*.meta'
            ];
        },
        enumerable: true,
        configurable: true
    });
    YamlClientContribution = __decorate([
        inversify_1.injectable(),
        __param(0, inversify_1.inject(browser_1.Workspace)),
        __param(1, inversify_1.inject(browser_1.Languages)),
        __param(2, inversify_1.inject(browser_1.LanguageClientFactory)),
        __metadata("design:paramtypes", [Object, Object, browser_1.LanguageClientFactory])
    ], YamlClientContribution);
    return YamlClientContribution;
}(browser_1.BaseLanguageClientContribution));
exports.YamlClientContribution = YamlClientContribution;
var JsonClientContribution = /** @class */ (function (_super) {
    __extends(JsonClientContribution, _super);
    function JsonClientContribution(workspace, languages, languageClientFactory) {
        var _this = _super.call(this, workspace, languages, languageClientFactory) || this;
        _this.workspace = workspace;
        _this.languages = languages;
        _this.languageClientFactory = languageClientFactory;
        _this.id = "json";
        _this.name = "JSON";
        return _this;
    }
    Object.defineProperty(JsonClientContribution.prototype, "globPatterns", {
        get: function () {
            return [
                '**/*.json'
            ];
        },
        enumerable: true,
        configurable: true
    });
    JsonClientContribution = __decorate([
        inversify_1.injectable(),
        __param(0, inversify_1.inject(browser_1.Workspace)),
        __param(1, inversify_1.inject(browser_1.Languages)),
        __param(2, inversify_1.inject(browser_1.LanguageClientFactory)),
        __metadata("design:paramtypes", [Object, Object, browser_1.LanguageClientFactory])
    ], JsonClientContribution);
    return JsonClientContribution;
}(browser_1.BaseLanguageClientContribution));
exports.JsonClientContribution = JsonClientContribution;
// register language with monaco on first load
registerDSL();
function registerDSL() {
    // initialize monaco
    monaco.languages.register({
        id: 'dsl',
        aliases: ['DSL', 'dsl'],
        extensions: ['.text'],
        mimetypes: ['text/b']
    });
    monaco.languages.setLanguageConfiguration('dsl', {
        comments: {
            lineComment: "//",
            blockComment: ['/*', '*/']
        },
        brackets: [['{', '}'], ['(', ')']],
        autoClosingPairs: [
            {
                open: '{',
                close: '}'
            },
            {
                open: '(',
                close: ')'
            }
        ]
    });
    monaco.languages.setMonarchTokensProvider('dsl', {
        // Set defaultToken to invalid to see what you do not tokenize yet
        // defaultToken: 'invalid',
        keywords: [
            'position', 'lagun', 'request', 'notification', 'extends'
        ],
        typeKeywords: [
            'boolean', 'number', 'string'
        ],
        operators: [
            ':'
        ],
        // we include these common regular expressions
        symbols: /[=><!~?:&|+\-*\/\^%]+/,
        escapes: /\\(?:[abfnrtv\\"']|x[0-9A-Fa-f]{1,4}|u[0-9A-Fa-f]{4}|U[0-9A-Fa-f]{8})/,
        // The main tokenizer for our languages
        tokenizer: {
            root: [
                // identifiers and keywords
                [/[a-z_$][\w$]*/, {
                        cases: {
                            '@typeKeywords': 'keyword',
                            '@keywords': 'keyword',
                            '@default': 'identifier'
                        }
                    }],
                [/[A-Z][\w\$]*/, 'type.identifier'],
                // whitespace
                { include: '@whitespace' },
                // delimiters and operators
                [/[{}()\[\]]/, '@brackets'],
                [/[<>](?!@symbols)/, '@brackets'],
                [/@symbols/, {
                        cases: {
                            '@operators': 'operator',
                            '@default': ''
                        }
                    }]
            ],
            whitespace: [
                [/[ \t\r\n]+/, 'white'],
                [/\/\*/, 'comment', '@comment'],
                [/\/\/.*$/, 'comment'],
            ],
            comment: [
                [/[^\/*]+/, 'comment'],
                [/\/\*/, 'comment.invalid'],
                ["\\*/", 'comment', '@pop'],
                [/[\/*]/, 'comment']
            ],
            string: [
                [/[^\\"]+/, 'string'],
                [/@escapes/, 'string.escape'],
                [/\\./, 'string.escape.invalid'],
                [/"/, 'string', '@pop']
            ],
        },
    });
    monaco.languages.register({
        id: 'testua',
        aliases: ['TESTUA', 'testua'],
        extensions: ['.testua'],
        mimetypes: ['text/testua']
    });
    monaco.languages.setLanguageConfiguration('testua', {
        comments: {
            lineComment: "//",
            blockComment: ['/*', '*/']
        },
        brackets: [['{', '}'], ['(', ')']],
        autoClosingPairs: [
            {
                open: '{',
                close: '}'
            },
            {
                open: '(',
                close: ')'
            }
        ]
    });
    monaco.languages.setMonarchTokensProvider('testua', {
        // Set defaultToken to invalid to see what you do not tokenize yet
        // defaultToken: 'invalid',
        keywords: [
            'Emmmm', 'bai', 'request', 'notification', 'extends'
        ],
        typeKeywords: [
            'boolean', 'number', 'string'
        ],
        operators: [
            ':'
        ],
        // we include these common regular expressions
        symbols: /[=><!~?:&|+\-*\/\^%]+/,
        escapes: /\\(?:[abfnrtv\\"']|x[0-9A-Fa-f]{1,4}|u[0-9A-Fa-f]{4}|U[0-9A-Fa-f]{8})/,
        // The main tokenizer for our languages
        tokenizer: {
            root: [
                // identifiers and keywords
                [/[a-z_$][\w$]*/, {
                        cases: {
                            '@typeKeywords': 'keyword',
                            '@keywords': 'keyword',
                            '@default': 'identifier'
                        }
                    }],
                [/[A-Z][\w\$]*/, 'type.identifier'],
                // whitespace
                { include: '@whitespace' },
                // delimiters and operators
                [/[{}()\[\]]/, '@brackets'],
                [/[<>](?!@symbols)/, '@brackets'],
                [/@symbols/, {
                        cases: {
                            '@operators': 'operator',
                            '@default': ''
                        }
                    }]
            ],
            whitespace: [
                [/[ \t\r\n]+/, 'white'],
                [/\/\*/, 'comment', '@comment'],
                [/\/\/.*$/, 'comment'],
            ],
            comment: [
                [/[^\/*]+/, 'comment'],
                [/\/\*/, 'comment.invalid'],
                ["\\*/", 'comment', '@pop'],
                [/[\/*]/, 'comment']
            ],
            string: [
                [/[^\\"]+/, 'string'],
                [/@escapes/, 'string.escape'],
                [/\\./, 'string.escape.invalid'],
                [/"/, 'string', '@pop']
            ],
        },
    });
    monaco.languages.register({
        id: 'blocks',
        aliases: ['BLOCKS', 'blocks'],
        extensions: ['.blocks'],
        mimetypes: ['text/blocks']
    });
    monaco.languages.setLanguageConfiguration('blocks', {
        comments: {
            lineComment: "//",
            blockComment: ['/*', '*/']
        },
        brackets: [['{', '}'], ['(', ')']],
        autoClosingPairs: [
            {
                open: '{',
                close: '}'
            },
            {
                open: '(',
                close: ')'
            }
        ]
    });
    monaco.languages.setMonarchTokensProvider('blocks', {
        // Set defaultToken to invalid to see what you do not tokenize yet
        // defaultToken: 'invalid',
        keywords: [
            'position', 'lagun', 'request', 'notification', 'extends'
        ],
        typeKeywords: [
            'boolean', 'number', 'string'
        ],
        operators: [
            ':'
        ],
        // we include these common regular expressions
        symbols: /[=><!~?:&|+\-*\/\^%]+/,
        escapes: /\\(?:[abfnrtv\\"']|x[0-9A-Fa-f]{1,4}|u[0-9A-Fa-f]{4}|U[0-9A-Fa-f]{8})/,
        // The main tokenizer for our languages
        tokenizer: {
            root: [
                // identifiers and keywords
                [/[a-z_$][\w$]*/, {
                        cases: {
                            '@typeKeywords': 'keyword',
                            '@keywords': 'keyword',
                            '@default': 'identifier'
                        }
                    }],
                [/[A-Z][\w\$]*/, 'type.identifier'],
                // whitespace
                { include: '@whitespace' },
                // delimiters and operators
                [/[{}()\[\]]/, '@brackets'],
                [/[<>](?!@symbols)/, '@brackets'],
                [/@symbols/, {
                        cases: {
                            '@operators': 'operator',
                            '@default': ''
                        }
                    }]
            ],
            whitespace: [
                [/[ \t\r\n]+/, 'white'],
                [/\/\*/, 'comment', '@comment'],
                [/\/\/.*$/, 'comment'],
            ],
            comment: [
                [/[^\/*]+/, 'comment'],
                [/\/\*/, 'comment.invalid'],
                ["\\*/", 'comment', '@pop'],
                [/[\/*]/, 'comment']
            ],
            string: [
                [/[^\\"]+/, 'string'],
                [/@escapes/, 'string.escape'],
                [/\\./, 'string.escape.invalid'],
                [/"/, 'string', '@pop']
            ],
        },
    });
    monaco.languages.register({
        id: 'csharp',
        aliases: ['CSHARP', 'csharp'],
        extensions: ['.cs'],
        mimetypes: ['text/csharp']
    });
    monaco.languages.setLanguageConfiguration('csharp', {
        comments: {
            lineComment: "//",
            blockComment: ['/*', '*/']
        },
        brackets: [['{', '}'], ['(', ')']],
        autoClosingPairs: [
            {
                open: '{',
                close: '}'
            },
            {
                open: '(',
                close: ')'
            }
        ]
    });
    monaco.languages.setMonarchTokensProvider('csharp', {
        // Set defaultToken to invalid to see what you do not tokenize yet
        // defaultToken: 'invalid',
        keywords: [
            'position', 'lagun', 'request', 'notification', 'extends'
        ],
        typeKeywords: [
            'boolean', 'number', 'string'
        ],
        operators: [
            ':'
        ],
        // we include these common regular expressions
        symbols: /[=><!~?:&|+\-*\/\^%]+/,
        escapes: /\\(?:[abfnrtv\\"']|x[0-9A-Fa-f]{1,4}|u[0-9A-Fa-f]{4}|U[0-9A-Fa-f]{8})/,
        // The main tokenizer for our languages
        tokenizer: {
            root: [
                // identifiers and keywords
                [/[a-z_$][\w$]*/, {
                        cases: {
                            '@typeKeywords': 'keyword',
                            '@keywords': 'keyword',
                            '@default': 'identifier'
                        }
                    }],
                [/[A-Z][\w\$]*/, 'type.identifier'],
                // whitespace
                { include: '@whitespace' },
                // delimiters and operators
                [/[{}()\[\]]/, '@brackets'],
                [/[<>](?!@symbols)/, '@brackets'],
                [/@symbols/, {
                        cases: {
                            '@operators': 'operator',
                            '@default': ''
                        }
                    }]
            ],
            whitespace: [
                [/[ \t\r\n]+/, 'white'],
                [/\/\*/, 'comment', '@comment'],
                [/\/\/.*$/, 'comment'],
            ],
            comment: [
                [/[^\/*]+/, 'comment'],
                [/\/\*/, 'comment.invalid'],
                ["\\*/", 'comment', '@pop'],
                [/[\/*]/, 'comment']
            ],
            string: [
                [/[^\\"]+/, 'string'],
                [/@escapes/, 'string.escape'],
                [/\\./, 'string.escape.invalid'],
                [/"/, 'string', '@pop']
            ],
        },
    });
    monaco.languages.register({
        id: 'yaml',
        aliases: ['YAML', 'yaml'],
        extensions: ['.prefab', '.meta'],
        mimetypes: ['text/yaml']
    });
    monaco.languages.setLanguageConfiguration('yaml', {
        comments: {
            lineComment: "//",
            blockComment: ['/*', '*/']
        },
        brackets: [['{', '}'], ['(', ')']],
        autoClosingPairs: [
            {
                open: '{',
                close: '}'
            },
            {
                open: '(',
                close: ')'
            }
        ]
    });
    monaco.languages.setMonarchTokensProvider('yaml', {
        // Set defaultToken to invalid to see what you do not tokenize yet
        // defaultToken: 'invalid',
        keywords: [
            'position', 'lagun', 'request', 'notification', 'extends'
        ],
        typeKeywords: [
            'boolean', 'number', 'string'
        ],
        operators: [
            ':'
        ],
        // we include these common regular expressions
        symbols: /[=><!~?:&|+\-*\/\^%]+/,
        escapes: /\\(?:[abfnrtv\\"']|x[0-9A-Fa-f]{1,4}|u[0-9A-Fa-f]{4}|U[0-9A-Fa-f]{8})/,
        // The main tokenizer for our languages
        tokenizer: {
            root: [
                // identifiers and keywords
                [/[a-z_$][\w$]*/, {
                        cases: {
                            '@typeKeywords': 'keyword',
                            '@keywords': 'keyword',
                            '@default': 'identifier'
                        }
                    }],
                [/[A-Z][\w\$]*/, 'type.identifier'],
                // whitespace
                { include: '@whitespace' },
                // delimiters and operators
                [/[{}()\[\]]/, '@brackets'],
                [/[<>](?!@symbols)/, '@brackets'],
                [/@symbols/, {
                        cases: {
                            '@operators': 'operator',
                            '@default': ''
                        }
                    }]
            ],
            whitespace: [
                [/[ \t\r\n]+/, 'white'],
                [/\/\*/, 'comment', '@comment'],
                [/\/\/.*$/, 'comment'],
            ],
            comment: [
                [/[^\/*]+/, 'comment'],
                [/\/\*/, 'comment.invalid'],
                ["\\*/", 'comment', '@pop'],
                [/[\/*]/, 'comment']
            ],
            string: [
                [/[^\\"]+/, 'string'],
                [/@escapes/, 'string.escape'],
                [/\\./, 'string.escape.invalid'],
                [/"/, 'string', '@pop']
            ],
        },
    });
    monaco.languages.register({
        id: 'json',
        aliases: ['JSON', 'json'],
        extensions: ['.json'],
        mimetypes: ['text/json']
    });
    monaco.languages.setLanguageConfiguration('json', {
        comments: {
            lineComment: "//",
            blockComment: ['/*', '*/']
        },
        brackets: [['{', '}'], ['(', ')']],
        autoClosingPairs: [
            {
                open: '{',
                close: '}'
            },
            {
                open: '(',
                close: ')'
            }
        ]
    });
    monaco.languages.setMonarchTokensProvider('json', {
        // Set defaultToken to invalid to see what you do not tokenize yet
        // defaultToken: 'invalid',
        keywords: [
            'position', 'lagun', 'request', 'notification', 'extends'
        ],
        typeKeywords: [
            'boolean', 'number', 'string'
        ],
        operators: [
            ':'
        ],
        // we include these common regular expressions
        symbols: /[=><!~?:&|+\-*\/\^%]+/,
        escapes: /\\(?:[abfnrtv\\"']|x[0-9A-Fa-f]{1,4}|u[0-9A-Fa-f]{4}|U[0-9A-Fa-f]{8})/,
        // The main tokenizer for our languages
        tokenizer: {
            root: [
                // identifiers and keywords
                [/[a-z_$][\w$]*/, {
                        cases: {
                            '@typeKeywords': 'keyword',
                            '@keywords': 'keyword',
                            '@default': 'identifier'
                        }
                    }],
                [/[A-Z][\w\$]*/, 'type.identifier'],
                // whitespace
                { include: '@whitespace' },
                // delimiters and operators
                [/[{}()\[\]]/, '@brackets'],
                [/[<>](?!@symbols)/, '@brackets'],
                [/@symbols/, {
                        cases: {
                            '@operators': 'operator',
                            '@default': ''
                        }
                    }]
            ],
            whitespace: [
                [/[ \t\r\n]+/, 'white'],
                [/\/\*/, 'comment', '@comment'],
                [/\/\/.*$/, 'comment'],
            ],
            comment: [
                [/[^\/*]+/, 'comment'],
                [/\/\*/, 'comment.invalid'],
                ["\\*/", 'comment', '@pop'],
                [/[\/*]/, 'comment']
            ],
            string: [
                [/[^\\"]+/, 'string'],
                [/@escapes/, 'string.escape'],
                [/\\./, 'string.escape.invalid'],
                [/"/, 'string', '@pop']
            ],
        },
    });
}
exports.registerDSL = registerDSL;
//# sourceMappingURL=language-contribution.js.map