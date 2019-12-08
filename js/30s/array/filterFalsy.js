/*Filters out the falsy values in an array.

Use Array.prototype.filter() to get an array containing only truthy values.*/

const filterFalsy = arr => arr.filter(Boolean)

filterFalsy(['', true, {}, false, 'sample', 1, 0]); // [true, {}, 'sample', 1]
