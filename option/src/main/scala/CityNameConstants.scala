class CityNameConstants(cities: List[String]) {
  val citiesByFirst = cities.map(c => c.head -> c).toMap[Char, String]

  def uppercaseGerman(str: String) = if (str.contains('ß')) None else Some(str.toUpperCase())//alt+enter

  def getUsingMatch(c: Char): Option[String] = {
    val city = citiesByFirst.get(c)
    val uppercased = city match {
      case Some(str) => uppercaseGerman(str)
      case None => None
    }
    ?? // hint: return Some(...replace(' ', '_'))
    uppercased match {
      case None => None
        case Some(str) => Some(str.replace(' ', '_'))
    }
  }

  def getUsingMap(c: Char): Option[String] = {
    val city = citiesByFirst.get(c)
    val uppercased = city.flatMap(uppercaseGerman)
    uppercased.map(_.replace(' ', '_'))
  }

  def getUsingFor(c: Char): Option[String] = {
    for {
      city <- citiesByFirst.get(c)
      uppercased <- uppercaseGerman(city)
    } yield uppercased.replace(' ', '_')
  }

  // helper to make the exercise compile
  def ??(): Option[Nothing] = throw new NotImplementedError()
}