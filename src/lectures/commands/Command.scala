package lectures.commands

import lectures.FileSystem.State

trait Command {
  def apply(state: State): State // abstract method
}

object Command {
  def from(input: String): Command = new UknownCommand
}
