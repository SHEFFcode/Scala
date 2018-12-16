package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State

import scala.annotation.tailrec

class Echo (args: Array[String]) extends Command {
  override def apply(state: State): State = {
    /*
    if no args, state
    else if just one arg, print to console
    else if multiple args
    {
      operator is > echo to a file (may create a file if not there)
      operator is >>  append to a file
      otherwise echo everything to console
    }
     */
    if (args.isEmpty) state
    else if (args.length == 1) state.setMessage(args(0))
    else {
      val operator = args(args.length - 2) // next to last argument
      val filename = args(args.length - 1) // the last argument
      val contents = createContent(args, args.length - 2) // top index in the args arrays

      if (">>".equals(operator)) {
        doEcho(state, contents, filename, append = true)
      } else if (">".equals(operator)) {
        doEcho(state, contents, filename, append = false)
      } else state.setMessage(createContent(args, args.length))
    }
  }
  // TOP INDEX is non inclusive
  def createContent(args: Array[String], topIndex: Int): String = {
    @tailrec
    def createContentHelper(currentIndex: Int, accumulator: String): String = {
      if (currentIndex >= topIndex) accumulator
      else createContentHelper(currentIndex + 1, accumulator + " " + args(currentIndex))
    }

    createContentHelper(0, "")
  }

  def doEcho(state: State, content: String, fileName: String, append: Boolean): State = ???
}
