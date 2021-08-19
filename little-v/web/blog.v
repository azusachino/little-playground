module main

import vweb
import time
import sqlite

struct App {
	vweb.Context
pub mut:
    db sqlite.DB
}

fn main() {
	mut app := App{
		db: sqlite.connect(':memory:') or { panic(err) }
	}
	sql app.db {
		create table Article
	}

	first_article := Article {
		title: 'Hello, World!'
		text: 'V is great language'
	}
	second_article := Article {
		title: 'Second Post'
		text: 'Nothing special here'
	}
	sql app.db {
		insert first_article into Article
		insert second_article into Article
	}
	vweb.run(app, 8081)
}

['/index']
pub fn (app App) index() vweb.Result {
	articles := app.find_all_articles()
	return $vweb.html()
}

['/now']
pub fn (mut app App) now() vweb.Result {
	return app.text(time.now().format())
}
