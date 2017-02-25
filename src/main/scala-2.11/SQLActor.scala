import java.sql.Timestamp

import akka.actor.Actor
import akka.actor.Actor.Receive
import slick.driver.MySQLDriver.api._


/**
  * Created by Brian on 2/24/17.
  */
class SQLActor extends Actor {

  import context.dispatcher

  val db = Database.forConfig("mysqldb")

  val sentiment = TableQuery[SentimentTable]

  override def receive: Receive = {
    case ps: ProcessedStatus => db.run(sentiment += ps) onFailure {
      case f => println(f)
    }
  }

}
