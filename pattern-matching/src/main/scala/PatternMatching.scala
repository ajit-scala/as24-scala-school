// examples inspired by http://danielwestheide.com/
object PatternMatching {

  trait User {
    def name: String
    def score: Int
  }

  case class FreeUserCC(name: String, score: Int, upgradeProbability: Double) extends User

  case class PremiumUserCC(name: String, score: Int) extends User

  class FreeUser(val name: String, val score: Int, val upgradeProbability: Double) extends User

  class PremiumUser(val name: String, val score: Int) extends User

  object FreeUser{
    def apply(name: String, score: Int, upgradeProbability: Double): FreeUser = new FreeUser(name, score, upgradeProbability)
    def unapply(arg: FreeUser): Option[(String, Int, Double)] = Some((arg.name,arg.score,arg.upgradeProbability))
  }
  object PremiumUser{
    def apply(name: String, score: Int):PremiumUser=new PremiumUser(name, score)
    def unapply(arg: PremiumUser): Option[(String, Int)] = Some((arg.name, arg.score))
  }

  def personalizedGreeting(user: User): String = user match {
    case FreeUserCC(name,_,1.0) => s"${name}, what can we do for you today?"
    case fu:FreeUserCC => s"Hello ${fu.name}"
    case pu:PremiumUserCC => s"Welcome back, dear ${pu.name}"
    case FreeUser(name,_,1.0) => s"${name}, what can we do for you today?"
    case fu:FreeUser => s"Hello ${fu.name}"
    case pu:PremiumUser => s"Welcome back, dear ${pu.name}"
  }
  def personalizedGreeting2(user: User): String = user match {
    case PremiumUserCC(_,_) | PremiumUser(_,_) => s"Welcome back, dear ${user.name}"
  }

  def freePremiumForThirdUser(s: Seq[User]): Option[String] = s match {
    case _ :: _ :: FreeUserCC(n,_, up) :: _ if up<0.3d => Some(s"Congratulations, $n, you won a free premium membership!")
    case _ => None
  }

  def reverse(s: List[User]): List[User] = s match {
    case premiumUser :: potentialPremiumUser :: freeUser :: Nil => freeUser :: potentialPremiumUser :: premiumUser :: Nil
    case _ => Nil
  }
}
