object MathConstants {
  //conflicting double values, import specific or use explicitly
  implicit val PI = 3.1415901244
  //implicit val PIE = 2.1415901244
}

object ImplicitParameters {

  // TODO: Exercise 2 - make the test pass without importing Math.Constants here
  def calculateCircumference(radius: Double)(implicit piArg: Double) : Double = radius*2*piArg

}
