
/***********************************************************************
 * Container trait
 * 
 * Trait for artifacts that hold other artifacts
 *
 */


trait Container {

    def name ():String
    def checkPerson ():Option[Person]
    def checkRoom ():Option[Room]

    private var contents : List[Thing] = List.empty()
    def things ():List[Thing] = contents
    def haveThing (t:Thing):Boolean = contents.find(t)

    def addThing (t:Thing):Unit = 
        contents = List.cons(t,contents)

    def delThing (t:Thing):Unit = {
        def notSame (c:Thing):Boolean = (c != t)
            contents = contents.filter(notSame)
    }
}
