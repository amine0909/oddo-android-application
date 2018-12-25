const express = require("express")
const router = express.Router()
const Odoo = require('odoo-xmlrpc');
var objectLogin = require("../object")

/**
 * Login
 * URL : http://localhost:3000/login
 */
router.post('/login', (req, res) => {
  let EMAIL = req.body.email;
  let PASS  = req.body.pass;

  console.log(req.body)
	let odoo = new Odoo({
			url: 'http://localhost',
			port: 8069,
			db: "test2", // put your database name
			username: EMAIL, // your login
			password: PASS
	});

  odoo.connect((err, val) => {
	  let response = { // initial response
			connected: 0,
			uid: 0
	  }
	  if (err) {  // if email/pass is incorrect
		res.status(200).json(response);
	  }
	  else { // if email/pass is correct
		  response.connected = 1;
		  response.uid = val;

		  objectLogin = odoo
		  res.status(200).json(response);
	  }
  });
})




module.exports = router