package chart4s

import play.api.BuiltInComponents
import play.api.inject.NewInstanceInjector
import play.api.inject.SimpleInjector
import play.api.routing.Router
import play.core.server._
import play.api.routing.sird._
import play.api.mvc._
import play.twirl.api._
import scala.io.Source
import akka.actor._

class ChartServer() {
  val components = new NettyServerComponents with BuiltInComponents {
    implicit val app = application
    val hub = actorSystem.actorOf(Props[Hub], "hub")
    
    lazy val router = Router.from {
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
      case GET(p"/socket") => WebSocket.acceptWithActor[String, String] { request => out =>
        Client.props(out, hub)
      }
    }
    
    //BUG: https://groups.google.com/d/msg/play-framework/wHOH-MyEsfU/YneNmpL3wowJ
    override lazy val injector = new SimpleInjector(NewInstanceInjector) + router + crypto + httpConfiguration + actorSystem
  }
  val server = components.server
}
