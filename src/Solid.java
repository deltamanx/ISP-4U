/**
 * This is the Solid interface. It has no fields, and no methods.
 * It is implemented by GameObjects to signify to the game engine
 * that this GameObject is solid, and other GameObjects cannot move
 * onto the space that it occupies within its enclosing World
 * Object. Classes implementing Solid are still able to move onto
 * Objects that do not implement Solid, however non-Solid Objects
 * cannot move into the space occupied by a Solid Object. Two Solid
 * Objects cannot move onto one another. If 2 Solid Objects are
 * created in the same area, they will continue to function normally,
 * and if an event is raised that would cause one of them to move to
 * a location that is not occupied by a Solid Object, it will do
 * so normally, as the game engine checks the area that the Object
 * has chosen to move to, not its current location when performing
 * movement operations on GameObjects. 
 * 
 * @author Mihail Kurbako
 * @since April 7, 2011
 * @version 1.0.0.0 : April 7, 2011
 * @see GameObject
 * @see Engine
 * @see Movable
 */

public interface Solid
{

}
