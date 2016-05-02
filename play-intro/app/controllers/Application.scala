package controllers

import java.io.ByteArrayOutputStream
import javax.inject.Inject

import io.github.cloudify.scala.spdf.{Landscape, Pdf, PdfConfig}
import models.Calculator
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import play.twirl.api.Html

class Application @Inject()(implicit val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action(Ok)

  //TODO add your controller methods here
 def circumferenceBad = Action(Status(400))


  def circumference(r:Double) = Action {
    Ok(Html(Calculator.circumference(r).toString))
  }

  def pdf = Action {
    val pdf = Pdf(new PdfConfig {
      orientation := Landscape
      pageSize := "Letter"
      marginTop := "1in"
      marginBottom := "1in"
      marginLeft := "1in"
      marginRight := "1in"
    })

    val page = <html><body><h1>Hello World</h1></body></html>

    // Save the PDF generated from the above HTML into a Byte Array
    val outputStream = new ByteArrayOutputStream
    //Ok(pdf.run(page, outputStream))
    Ok(outputStream)
  }

}
