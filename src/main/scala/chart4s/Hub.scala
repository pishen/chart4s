package chart4s

import akka.actor._
import Hub._

class Hub extends Actor {
  var clients = Set.empty[ActorRef]
  
  def receive = {
    case AddClient(client) =>
      clients += client
    case RemoveClient(client) =>
      clients -= client
    case Broadcast(send) =>
      clients.foreach(_ ! send)
  }
}

object Hub {
  case class AddClient(client: ActorRef)
  case class RemoveClient(client: ActorRef)
  case class Broadcast(send: Client.Send)
}
