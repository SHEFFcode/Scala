package lectures.commands

trait Command {
  def apply(state: State)
}
