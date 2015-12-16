package chart4s

import akka.actor._
import Client._
import play.api.libs.json._

class Client(out: ActorRef, hub: ActorRef) extends Actor {
 
  hub ! Hub.AddClient(self)
  
  def receive = {
    case Send(json) =>
      out ! Json.stringify(json)
  }
  
  override def postStop() = {
    hub ! Hub.RemoveClient(self)
  }
}

object Client {
  def props(out: ActorRef, hub: ActorRef) = Props(new Client(out, hub))
  case class Send(json: JsValue)
}
