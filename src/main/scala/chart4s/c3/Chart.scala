package chart4s.c3

import better.files._
import java.awt.Desktop
import play.api.libs.json._
import scalatags.Text.all._
import scalatags.Text.tags2

trait Chart {
  def json: JsValue

  def draw(): Unit = {
    val htmlFile = File.newTemporaryFile(suffix = ".html")

    val content = "<!DOCTYPE html>" + html(
      head(
        tags2.title("chart4s"),
        link(rel := "stylesheet", href := "https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css")
      ),
      body(
        div(id := "chart", data.chart.json := Json.stringify(json)),
        script(src := "https://d3js.org/d3.v3.min.js"),
        script(src := "https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"),
        script("c3.generate(JSON.parse(document.getElementById('chart').dataset.chartJson))")
      )
    )

    htmlFile.overwrite(content)
    Desktop.getDesktop.browse(htmlFile.uri)
  }
}
