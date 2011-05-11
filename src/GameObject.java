
/**
 * This interface serves as the base for all GameObjects in this Game.
 * GameObjects are any Object that exists within a <code>World</code> and can
 * be placed and removed from its enclosing World Object. Objects
 * implementing the <code>Movable</code> interface are also capable
 * of changing their position within the World they exist in. Objects
 * implementing the <code>Solid</code> interface prevent Movable
 * Object from moving into the area they occupy within a World.
 * 
 * @author Mihail Kurbako
 * @since April 4, 2011
 * @version 1.0.0.12 : April 7, 2011
 * @see Movable
 * @see Solid
 * @see AbstractGameObject
 * @see Player
 * @see Block
 * @see Engine
 *
 */
public interface GameObject 
{	
	/**
	 * This method is used to retrieve the current X coordinate
	 * of a specific GameObject. Implementing classes must
	 * override this method.
	 * 
	 * @return Returns the current X coordinate of this GameObject.
	 */
	int getX();
	/**
	 * This method is used to retrieve the distance of the point
	 * on this GameObject that is farthest away from it's origin.
	 * (The origin is it's current X coordinate). Implementing
	 * classes must override this method.
	 * 
	 * @return Returns the GameObject's vertical size.
	 */
	int getHeight();
	
	/**
	 * This method is used to retrieve the current Y coordinate
	 * of a specific GameObject. Implementing classes must
	 * override this method.
	 * 
	 * @return Returns the current Y coordinate of this GameObject.
	 */
	int getY();
	/**
	 * This method is used to retrieve the distance of the point
	 * on this GameObject that is farthest away from it's origin.
	 * (The origin is it's current Y coordinate). Implementing
	 * classes must override this method.
	 * 
	 * @return Returns the GameObject's horizontal size.
	 */
	int getWidth();
	
	Pole getPole();
	
	double getStrength();
	
	/**
	 * This method places the GameObject onto the specified grid,
	 * and assigns it's value to a field within the GameObject for
	 * future reference to the Object. Implementing classes must
	 * override this method.
	 * 
	 * @see World
	 * @param world The World Object into which this GameObject will be placed.
	 */
	void addSelfToWorld(World<GameObject> world);
	/**
	 * This method gives access to the stored value for the World
	 * Object that this gameObject exists within. Implementing
	 * classes must override this method.
	 * 
	 * @see World
	 * @return If the GameObject exists within a World, returns that World Object, otherwise <code>null</code>.
	 */
	World<GameObject> getEnclosingWorld();
	/**
	 * Removes the GameObject from the World Object that it's value
	 * is currently enclosed within. Also sets the value for the field
	 * that stores the current value for the World Object to <code>null</code>.
	 * If the GameObject is not in a World when this method is called,
	 * it will perform no action. Implementing classes must override 
	 * this method.
	 * 
	 * @see World
	 */
	void removeSelfFromWorld();
}
