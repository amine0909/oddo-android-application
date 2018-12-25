const Odoo = require('odoo-xmlrpc');

var odooConfig = new Odoo({
    url: 'http://localhost',
    port: 8069,
    db: "test2", // put your database name
    username: 'admin@gmail.com', // your login
    password: '123456789'
});


module.exports = odooConfig