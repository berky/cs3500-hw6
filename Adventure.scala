
/***********************************************************************
 * Adventure class
 * 
 * The main class for the simulation
 * Creates the virtual world, and launches the interactive loop
 *
 ********************************************************************** */


object Adventure {

    // let's hide all the fields together
    private object Vars {
        var me = Option.none[Player]()
        var clock = Option.none[Clock]()
        var world = List.empty[Room]()
        var godMode = false
    }
    
    def me ():Player = 
        if (Vars.me.isSome())
            Vars.me.valOf()
        else
            throw new RuntimeException("Player uninitialized")

    def clock ():Clock = 
        if (Vars.clock.isSome())
            Vars.clock.valOf()
        else
            throw new RuntimeException("Clock uninitialized")

    def world ():List[Room] = Vars.world

    def addRoom (r:Room):Unit = 
        Vars.world = List.cons(r,Vars.world)

    def isGodMode ():Boolean = Vars.godMode

    def toggleGodMode ():Unit = 
        if (Vars.godMode)
            Vars.godMode = false
        else
            Vars.godMode = true
    
    // build and return a list of all people in the world
    def people ():List[Person] = {
        def isPerson (t:Thing):Boolean =
            t.checkPerson.isSome()
        def toPerson (t:Thing):Person = 
            t.checkPerson.valOf()

        var worldPeople:List[Person] = List.empty()
        
        for (room <- world()) {
            val peopleTemp = room.things().filter(isPerson).map(toPerson)
            for (person <- peopleTemp)
                worldPeople = List.cons(person, worldPeople)
        }

        return worldPeople
    }

    // build and return a list of all things in the world
    def worldThings ():List[Thing] = {
        def isPerson (t:Thing):Boolean = 
            t.checkPerson.isSome()
        def toPerson (t:Thing):Person = 
            t.checkPerson.valOf()

        var worldThings:List[Thing] = List.empty()

        for (person <- people())
            for (thing <- person.things())
                worldThings = List.cons(thing, worldThings)
        
        for (room <- world())
            for (thing <- room.things().filter(x => !isPerson(x)))
                worldThings = List.cons(thing, worldThings)
    
        return worldThings
    }

    /*
     * A helpful auxiliary method to connect places on the map
     *
     */
    private def connect (from:Room, direction:String, 
		         reverseDirection:String, to:Room):Unit = {
        Exit.create(from,to,direction)
        Exit.create(to,from,reverseDirection)
    }
    
    
    private def createWorld (myName:String):Unit = {

        val wvh328 = Room.create("riccardo-office")
        val wvh108 = Room.create("wvh-108")
        val wvh1st = Room.create("wvh-first-floor")
        val wvhlab = Room.create("wvh-computer-lab")
        val wvh2nd = Room.create("wvh-second-floor")
        val wvh202 = Room.create("cs-office")
        val wvh3rd = Room.create("wvh-third-floor")
        val wvhprl = Room.create("prl-lab")
        val matthiaslair = Room.create("matthias-lair")
        val currycenter = Room.create("curry-center")
        val marinocenter = Room.create("marino-center")
        val abp = Room.create("au-bon-pain")
        val centennial = Room.create("centennial-common")
        val snell = Room.create("snell-library")

        val wvg = Room.create("wvg")
        val willis = Room.create("willis-hall")
        val behrakis = Room.create("behrakis")
        val lake = Room.create("lake-hall")
        val knowles = Room.create("knowles-center")
        val shillman = Room.create("shillman-hall")
        val ell = Room.create("ell-hall")
        val krentzman = Room.create("krentzman-quad")
        val speare = Room.create("speare-hall")
        
        // EXERCISE 0
        val hurtig = Room.create("hurtig-hall")
        val wvh346 = Room.create("pete-office")
        val wvh322 = Room.create("viera-office")
        
        connect(hurtig, "south", "north", snell)
        connect(wvh346, "west", "east", wvh3rd)
        connect(wvh322, "east", "west", wvh3rd)
	
        connect(wvh328, "south", "north", wvh3rd)
        connect(wvh3rd, "south", "north", wvhprl)
        connect(wvhprl, "south", "north", matthiaslair)
        connect(wvh3rd, "down", "up", wvh2nd)
        connect(wvh2nd, "east", "west", wvh202)
        connect(wvh2nd, "down", "up", wvh1st)
        connect(wvh1st, "south", "north", wvh108)
        connect(wvh1st, "west", "east", wvhlab)
        connect(wvh1st, "north", "south", knowles)
        connect(knowles, "east", "west", lake)
        connect(lake, "south", "north", wvg)
        connect(wvg, "east", "west", behrakis)
        connect(behrakis, "north", "south", willis)
        connect(lake, "east", "west", willis)
        connect(wvh1st, "east", "west", wvg)
        connect(knowles, "north", "south", currycenter)
        connect(knowles, "west", "east", marinocenter)
        connect(snell, "west", "east", currycenter)
        connect(centennial, "west", "east", snell)
        connect(centennial, "south", "north", shillman)
        connect(marinocenter, "south", "north", abp)
        connect(currycenter, "west", "east", ell)
        connect(ell, "west", "east", krentzman)
        connect(krentzman, "west", "east", speare)
        connect(speare, "south", "north", marinocenter)

        // The player is the first 'thing' that has to be created
        Vars.me = Option.some(Player.create(myName, Util.pickRandom(world())))
        
        Computer.create("hal-9000", wvhlab)
        Computer.create("johnny-5", wvhprl)
        Thing.create("blackboard", wvh108)
        Thing.create("lovely-trees", centennial)
        MobileThing.create("cs-book", snell)
        MobileThing.create("math-book", snell)
        MobileThing.create("htdp", matthiaslair)

        // EXERCISE 0
        Laptop.create("eric-mbp", hurtig)
        Thing.create("comfy-chair", abp)
        Thing.create("not-so-comfy-chair", wvh346)
        MobileThing.create("chem-book", hurtig)
        MobileThing.create("banana", lake)
        GPSTracker.create("lcars", matthiaslair)
        GPSTracker.create("iphone", me())
        MaraudersMap.create("marauders-map", me())

        val homeworks = Array("hw-1",
			      "hw-2",
			      "hw-3",
			      "hw-4",
			      "hw-5",
			      "hw-6",
                              "hw-7", 
                              "hw-8", 
                              "hw-9", 
                              "hw-10")

        for (homework <- homeworks)
            UnfinishedHomework.create(homework,
				      Util.pickRandom(world()))

        val students = Array("frankie-freshman",
			     "joe-junior",
			     "sophie-sophomore",
			     "cedric-senior")

        for (student <- students) 
            AutonomousPerson.create(student,
			            Util.pickRandom(world()),
			            Util.random(5), Util.random(5))

        val graders = Array("nick","jake")
        
        for (grader <- graders)
            Grader.create(grader,
        		  Util.pickRandom(world()),
        		  Util.random(5),
        		  Util.random(5))

        val professors = Array("riccardo","viera","pete")

        for (professor <- professors)
            Professor.create(professor,
		             Util.pickRandom(world()),
		             Util.random(5),
		             Util.random(5))

        val prlGrads = Array("sam",
                             "carl")

        for (prlGrad <- prlGrads)
            PrlGrad.create(prlGrad,
		           Util.pickRandom(world()),
		           Util.random(5), Util.random(5))

        val trolls = Array("felix",
                           "matthias")

        for (troll <- trolls)
            Troll.create(troll,
		         Util.pickRandom(world()),
		         Util.random(3), Util.random(3))
    }
    
    
    def createVocabulary (p:Publisher[Array[String],Result]):Unit = {
        p.subscribe(Quit.create())
        p.subscribe(Seppuku.create())
        p.subscribe(God.create())
        p.subscribe(Look.create())
        p.subscribe(Wait.create())
        p.subscribe(Teleport.create())
        p.subscribe(Wormhole.create())
        p.subscribe(Take.create())
        p.subscribe(Drop.create())
        p.subscribe(Give.create())
        p.subscribe(Use.create())
        p.subscribe(Ask.create())
        p.subscribe(DirectionVerb.create("north"))
        p.subscribe(DirectionVerb.create("south"))
        p.subscribe(DirectionVerb.create("east"))
        p.subscribe(DirectionVerb.create("west"))
        p.subscribe(DirectionVerb.create("up"))
        p.subscribe(DirectionVerb.create("down"))
        p.subscribe(Cheat.create())
    }


    private def printTickAction (t:Int):Unit = 
        println("The clock ticks " + t)
    

    // 
    // The entry point for the game
    //
    
    def main (argv:Array[String]):Unit = {

        if (argv.size > 0 && argv(0).equals("--god"))
            Vars.godMode = true
        else
            Vars.godMode = false
        
        Vars.clock = Option.some(Clock.create(0))
        
        println("The CS 3500 Adventure Game, version 1.3.1 (Spring 2011)")

        // Register an action to be performed at every turn
        clock().register(printTickAction,1)

        // Create the world (name of the player as an argument)
        createWorld("eric")
        
        // Create the vocabulary to control artifacts
        createVocabulary(InteractionLoop)

        InteractionLoop.run()
    }
    
}

