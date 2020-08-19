const cp = require('child_process')

cp.fork(__dirname + '/child.js')

process.send('from parent')