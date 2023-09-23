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

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, implicit val ec: ExecutionContext, taskRepo: TaskRepo) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request =>
    
    //access the taskRepo to create the schema
    taskRepo.createTaskSchema.map(_ => Ok)
  }
}
