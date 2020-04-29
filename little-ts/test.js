var Ax = /** @class */ (function () {
    function Ax() {
    }
    Ax.prototype.print = function (s) {
        console.log(s);
    };
    return Ax;
}());
var a = new Ax();
a.id = "" + 123;
a.print(a.id);
