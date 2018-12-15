package lectures.files

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def hasEntry(name: String): Boolean = {
    ???
  }

  override def asDirectory: Directory = this

  def getAllFoldersInPath: List[String] = path.substring(1).split(Directory.SEPARATOR).toList

  def findDescendent(path: List[String]): Directory = ???

  def addEntry(newEntry: DirEntry): Directory = ???

  def findEntry(entryName: String): DirEntry = ???

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory = ???
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")
  def empty (parentPath: String, name: String): Directory = new Directory(parentPath, name, List())
}