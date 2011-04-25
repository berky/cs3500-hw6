
/***********************************************************************
 * Util class
 * 
 * Collection of utility functions
 *
 ********************************************************************** */


object Util {

    // a useful exception for when it is not possible to convert a name
    // into an artifact in the world

    class ArtNotFoundException (n:String) extends RuntimeException(n)

    // pick an integer at random in [1..incl]

    def random (incl:Int):Int = 
        util.Random.nextInt(incl)+1

    def pickRandom[U] (l:List[U]):U = 
        if (l.isEmpty())
            throw new RuntimeException ("pickRandom() on empty list");
        else
            l.nth(random(l.length()))
    

    def noSpaces (text:String):String = 
        return text.split("\\s++")(0).toLowerCase()

    // return a string containing all the names of the artifacts in the 
    // list separated by spaces
    def getNames[U<:Artifact] (l:List[U]):String = {
        def concat (art:U,s:String):String = art.name() + " " + s
        l.foldr(concat,"")
    }
}
