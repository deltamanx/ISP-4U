
import java.util.ArrayList;
import java.io.Serializable;

/**
 * The World interface is implemented by classes in order to
 * give them functionality required by the Game Engine such
 * as GameObject manipulation and handling.
 * 
 * @author Mihail Kurbako
 * @param E The Object type that is to be stored in this World.
 * @version 1.0.0.1 : May 14, 2011
 * @since May 9, 2011
 * @see AbstractWorld
 * @see GameObject
 */
public interface World<E> extends Serializable
{
	/**
	 * Adds the specified Object to the World. Returns true as specified
	 * by the AsbtractList interface.
	 * 
	 * @param obj The Object to be added to the World.
	 * @return <code>true</code>.
	 */
	boolean addToWorld(E obj);
	/**
	 * Checks if the specified Object exists within the World.
	 * 
	 * @param obj The Object that this method searches for.
	 * @return <code>true</code> if the Object exists in the World, <code>false</code> otherwise.
	 */
	boolean existsInWorld(E obj);
	/**
	 * Removes the specified Object from the World if it exists.
	 * Returns the removed Object. If it doesn't exist, returns null;
	 * 
	 * @param obj The Object to remove from the World.
	 * @return The Object that was removed from the World if it existed in the World, <code>null</code otherwise.
	 */
	E removeFromWorld(E obj);
	/**
	 * Used by the Game Engine to find all Movable Objects in this World.
	 * Returns them as an ArrayList\<Movable\>.
	 * 
	 * @return All instances of Movable in this World Object.
	 */
	public ArrayList<Movable> getAllMovable();
	/**
	 * Used by the Game Engine to find all Solid Objects in this World.
	 * Returns them as an ArrayList\<Solid\>.
	 * 
	 * @return All instances of Solid in this World Object.
	 */
	public ArrayList<Solid> getAllSolids();

	/**
	 * This method is used to retrieve the vertical size value
	 * that was assigned to this World during instantiation.
	 * 
	 * @return The current value for the height of this World Object.
	 */
	int getHeight();
	/**
	 * This method is used to retrieve the horizontal size value
	 * that was assigned to this World during instantiation.
	 * 
	 * @return The current value for the width of this World Object.
	 */
	int getWidth();
	/**
	 * Returns the Solidity value for all Objects in this World.
	 * 
	 * @return Used by the Engine to calculate Bounce distance and energy loss.
	 */
	double getSolidity();
	/**
	 * Used to access the X coordinate of the World's goal that
	 * the Player must touch to complete it.
	 * 
	 * @return The X coordinate for the goal.
	 */
	double getGoalX();
	/**
	 * Used to access the Y coordinate of the World's goal that
	 * the Player must touch to complete it.
	 * 
	 * @return The Y coordinate for the goal.
	 */
	double getGoalY();
	/**
	 * Return the radius of the goal for this World.
	 * (How far out it spans from the origin).
	 * Used by the Engine to render the goal.
	 * 
	 * @return The radius for the goal in this World.
	 */
	double getGoalR();
	/**
	 * The starting value for the Player's score in this instance
	 * of the World Object. It is then compared to time and number
	 * of bounces to produce a final score for the level.
	 * 
	 * @return The starting value for score.
	 */
	int getBaseScore();
	/**
	 * Returns the first instance Player Object that is
	 * contained within this World. <code>null</code> is
	 * returned if no instance of Player exists within 
	 * this World.
	 * 
	 * @return The Player Object within this World, <code>null</code> otherwise.
	 */
	Player getPlayer();
}