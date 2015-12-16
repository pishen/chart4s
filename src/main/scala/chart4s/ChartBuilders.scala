package chart4s

import play.api.libs.json._

object ChartBuilders {
  implicit class DoubleSeqConverter(seq: Seq[(String, Double)]) {
    def toLineChart() = {
      val json = Json.obj(
        "chartType" -> "line",
        "data" -> Json.obj(
          "labels" -> seq.map(_._1),
          "datasets" -> Seq(Json.obj(
            "data" -> seq.map(_._2),
            "fillColor" -> "transparent"
          ))
        ),
        "options" -> Json.obj()
      )
      new Chart(json)
    }
  }
  
  implicit class IntSeqConverter(seq: Seq[(String, Int)]) {
    def toLineChart() = seq.map { case (s, i) => (s, i.toDouble) }.toLineChart
  }
}
