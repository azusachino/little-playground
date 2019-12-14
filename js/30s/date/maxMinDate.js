/*Returns the maximum of the given dates.

Use the ES6 spread syntax with Math.max to find the maximum date value, new Date() to convert it to a Date object.*/

const maxDate = dates => new Date(Math.max(...dates))
const minDate = dates => new Date(Math.min(...dates));

const array = [
    new Date(2017, 4, 13),
    new Date(2018, 2, 12),
    new Date(2016, 0, 10),
    new Date(2016, 0, 9)
];
console.log(maxDate(array))
console.log(minDate(array))
