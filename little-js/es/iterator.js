const arr = ['es5', "es6"]

arr[Symbol.iterator] = function () {
  let nextIndex = 0;
  return {
    next() {
      return nextIndex < arr.length ? {
        value: arr[nextIndex++],
        done: false
      } : {
        value: undefined,
        done: true
      }
    }
  }
}
