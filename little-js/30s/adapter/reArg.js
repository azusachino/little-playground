/*Creates a function that invokes the provided function with its arguments arranged according to the specified indexes.

Use Array.prototype.map() to reorder arguments based on indexes in combination with the spread operator (...) to pass the transformed arguments to fn.*/

const reArg = (fn, indexes) => (...args) => fn(...indexes.map(i => args[i]))

let reArged = reArg(function (a, b, c) {
  return [a, b, c]
}, [2, 0, 1])

console.log(reArged('b', 'c', 'a'))
