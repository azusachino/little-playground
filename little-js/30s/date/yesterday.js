/*Results in a string representation of yesterday's date.

Use new Date() to get the current date, decrement by one using Date.getDate() and set the value to the result using Date.setDate(). Use Date.prototype.toISOString() to return a string in yyyy-mm-dd format.*/


const yesterday = () => {
  let t = new Date();
  t.setDate(t.getDate() - 1);
  return t.toISOString().split('T')[0];
};

console.log(yesterday())
