const db = require('../utils/mysql')

module.exports = {
  searchAll: function () {
    return new Promise(resolve => {
      db.query('select * from article', null, (res, filed) => {
        resolve(res)
      })
    })
  },
  find: function (id) {
    return new Promise((resolve => {
      db.query('select * from article where id = ?', [id], (res, filed) => {
        resolve(res)
      })
    }))
  },
  delete: function (id) {
    return new Promise((resolve => {
      if (!id) throw new Error('id did not exist')
      db.query('delete from article where id = ?', [id], (res, filed) => {
        resolve(res)
      })
    }))
  },
  create: function (article) {
    return new Promise((resolve => {
      if (!article) throw new Error('id did not exist')
      db.query('insert into article(title,content) values (?,?)', [article], (res, filed) => {
        resolve(res)
      })
    }))
  }
}
