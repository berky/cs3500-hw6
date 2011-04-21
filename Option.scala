
object Option {

  def none[T] ():Option[T] = new OptNone[T]

  def some[T] (v:T):Option[T] = new OptSome[T](v)


  private class OptNone[T] extends Option[T] {

    def isNone ():Boolean = true

    def valOf ():T = 
      throw new RuntimeException("Option.none().valOf()")

    override def equals (other:Any):Boolean = other match {
      case that:Option[_] => that.isNone()
      case _ => false
    }

    override def hashCode ():Int = 41

    override def toString ():String = "-"
  }


  private class OptSome[T] (v:T) extends Option[T] {

    def isNone ():Boolean = false

    def valOf ():T = v

    override def equals (other:Any):Boolean = other match {
      case that:Option[_] => (that.isSome() && v==that.valOf())
      case _ => false
    }

    override def hashCode ():Int = 
      41 + v.hashCode()

    override def toString ():String = v.toString()
  }
}


abstract class Option[+T] {

  def isNone ():Boolean
  def valOf ():T

  def isSome():Boolean = (!isNone())
}
