/*Returns a string with whitespaces compacted.

Use String.prototype.replace() with a regular expression to replace all occurrences of 2 or more whitespace characters with a single space.*/

const compactWhiteSpace = str =>
  str.replace(/\s{2,}/g, ' ')

compactWhitespace('Lorem    Ipsum'); // 'Lorem Ipsum'
compactWhitespace('Lorem \n Ipsum'); // 'Lorem Ipsum'
