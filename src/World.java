import java.util.ArrayList;

public interface World<E>
{
	/**
	 * 
	 * @param obj The Object to be added to the World.
	 * @return <code>true</code>.
	 */
	boolean addToWorld(E obj);
	/**
	 * 
	 * @param obj The Object that this method searches for.
	 * @return <code>true</code> if the Object exists in the World, <code>false</code> otherwise.
	 */
	boolean existsInWorld(E obj);
	/**
	 * 
	 * @param obj The Object to remove from the World.
	 * @return The Object that was removed from the World if it existed in the World, <code>null</code otherwise.
	 */
	E removeFromWorld(E obj);
	
	public ArrayList<Movable> getAllMovable();
	
	/**
	 * 
	 * @return The current value for the height of this World Object.
	 */
	int getHeight();
	/**
	 * 
	 * @return The current value for the width of this World Object.
	 */
	int getWidth();
}