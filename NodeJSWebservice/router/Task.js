const express = require("express")
const router = express.Router()

const Odoo = require('odoo-xmlrpc');


//const odoo = odooConfig


/**
 * Get List of Tasks of specific project - By Project ID and user id and stage id
 * URL example : http://localhost:3000/getTasksByProjectIdAndUserId?project_id=&user_id=&stage_id=
 */
router.get('/getTasksByProjectIdAndUserIdAndStageId', (req, res) => {

    res.setHeader('Content-Type', 'application/json');
    let PROJECT_ID = req.query.project_id; 
    let USER_ID = req.query.user_id
    let stage_id = req.query.stage_id
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
        if(err) {
            return res.json(502, err)
        }

        var inParams = [];
        inParams.push([
            ['project_id', '=', parseInt(PROJECT_ID)],
            ['user_id','=',parseInt(USER_ID)],
            ["stage_id","=",parseInt(stage_id)]
        ]); // convert it to Integer

        // fields
        inParams.push(["id","name","description","date_start","date_deadline","stage_id","user_id","project_id"])
        var params = [];
        params.push(inParams);
        odoo.execute_kw('project.task', 'search_read', params, function (err, value) {
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


/**
 * Update the stage of the task
 * URL example : http://localhost:3000/task?task_id=&stage_id=
 */
router.put("/task", (req,res) => {
    res.setHeader('Content-Type', 'application/json');
    const task_id = req.query.task_id
    const stage_id = req.query.stage_id
    let email = req.query.email
    let password = req.query.password

    
    let odoo = new Odoo({
        url: 'http://localhost',
        port: 8069,
        db: "test2", // put your database name
        username: email, 
        password: password
    });


    odoo.connect((err) => {
        if(err) {
            return res.json(502, err)
        }

        var inParams = [];
        inParams.push([parseInt(task_id)]); //id to update
        inParams.push({'stage_id': parseInt(stage_id)})
        var params = [];
        params.push(inParams);
        odoo.execute_kw('project.task', 'write', params, function (err, value) {
            if (err) { return res.json(502, err) }
            	console.log(value)
            return res.json(200,value);
        });

    })
})








module.exports = router