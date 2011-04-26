
/***********************************************************************
 * Poke class
 * 
 * Implements the "poke" verb
 *
 ********************************************************************** */


object Poke {

    def create ():Verb = new Poke
}


class Poke protected extends Verb("poke") {

    override def action ():Result = {

        Result.REMAIN
    }

}
