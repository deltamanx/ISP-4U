
import java.util.ArrayList;

/**
 * The AbstractWorld class.
 * <p>
 * This is the code implementation for the World interface, and
 * contains all basic code functionality for the methods specified
 * in the World interface.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.2 : May 15, 2011
 * @since May 9, 2011
 * @see World
 * @see GameObject
 * @see AbstractGameObject
 */
public class AbstractWorld<E>
implements World<E>
{
	private static final long serialVersionUID = -6355649104131966598L;
	private ArrayList<E> world;
	private int height;
	private int width;
	private int baseScore;
	private double solidity;
	private double goalX;
	private double goalY;
	private double goalR;


	/**
	 * Creates an instance of the World Object with given parameters
	 * (namely width and height). These parameter values are set in
	 * the constructor along with the instantiation of the private field
	 * world, which is an <code>ArrayList\<E\></code> Object in which
	 * data for all GameObjects in this world is stored.
	 * 
	 * @param w The initial width value for the World
	 * @param h The initial height value for the World
	 * @see World
	 */
	public AbstractWorld(int w, int h, double solidity, double goalX, double goalY, double goalR, int s)
	{
		setWorld(new ArrayList<E>());
		setHeight(h);
		setWidth(w);
		setSolidity(solidity);
		this.goalX = goalX;
		this.goalY = goalY;
		this.goalR = goalR;
		baseScore = s;
	}

	@Override
	public boolean addToWorld(E obj)
	{
		return world.add(obj);
	}
	
	/**
	 * Used to retrieve the X coordinate for the goal in
	 * this instance of World.
	 * 
	 * @return The X coordinate for the origin of the goal.
	 */
	public double getGoalX()
	{
		return goalX;
	}
	/**
	 * Used to retrieve the Y coordinate for the goal in
	 * this instance of World.
	 * 
	 * @return The Y coordinate for the origin of the goal.
	 */
	public double getGoalY()
	{
		return goalY;
	}
	
	public double getGoalR()
	{
		return goalR;
	}
	@Override
	public boolean existsInWorld(E obj)
	{
		return world.contains(obj);
	}

	@Override
	public E removeFromWorld(E obj)
	{
		return (world.indexOf(obj) != -1 ? world.remove(world.indexOf(obj)): null);
	}

	/**
	 * Sets the value for the ArrayList Object.
	 * 
	 * @param world The new value for this field.
	 */
	public void setWorld(ArrayList<E> world)
	{
		this.world = world;
	}

	/**
	 * Returns the ArrayList Object contained within this World.
	 * 
	 * @return The current value for this field.
	 */
	public ArrayList<E> getWorld()
	{
		return world;
	}

	/**
	 * Sets the value for the vertical size of this World Object.
	 * 
	 * @param height The new value for the height of this World Object.
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getHeight()
	{
		return height;
	}

	/**
	 * Sets the value for the horizontal size of this World Object.
	 * 
	 * @param width The new value for the width of this World Object.
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getWidth()
	{
		return width;
	}

	/**
	 * Returns the Object at the specified coordinates within this World.
	 * If no Object falls within those coordinates, it returns null.
	 * 
	 * @param x The X value the Object's bounds exist within.
	 * @param y The Y value the Object's bounds exist within.
	 * @return The first instance of a GameObject within the area of (X, Y), <code>null</code> otherwise.
	 */
	public Object getAtCoords(int x, int y)
	{
		for(int i = 0; i < world.size(); i++)
		{
			if(world.get(i) instanceof GameObject)
			{
				GameObject go = (GameObject)world.get(i);
				int minX = (int) (go.getX() - go.getHeight());
				int maxX = (int) (go.getX() + go.getHeight());
				int minY = (int) (go.getY() - go.getWidth());
				int maxY = (int) (go.getY() + go.getWidth());
				if((minX < x && maxX > x) && (minY < y && maxY > y))
					return go;
			}
		}
		return null;
	}

	//Documented in interface
	public ArrayList<Movable> getAllMovable()
	{
		ArrayList<Movable> movableObjects = new ArrayList<Movable>();
		for (int i = 0; i < getWorld().size(); i++)
			if (getWorld().get(i) instanceof Movable)
				movableObjects.add((Movable)getWorld().get(i));
		return movableObjects;
	}

	public ArrayList<Solid> getAllSolids()
	{
		ArrayList<Solid> movableObjects = new ArrayList<Solid>();
		for (int i = 0; i < getWorld().size(); i++)
			if (getWorld().get(i) instanceof Solid)
				movableObjects.add((Solid)getWorld().get(i));
		return movableObjects;
	}

	/**
	 * Returns the Solidity value for all Objects in this World.
	 * 
	 * @return Used by the Engine to calculate Bounce distance and energy loss.
	 */
	public double getSolidity()
	{
		return solidity;
	}

	public void setSolidity(double solidity)
	{
		this.solidity = solidity;
	}

	/**
	 * Returns the hash code for this World Object. This code
	 * is based on all values contained within this Object and is
	 * usually unique. Override from java.lang.Object.
	 * 
	 * @return The hash code for this World.
	 */
	@Override
	public int hashCode()
	{
		int hash = 6;
		hash = (getWidth() * 97) + hash;
		hash = (getHeight() * 97) + hash;
		hash = world.hashCode() + hash;
		return hash;
	}

	/**
	 * Checks if a given Object and this Object are the same.
	 * Automatically returns false if the given Object is not
	 * an instance of World. If the given Object is a World it
	 * compares the Objects' hash codes. Override from java.lang.Object.
	 * 
	 * @return <code>true</code> if the given Object is equal, otherwise <code>false</code>.
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof World))
			return false;
		if (hashCode() == o.hashCode())
			return true;
		return false;
	}

	public Player getPlayer(){
		for (E o:world){
			if (o instanceof Player)
				return (Player) o;
		}
		return null;
	}
	
	@Override
	public int getBaseScore()
	{
		return baseScore;
	}
}
