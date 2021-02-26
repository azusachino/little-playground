/*Accepts a converging function and a list of branching functions and returns a function that applies each branching function to the arguments and the results of the branching functions are passed as arguments to the converging function.

Use Array.prototype.map() and Function.prototype.apply() to apply each function to the given arguments. Use the spread operator (...) to call coverger with the results of all other functions.*/

const curry = (fn, arity = fn.length, ...args) =>
  arity <= args.length ? fn(...args) : curry.bind(null, fn, arity, ...args)

curry(Math.pow)(2)(10); // 1024
curry(Math.min, 3)(10)(50)(2); // 2

