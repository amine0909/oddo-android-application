const express = require("express")
const router = express.Router()
const odooConfig = require("../config")
const Odoo = require('odoo-xmlrpc');


//const odoo = odooConfig

/**
 * Get List of activities of specific task - By task id
 * URL example : http://localhost:3000/getActivitiesFromTask?task_id=
*/
router.get("/getActivitiesFromTask", (req,res) => {
    const task_id = req.query.task_id
    let email = req.query.email
    let password = req.query.password

    
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
            ["res_id","=",task_id]
        ]); 

        // fields
        inParams.push(["id","activity_type_id", "planned","date_deadline","note"])

        var params = [];
        params.push(inParams);
        odoo.execute_kw('mail.activity', 'search_read', params, function (err, value) {
            if (err) {
                return console.log(err);
            }
            if (value === undefined || value.length == 0) {
                // if there is no project with this id
                res.send([]);
            } else {
                res.send(value);
            }

        });
    });
})

module.exports = router