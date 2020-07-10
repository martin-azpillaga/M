"use strict";
var __decorate = (this && this.__decorate) || function(decorators, target, key, desc) {
    var c = arguments.length,
        r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc,
        d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else
        for (var i = decorators.length - 1; i >= 0; i--)
            if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function(k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var DocumentationWidget_1, BlocksWidget_1;
Object.defineProperty(exports, "__esModule", {
    value: true
});
const inversify_1 = require("inversify");
const browser_1 = require("@theia/core/lib/browser");
const browser_2 = require("@theia/languages/lib/browser");
/// <reference types='@typefox/monaco-editor-core/monaco'/>
exports.default = new inversify_1.ContainerModule(bind => {
    bind(browser_2.LanguageClientContribution).to(MClient);
    browser_1.bindViewContribution(bind, DocumentationWidgetContribution);
    bind(browser_1.FrontendApplicationContribution).toService(DocumentationWidgetContribution);
    bind(DocumentationWidget).toSelf();
    bind(browser_1.WidgetFactory).toDynamicValue(ctx => ({
        id: DocumentationWidget.ID,
        createWidget: () => ctx.container.get(DocumentationWidget)
    })).inSingletonScope();
    browser_1.bindViewContribution(bind, BlocksWidgetContribution);
    bind(browser_1.FrontendApplicationContribution).toService(BlocksWidgetContribution);
    bind(BlocksWidget).toSelf();
    bind(browser_1.WidgetFactory).toDynamicValue(ctx => ({
        id: BlocksWidget.ID,
        createWidget: () => ctx.container.get(BlocksWidget)
    })).inSingletonScope();
});
let MClient = class MClient extends browser_2.BaseLanguageClientContribution {
    constructor() {
        super(...arguments);
        this.id = 'mserver';
        this.name = 'mserver';
    }
    get globPatterns() {
        return ['**/*.m'];
    }
};
MClient = __decorate([
    inversify_1.injectable()
], MClient);
registerM();

function registerM() {
    monaco.languages.register({
        id: 'mserver',
        aliases: ['mserver'],
        extensions: ['.m'],
        mimetypes: ['text/m']
    });
}
exports.registerM = registerM;
let DocumentationWidget = DocumentationWidget_1 = class DocumentationWidget extends browser_1.BaseWidget {
    constructor() {
        super();
        this.id = DocumentationWidget_1.ID;
        this.title.label = 'Documentation';
        this.title.caption = 'Documentation for M';
        this.title.closable = true;
        this.node.innerHTML = `<object type="text/html" data="../Documentation/README.html" style="width:100%;height:100%"/>`;
    }
};
DocumentationWidget.ID = 'documentation-widget';
DocumentationWidget = DocumentationWidget_1 = __decorate([
    inversify_1.injectable(),
    __metadata("design:paramtypes", [])
], DocumentationWidget);
exports.DocumentationWidgetCommand = {
    id: 'documentation.command',
    label: 'Documentation'
};
class DocumentationWidgetContribution extends browser_1.AbstractViewContribution {
    constructor() {
        super({
            widgetId: DocumentationWidget.ID,
            widgetName: "Documentation Widget",
            defaultWidgetOptions: {
                area: 'left'
            },
            toggleCommandId: 'fileNavigator:toggle',
            toggleKeybinding: 'ctrlcmd+shift+1'
        });
    }
    registerCommands(commands) {
        commands.registerCommand(exports.DocumentationWidgetCommand, {
            execute: () => super.openView({
                activate: false,
                reveal: true
            })
        });
    }
    registerMenus(registry) {
        registry.registerMenuAction(browser_1.CommonMenus.VIEW, {
            commandId: exports.DocumentationWidgetCommand.id,
            label: exports.DocumentationWidgetCommand.label
        });
        super.registerMenus(registry);
    }
}
let BlocksWidget = BlocksWidget_1 = class BlocksWidget extends browser_1.BaseWidget {
    constructor() {
        super();
        this.id = BlocksWidget_1.ID;
        this.title.label = 'Blocks';
        this.title.caption = 'Blocks for M';
        this.title.closable = true;
        this.node.innerHTML = `<object type="text/html" data="../Blockly/index.html" style="width:100%;height:100%"/>`;
    }
};
BlocksWidget.ID = 'blocks-widget';
BlocksWidget = BlocksWidget_1 = __decorate([
    inversify_1.injectable(),
    __metadata("design:paramtypes", [])
], BlocksWidget);
exports.BlocksWidgetCommand = {
    id: 'blocks.command',
    label: 'Blocks'
};
class BlocksWidgetContribution extends browser_1.AbstractViewContribution {
    constructor() {
        super({
            widgetId: BlocksWidget.ID,
            widgetName: "Blocks Widget",
            defaultWidgetOptions: {
                area: 'left'
            },
            toggleCommandId: 'fileNavigator:toggle',
            toggleKeybinding: 'ctrlcmd+shift+1'
        });
    }
    registerCommands(commands) {
        commands.registerCommand(exports.BlocksWidgetCommand, {
            execute: () => super.openView({
                activate: false,
                reveal: true
            })
        });
    }
    registerMenus(registry) {
        registry.registerMenuAction(browser_1.CommonMenus.VIEW, {
            commandId: exports.BlocksWidgetCommand.id,
            label: exports.BlocksWidgetCommand.label
        });
        super.registerMenus(registry);
    }
}
//# sourceMappingURL=frontend.js.map