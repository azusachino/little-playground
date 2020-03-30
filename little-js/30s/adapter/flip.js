/*
* Flip takes a function as an argument, then makes the first argument the last.
Return a closure that takes variadic inputs,
and splices the last argument to make it the first argument before applying the rest.
* */


const flip = fn => (first, ...rest) => fn(...rest, first)

let a = {name: 'John Smith'}
let b = {}
const mergeForm = flip(Object.assign)
let mergePerson = mergeForm.bind(null, a)
mergePerson(b)
b = {}
Object.assign(b, a) // == b

