// es5
let obj = {}
let newVal = ''

Object.defineProperty(obj, 'name', {
  get() {
    return newVal
  },
  set(val) {
    newVal = val
  }
})


obj.name = 'es'
console.log(obj.name)
console.log(newVal)

// proxy
let inner = {}
let p = new Proxy(inner, {})
p.name = 'immoc'
console.log(inner.name)

let arr = [1, 2, 3]
arr = new Proxy(arr, {
  get(target, prop) {
    return prop in target ? target[prop] : 'error'
  }
})
console.log(arr[4])

let userInfo = {
  username: "aa",
  age: 21,
  _password: "123"
}

userInfo = new Proxy(userInfo, {
  ownKeys(target) {
    return Object.keys(target).filter(k => !k.startsWith('_'))
  }
})

for (let k in userInfo) {
  console.log(k)
}
