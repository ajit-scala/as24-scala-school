package exercise03

/* --------------- Exercise 03 -------------
 * TODO allow orderings over supertypes of A in ImmutableList.min
 * Hint: introduce a type parameter with a lower bound to method ImmutableList.min
 */
trait ImmutableList[A] {
  def prepend(el: A): ImmutableListItem[A] = ImmutableListItem(el, this)

  def contains(el: A): Boolean = this match {
    case ImmutableListEnd() => false
    case ImmutableListItem(value, tail) => if (value == el) true else tail.contains(el)
  }

  def min(cmp: Ordering[A]): Option[A] = this match {
    case ImmutableListEnd() => None
    case ImmutableListItem(value, tail) => tail.min(cmp) match {
      case None => Some(value)
      case Some(min) => if (cmp.lt(value, min)) Some(value) else Some(min)
    }
  }
}

case class ImmutableListEnd[A]() extends ImmutableList[A]

case class ImmutableListItem[A](value: A, tail: ImmutableList[A]) extends ImmutableList[A]

object ImmutableList {
  def findSimilar[T <: Similar](el: T, list: ImmutableList[T]): Boolean = list match {
    case ImmutableListEnd() => false
    case ImmutableListItem(value, tail) => if (el.isSimilar(value)) true else findSimilar(el, tail)
  }
}

trait Similar {
  def isSimilar(x: Any): Boolean
}

case class MyInt(x: Int) extends Similar {
  def isSimilar(m: Any): Boolean = m.isInstanceOf[MyInt] && m.asInstanceOf[MyInt].x == x
}
