package chart4s

import play.api.libs.json._

object ChartBuilders {
  implicit class DoubleSeqConverter(seq: Seq[Double]) {
    def toLineChart = {
      val json = Json.obj("data" -> Json.obj(
        "json" -> Json.obj("line1" -> seq)
      ))
      new LineChart(json)
    }
  }

  implicit class IntSeqConverter(seq: Seq[Int]) {
    def toLineChart = seq.map(_.toDouble).toLineChart
  }

  implicit class DoubleMapConverter(map: Map[String, Double]) {
    def toPieChart = {
      val json = Json.obj("data" -> Json.obj(
        "json" -> Json.toJson(map),
        "type" -> "pie"
      ))
      new Chart(json)
    }
  }
  
  implicit class IntMapConverter(map: Map[String, Int]) {
    def toPieChart = map.mapValues(_.toDouble).toPieChart
  }
}
