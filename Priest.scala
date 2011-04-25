
/***********************************************************************
 * Priest class
 * 
 * Implements priests
 * Converts Persons of one class to another
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

    def personDowncast (t:Thing):Unit = 
         t match {
             case tr:Troll => tr
             case pr:Professor => pr
             case pg:PrlGrad => pg
             case ps:Priest => ps
             case ap:AutonomousPerson => ap
             case p:Person => p
             case mt:MobileThing => mt
             case t:Thing => t
         }

    

}


