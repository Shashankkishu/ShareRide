var chgpass = require('config/chgpass');
var signup = require('config/signup');
var login = require('config/login');
var addride = require('config/addride');
var nodemailer = require('nodemailer');
var next30rides = require('config/next30rides');
var ridesQuery = require('config/ridesQuery');
var rides = require('config/ridemodel');
var mongoose = require('mongoose');
var smtpTransport = require('nodemailer-smtp-transport');
var checkOTP = require('config/checkOTP');
var alreadyUser = require('config/alreadyUser');
var addrequest = require('config/addrequest');

module.exports = function(app) {
 
 
 //this has as such no use in the app .
    app.get('/api', function(req, res) {
 
        res.end("share-ride-project");
    });
   // my app is opened for the second time then his token is checked against that of the exsisting users
    app.get('/api/alreadyUser',function(req,res){
        console.log(JSON.stringify(req.headers));;
        var token = req.headers["x-auth-token"];
        console.log(token);
        alreadyUser.alreadyUser(token,function (found) {
        console.log(found);
        res.json(found);
    });
    });
 
// This gets activated when user hits the sign up which only sends the mail to the user with the OTP the real login is done byt he CheckOTP function
    app.post('/api/signup',function(req,res){
        var email = req.body["x-auth-email"];
        var password = req.body["x-auth-password"];
        var name = req.body["x-auth-name"];
        signup.signup(email,password,name,function (found) {
            res.json(found);
    });
    });
    //this function creates all the auth token and hashkey witht the SHA512 key from my system and creates a new user
    app.post('/api/checkOTP',function(req,res){
        console.log(JSON.stringify(req.body));;
        var OTP = req.body["OTP"];
        var email = req.body["x-auth-email"];
        var password = req.body["x-auth-password"];
        var name = req.body["x-auth-name"];
        checkOTP.checkOTP(OTP,email,password,name,function (found) {
        console.log(found);
        res.json(found);
    });
    });
 // This gets acivated when the user hits the login button (if he is a excisting user)
    app.post('/api/login',function(req,res){
        var email = req.body["x-auth-email"];
        var password = req.body["x-auth-password"];
        login.login(email,password,function (found) {
        console.log(found);
        res.json(found);

    });
    });
 
    //when ever we call this method a mail sent to the reqest email with the content type in the request body 
    app.get('/api/email', function(req, res)
     {
            //node mailer is a service used to connect to the SMTP connection i want to set up the server with 
            var nodemailer = require('nodemailer');
            // create reusable transporter object using SMTP transport
            var transporter = nodemailer.createTransport("SMTP",{
                service: 'Gmail',
                debug: true,
                auth: {
                    // <doubt> we have to find a away to secrealty store the password in the 
                    user: "shashankkishu@gmail.com",
                            pass: "gmailpassword"
                }
            });

            // NB! No need to recreate the transporter object. You can use
            // the same transporter object for all e-mails

            // setup e-mail data with unicode symbols
            var mailOptions = {
                from: 'Shashank Bhushan ✔ <shashankkishu@gmail.com>', // sender address
                to: 'shashankkishu@gmail.com', // list of receivers
                subject: 'Hello ✔', // Subject line
                text: 'Hello world ✔', // plaintext body
                html: '<b>Hello world ✔</b>' // html body
            };

            // send mail with defined transport object
            transporter.sendMail(mailOptions, function(error, info){
                if(error){
                    return console.log(error);
                }
                // console.log('Message sent: ' + info.response);

            });
    });
    //when a filter is applied then this function recives a request with the set of parament
    app.get('/api/ridesQuery',function(req,res){
        console.log(JSON.stringify(req.headers));;
        var millis = req.headers["millis"];
        var origin = req.headers["origin"];
        var destination = req.headers["destination"];
        console.log(millis + origin + destination);
        ridesQuery.ridesQuery(millis,origin,destination,function (found) {
        console.log(found);
        res.json(found);
    });
    });
 
 
    //when a ride is booked ny the user and a request is sent to the admin of the ride with a token and the ride ID
    app.post('/api/addrequest',function(req,res){
        console.log(JSON.stringify(req.body));;
        // var token = req.headers["x-auth-token"];
        // var ID = req.body["ride-ID"];
        addrequest.addrequest(function (found) {
        console.log(found);
        res.json(found);
    });
    });
 
    //when the rides activity is set the next
 app.get('/api/getRides', function(req, res) {
        rides.find({"millis":{$gte:"1439474834014"}},function(err, results)
        {
            res.json({'rides' : results});
        });
    });

// This gets activiated when we hit the submit button in the add ride page .
 app.post('/api/addrides',function(req,res){
                // console.log(req.body);
            // console.log(req);
        // var adminemail = req.body["initiator"];

        var token = req.headers["x-auth-token"];
        var date = req.body["date"];
        var millis = req.body["millis"];
        var time = req.body["time"];
        var origin = req.body["origin"];
        var destination = req.body["destination"];
        var has_booked = req.body["has_booked"];
        var freeSpace = req.body["freeSpace"];
        var totalseats = req.body["totalseats"];
        var only_girls = req.body["only_girls"];
        var price = req.body["price"];
        var transport_mode = req.body["transport_mode"];
        var transport_mode_info = req.body["transport_mode_info"];
        // var riders = req.body["riders"];

        addride.addride(token,time,origin,destination,date,millis,freeSpace,totalseats,only_girls,price,transport_mode,transport_mode_info,function (found) {
            // console.lsog(found);
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
 //
 
    app.post('/api/resetpass/chg', function(req, res) {
 
        var email = req.body.email;
        var code = req.body.code;
        var npass = req.body.newpass;
 
        chgpass.respass_chg(email,code,npass,function(found){
            console.log(found);
            res.json(found);
    });
    });

};