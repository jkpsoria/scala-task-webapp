package models.repo

import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import models.domain._
import java.util.UUID
import java.time.LocalDateTime

@Singleton
final class TaskRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{

    import profile.api._

    protected class TaskTable (tag: Tag) extends Table[Task](tag, "TASKS") {
        
        def id = column[UUID]("TASK_ID", O.PrimaryKey)
        def title = column[String]("TASK_TITLE", O.Length(100))
        def desc = column[String]("TASK_DESCRIPTION")
        def status = column[String]("TASK_STATUS")
        // def createdOn = column[Option[String]]("TASK_CREATED_ON")
        def deadline = column[Option[String]]("TASK_DEADLINE")

        def * = (id, title, desc, status, deadline).mapTo[Task]
    }


    val tasks = TableQuery[TaskTable]

    def createTaskSchema = {
      db.run(tasks.schema.createIfNotExists)
    }




}