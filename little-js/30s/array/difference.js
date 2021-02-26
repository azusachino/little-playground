/*Returns the difference between two arrays.

Create a Set from b, then use Array.prototype.filter() on a to only keep values not contained in b.*/

const difference = (a, b) => {
  const s = new Set(b)
  return a.filter(x => !s.has(x))
}

console.log(difference([1, 2, 3], [1, 2, 3, 4, [6]]))
