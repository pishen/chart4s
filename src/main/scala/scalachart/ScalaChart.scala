package scalachart

import java.nio.file.{Files, Paths}
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._
import scala.io.Source
import sys.process._
import java.awt.Desktop


object ScalaChart {
  
  def startBrowser(data: JValue) = {
    val jsonStr = compact(render(("bindto" -> "#chart") ~ ("data" -> data)))
    val template = Source.fromInputStream(getClass.getResourceAsStream("/template.html")).mkString
    Files.write(Paths.get("template/index.html"), template.replaceAll("@js", s"c3.generate($jsonStr)").getBytes())
    "sensible-browser template/index.html".!
  }
  
  def line(seq: Seq[Int]) = {
    val data = "columns" -> Seq(JString("data1") +: seq.map(x => JInt(x)))
    startBrowser(data)
  }
  
  def pie(map: Map[String, Double]) = {
    val data = ("columns" -> map.toSeq.map{case (k, v) => Seq(JString(k), JDouble(v))}) ~ ("type" -> "pie")
    startBrowser(data)
  }
}
