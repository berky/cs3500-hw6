
/***********************************************************************
 * Troll class
 * 
 * Implements trolls
 *
 ********************************************************************** */


object Troll {

    def create (name:String,birthplace:Room,speed:Int,hunger:Int):Troll = {
        val obj = new Troll(name,birthplace,speed,hunger)
        obj.install()
        obj
    }
}


class Troll (n:String,l:Room,s:Int,hunger:Int) 
                    extends AutonomousPerson(n,l,s,10) {

    override protected def install ():Unit = {
        super.install()
        Adventure.clock().register(eatPeopleAction,10)
    }

    private def eatPeopleAction (i:Int):Unit = eatPeople()

    protected def eatPeople () {
        if (Util.random(hunger) == hunger) {
            val people : List[Person] = peopleAround()
            if (!people.isEmpty()) {
	        val victim : Person = Util.pickRandom(people)
                say("Koona t'chuta, " + victim.name() + "?")
	        report(name() + " takes a bite out of " + victim.name())
	        victim.suffer(Util.random(3))
            } else {
	        report(name() + "'s belly rumbles")
            }
        }
    }
}


