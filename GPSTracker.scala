
/***********************************************************************
 * GPSTracker class
 *
 * ...
 *
 ********************************************************************** */


object GPSTracker {

    def create (name:String,location:Container):GPSTracker = {
        val obj = new GPSTracker(name,location)
        obj.install()
        obj
    }
}


class GPSTracker protected (n:String,l:Container) 
    extends MobileThing(n,l) with Usable {

    def isPerson[T] (a:T):Boolean = 
        a match {
            case _:Person => true
            case _        => false
        }

    def personCast (ar:Thing):Person =
        ar match {
            case per:Person => per
            case _          => 
                throw new RuntimeException("downcast to Person failed")
        }

    def personList (tl:List[Thing]):List[Person] = 
        if (tl.isEmpty())
            List.empty[Person]()
        else if (isPerson(tl.first()))
            List.cons[Person](personCast(tl.first()),
                              personList(tl.rest()))
        else
            personList(tl.rest())

    def use (user:Person):Unit = {
        
        Adventure.me().say("I fiddle with the buttons on the " + n)
        
        var pairs:List[Pair[Person,Room]] = List.empty()

        for (room <- Adventure.world()) {
            val peopleTemp = personList(room.things())
            for (person <- peopleTemp) {
                pairs = List.cons(Pair.create(person, room), pairs)
            }
        }
        
        for (pair <- pairs)
            Adventure.me().say("  " + pair.first().name() + 
                               " is in " + pair.second().name())
    }

    override def checkUsable ():Option[Usable] = Option.some(this)

}
