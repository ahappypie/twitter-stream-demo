import akka.actor.{Actor, PoisonPill, Props}
import akka.actor.Actor.Receive
import twitter4j.Status

/**
  * Created by Brian on 2/24/17.
  */
class Dispatcher extends Actor {


  override def receive: Receive = {
    case status: Status => context.actorOf(Props[SentimentActor]) ! status
    case ps: ProcessedStatus => context.actorOf(Props[SQLActor]) ! ps
      sender ! PoisonPill
  }
}
