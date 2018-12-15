package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State
import lectures.part3filesystem.files.Directory

class Rm(name: String) extends Command {
  override def apply(state: State): State = {
    //1. Get working directory
    val wd = state.wd
    //2. Get the absolute path
    val absolutePath = {
      if (name.startsWith(Directory.ROOT_PATH)) name
      else if (wd.isRoot) wd.path + name
      else wd.path + Directory.SEPARATOR + name
    }
    //3. Do some checks
    if (Directory.ROOT_PATH.equals(absolutePath)) {
      state.setMessage("Nuclear war not supported yet!")
    } else doRm(state, absolutePath)

  }

  def doRm(state: State, path: String): State = {
    //TODO: remember to implement findDescendant(String)
    def rmHelper(currentDirectory: Directory, path: List[String]): Directory = {
      if (path.isEmpty) currentDirectory
      else if (path.tail.isEmpty) currentDirectory.removeEntry(path.head)
      else {
        val nextDirectory = currentDirectory.findEntry(path.head)
        if (!nextDirectory.isDirectory) currentDirectory
        else {
          val newNextDirectory = rmHelper(nextDirectory.asDirectory, path.tail)
          if (newNextDirectory == nextDirectory) currentDirectory
          else currentDirectory.replaceEntry(path.head, newNextDirectory)
        }
      }
    }

    val tokens = path.substring(1).split(Directory.SEPARATOR).toList
    val newRoot: Directory = rmHelper(state.root, tokens)

    if (newRoot == state.root) {
      state.setMessage(path + ": no such file or direectory")
    } else {
      State(newRoot, newRoot.findDescendent(state.wd.path.substring(1)))
    }
  }
}
