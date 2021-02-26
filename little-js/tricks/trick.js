let type = (data) => {
  let toString = Object.prototype.toString();
  return data instanceof Element
    ? "element" : toString.call(data)
      .replace(/[object\s(.+)]/, "$1")
      .toLowerCase()
}

let listMap = (arr, type, fn) => !fn ? arr : Array.prototype[type]["call"](arr, fn)
listMap(document.querySelectorAll("div"), "forEach", e => console.log(e))
