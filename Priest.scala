
/***********************************************************************
 * Priest class
 * 
 * Implements priests
 * Randomly creates new homeworks
 *
 ********************************************************************** */


object Priest {

    def create (name:String,birthplace:Room,speed:Int,mana:Int):Priest = {
        val obj = new Priest(name,birthplace,speed,mana)
        obj.install()
        obj
    }
}


class Priest (n:String,l:Room,s:Int,mana:Int) extends AutonomousPerson(n,l,s,10) {

    override protected def install ():Unit = {
        super.install()
        Adventure.clock().register(magicHomeworkAction,50)
    }   

    private def magicHomeworkAction (i:Int):Unit = magicHomework()

    protected def magicHomework ():Unit = {
        if (Util.random(mana) == mana) {
            say("WOLOLO!")
            UnfinishedHomework.create("magical-hw",l)
        }
    }
}


