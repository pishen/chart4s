package chart4s.c3

import chart4s.Decimal
import play.api.libs.json._

case class XYLineChart[X, Y](lines: Seq[(String, Seq[(X, Y)])])(implicit dx: Decimal[X], dy: Decimal[Y]) extends Chart {
  def json = Json.obj(
    "bindTo" -> "#chart",
    "data" -> Json.obj(
      "xs" -> JsObject(
        lines.map(_._1).zipWithIndex.toMap.mapValues(i => JsString(s"x$i"))
      ),
      "columns" -> JsArray(
        lines.zipWithIndex.flatMap {
          case ((label, line), i) =>
            Seq(
              JsArray(JsString(s"x$i") +: line.map(_._1).map(dx.toBigDecimal).map(JsNumber.apply)),
              JsArray(JsString(label) +: line.map(_._2).map(dy.toBigDecimal).map(JsNumber.apply))
            )
        }
      )
    )
  )
}
