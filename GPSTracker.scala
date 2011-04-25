
/***********************************************************************
 * GPSTracker class
 *
 * A Usable MobileThing that tells the location of every Person in
 * the world
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

    def use (user:Person):Unit = {

        def isPerson (t:Thing):Boolean = 
            t.checkPerson.isSome()
        def toPerson (t:Thing):Person = 
            t.checkPerson.valOf()
        
        Adventure.me().say("I fiddle with the buttons on the " + n)
        
        var pairs:List[Pair[Person,Room]] = List.empty()

        // for every room in the world, make a pair of 
        // every person in the room and the room itself
        for (room <- Adventure.world()) {
            
            val peopleTemp = room.things().filter(isPerson).map(toPerson)

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
