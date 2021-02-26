/*拷贝源函数:

通过变量储存源函数
使用Object.create复制源函数的prototype给fToBind
返回拷贝的函数
调用拷贝的函数：

new调用判断：通过instanceof判断函数是否通过new调用，来决定绑定的context
绑定this+传递参数
返回源函数的执行结果
*/
Function.prototype.myBind = function (objThis, ...params) {
  const thisFn = this
  let fnToBind = function (...secondParams) {
    const isNew = this instanceof fnToBind
    const context = isNew ? this : Object(objThis)
    return thisFn.apply(context, ...params, ...secondParams)
  }
  fnToBind.prototype = Object.create(thisFn.prototype)
  return fnToBind
}
