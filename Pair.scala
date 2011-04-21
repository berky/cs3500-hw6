
object Pair {

    def create[T,U] (f:T,s:U):Pair[T,U] = 
        new PairImpl[T,U](f,s)


    private class PairImpl[T,U] (f:T,s:U) extends Pair[T,U] {

        def first ():T = f
        def second ():U = s

        override def equals (other:Any):Boolean = other match {
            case p:Pair[_,_] => (p.first()==f && p.second()==s)
                case _ => false
        }
        
        override def hashCode ():Int = 
            41 * (
	        41 + f.hashCode()
            ) + s.hashCode()

        override def toString ():String = 
            "(" + f.toString() + "," + s.toString() + ")"
    }
}


abstract class Pair[T,U] {

    def first ():T
    def second ():U
}
