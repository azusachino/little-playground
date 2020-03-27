interface A {
  id:string
  print(s:string):void
}

class Ax implements A {
  id: string;

  print(s: string): void {
    console.log(s)
  }

}

let a = new Ax()
a.id = ""+123
a.print(a.id)
