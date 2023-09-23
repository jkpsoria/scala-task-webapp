package models.repo

import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import models.domain._
import java.util.UUID

@Singleton
final class TaskRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{

    import profile.api._

    protected class TaskTable (tag: Tag) extends Table[Task](tag, "TASKS") {
        //provide the necessary columns
        def id = column[UUID]("TASK_ID", O.PrimaryKey)
        def title = column[String]("TASK_TITLE", O.Length(100))
        def desc = column[String]("TASK_DESCRIPTION")
        def status = column[String]("TASK_STATUS")

        def * = (id, title, desc, status).mapTo[Task]
    }


    val tasks = TableQuery[TaskTable]

    def createTaskSchema = {
      db.run(tasks.schema.createIfNotExists)
    }




}