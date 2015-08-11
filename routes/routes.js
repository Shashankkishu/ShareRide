var chgpass = require('config/chgpass');
var signup = require('config/signup');
var login = require('config/login');
// var giveride = require('config/giveride');
 
 
module.exports = function(app) {
 
 
 //this has as such no use in the app .
    app.get('/api', function(req, res) {
 
        res.end("share-ride-project");
    });
 
 // This gets acivated when the user hits the login button (if he is a excisting user)
    app.post('/api/login',function(req,res){
        var email = req.body.email;
            var password = req.body.password;
 
        login.login(email,password,function (found) {
            console.log(found);
            res.json(found);
    });
    });
 
 // This gets activated when user hits the sign up 
    app.post('/api/signup',function(req,res){
        console.log(JSON.stringify(req.body));
        var email = req.body["x-auth-email"];
        // var xAuthEmail = req.header.
        // var xAuthPassword
        // var xAuthName
            console.log('has entered the emai if + '+email);

            var password = req.body["x-auth-password"];
    console.log('has entered the emai if + '+password);
        var name = req.body["x-auth-name"];
        signup.signup(email,password,function (found) {
            console.log(found);
            res.json(found);
    });
    });
 
 //This gets activated when we hit the change password in the profile section password 
    app.post('/api/chgpass', function(req, res) {
        var id = req.body.id;
                var opass = req.body.oldpass;
        var npass = req.body.newpass;
 
        chgpass.cpass(id,opass,npass,function(found){
            console.log(found);
            res.json(found);
    });
    });
 
 // this gets activated when we user hits forgot password in sign in page
    app.post('/api/resetpass', function(req, res) {
 
        var email = req.body.email;
 
        chgpass.respass_init(email,function(found){
            console.log(found);
            res.json(found);
    });
    });
 
 
    app.post('/api/resetpass/chg', function(req, res) {
 
        var email = req.body.email;
        var code = req.body.code;
        var npass = req.body.newpass;
 
        chgpass.respass_chg(email,code,npass,function(found){
            console.log(found);
            res.json(found);
    });
    });

// This gets activiated when we hit the submit button in the add ride page .
 app.post('/api/addride',function(req,res){
    
        var ridecode = req.body.ridecode;
        var adminemail = req.body.adminemail;
        var starttime = req.body.starttime;
        var origin = req.body.origin;
        var end = req.body.end;
        var availableseats = req.body.availableseats;
        var totalseats = req.body.totalseats;
        var onlygirls = req.body.onlygirls;
        var price = req.body.price;
        var mode = req.body.mode;
        var modeinfo = req.body.modeinfo;
        var riders = req.body.riders;

        addride.addride(ridecode,adminemail,starttime,origin,end,availableseats,totalseats,samesex,price,mode,moderiders,riders,function (found) {
            console.log(found);
            res.json(found);
    });
    });
 
};