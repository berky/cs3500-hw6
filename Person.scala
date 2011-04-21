
/***********************************************************************
 * Person class
 * 
 * Implements persons
 *
 ********************************************************************** */


object Person {

    def create (name:String, birthplace:Room):Person = {
        val obj = new Person(name,birthplace)
        obj.install()
        obj
    }
}


class Person protected (n:String,l:Room)
                            extends MobileThing(n,l) with Container {

    private val initialHealth = 3
    private var theHealth = initialHealth

    def health ():Int = theHealth

    def resetHealth ():Unit =
        theHealth = initialHealth

    def say (arg:String):Unit  = 
        // this checkRoom().valOf() should always succeed
        location().checkRoom().valOf().report("At " + location().name() + 
					      " " + name() + " says -- " + arg)

    def haveFit ():Unit =
        say("Yaaaaah! I am upset!")

    def peopleAround ():List[Person] = {
        def isPersonNotMe (t:Thing):Boolean = (t.checkPerson().isSome() &&
					       t.checkPerson().valOf() != this)
            def toPerson (t:Thing):Person = t.checkPerson().valOf()
        
        location().things().filter(isPersonNotMe).map(toPerson)
    }

    def stuffAround ():List[Thing] = {
        def isNotPerson (t:Thing):Boolean = t.checkPerson().isNone()
        location().things().filter(isNotPerson)
    }

    def peekAround ():List[Thing] = {
        def app (p:Person,l:List[Thing]):List[Thing] = p.things().append(l)
        peopleAround().foldr(app,List.empty[Thing]())
    }

    def take (t:Thing):Unit = {
        if (haveThing(t)) {
            this.say("I am already carrying " + t.name())
            return ()
        }
        if (t.checkPerson().isSome() || t.checkMobileThing().isNone()) {
            this.say("I try but cannot take " + t.name())
            return ()
        }

        val m : MobileThing = t.checkMobileThing().valOf()
        
        val owner : Container = m.location()
        say("I take " + m.name() + " from " + owner.name())
        if (owner.checkPerson().isSome())
	    owner.checkPerson().valOf().lose(m,this)
        else
            m.changeLocation(this)
    }


    def lose (m:MobileThing, loseTo:Container):Unit = {
        say("I lose " + m.name())
        this.haveFit()
        m.changeLocation(loseTo)
    }
    
    def drop (t:MobileThing):Unit = {
        this.say ("I drop " + t.name() + " at " + location().name())
        t.changeLocation(location())
    }

    def goExit (exit:Exit):Boolean =  exit.use(this)

    def go (direction:String):Boolean = {
        val exit : Option[Exit] = // this checkRoom().valOf() should always succeed
            location().checkRoom().valOf().exitTowards(direction)
        if (exit.isSome())
            exit.valOf().use(this)
        else {
            broadcast("No exit in " + direction + " direction")
            false
        }
    }

    def suffer (hits:Int):Unit = {
        say("Ouch! " + hits + " hits is more than I want!")
        theHealth = theHealth - hits
        if (theHealth < 0)
	    die()
        else
	    say("My health is now " + theHealth)
    }

    def die ():Unit = {
        for (item <- things())
            // the checkMobileThing().valOf() should always succeed
	    lose(item.checkMobileThing().valOf(),location())
        broadcast("An earth-shattering, soul-piercing scream is heard...")
        destroy()
    }

    def ask ():Unit = {
        say ("Don't bug me, I'm very busy right now!")
    }

    override def enterRoom ():Unit = {
        val people = peopleAround()
        if (!people.isEmpty())
            say("Hi " + Util.getNames(people))
    }
    
    override def checkPerson ():Option[Person] = Option.some(this)
}

