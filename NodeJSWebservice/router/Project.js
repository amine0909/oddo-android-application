const express = require("express")
const router = express.Router()
const odooConfig = require("../config")
const Odoo = require('odoo-xmlrpc');


//const odoo = odooConfig

/**
 * Get Projects Info By user_id
 * URL Example : http://localhost:3000/projectsByUserID?user_id=:id&password=&email=
 * 
 * i need to use the task table to get project name and use the user_id field in task, to check
 * because i can't get the user_id in the project table :))
 */
router.post('/projectsByUserID', (req, res) => {

    res.setHeader('Content-Type', 'application/json');
    let user_id = req.query.user_id; 
    let password = req.query.password
    let email = req.query.email

    let odoo = new Odoo({
        url: 'http://localhost',
        port: 8069,
        db: "test2", // put your database name
        username: email, 
        password: password
    });



    odoo.connect(function (err) {
        var inParams = [];
        inParams.push([
            ["user_id","=",parseInt(user_id)]
        ]); 

        // fields
        inParams.push(["project_id"])
        var params = [];
        params.push(inParams);
        odoo.execute_kw('project.task', 'search_read', params, function (err, value) {
            if (err) {
                return console.log(err);
            }
            
            // sometimes you get repeted informations
            let newResponse = [value[0]]
            for(let i=1;i<value.length;i++) {
                if(newResponse[0].project_id[0] != value[i].project_id[0]) {
                    newResponse.push(value[i])
                }
            }
            res.status(200).json(newResponse);
        });
    });

})


module.exports = router