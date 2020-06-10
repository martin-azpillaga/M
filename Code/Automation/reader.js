const readline = require('readline');
var rl = readline.createInterface({
input: process.stdin,
output: process.stdout,
terminal: false
});

rl.on('line', function(line){
    console.log(JSON.parse(line).blocks);
})

// execute by piping pandoc's json output to node
// pandoc file -t json | node reader.js