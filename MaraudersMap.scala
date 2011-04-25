
/***********************************************************************
 * MaurauderMap class
 * 
 * "cheat" and GPSTracker all rolled into one
 * 
 ********************************************************************** */


object MaraudersMap {

    def create (name:String,location:Container):MaraudersMap = {
        val obj = new MaraudersMap(name,location)
        obj.install()
        obj
    }
}


class MaraudersMap protected (n:String,l:Container) 
    extends Thing(n,l) with Usable {

    def use (user:Person):Unit = {

        Adventure.me().say("I solemnly swear that I am up to no good.")

        def isPerson (t:Thing):Boolean =
            t.checkPerson.isSome()
        def toPerson (t:Thing):Person = 
            t.checkPerson.valOf()

        var people:List[Pair[Person,Room]] = List.empty()
        var things:List[Pair[Thing,Container]] = List.empty()
        
        // iterate over rooms
        for (room <- Adventure.world()) {
            val peopleTemp = room.things().filter(isPerson).map(toPerson)
            val notPeopleTemp = room.things().filter(x => !isPerson(x))

            // gather people in every room
            for (person <- peopleTemp) 
                people = List.cons(Pair.create(person, room), people)
            
            // gather standalone things in rooms
            for (thing <- notPeopleTemp)
                things = List.cons(Pair.create(thing, room), things)

            // gather things on people
            for (person <- peopleTemp)
                for (thing <- person.things())
                    things = List.cons(Pair.create(thing, person), things)
            
        }

        // location of all people
        println("----------------------------------------")
        for (person <- people)
            Adventure.me().say("  " + person.first().name() + 
                               " is in " + person.second().name())
        
        // location of all other things
        println("----------------------------------------")
        for (thing <- things) 
            Adventure.me().say("  " + thing.first().name() + 
                               " is in " + thing.second().name())

        println("----------------------------------------")
        Adventure.me().say("Mischief managed.")

    }
  
    override def checkUsable ():Option[Usable] = Option.some(this)

}
