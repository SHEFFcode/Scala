package lectures.part3filesystem.FileSystem

import java.util.Scanner

import lectures.part3filesystem.commands.Command
import lectures.part3filesystem.files.Directory

object FileSystem extends App {
  val root = Directory.ROOT
  // [1, 2, 3, 4] and u want the sum of all of these numbers
  /*
  this is fold left:
  0 (operator) 1 => 1
  1 (operator) 2 => 3
  3 (operators) 3=> 6
  6 (operator) 4 => 10
  List(1, 2, 3, 4).foldLeft(0)((x, y) => x + y)
   */

  //io.Source.stdin gets a collection, from which we get an iterator and apply foldLeft method
  //def foldLeft[B](z: B)(op: (B, A) ⇒ B): B
  io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
    currentState.show
    Command.from(newLine).apply(currentState)
  })

  /*
  We don't need the items below because of the io.Source refactoring
   */
  //  var state = State(root, root)
  //  val scanner = new Scanner(System.in)
  //
  //  while (true) {
  //    state.show
  //    val input = scanner.nextLine()
  //    state = Command.from(input).apply(state)
  //    print(scanner.nextLine())
  //  }

}
