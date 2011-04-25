
/***********************************************************************
 * Player class
 * 
 * Implements the player character
 *
 ********************************************************************** */


object Player {

    def create (name:String, birthplace:Room):Player = {
        val obj = new Player(name,birthplace)
        obj.install()
        obj
    }
}


class Player protected (n:String,l:Room) extends Person(n,l) {

    /* Grab any kind of thing from player's location, 
     * given its name.  The thing may be in the possession of
     * the place, or in the possession of a person at the place.
     */

    def thingNamed (name:String):Thing = {
        val all = location().things().append(peekAround()).append(things())
        def sameName (t:Thing):Boolean = (t.name()==name)
            val l = all.filter(sameName)
        l.length() match {
            case 0 => throw new Util.ArtNotFoundException(name)
            case 1 => l.first()
            case _ => throw new Util.ArtNotFoundException(name + " (ambiguous)")
        }
    }


    def lookAround ():Unit = {
        // this checkRoom().valOf() should always succeed
        val place = location().checkRoom().valOf()
        val exits = place.exits()
        val otherPeople = peopleAround()
        val myStuff = things()
        val allStuff = stuffAround()

        println("------------------------------------------------------------")
        println("You are in " + place.name())

        if (myStuff.isEmpty())
            println("You are not holding anything")
        else
            println("You are holding: " + Util.getNames(myStuff))

        if (allStuff.isEmpty())
            println("There is nothing in the room")
        else
            println("You see: " + Util.getNames(allStuff))

        if (otherPeople.isEmpty())
            println("There are no other people around you")
        else
            println("You see people: " + Util.getNames(otherPeople))
        
        if (exits.isEmpty())
            println("There are no exits")
        else
            println("The exits are in directions: " + Util.getNames(exits))
    }


    override def die ():Unit = {
        say("I am slain!")
        super.die()
        println("(I am afraid this game is over for you)")
        System.exit(0)
    }
    
}
