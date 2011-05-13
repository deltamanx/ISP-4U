/**
 * The Movable interface is implemented by GameObjects
 * to give them functionality and capabilities required
 * for the Objects to move, and signify to the Engine that
 * this Object can move. Classes should extends AbstractGameObject
 * rather than implementing this interface.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornikov
 * @version 1.0.0.1 : April 10, 2011
 * @since April 9, 2011
 * @see GameObject
 * @see AbstractMovable
 */
public interface Movable extends GameObject
{
	/**
	 * Sets this GameObjects x and y coordinate value
	 * to the given values. The Object will be moved next
	 * time the Engine processes the GameObject.
	 * 
	 * @param x The new value for this GameObject's X coordinate.
	 * @param y The new value for this GameObject's Y coordinate.
	 */
	void moveTo(int x, int y);
	/**
	 * Checks whether this GameObject can move to the
	 * specified coordinates. This will always return false
	 * if the given coordinates are beyond the edge of the
	 * GameObject's enclosing World.
	 * 
	 * @param x The potential value for the GameObject's X coordinate.
	 * @param y The potential value for the GameObject's Y coordinate.
	 * @return <code>true</code> if it is able, <code>false</code> otherwise.
	 */
	boolean canMoveTo(int x, int y);
	
	/**
	 * This method adjusts speed based on gravity. Implementing
	 * classes must override this method.
	 */
	public void countGravity();
	
	/**
	 * Adjusts speed based on magnetic attraction to given object. Implementing
	 * classes must override this method.
	 * 
	 * @param obj the object to calculate attraction towards.
	 */
	public void countMagnetism(GameObject obj);
	
	/**
	 * The accessor for horizontal the speed of the object. Included here for calling purposes.
	 * Implementing classes must override this method. 
	 * 
	 * @return the horizontal speed of the object.
	 */
	public double getXSpeed();
	
	/**
	 * The mutator for horizontal the speed of the object. Included here for calling purposes.
	 * Implementing classes must override this method. 
	 * 
	 * @param speed the new horizontal speed.
	 */
	public void setXSpeed(double speed);
	
	/**
	 * The accessor for vertical the speed of the object. Included here for calling purposes.
	 * Implementing classes must override this method. 
	 * 
	 * @return the vertical speed of the object.
	 */
	public double getYSpeed();
	
	/**
	 * The mutator for vertical the speed of the object. Included here for calling purposes.
	 * Implementing classes must override this method. 
	 * 
	 * @param speed the new vertical speed.
	 */
	public void setYSpeed(double speed);
	/**
	 * Checks whether this GameObject is currently moving.
	 * 
	 * @return <code>true</code> if xSpeed and ySpeed are 0, <code>false</code> otherwise.
	 */
	public boolean isMoving();
}
