package lectures.commands
import lectures.FileSystem.State
import lectures.files.Directory

class Mkdir(name: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry " + name + " already exists!")
    } else if (name.contains(Directory.SEPARATOR)) {
      // MKDIR something/something else is forbidden
      state.setMessage(name + " must not contain separators!")
    } else if (checkIllegal(name)) {
      state.setMessage(name + ": is illegal entry name!")
    } else {
      doMkdir(state, name)
    }

  }

  def checkIllegal(str: String) = {
    name.contains(".")
  }

  def doMkdir(currentState: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: Directory): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head)
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry.asDirectory, path.tail, newEntry))
      }
    }

    val wd = currentState.wd

    // 1. get all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath
    // 2. create new directory entry in the working directory
    val newDir = Directory.empty(wd.path, name)
    // 3. update the whole directory structure starting from the root
    // (the directory structure is IMMUTABLE) one of the principals of functional programming.
    val newRoot = updateStructure(currentState.root, allDirsInPath, newDir)
    // 4. find new working directory INSTANCE given wd's full path in the NEW directory structure
    val newWd = newRoot.findDescendent(allDirsInPath)

    State(newRoot, newWd)
  }
}
