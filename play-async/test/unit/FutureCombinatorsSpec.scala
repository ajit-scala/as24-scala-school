package unit

import java.io.FileNotFoundException

import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.io.{BufferedSource, Source}
import scala.concurrent.ExecutionContext.Implicits.global

class FutureCombinatorsSpec extends WordSpec with Matchers {

  def fileBy(name: String): Future[BufferedSource] = Future {
    Source.fromFile(name)
  }

  def asyncToUpperCase(s : String) : Future[String] = Future {
    s.toUpperCase
  }

  def getHelloWordFile() = fileBy("test/HelloWorld.txt")

  // TODO Exercise 3 - use combinator (instance) methods flatMap, map, and recover provided by Future
  def getHelloWorld() :  Future[String] = getHelloWordFile().map(s=>s.mkString)//passing

  def getHelloWorldUpperCase() : Future[String] = getHelloWorld().flatMap(s=>asyncToUpperCase(s))//passing function

  def getUndefinedFile() : Future[String] = fileBy("test/undefined.txt").map(_.mkString).recover{
    case ex: FileNotFoundException => "I have recovered"
    case _ => "default"
  }



  "The Future" should {

    "return 'Hello Word!'" in {

      val helloWorldFuture  = getHelloWorld()

      Await.result(helloWorldFuture, 1 second) should be("Hello World!")

    }

    "return 'HELLO WORLD!'" in {

      val helloWorldFuture = getHelloWorldUpperCase()

      Await.result(helloWorldFuture, 1 second) should be("HELLO WORLD!")

    }

    "return 'Hello Word! in failure case'" in {

      val undefinedFile = getUndefinedFile()

      Await.result(undefinedFile, 1 second) should be("I have recovered")

    }
  }
}
