/*
Creates a function that invokes each provided function with the arguments it receives and returns the results.

Use Array.prototype.map() and Function.prototype.apply() to apply each function to the given arguments.
*/

const over = (...fns) => (...args) => fns.map(fn => fn.apply(null, args))

const minMax = over(Math.max, Math.min, Math.cos)
console.log(minMax(1, 2, 3, 4, 5))
