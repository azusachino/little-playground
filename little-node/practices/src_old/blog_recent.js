const http = require('http')
const fs = require('fs')

http.createServer((req, res) => {
    if (req.url === '/') {
        fs.readFile('./title.json',(err, data) => {
            if (err) {
                console.error(err)
                res.end('server error')
            } else {
                const titles = JSON.parse(data.toString())
                fs.readFile('./template.html', (err,data) => {
                    if (err) {
                        console.error(err)
                        res.end('server error')
                    } else {
                        const tmp = data.toString();
                        const html = tmp.replace('%', titles.join('</li><li>'))
                        res.writeHead(200,{'Content-Type': 'text/html'})
                        res.end(html)
                    }
                })
            }
        })
    }
}).listen(8080, '127.0.0.1')
