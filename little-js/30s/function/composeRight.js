/*Performs left-to-right function composition.

Use Array.prototype.reduce() to perform left-to-right function composition. The first (leftmost) function can accept one or more arguments; the remaining functions must be unary.*/

const composeRight = (...fns) => fns.reduce((f, g) => (...args) => g(f(...args)))

const add5 = x => x + 5;
const multiply = (x, y) => x * y;
const multiplyAndAdd5 = composeRight(
  add5,
  multiply
);
const add = (x, y) => x + y;
const square = x => x * x;
const addAndSquare = composeRight(add, square);
multiplyAndAdd5(5, 2); // 15
console.log(multiplyAndAdd5(2, 5))
console.log(addAndSquare(2, 5))
