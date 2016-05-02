object ImplicitConversions {

  case class RichString(str: String) {
    def toUpper: String = str.toUpperCase
  }

  //TODO: Exercise 1 - provide implicit conversions from String to RichString anc vice versa
  implicit def stringToRichString(str: String):  RichString = new RichString(str)
  implicit def RichStringString(rs: RichString):  String = rs.toUpper
  //implicit def RichStringString1(rs: RichString) = rs.toUpper

}
