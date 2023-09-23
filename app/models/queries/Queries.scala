package models.queries

import java.time.LocalDate
import models.domain._
import models.repo._
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext
import javax.inject._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.Future
import java.util.UUID

@Singleton
final class Queries @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, val taskRepo: TaskRepo)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{
    import profile.api._


    def addTask(task: Task): Future[Int] = db.run(taskRepo.tasks += task)
    def deleteTask(id: UUID): Future[Int] = db.run(taskRepo.tasks.filter(_.id === id).delete)
    //def getTask(id: UUID): Future[Option[Task]] = db.run(taskRepo.tasks.filter(_.id === id).result.headOption)
    //def getTasks: Future[Seq[Task]] = db.run(taskRepo.tasks.result)
    //def updateTask(id: UUID, task: Task): Future[Int] = db.run(taskRepo.tasks.filter(_.id === id).update(task))
    //def getTasksByStatus(status: String): Future[Seq[Task]] = db.run(taskRepo.tasks.filter(_.status === status).result)
    //def getTasksByDate(date: LocalDate): Future[Seq[Task]] = db.run(taskRepo.tasks.filter(_.status === date.toString).result)

}