/**
 * Module dependencies.
 */
var express  = require('express');
var connect = require('connect');
var app      = express();
var port     = process.env.PORT || 8080;

var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:27017/node-android');
mongoose.connection.on('error',function (err) {
	console.log(err)
})
 
// Configuration
app.use(express.static(__dirname + '/public'));
app.use(connect.logger('dev'));
app.use(connect.json());
app.use(connect.urlencoded());
 
// Routes(where we will define what happens if we hit diffrent urls)
 
require('./routes/routes.js')(app);
 
app.listen(port);

 
console.log('The App runs on port ' + port);
