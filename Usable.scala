
/***********************************************************************
 * Usable trait
 * 
 * Trait for artifacts that can be used
 *
 */


trait Usable {

    def name ():String
    def use (user:Person):Unit
}
