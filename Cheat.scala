
/***********************************************************************
 * Cheat class
 * 
 * Implements the "cheat" verb
 * Show the location of all items in the world
 * 
 ********************************************************************** */


object Cheat {

    def create ():Verb = new Cheat()
}


class Cheat protected () extends Verb("cheat") {

    override def action ():Result = {

        def isPerson (t:Thing):Boolean = 
            t.checkPerson.isSome()
        def toPerson (t:Thing):Person = 
            t.checkPerson.valOf()

        Adventure.me().say("All of a sudden I feel all-seeing...")

        var things:List[Thing] = List.empty()
        
        for (room <- Adventure.world()) {
            val notPeopleTemp = room.things().filter(x => !isPerson(x))
            val peopleTemp = room.things().filter(isPerson).map(toPerson)

            for (thing <- notPeopleTemp)
                Adventure.me().say("  " + thing.name() + 
                                   " is in " + thing.location().name())
            
            for (person <- peopleTemp)
                for (thing <- person.things())
                    Adventure.me().say("  " + thing.name() + 
                                       " is in " + thing.location().name())
        }
        Result.REMAIN
    }
}
