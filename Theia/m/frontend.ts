import {ContainerModule, inject, injectable} from 'inversify'
import {CommandContribution, CommandRegistry, MessageService, MenuContribution, MenuModelRegistry} from '@theia/core'
import { AbstractViewContribution, CommonMenus, bindViewContribution, FrontendApplicationContribution, WidgetFactory, BaseWidget } from '@theia/core/lib/browser';
import { LanguageClientContribution, BaseLanguageClientContribution } from '@theia/languages/lib/browser'
/// <reference types='@typefox/monaco-editor-core/monaco'/>


export default new ContainerModule(bind=>
{
    bind(CommandContribution).to(FirstCommand);
    bind(MenuContribution).to(HelloWorldMenuContribution);

    bindViewContribution(bind, WidgetContribution);
    bind(FrontendApplicationContribution).toService(WidgetContribution);
    bind(DocumentationWidget).toSelf();
    bind(WidgetFactory).toDynamicValue(ctx => ({
        id: DocumentationWidget.ID,
        createWidget: () => ctx.container.get<DocumentationWidget>(DocumentationWidget)
    })).inSingletonScope();

    bind(LanguageClientContribution).to(MClient)
});

@injectable()
class MClient extends BaseLanguageClientContribution
{
    id='mserver';
    name='mserver';

    protected get globPatterns()
    {
        return ['**/*.m']
    }
}

@injectable()
class DocumentationWidget extends BaseWidget
{
    static ID = 'documentation-widget'

    constructor()
    {
        super();
        this.id = DocumentationWidget.ID;
        this.title.label = 'Documentation';
        this.title.caption = 'Documentation for M';
        this.title.closable = true;
        this.node.innerHTML = `<object type="text/html" data="../../Docs/site/index.html" style="width:100%;height:100%"/>`
    }
}

export const DocumentationWidgetCommand =
{
    id: 'documentation.command',
    label: 'Documentation'
}
console.log("Container ok");

class WidgetContribution extends AbstractViewContribution<DocumentationWidget>
{
    constructor()
    {
        super(
        {
            widgetId: DocumentationWidget.ID,
            widgetName: "Documentation Widget",
            defaultWidgetOptions: {area: 'left'},
            toggleCommandId: 'fileNavigator:toggle',
            toggleKeybinding: 'ctrlcmd+shift+1'
        })
    }

    registerCommands(commands: CommandRegistry)
    {
        commands.registerCommand(DocumentationWidgetCommand,
        {
            execute: () => super.openView({activate: false, reveal: true})
        })
    }

    registerMenus(registry: MenuModelRegistry)
    {
        registry.registerMenuAction(CommonMenus.EDIT_FIND, {
            commandId: DocumentationWidgetCommand.id,
            label: DocumentationWidgetCommand.label
        });
        super.registerMenus(registry)
    }
}

console.log("Widget contribution ok");



export const HelloWorldCommand = {
    id: 'HelloWorld.command',
    label: "Say hello to me",
    text: "Hello Martin!"
};

@injectable()
export class FirstCommand implements CommandContribution {

    constructor(
        @inject(MessageService) private readonly messageService: MessageService,
    ) { }

    registerCommands(registry: CommandRegistry): void {
        registry.registerCommand(HelloWorldCommand, {
            execute: () => this.messageService.info(HelloWorldCommand.text)
        });
    }
}

@injectable()
export class HelloWorldMenuContribution implements MenuContribution {

    registerMenus(menus: MenuModelRegistry): void {
        menus.registerMenuAction(CommonMenus.EDIT_FIND, {
                commandId: HelloWorldCommand.id,
                label: HelloWorldCommand.label
            });
    }
}


console.log("Hello command contribution ok");

// register language with monaco on first load
registerM();

export function registerM() {
    // initialize monaco
    monaco.languages.register({
        id: 'mserver',
        aliases: ['mserver'],
        extensions: ['.m'],
        mimetypes: ['text/m']
    })
}

console.log("monaco ok")