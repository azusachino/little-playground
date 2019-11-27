const http = require('http')
const fs = require('fs')

http.createServer((req, res) => {
    if (req.url === '/') {
        getTitle(res)
    }
}).listen(8080, '127.0.0.1')

function getTitle(res) {
    fs.readFile('./title.json', (err, data) => {
        if (err) return handleError(err, res)
        getTemplate(JSON.parse(data.toString()), res)
    })
}

function getTemplate(titles, res) {
    fs.readFile('./template.html', (err, data) => {
        if (err) return handleError(err, res)
        formatHtml(titles, data.toString(), res)
    })
}

function formatHtml(titles, tmp, res) {
    const html = tmp.replace('%', titles.join('</l1><l1>'))
    res.writeHead(200, {'Content-Type': 'text/html'})
    res.end(html)
}

function handleError(err, res) {
    console.error(err)
    res.end('server error')
}
