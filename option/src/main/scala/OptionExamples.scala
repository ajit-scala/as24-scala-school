object OptionExamples {

  //use map, flatmap, filter
  val listFunction = { a : Int => List(a + 1) }
  val optionFunction = { a : Int => Some(a + 1) }
  val filterFunction = { a : Int => a > 2 }

  def incrementList(aList: List[Int]): List[List[Int]] = aList.map(listFunction)
  
  def incrementAndFlattenList(aList: List[Int]): List[Int] = aList.flatMap(listFunction)
  
  def filterList(aList: List[Int]): List[Int] = aList.filter(filterFunction)

  def incrementOption(option: Option[Int]): Option[Option[Int]] = option.map(optionFunction)

  def incrementAndFlattenOption(option: Option[Int]): Option[Int] = option.flatMap(optionFunction)

  def filterOption(option: Option[Int]): Option[Int] = option.filter(filterFunction)
}

//option is a list of none, one or more elemnts
//specifies a function may return null