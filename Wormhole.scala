
/***********************************************************************
 * Wormhole class
 * 
 * Implements the "wormhole" verb
 * sends the player to a random room
 *
 ********************************************************************** */


object Wormhole {

    def create ():Verb = new Wormhole
}


class Wormhole protected extends Verb("wormhole") {

    override def action ():Result = {
        Adventure.me().wormhole()
        Result.ADVANCE
    }
}
