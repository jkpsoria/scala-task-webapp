package models.domain

import java.util.UUID
import play.api.libs.json._


case class Task(id: UUID, title: String, desc: String, status: String)

object Task {
    // implicit val formatter: Format[Task] = Json.format[User]
    val tupled = (apply: (UUID, String, String, String) => Task).tupled
    def apply(title: String, desc: String, status: String): Task = apply(UUID.randomUUID(), title, desc, status)
}