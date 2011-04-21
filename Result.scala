
object Result {

    val ERROR : Result = new Impl(0)
    val REMAIN : Result = new Impl(1)
    val ADVANCE : Result = new Impl(2)

    private abstract class ResultWithIndex extends Result {

        protected def index ():Int
    }

    private class Impl (i:Int) extends ResultWithIndex {

        def index ():Int = i

        override def toString ():String = "Result("+i+")"

        override def equals (other:Any):Boolean = other match {
            case that:ResultWithIndex => i==that.index()
            case _ => false
        }

        override def hashCode ():Int = 41 + i
    }
}


abstract class Result
