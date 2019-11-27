const Watcher = require('./watcher')

const watch = new Watcher('./', './');

let a = 1572566400
let b = 1575158400

let c = `Like "%" & [Forms]![request]![sql_店舗コード] & "%"
Like "%" & [Forms]![request]![sql_店舗名] & "%"
Between [Forms]![request]![sql_計上月from] And [Forms]![request]![sql_計上月to]`

