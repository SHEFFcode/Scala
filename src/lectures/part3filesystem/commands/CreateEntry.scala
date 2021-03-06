package lectures.part3filesystem.commands

import lectures.part3filesystem.FileSystem.State
import lectures.part3filesystem.files.{DirEntry, Directory}

abstract class CreateEntry(name: String) extends Command {
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
      doCreateEntry(state, name)
    }

  }

  def checkIllegal(str: String) = {
    name.contains(".")
  }

  def doCreateEntry(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head)
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry.asDirectory, path.tail, newEntry))
      }
    }

    val wd = state.wd

    // 1. get all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath
    // 2. create new directory entry in the working directory
    //TODO: implement this
    val newEntry: DirEntry = createSpecificEntry(state)
//    val newDir = Directory.empty(wd.path, name)
    // 3. update the whole directory structure starting from the root
    // (the directory structure is IMMUTABLE) one of the principals of functional programming.
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)
    // 4. find new working directory INSTANCE given wd's full path in the NEW directory structure
    val newWd = newRoot.findDescendent(allDirsInPath)

    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State): DirEntry
}
