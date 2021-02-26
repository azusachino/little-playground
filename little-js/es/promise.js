function test() {
  let p1 = new Promise(((resolve, reject) => {
    setTimeout(() => {
      console.log("p1 resolve")
      resolve('haha')
    }, 1000)
  })).then(
    res => {
      console.log(res)
    }
  )
  let p2 = new Promise(((resolve, reject) => {
    setTimeout(() => {
      console.log("p2 resolve")
      resolve('hehe')
    }, 1000)
  })).then(
    res => {
      console.log(res)
    }
  )

  Promise.all([p1, p2]).then(res => {
    console.log(res)
  })

  Promise.race([p1, p2]).finally(() => {
    console.log("done")
  })

}

const symbol = Symbol("a")
const courses = {
  "a": {
    "1": 1,
    "2": 2
  },
  "b": {
    "1": 1,
    "2": 2
  },
  "c": {
    "1": 1,
    "2": 2
  }
}

courses[Symbol.iterator] = () => {

  return ({
    "": ""
  })
}

for (let c of courses) {
  console.log(c)
}
