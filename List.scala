
object List {

    def empty[T] ():List[T] = new ListEmpty[T]()

    def cons[T] (t:T, L:List[T]):List[T] = new ListCons[T](t,L)


    private class ListEmpty[T] () extends List[T] {
        def isEmpty ():Boolean = true
        def first ():T = 
            throw new RuntimeException("empty().first()")
        def rest ():List[T] = 
            throw new RuntimeException("empty().rest()")
        def nth (n:Int):T = 
            throw new RuntimeException("empty().nth()")

        def length ():Int = 0
        def append (M:List[T]):List[T] = M
        def isEqual (l:List[T]):Boolean = 
            l.isEmpty()

        def filter (p:(T)=>Boolean):List[T] = this

        def map[U] (f:(T)=>U):List[U] = List.empty()

        def foldr[U] (f:(T,U)=>U,b:U):U = b

        def foreach (f:(T)=>Unit):Unit = ()

            def toStringLst ():String = ""

        override def toString ():String = ""
        override def hashCode ():Int = 
            41
        override def equals (other:Any):Boolean = other match {
            case that:List[_] => that.isEmpty()
            case _ => false
        }
    }


    private class ListCons[T] (n:T, L:List[T]) extends List[T] {
        def isEmpty ():Boolean = false
        def first ():T = n
        def rest ():List[T] = L
        def nth (i:Int):T = 
            if (i==1)
	        n
            else 
	        L.nth(i-1)

        def length ():Int = 1 + L.length()
        def append (M:List[T]):List[T] = List.cons(n,L.append(M))
        def isEqual (l:List[T]):Boolean = {
            (!l.isEmpty()) && (l.first()==n) && (l.rest().isEqual(L))
        }

        def filter (p:(T)=>Boolean):List[T] = 
            if (p(n))
	        List.cons(n,L.filter(p))
            else 
	        L.filter(p)

        def map[U] (f:(T)=>U):List[U] = List.cons(f(n),L.map(f))

        def foldr[U] (f:(T,U)=>U,b:U):U = f(n,L.foldr(f,b))

        def foreach (f:(T)=>Unit):Unit = { f(n); L.foreach(f) }

        def toStringLst ():String = "," + n + L.toStringLst()

        override def toString ():String = n + L.toStringLst()
        override def hashCode ():Int = 
            41 * (
	        41 + n.hashCode()
            ) + L.hashCode()
        override def equals (other:Any):Boolean = other match {
            case that:List[_] => {
	        (!that.isEmpty()) && (that.first()==n) && (that.rest()==L)
            }
            case _ => false
        }
    }
}


abstract class List[T] {

    def isEmpty ():Boolean
    def first ():T
    def rest (): List[T]
    def length ():Int
    def append (M:List[T]):List[T]
    def isEqual (l:List[T]):Boolean
    def nth (n:Int):T
    def filter (p:(T)=>Boolean):List[T]
    def map[U] (f:(T)=>U):List[U]
    def foldr[U] (f:(T,U)=>U,b:U):U
    def foreach (f:(T)=>Unit):Unit

    def find (f:T):Boolean = 
        !(filter((x:T) => f==x).isEmpty())

    def filterFirst (p:(T)=>Boolean):Option[T] = {
        val l = filter(p)
        if (l.isEmpty())
            Option.none()
        else
            Option.some(l.first())
    }

    protected def toStringLst ():String
}
