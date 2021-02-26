Function.prototype.myCall = function (context, ...arg) {
  if (context === null || context === undefined) {
    context = window
  } else {
    context = Object(context)
  }
  context.testFn = this
  console.log(this)
  let result = context.testFn(...arg)
  delete context.testFn
  return result
}

console.log(Math.max.myCall(Math, 1, 2, 3, 4, 5))
