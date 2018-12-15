package lectures.commands
import lectures.FileSystem.State

class UknownCommand extends Command {
  override def apply(state: State): State = state.setMessage("Command not found!")
}
