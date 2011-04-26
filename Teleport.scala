
/***********************************************************************
 * Teleport class
 * 
 * Implements the "teleport" verb
 * sends the player to the given room
 *
 ********************************************************************** */

object Teleport {

    def create ():Verb = new Teleport
}

class Teleport protected extends Verb("teleport") {

    // find the room in the world with the given name
    def roomNamed (name:String):Room = {
        val all = Adventure.world()
        def sameName (r:Room):Boolean = (r.name() == name)
            val l = all.filter(sameName)
        l.length() match {
            case 0 => throw new Util.ArtNotFoundException(name)
            case 1 => l.first()
            case _ => throw new Util.ArtNotFoundException(name + " (ambiguous)")
        }
    }

    // find the person in the world with the given name
    def personNamed (name:String):Person = {
        val all = Adventure.people()
        def sameName (p:Person):Boolean = (p.name() == name)
            val l = all.filter(sameName)
        l.length() match {
            case 0 => throw new Util.ArtNotFoundException(name)
            case 1 => l.first()
            case _ => throw new Util.ArtNotFoundException(name + " (ambiguous)")
        }
    }

    override def notify (input:Array[String]):Result = {
        if (input.length < 1)
            Result.ERROR
        else
            try {
                if (input(0) == "teleport") {
                    if (input.length == 1)
                        action()
                    else if (input.length == 2)
                        action(roomNamed(input(1)))
                    else if (input.length == 3) {
                        val obj1 = personNamed(input(1))
                        val obj2 = roomNamed(input(2))
                        action(obj1,obj2)
                    }
                    else
                        Result.ERROR
                }
                    else
                        Result.ERROR
            }
        catch {
            case e:Util.ArtNotFoundException => Result.ERROR
        }
    }

    // "teleport" sans arguments -> "wormhole"
    override def action ():Result = {
        Adventure.me().wormhole()
        Result.ADVANCE
    }

    // "teleport <room>" sends you to the given room name
    def action (r1:Room):Result = {
        Adventure.me().changeLocation(r1)
        Result.ADVANCE
    }

    // "teleport <person> <room>" sends the given person to the given room
    // (works for player name too)
    def action (p:Person,r:Room):Result = {
        p.changeLocation(r)
        Result.ADVANCE
    }
}
