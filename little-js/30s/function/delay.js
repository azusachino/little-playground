/*
* Invokes the provided function after wait milliseconds.

Use setTimeout() to delay execution of fn. Use the spread (...) operator to supply the function with an arbitrary number of arguments.*/

const delay = (fn, wait, ...args) =>
  setTimeout(fn, wait, ...args)


delay(function (text) {
  console.log(text)
}, 1000, 'later')
