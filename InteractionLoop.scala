
/***********************************************************************
 * InteractionLoop class
 * 
 * The main interaction loop of the game, which executes player commands
 *
 ********************************************************************** */


//import java.util._
//import java.io._


object InteractionLoop extends Publisher[Array[String],Result] {

    private var verbs : List[Subscriber[Array[String],Result]] = List.empty()

    private def getPlayerInput ():Array[String] = {

        var response = ""

        print("\nWhat is thy bidding? ")
        try {
            response = readLine()
            if (response==null) {
                println ("\nPlease use 'quit' to leave")
                response=""
            } else
                response = response.trim()
        } catch {
            case ioe:java.io.IOException => {
                println("IO error reading from terminal\n");
                System.exit(1)
            }
        }
        response.split ("\\s++")
    }


    def subscribe (s:Subscriber[Array[String],Result]):Unit =
        verbs = List.cons(s,verbs)


    private def publish (data:Array[String]):Result = {
        var finalResult = Result.ERROR
        for (s <- verbs) {
            val r = s.notify(data)
            if (r==Result.REMAIN && finalResult!=Result.ADVANCE)
                finalResult = Result.REMAIN;
            if (r==Result.ADVANCE)
                finalResult = Result.ADVANCE;
        }
        finalResult
    }


    def run ():Unit =  {

        Adventure.me().lookAround()

        var lastResult = Result.REMAIN
        while (lastResult != Result.ADVANCE) {
            val response = getPlayerInput ()
            lastResult = publish(response)
            if (lastResult == Result.ERROR) 
                println("I do not understand your request")
        }
        Adventure.clock().tick();
        run()
    }
}
