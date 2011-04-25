
/***********************************************************************
 * Verb class
 * 
 * Implements verb functionality
 *
 */


abstract class Verb (w:String) extends Subscriber[Array[String],Result] {

    private val theWord : String = Util.noSpaces(w)
    
    def word ():String = theWord

    /* the default implementation of a verb execution:
     check that the verb position is the verb we're working with
     if so, call the appropriate action() based on how many 
     arguments we provide (up to 2 arguments)
     */

    def notify (input:Array[String]):Result = {
        // println("input = " + input.mkString(","))
        if (input.length<1)
            Result.ERROR
        else 
            try {
	        if (input(0)==theWord) {
	            if (input.length==1)
	                action()
	            else if (input.length==2) 
	                action(Adventure.me().thingNamed(input(1)))
	            else if (input.length==3) {
	                val obj1 = Adventure.me().thingNamed(input(1))
	                val obj2 = Adventure.me().thingNamed(input(2))
	                action(obj1,obj2)
	            } else
	                Result.ERROR
	        } else
	            Result.ERROR
            } catch {
	        case e:Util.ArtNotFoundException => Result.ERROR
            }
    }
    
    def action ():Result = {
        println ("I do not know how to " + word())
        Result.REMAIN
    }
    
    def action (obj:Thing):Result = {
        println ("I do not know how to " + word() + " with " + obj.name())
        Result.REMAIN
    }
    
    def action (obj:Thing, indObj:Thing):Result = {
        println ("I do not know how to " + word() + " with " + 
	         obj.name() + " and " + indObj.name())
        Result.REMAIN
    }
}



