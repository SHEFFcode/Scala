package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State
import lectures.part3filesystem.files.{Directory, File}

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

  def getRootAfterEcho(currentDirectory: Directory, path: List[String], contens: String, append: Boolean): Directory = {
    /*
    if path is empty, fail (return current directory)
    if path.tail is empty:
      find the file to create  or add content to
        if file not found, then create file
        else if its a directory, fail
        else
          append content to file
          replace the entry with file name with NEW file
      else find the next directory to navigate to
        get root after echo RECURSIVELY on that

        if recursive call fails, fail
        else replace entry with NEW directory after the recursive call
     */

    if (path.isEmpty) currentDirectory
    else if (path.tail.isEmpty) {
      val dirEntry = currentDirectory.findEntry(path.head)
      if (dirEntry == null) currentDirectory.addEntry(new File(currentDirectory.path, path.head, contens))
      else if (dirEntry.isDirectory) currentDirectory
      else
        if (append) currentDirectory.replaceEntry(path.head, dirEntry.asFile.appendContents(contens))
        else currentDirectory.replaceEntry(path.head, dirEntry.asFile.setContents(contens))
    } else {
      val nextDirectory = currentDirectory.findEntry(path.head).asDirectory // this will always be a valid directory because no absolute or relative paths supported
      val newNExtDirectory = getRootAfterEcho(nextDirectory, path.tail, contens, append)

      if (newNExtDirectory == nextDirectory) currentDirectory
      else currentDirectory.replaceEntry(path.head, newNExtDirectory)
    }
  }

  def doEcho(state: State, content: String, fileName: String, append: Boolean): State = {
    if (fileName.contains(Directory.SEPARATOR)) state.setMessage("Echo: filename must not contain separators!")
    else {
      val newRoot: Directory = getRootAfterEcho(state.root, state.wd.getAllFoldersInPath :+ fileName, content, append)
      if (newRoot == state.root) {
        state.setMessage(fileName + ": no such file!")
      } else {
        State(newRoot, newRoot.findDescendent(state.wd.getAllFoldersInPath))
      }
    }
  }
}
