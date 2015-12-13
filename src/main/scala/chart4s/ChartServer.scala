package chart4s

import play.core.server._
import play.api.routing.sird._
import play.api.mvc._
import play.twirl.api._
import scala.io.Source

class ChartServer() {
  val server = NettyServer.fromRouter() {
    case GET(p"/") => Action {
      val htmlStr = Source.fromInputStream(getClass().getResourceAsStream("/template.html")).getLines().mkString
      val html = Html(htmlStr)
      Results.Ok(html)
    }
    case GET(p"/template.js") => Action {
      val jsStr = Source.fromInputStream(getClass().getResourceAsStream("/template.js")).getLines().mkString("\n")
      val js = JavaScript(jsStr)
      Results.Ok(js)
    }
  }
}
