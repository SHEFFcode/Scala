package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State

class Cat(fileName: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd

    val dirEntry = wd.findEntry(fileName)
    if (dirEntry == null || !dirEntry.isFile) state.setMessage(fileName + ": no such file")
    else state.setMessage(dirEntry.asFile.contents)
  }
}
