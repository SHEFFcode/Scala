package lectures.part3filesystem.commands
import lectures.part3filesystem.FileSystem.State

class UknownCommand extends Command {
  override def apply(state: State): State = state.setMessage("Command not found!")
}
