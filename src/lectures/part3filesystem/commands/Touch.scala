package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State
import lectures.part3filesystem.files.{DirEntry, File}

class Touch(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry = File.empty(state.wd.path, name)
}
