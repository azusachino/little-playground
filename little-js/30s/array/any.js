/*Returns true if the provided predicate function returns true for at least one element in a collection, false otherwise.

Use Array.prototype.some() to test if any elements in the collection return true based on fn. Omit the second argument, fn, to use Boolean as a default.*/

const any = (arr, fn = Boolean) => arr.some(fn)


console.log(any([2, 1, 1, 0], x => x > 2))
