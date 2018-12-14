package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming} // notice we grouped the imports together

object PackagingAndImports extends App {
  // Packages are accessible by their simple name
  var writer = new Writer("Daniel", "RockTheJVM", 2018)

  // if you are using something from outside your package, you will have to import it
  val cinderella = new Princess // you can also say new payground.Cinderella (fully qualified name)

  // packages are ordered hierarchically, matching folder structure in the file system

  // package object
  sayHello // this comes from the package object

  // import
  val prince = new PrinceCharming

  // default imports, these get imported automatically withour your knowledge
  // these are java.lang - String, Object, Exception etc
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
