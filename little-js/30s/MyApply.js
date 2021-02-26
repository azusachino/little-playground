Function.prototype.myApply = function (context) {
  if (context === null || context === undefined) {
    context = window
  } else {
    context = Object(context)
  }

  function isArrayLike(o) {
    if (o && typeof o === 'object'
      && isFinite(o.length)
      && o.length >= 0
      && o.length === Math.floor(o.length)
      && o.length < 4294967296) {
      return true
    } else {
      return false
    }
  }

  context.testFn = this
  console.log(this)
  const args = arguments[1]
  let result
  if (args) {
    if (!Array.isArray(args) && !isArrayLike(args)) {
      throw new TypeError('myApply 第二个参数不为数组并且不为类数组对象抛出错误')
    } else {
      let arg = Array.from(args)
      console.log(arg)
      result = context.testFn(...arg)
    }
  } else {
    result = context.testFn()
  }
  delete context.testFn
  return result
}

console.log(Math.max.myApply(Math, [1, 2, 3, 4, 5]))

console.log(Math.max.myApply(Math, {1: '1', 2: '2', 5: '5', length: 3}))
