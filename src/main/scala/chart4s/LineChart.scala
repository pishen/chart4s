package chart4s

import play.api.libs.json._

class LineChart(json: JsObject) extends Chart(json) {
  def withLabel(label: String) = {
    val transformer = (__ \ "data" \ "json").json.copyFrom {
      (__ \ "data" \ "json").read[JsObject].map(json => Json.obj(label -> json.values.head))
    }
    new LineChart(json.transform(transformer).get)
  }
}
