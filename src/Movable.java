/**
 * 
 * Reminder to javadoc.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornikov
 */
public interface Movable extends GameObject
{
	void moveTo(int x, int y);
	
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
	
	public boolean isMoving();
}
