/*Results in a boolean representation of a specific date.

Pass the specific date object firstly. Use Date.getDay() to check weekday by using a modulo operator and then returning a boolean.*/

const isWeekDay = (t = new Date()) => {
  return t.getDay() % 6 != 0
}

const isWeekend = (t = new Date()) => {
  return t.getDay() % 6 === 0;
};

console.log(isWeekDay())
isWeekend(); // 2018-10-19 (if current date is 2018-10-18)
