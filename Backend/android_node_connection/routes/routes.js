var chgpass = require('config/chgpass');
var signup = require('config/signup');
var login = require('config/login');
var addride = require('config/addride');
var nodemailer = require('nodemailer');
var next30rides = require('config/next30rides');
var rides = require('config/models');
var mongoose = require('mongoose');
var smtpTransport = require('nodemailer-smtp-transport');

 
module.exports = function(app) {
 
 
 //this has as such no use in the app .
    app.get('/api', function(req, res) {
 
        res.end("share-ride-project");
    });
    app.get('/api/email', function(req, res)
     {
            var nodemailer = require('nodemailer');
                console.log(nodemailer);
            // create reusable transporter object using SMTP transport
            var transporter = nodemailer.createTransport("SMTP",{
                service: 'Gmail',
                debug: true,
                auth: {
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
                console.log('Message sent: ' + info.response);

            });
    });
 
 // This gets acivated when the user hits the login button (if he is a excisting user)
    app.post('/api/login',function(req,res){
        console.log(JSON.stringify(req.body));
        var email = req.body["x-auth-email"];
        var password = req.body["x-auth-password"];
        console.log('Password'+password);
        console.log('Email'+email);
        login.login(email,password,function (found) {
            console.log(found);
            res.json(found);
    });
    });
 
 // This gets activated when user hits the sign up 
    app.post('/api/signup',function(req,res){
        console.log(JSON.stringify(req.body));
        var email = req.body["x-auth-email"];
        var password = req.body["x-auth-password"];
        var name = req.body["x-auth-name"];
        // console.log('Password'+password);
        // console.log('Email'+email);
        signup.signup(email,password,name,function (found) {
            console.log(found);
            res.json(found);
    });
    });
 app.get('/api/next30rides', function(req, res) {
        var results = rides.find({"millis":{$gte:"1439474834014"}})
        // (function(err, results1)
        // {
        //     console.log(results1+results); // output all records
        // });
        // next30rides.next30rides({
        //     console.log(res);
        //     res.json(res);
        // });
        // console.log(results);
        console.log(results.stringify);
        res.json(results.stringify);

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
 app.post('/api/addrides',function(req,res){
                // console.log(req.body);

        // var adminemail = req.body["initiator"];
        var date = req.body["date"]
        var millis = req.body["millis"]
        var starttime = req.body["time"];
        var origin = req.body["origin"];
        var end = req.body["destination"];
        var availableseats = req.body["availableseats"];
        var totalseats = req.body["totalseats"];
        var onlygirls = req.body["onlygirls"];
        var price = req.body["price"];
        var mode = req.body["transport_mode"];
        var modeinfo = req.body["transport_mode_info"];
        // var riders = req.body["riders"];

        addride.addride(starttime,origin,end,date,millis,availableseats,totalseats,onlygirls,price,mode,modeinfo,function (found) {
            console.log(found);
            res.json(found);
    });
    });
 
};