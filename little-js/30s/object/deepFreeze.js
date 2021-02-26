/*Deep freezes an object.

Calls Object.freeze(obj) recursively on all unfrozen properties of passed object that are instanceof object.*/

const deepFreeze = obj =>
  Object.keys(obj).forEach(
    prop => !(obj[prop] instanceof Object || Object.isFrozen(obj[prop]) ? null : deepFreeze(obj[prop])) || Object.freeze(obj))

const o = deepFreeze([1, [2, 3]])

o[0] = 3
