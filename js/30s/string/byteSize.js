/*Returns the length of a string in bytes.

Convert a given string to a Blob Object and find its size.*/

const byteSize = str => new Blob([str]).size

byteSize('😀'); // 4
byteSize('Hello World'); // 11
console.log(byteSize("ヾ(•ω•`)o"))
