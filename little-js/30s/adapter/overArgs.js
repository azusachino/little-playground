/*Creates a function that invokes the provided function with its arguments transformed.

Use Array.prototype.map() to apply transforms to args in combination with the spread operator (...) to pass the transformed arguments to fn.*/

const overArgs = (fn, transforms) => (...args) => fn(...args.map((val, i) => transforms[i](val)))

const square = n => n * n
const double = n => n * 2
const fn = overArgs((x, y) => [x, y], [square, double])
console.log(fn(9, 3))
