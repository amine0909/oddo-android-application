const express = require("express")
const router = express.Router()
const odooConfig = require("../config")
const Odoo = require('odoo-xmlrpc');


//const odoo = odooConfig

/*
*   this method is to get all stages on that project 
    URL Example : http://localhost:3000/stages?project_id=:id&email=&password 
*/
router.get("/stages", (req,res) => {
    res.setHeader('Content-Type', 'application/json');
    let project_id = req.query.project_id
    let email = req.query.email
    let password = req.query.password

    console.log(req.query)

    
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
            ["id","=",project_id]
        ]); 

        // fields
        inParams.push(["type_ids"])
        var params = [];
        params.push(inParams);
        odoo.execute_kw('project.project', 'search_read', params, function (err, value) {
            if (err) {
                return console.log(err);
            }
 
            var inParams2 = [];

            inParams2.push([
                ["project_ids","=",parseInt(project_id)]
            ])
   
            // fields
            //inParams.push(["project_id"])
            var params2 = [];
            params2.push(inParams2);

            odoo.execute_kw('project.task.type', 'search_read', params2, function (err2, value2) {
                if (err2) {
                    return console.log(err2);
                }
    
                // i want to get just the id of the stage and the name
                let newJsonResponse = [];
                for(let i=0;i<value2.length;i++) {
                    newJsonResponse.push({
                        "id": value2[i].id,
                        "name": value2[i].name
                    })
                }
                
                res.send(newJsonResponse);
            });
            
            //res.send(value);
        });
    });

})

module.exports = router
