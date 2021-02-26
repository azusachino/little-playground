/*Removes elements in an array until the passed function returns true. Returns the remaining elements in the array.

Loop through the array, using Array.prototype.slice() to drop the first element of the array until the returned value from the function is true. Returns the remaining elements.*/

const dropWhile = (arr, fn) => {
  while (arr.length > 0 && !fn(arr[0])) {
    arr = arr.slice(1)
  }
  return arr
}

console.log(dropWhile([1, 2, 3, 4, 5, 6, 7], n => n > 5))  // [6,7]
