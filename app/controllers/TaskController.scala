package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}
import play.api.data._
import play.api.data.Forms._
import models.domain._
import models.repo._
import java.util.UUID
import play.api.libs.json.Json
import models.queries.Queries
import play.api.i18n.I18nSupport
import java.time.LocalDateTime
import play.api.data.format.Formats._ 

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class TaskController @Inject()(val controllerComponents: ControllerComponents, implicit val ec: ExecutionContext, taskRepo: TaskRepo, queries: Queries) extends BaseController  with I18nSupport{


  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  val taskForm = Form(
    mapping(
      "id" -> ignored(UUID.randomUUID()),
      "title" -> nonEmptyText,
      "desc" -> nonEmptyText,
      "status" -> default(text, "TODO"),
      // "createdOn" -> optional(text),
      "deadline" -> optional(text)
      
    )(Task.apply)(Task.unapply)
  )
  

  def index() = Action.async { implicit request =>
    taskRepo.createTaskSchema.flatMap { _ =>
      queries.getTasks.map { tasks =>
        Ok(views.html.index(tasks, taskForm))  
      }
    }
  }


  def create() = Action.async { implicit request =>
    taskForm.bindFromRequest().fold(
      formWithErrors => {
        Future.successful(BadRequest)
      },
      task => {
        queries.addTask(task.copy(id = UUID.randomUUID())).map(_ => Redirect(routes.TaskController.index()))
      }
    )
  }

  def show() = Action.async { implicit request =>
    queries.getTasks.map { task => Ok(task.mkString("\n"))
    }
  }

  def remove(id: UUID) = Action.async { implicit request =>
    queries.deleteTask(id).map(_ => Redirect(routes.TaskController.index()))
  }

  def sort() = Action.async { implicit request =>
    queries.sortByDeadline.map(_ => Redirect(routes.TaskController.index()))

  }
}
