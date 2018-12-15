package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State
import lectures.part3filesystem.files.{DirEntry, Directory}

import scala.annotation.tailrec

class Cd(dir: String) extends Command {
  override def apply(state: State): State = {
    /*
    This will support cd absolute path:
    cd /something/somethingElse/...
    And relative path to current working dir:
    cd a/b/c...

    Part 2 will support going into cd. && cd .. etc
     */

    // 1. Fine the root
    val root = state.root
    val wd = state.wd

    //2. Find absolute path of the directory I want to cd to
    val absolutePath = {
      if (dir.startsWith(Directory.SEPARATOR)) "/"
      else if (wd.isRoot) wd.path + dir
      else wd.path + Directory.SEPARATOR + dir
    }

    //3. Find the directory to cd into, given a path

    val destinationDirectory = doFindEntry(root, absolutePath)

    //4. Change the state given the name of the new directory
    if (destinationDirectory == null || !destinationDirectory.isDirectory) {
      state.setMessage(dir + ": no such directory")
    } else {
      State(root, destinationDirectory.asDirectory)
    }

  }
    def doFindEntry(root: Directory, path: String): Directory = {
      @tailrec
      def findEntryHelper(currentDirecotry: Directory, path: List[String]): DirEntry = {
        if (path.isEmpty || path.head.isEmpty) currentDirecotry
        else if (path.tail.isEmpty) currentDirecotry.findEntry(path.head)
        else {
          val nextDir = currentDirecotry.findEntry(path.head)
          if (nextDir == null || !nextDir.isDirectory) null
          else findEntryHelper(nextDir.asDirectory, path.tail)
        }
      }

      //1. Tokens
      val tokens: List[String] = path.substring(1).split(Directory.SEPARATOR).toList
      //2. Navigate to the correct entry
      findEntryHelper(root, tokens).asDirectory
    }

}
