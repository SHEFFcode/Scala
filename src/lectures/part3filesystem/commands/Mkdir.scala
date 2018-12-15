package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State
import lectures.part3filesystem.files.{DirEntry, Directory}

class Mkdir(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = Directory.empty(state.wd.path, name)
}
