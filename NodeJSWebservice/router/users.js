const express = require("express")
const router = express.Router()
const odooConfig = require("../config")


const odoo = odooConfig


/**
 * Show All Users
 * URL : http://localhost:3000/users
 */
router.get('/users', (req, res) => {

    res.setHeader('Content-Type', 'application/json');
    let USER_ID = req.query.id;
    odoo.connect(function (err) {

        // these 3 lines is so important.. 
        // i don't know why... but we need to create an array and push it to another array
        // if you know why, please write a comment here... Thank you
        // - Adem Kouki
        let inParams = [];
        let params = [];
        params.push(inParams);

        odoo.execute_kw('res.users', 'search_read', params, function (err, value) {
            if (err) {
                return console.log(err);
            }
            res.send(value);
        });
    });

})


module.exports = router