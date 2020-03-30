const express = require('express')
const article = require('./api/article')
const app = express()

const port = process.env.PORT || 3000
// 创建 application/json 解析


// 创建 application/x-www-form-urlencoded 解析
app.use()

app.set('port', port)

app.get('/articles', (req,res, next) => {
    article.searchAll()
        .then((r) => {
            res.send(r)
        })
})

app.listen(port, ()=> {
    console.log(`Express app at localhost: ${port}`)
})
