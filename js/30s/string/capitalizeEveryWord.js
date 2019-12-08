/*Capitalizes the first letter of every word in a string.

Use String.prototype.replace() to match the first character of each word and String.prototype.toUpperCase() to capitalize it.*/

const capitalizeAll = str =>
    str.replace(/\b[a-z]/g, char => char.toUpperCase())

capitalizeAll('hello world!'); // 'Hello World!'
