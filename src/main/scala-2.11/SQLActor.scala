import java.sql.Timestamp

import akka.actor.Actor
import akka.actor.Actor.Receive
import slick.driver.MySQLDriver.api._

import scala.util.{Failure, Success}


/**
  * Created by Brian on 2/24/17.
  */
class SQLActor extends Actor {

  import context.dispatcher

  val sentiment = TableQuery[SentimentTable]

  override def receive: Receive = {
    case ps: ProcessedStatus =>   val db = Database.forConfig("mysqldb")
      db.run(sentiment += ps) onComplete( {
        case Success(s) => db.close()
        case Failure(f) => println(f)
          db.close()
      })
  }

}
