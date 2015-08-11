var mongoose = require('mongoose');
 
var Schema = mongoose.Schema;
 
var userSchema = mongoose.Schema({
    token : String,
    email: String,
    hashed_password: String,
    salt : String,
    temp_str:String,
    sentrequest:Array,// ridecode ,status
    recievedreques:Array,
    sex:String,
    phone:int,
    activerides:Array,//emails of the riders 
    location:String
});

var rideSchema = mongoose.Schema({
	adminemail:String,
	ricecode:String,
	date:String,
	starttime:String,
	origin:String,
	end:String,
	availableseats:int,
	totalseats:int,
	onlygirls:boolean,
	price:int,
	mode:String,
	modeinfo:String,
	riders:Array,
	

});
 
mongoose.connect('mongodb://localhost:27017/node-android');
module.exports = mongoose.model('users', userSchema);
module.exports = mongoose.model('rides', rideSchema);