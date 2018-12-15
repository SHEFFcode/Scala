package lectures.part3filesystem.files

import lectures.part3filesystem.FileSystem.FileSystemException

class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name)
{
  override def asDirectory: Directory = throw new FileSystemException("File cannot be converted to a directory")

  override def getType: String = "File"

  override def asFile: File = this
}

object File {
  def empty(parentPath: String, name: String): File = {
    new File(parentPath, name, "")
  }
}