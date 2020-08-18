const glob = require('glob')

console.time('sync')
const result1 = glob.sync(__dirname+"/**/*")
console.timeEnd('sync') // 2.383ms
console.log(result1)

console.time('async')
const result2 = glob(__dirname+'/**/*', (err, result) => {
    if (err !== null) {
        console.error(err)
    }
    console.log(result)
})
console.timeEnd('async') // 0.956ms
console.log('doing something while async task')
