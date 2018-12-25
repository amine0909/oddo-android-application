/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!! Doc : https://github.com/faisalsami/odoo-xmlrpc !!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */


const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 3000
const project = require("./router/Project")
const task  = require("./router/Task")
const user = require("./router/users")
const activity = require("./router/activity")
const stage = require("./router/stage")
const login = require("./router/login")

/* we need this For POST Method */ 
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())


app.use("/api", project);
app.use("/api", task);
app.use("/api", user);
app.use("/api", activity);
app.use("/api", stage);
app.use("/api", login);

app.listen(port, () => console.log(`Odoo Client listening on port ${port}!`))