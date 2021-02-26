/*Check if a date is the same as another date.

Use Date.prototype.toISOString() and strict equality checking (===) to check if the first date is the same as the second one.*/

const isSameDate = (dateA, dateB) =>
  dateA.toISOString() === dateB.toISOString()

isSameDate(new Date(2010, 10, 20), new Date(2010, 10, 20)); // true
