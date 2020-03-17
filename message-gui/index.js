var express = require("express");
var app = express();
app.use('/', express.static(__dirname + '/public'));

var server = require("http").createServer(app);
server.listen(8080);

app.get("/", function(request, response){
  response.sendFile(__dirname + "/index.html");
});
