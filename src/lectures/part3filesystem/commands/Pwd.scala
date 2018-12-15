package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State

class Pwd extends Command {
  override def apply(state: State): State = state.setMessage(state.wd.path)
}
