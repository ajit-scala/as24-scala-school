package unit

import java.io.{FileNotFoundException, ByteArrayOutputStream}

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

import scala.concurrent.{Await, Future}
import scala.io.Source
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

class ComposeFuturesSpec extends WordSpec with Matchers {

  "The Futures" should {

    "calculate the sum" in {

      //val (f1,f2,f3) = (Future {1}, Future {2}, Future {3})
      val lst:List[Future[Int]] = List (Future {1}, Future {2}, Future {3}, Future {4})

      // TODO Exercise 4 - calculate the sum of all futures, e.g., using 'static' helper methods in Future companion object
      val sum = Future.fold(lst) (0)(_+_)
      val sum1 = Future.sequence(lst).map (_.sum)

      Await.result(sum, 1 second) should be(10)
    }

  }

}
