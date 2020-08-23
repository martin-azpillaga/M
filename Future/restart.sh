#! /bin/sh

cd Code/Language
gradle shadowJar
cp build/libs/Language-ls-ls.jar ../../ls.jar
cp build/libs/Language-ls-ls.jar ~/.vscode-oss/extensions/azpillaga-world.m-ls-0.2.0/ls.jar
cp build/libs/Language-ls-ls.jar ~/.vscode/extensions/azpillaga-world.m-ls-0.2.0/ls.jar