/*Converts the given array elements into <li> tags and appends them to the list of the given id.

Use Array.prototype.map(), document.querySelector(), and an anonymous inner closure to create a list of html tags.*/

const arrayToHtmlist = (arr, listId) =>
  (el => (el = document.querySelector('#' + listId)),
    (el.innerHTML += arr.map(item => `<li>${item}</li>`).join('')))()

const arrayToHtmlList = (arr, listID) =>
  (el => (
    (el = document.querySelector('#' + listID)),
      (el.innerHTML += arr.map(item => `<li>${item}</li>`).join(''))
  ))();
console.log(arrayToHtmlList(['item1', 'item2'], 'mylistId'))
