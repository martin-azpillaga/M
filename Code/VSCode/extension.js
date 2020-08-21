var {join} = require("path");

exports.activate = function (context)
{
    console.log("activate"+join("a", "b"));
}
exports.deactivate = function ()
{
    console.log("deactivate");
}