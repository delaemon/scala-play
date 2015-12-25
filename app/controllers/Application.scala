package controllers

import play.api._
import play.api.mvc._
import play.api.db.DB
import anorm._

DB.withConnection { implicit c =>
  val result: Boolean = SQL("SELECT 1").execute()
}

class Application extends Controller {

  def index = Action {
    val selectCountries = SQL("SLECT * FROM test")

    val data = selectCountries().map(row =>
      row[Integer]("id") -> row[String]("name")
    ).toList
    Ok(data[0].name)
  }

  def page = Action {
      Ok(views.html.index("Your new application is ready."))
  }
}
