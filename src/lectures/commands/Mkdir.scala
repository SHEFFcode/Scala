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

  def doMkdir(currentState: State, str: String): State = {
    ???
  }
}
