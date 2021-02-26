/*Creates a deep clone of an object.

Use recursion. Use Object.assign() and an empty object ({}) to create a shallow clone of the original. Use Object.keys() and Array.prototype.forEach() to determine which key-value pairs need to be deep cloned.*/

const deepClone = obj => {
  let clone = Object.assign({}, obj)
  Object.keys(clone).forEach(
    key => (clone[key] = typeof obj[key] === 'object' ? deepClone(obj[key]) : obj[key])
  )
  return Array.isArray(obj) && obj.length ?
    (clone.length = obj.length) && Array.from(clone) : Array.isArray(obj) ? Array.from(obj) : clone
}

const a = {foo: 'bar', obj: {a: 1, b: 2}}
const b = {foo: 'bar', obj: {a: 1, b: 2}, arr: [1, 2, 3]}

console.log(deepClone(a))
console.log(deepClone(b))
console.log(Object.assign({}, b))
