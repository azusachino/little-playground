/*
* Removes elements from the end of an array until the passed function returns true. Returns the remaining elements in the array.

Loop through the array, using Array.prototype.slice() to drop the last element of the array until the returned value from the function is true. Returns the remaining elements.*/

const dropRightWhile = (arr, fn) => {
  let rightIndex = arr.length
  while (rightIndex-- && !fn(arr[rightIndex])) ;
  return arr.slice(0, rightIndex + 1)
}

dropRightWhile([1, 2, 3, 4], n => n < 3); // [1, 2]
console.log(dropRightWhile([1, 2, 3, 4], n => n < 3))
