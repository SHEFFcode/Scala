package lectures.part3filesystem.FileSystem

import java.util.Scanner

import lectures.part3filesystem.commands.Command
import lectures.part3filesystem.files.Directory

object FileSystem extends App {
  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
    print(scanner.nextLine())
  }

}
