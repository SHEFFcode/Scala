package lectures.part3filesystem.commands

import lectures.part3filesystem.FileSystem.State

trait Command {
  def apply(state: State): State // abstract method
}

object Command {
  val MKDIR = "mkdir"
  val LS = "ls"

  def emptyCommand: Command = new Command {
    override def apply(state: State): State = state
  }
  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(name + ": incomplete command!")
  }


  def from(input: String): Command = {
    val tokens = input.split(" ") // arrays of String holding all the tokens

    if (input.isEmpty || tokens.isEmpty) emptyCommand
    else if (MKDIR.equals(tokens(0))) {
      if (tokens.length < 2) incompleteCommand(MKDIR)
      else new Mkdir(tokens(1))
    } else if (LS.equals(tokens(0))) {
      new Ls
    } else new UknownCommand
  }
}
