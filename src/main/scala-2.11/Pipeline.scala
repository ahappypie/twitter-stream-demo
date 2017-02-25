import akka.actor.{ActorSystem, Props}
import twitter4j.auth.{AccessToken, Authorization, RequestToken}
import twitter4j.conf.Configuration
import twitter4j._

/**
  * Created by Brian on 2/24/17.
  */
object Pipeline extends App {
  implicit val system = ActorSystem()
  implicit val executionContext = system.dispatcher

  val stream: TwitterStream = new TwitterStreamFactory().getInstance()
  val listener: StatusListener = new StatusListener {
    override def onStallWarning(warning: StallWarning): Unit = {
      println("stall warning")
    }

    override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {
      println("deletion notice")
    }

    override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {
      println("scrub geo")
    }

    override def onStatus(status: Status): Unit = {
      println("got status")
      if(status.getLang.equals("en") && !status.isRetweet) {
        system.actorOf(Props[Dispatcher]) ! status
      }
      else {
        println("status rejected")
      }
    }

    override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {
      println("limitation notice")
    }

    override def onException(ex: Exception): Unit = {
      println("generic error: " + ex.getMessage)
    }
  }

  val fq: FilterQuery = new FilterQuery()

  val keywords: Array[String] = Array("fijiwater", "fiji water", "FIJIWater", "FijiWater", "FIJI Water", "Fiji Water")

  fq.track(keywords)

  stream.addListener(listener)
  stream.filter(fq)

  sys.addShutdownHook {
    println("shutting down...")
    stream.cleanUp()
    stream.shutdown()
  }
}
