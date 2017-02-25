import java.sql.Timestamp

import slick.driver.MySQLDriver.api._
/**
  * Created by Brian on 2/24/17.
  */
case class ProcessedStatus(id: Long, created_at: Timestamp, user: String, text: String, sentiment: String)

class SentimentTable(tag: Tag) extends Table[ProcessedStatus](tag, "tweets") {
  def id = column[Long]("ID", O.PrimaryKey)
  def created_at = column[Timestamp]("created_at")
  def user = column[String]("user")
  def text = column[String]("text")
  def sentiment = column[String]("sentiment")
  def * = (id, created_at, user, text, sentiment) <> (ProcessedStatus.tupled, ProcessedStatus.unapply)
}
