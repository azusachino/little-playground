/*Counts the occurrences of a value in an array.

Use Array.prototype.reduce() to increment a counter each time you encounter the specific value inside the array.*/

const countOccurrences = (arr, val) => arr.reduce((acc, v) => (v === val ? acc + 1 : acc), 0)


console.log(countOccurrences([1, 2, 3, 4, 1, 1, 1], 1))
