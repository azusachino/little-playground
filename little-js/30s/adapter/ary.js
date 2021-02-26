/*Creates a function that accepts up to n arguments,
ignoring any additional arguments.
Call the provided function, fn, with up to n arguments,
using Array.prototype.slice(0,n) and the spread operator (...).*/

//获取给定数量的变量
const ary = (fn, n) => (...args) => fn(...args.slice(0, n));

const firstTwoMax = ary(Math.max, 2);

console.log([[2, 6, 'a'], [8, 4, 6], [10]].map(x => firstTwoMax(...x))); // [6,8,10]
