package util

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object FileUtil {
  def stripFileContentInPlace(f: File): File = {
    val stripped = readFile(f)
      .map(_.strip)
      .mkString("", "\n", "")
    writeToFile(f) { writer => writer.write(stripped) }
    f
  }

  def stripFileContent(f: File): Iterator[String] = {
    readFile(f).map(_.strip())
  }

  def writeToFile(f: File)(op: java.io.PrintWriter => Unit) {

    val p = new java.io.PrintWriter(f)
    try { op(p) }
    finally { p.close() }
  }

  def readFile(f: File): Iterator[String] = {
    val source = io.Source.fromFile(f)
    source.getLines
  }
}
