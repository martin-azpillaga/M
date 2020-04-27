"use strict";
/*
 * Copyright (C) 2017 TypeFox and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
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
Object.defineProperty(exports, "__esModule", { value: true });
var inversify_1 = require("inversify");
var node_1 = require("@theia/languages/lib/node");
var server_1 = require("vscode-ws-jsonrpc/lib/server");
var path = require("path");
var net = require("net");
exports.default = new inversify_1.ContainerModule(function (bind) {
    bind(node_1.LanguageServerContribution).to(DSLContribution);
    bind(node_1.LanguageServerContribution).to(NContribution);
    bind(node_1.LanguageServerContribution).to(BlocksContribution);
    bind(node_1.LanguageServerContribution).to(CSharpContribution);
    bind(node_1.LanguageServerContribution).to(YamlContribution);
    bind(node_1.LanguageServerContribution).to(JsonContribution);
});
function getPort() {
    var arg = process.argv.filter(function (arg) { return arg.startsWith('--LSP_PORT='); })[0];
    if (!arg) {
        return undefined;
    }
    else {
        return Number.parseInt(arg.substring('--LSP_PORT='.length));
    }
}
var DSLContribution = /** @class */ (function (_super) {
    __extends(DSLContribution, _super);
    function DSLContribution() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.id = "dsl";
        _this.name = "DSL";
        return _this;
    }
    DSLContribution.prototype.start = function (clientConnection) {
        var socketPort = getPort();
        if (socketPort) {
            var socket_1 = new net.Socket();
            var serverConnection = server_1.createSocketConnection(socket_1, socket_1, function () {
                socket_1.destroy();
            });
            this.forward(clientConnection, serverConnection);
            socket_1.connect(socketPort);
        }
        else {
            var jar = path.resolve(__dirname, 'ls.jar');
            var command = 'java';
            var args = [
                '-jar',
                jar
            ];
            var serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    };
    DSLContribution.prototype.onDidFailSpawnProcess = function (error) {
        _super.prototype.onDidFailSpawnProcess.call(this, error);
        console.error("Error starting Text language server." + error.name + " : " + error.message, error);
    };
    DSLContribution = __decorate([
        inversify_1.injectable()
    ], DSLContribution);
    return DSLContribution;
}(node_1.BaseLanguageServerContribution));
var NContribution = /** @class */ (function (_super) {
    __extends(NContribution, _super);
    function NContribution() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.id = "testua";
        _this.name = "TESTUA";
        return _this;
    }
    NContribution.prototype.start = function (clientConnection) {
        var socketPort = getPort();
        if (socketPort) {
            var socket_2 = new net.Socket();
            var serverConnection = server_1.createSocketConnection(socket_2, socket_2, function () {
                socket_2.destroy();
            });
            this.forward(clientConnection, serverConnection);
            socket_2.connect(socketPort);
        }
        else {
            var jar = path.resolve(__dirname, 'ls.jar');
            var command = 'java';
            var args = [
                '-jar',
                jar
            ];
            var serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    };
    NContribution.prototype.onDidFailSpawnProcess = function (error) {
        _super.prototype.onDidFailSpawnProcess.call(this, error);
        console.error("Error starting Testua language server.", error);
    };
    NContribution = __decorate([
        inversify_1.injectable()
    ], NContribution);
    return NContribution;
}(node_1.BaseLanguageServerContribution));
var BlocksContribution = /** @class */ (function (_super) {
    __extends(BlocksContribution, _super);
    function BlocksContribution() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.id = "blocks";
        _this.name = "BLOCKS";
        return _this;
    }
    BlocksContribution.prototype.start = function (clientConnection) {
        var socketPort = getPort();
        if (socketPort) {
            var socket_3 = new net.Socket();
            var serverConnection = server_1.createSocketConnection(socket_3, socket_3, function () {
                socket_3.destroy();
            });
            this.forward(clientConnection, serverConnection);
            socket_3.connect(socketPort);
        }
        else {
            var jar = path.resolve(__dirname, 'ls.jar');
            var command = 'java';
            var args = [
                '-jar',
                jar
            ];
            var serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    };
    BlocksContribution.prototype.onDidFailSpawnProcess = function (error) {
        _super.prototype.onDidFailSpawnProcess.call(this, error);
        console.error("Error starting Blocks language server.", error);
    };
    BlocksContribution = __decorate([
        inversify_1.injectable()
    ], BlocksContribution);
    return BlocksContribution;
}(node_1.BaseLanguageServerContribution));
var CSharpContribution = /** @class */ (function (_super) {
    __extends(CSharpContribution, _super);
    function CSharpContribution() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.id = "csharp";
        _this.name = "CSHARP";
        return _this;
    }
    CSharpContribution.prototype.start = function (clientConnection) {
        var socketPort = getPort();
        if (socketPort) {
            var socket_4 = new net.Socket();
            var serverConnection = server_1.createSocketConnection(socket_4, socket_4, function () {
                socket_4.destroy();
            });
            this.forward(clientConnection, serverConnection);
            socket_4.connect(socketPort);
        }
        else {
            var jar = path.resolve(__dirname, 'ls.jar');
            var command = 'java';
            var args = [
                '-jar',
                jar
            ];
            var serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    };
    CSharpContribution.prototype.onDidFailSpawnProcess = function (error) {
        _super.prototype.onDidFailSpawnProcess.call(this, error);
        console.error("Error starting CSharp language server.", error);
    };
    CSharpContribution = __decorate([
        inversify_1.injectable()
    ], CSharpContribution);
    return CSharpContribution;
}(node_1.BaseLanguageServerContribution));
var YamlContribution = /** @class */ (function (_super) {
    __extends(YamlContribution, _super);
    function YamlContribution() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.id = "yaml";
        _this.name = "YAML";
        return _this;
    }
    YamlContribution.prototype.start = function (clientConnection) {
        var socketPort = getPort();
        if (socketPort) {
            var socket_5 = new net.Socket();
            var serverConnection = server_1.createSocketConnection(socket_5, socket_5, function () {
                socket_5.destroy();
            });
            this.forward(clientConnection, serverConnection);
            socket_5.connect(socketPort);
        }
        else {
            var jar = path.resolve(__dirname, 'ls.jar');
            var command = 'java';
            var args = [
                '-jar',
                jar
            ];
            var serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    };
    YamlContribution.prototype.onDidFailSpawnProcess = function (error) {
        _super.prototype.onDidFailSpawnProcess.call(this, error);
        console.error("Error starting Yaml language server.", error);
    };
    YamlContribution = __decorate([
        inversify_1.injectable()
    ], YamlContribution);
    return YamlContribution;
}(node_1.BaseLanguageServerContribution));
var JsonContribution = /** @class */ (function (_super) {
    __extends(JsonContribution, _super);
    function JsonContribution() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.id = "json";
        _this.name = "JSON";
        return _this;
    }
    JsonContribution.prototype.start = function (clientConnection) {
        var socketPort = getPort();
        if (socketPort) {
            var socket_6 = new net.Socket();
            var serverConnection = server_1.createSocketConnection(socket_6, socket_6, function () {
                socket_6.destroy();
            });
            this.forward(clientConnection, serverConnection);
            socket_6.connect(socketPort);
        }
        else {
            var jar = path.resolve(__dirname, 'ls.jar');
            var command = 'java';
            var args = [
                '-jar',
                jar
            ];
            var serverConnection = this.createProcessStreamConnection(command, args);
            this.forward(clientConnection, serverConnection);
        }
    };
    JsonContribution.prototype.onDidFailSpawnProcess = function (error) {
        _super.prototype.onDidFailSpawnProcess.call(this, error);
        console.error("Error starting Json language server.", error);
    };
    JsonContribution = __decorate([
        inversify_1.injectable()
    ], JsonContribution);
    return JsonContribution;
}(node_1.BaseLanguageServerContribution));