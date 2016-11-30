package forcomp

case class Talk(title: String, themes: Set[String], time: Time)

case class Track(talks: Seq[Talk]) {

  /** all talks in this track on the theme */
  def talksOn(theme: String): Seq[Talk] = talks.filter(_.themes.contains(theme))

  /** the talk in this track at the given time */
  def talkAt(time: Time): Option[Talk] = talks.find(_.time == time)

  /** all themes of talks in this track */
  def themes: Set[String] = talks.to[Set].flatMap(_.themes)

}

case class Schedule(tracks: Set[Track]) {

  // all talks
  def talks: Set[Talk] = tracks.flatMap(_.talks)

  // all talks at the given time on the theme
  def talksOn(time: Time, theme: String): Set[Talk] = for {
    track <- tracks
    talk  <- track.talkAt(time)
    if talk.themes.contains(theme)
  } yield talk

  // counts how often a theme occurs overall
  def themeCount: Map[String, Int] =
  for {
    (theme, list) <-  talks.to[Seq].flatMap(_.themes).groupBy(identity)
  } yield (theme, list.size)

  // ranking of themes in descending order of occurrence in this schedule.
  def themeRank: List[String] =
  themeCount.to[List].sortBy(_._2).reverse.map(_._1)
}

/**
  * helper class representing time as hours and minutes.
  */
case class Time(hours: Int = 0, minutes: Int = 0) {

  override def toString = f"$hours%02d:$minutes%02d"

}
