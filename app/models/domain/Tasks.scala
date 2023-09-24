package models.domain

import java.util.UUID
import play.api.libs.json._
import java.time.LocalDateTime


case class Task(id: UUID, title: String, desc: String, status: String = "TODO", deadline: Option[String])

object Task {
    // implicit val formatter: Format[Task] = Json.format[User]
    val tupled = (apply: (UUID, String, String, String, Option[String]) => Task).tupled
    def apply(title: String, desc: String, status: String, deadline: Option[String]): Task = apply(UUID.randomUUID(), title, desc, status, deadline)
}