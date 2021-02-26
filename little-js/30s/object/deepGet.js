/*Returns the target value in a nested JSON object, based on the keys array.

Compare the keys you want in the nested JSON object as an Array. Use Array.prototype.reduce() to get value from nested JSON object one by one. If the key exists in object, return target value, otherwise, return null.*/

const deepGet = (obj, keys) =>
  keys.reduce((xs, x) => (xs && xs[x] ? xs[x] : null), obj)


let index = 2
const data = {
  foo: 'bar',
  bar: {
    baz: [1, 2, 3]
  }
}

console.log(deepGet(data, ['bar', 'baz', index]))
