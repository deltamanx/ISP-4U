import java.util.ArrayList;

public final class Engine extends Thread
{
	private World<GameObject> world;
	
	//Fields accessed from Threads
	private Thread moveCalculator;
	private Thread moveManager;
	
	/**
	 * This constructs a new instance of the Engine Object. Engine, subclass
	 * of java.lang.Thread, runs in a Thread separate from the main program,
	 * and creates 2 sub Threads of its own, that it partially handles. This
	 * class is responsible for Object movement and Physics.
	 * 
	 * @param world The World Object that GameObjects are stored within that this will manage.
	 */
	public Engine(World<GameObject> world)
	{
		super("World Thread Handler Thread");
		setWorld(world);
	}
	
	/**
	 * This method will be invoked by a Thread.
	 * This method will invoke methods to update velocity values.
	 */
	public void recalculateMovement()
	{
		ArrayList<Movable> moving = ((AbstractWorld<GameObject>)getWorld()).getAllMovable();
		//Go through all Movable Objects
		for (int i = 0; i < moving.size(); i++)
		{
			//Check to see if magnetism exists
			for (int j = 0; j < ((AbstractWorld<GameObject>)world).getWorld().size(); j++)
			{
				//Count Magnetism for all Objects in the World.
				moving.get(i).countMagnetism(((AbstractWorld<GameObject>)world).getWorld().get(j));
			}
		}
	}
	/**
	 * This method will be invoked by a Thread.
	 * This method will process and update GameObjects.
	 */
	public void handleMovement()
	{
		ArrayList<Movable> moving = ((AbstractWorld<GameObject>)getWorld()).getAllMovable();
		//Go through all Movable Objects
		for (int i = 0; i < moving.size(); i++)
		{
			//Check if the Object has an X and/or Y speed.
			if(moving.get(i).isMoving())
			{
				//Find coordinates for Objects next desired move spot.
				int newX = (int)(moving.get(i).getX() + moving.get(i).getXSpeed());
				int newY = (int)(moving.get(i).getY() + moving.get(i).getYSpeed());
				//Check if Object is able to move to those coordinates.
				if (moving.get(i).canMoveTo(newX, newY))
					moving.get(i).moveTo(newX, newY);
			}
		}
	}

	/**
	 * Sets the World Object that this engine processes.
	 * 
	 * @param world The value for the World Object.
	 */
	public void setWorld(World<GameObject> world)
	{
		this.world = world;
	}

	/**
	 * Returns the World Object that this Engine handles.
	 *  
	 * @return The World Object.
	 */
	public World<GameObject> getWorld()
	{
		return world;
	}
	
	/**
	 * Checks whether a given GameObject can move to given coordinates. 
	 *
	 * @param go The GameObject in question.
	 * @param x The X coordinate in question.
	 * @param y The Y coordinate in question.
	 * @return <code>true</code> if able, <code>false</code> otherwise.
	 */
	@SuppressWarnings("rawtypes")
	public boolean canMoveObject(GameObject go, int x, int y)
	{
		if (!go.getEnclosingWorld().equals(getWorld()))
			return false;
		if (!(go instanceof Movable))
			return false;
		if (!((Movable)go).canMoveTo(x, y))
			return false;
		if (go instanceof Solid && ((AbstractWorld)go.getEnclosingWorld()).getAtCoords(x, y) != null)
			return false;
		return true;
	}
	
	/**
	 * Moves a GameObject to a given location in the World.
	 * 
	 * @param go The GameObject in question.
	 * @param x The X coordinate to be moved to.
	 * @param y The Y coordinate to be moved to.
	 */
	public void performMove(GameObject go, int x, int y)
	{
		if (go instanceof Movable)
		{
			((Movable)go).moveTo(x, y);
		}
		else
			return;
	}
	
	/**
	 * This method is called by the MoveManager Thread from the context of
	 * the Engine class to prevent IllegalAccessExceptions. It is used to
	 * notify the MoveCalculator Thread that it may stop waiting and resume
	 * its functionality as normal.
	 * 
	 * @see MoveCalculator
	 * @see MoveManager
	 */
	public synchronized void notifyMoveCalculator()
	{ moveCalculator.notify(); }

	/**
	 * This method is called by the MoveCalculator Thread from the context of
	 * the Engine class to prevent IllegalAccessExceptions. It is used to
	 * notify the MoveManager Thread that it may stop waiting and resume
	 * its functionality as normal.
	 * 
	 * @see MoveCalculator
	 * @see MoveManager
	 */
	public synchronized void notifyMoveManager()
	{ moveManager.notify(); }
	
	/**
	 * The run method inherited from Thread. It was overridden for the use
	 * in this class. This method performs the following: First, it creates
	 * instances of both the MoveCalculator and MoveManager Objects (both
	 * are subclasses of java.lang.Thread and therefore run in Threads).
	 * Once the Objects have been instantiated, it invokes the start() method
	 * which causes their appropriate run methods to be invoked in separate
	 * Threads. After this point it simply monitors and manages the Threads.
	 * The Threads are self and mutually maintained by each other, and are
	 * capable of continuously running and managing each other until stop
	 * conditions are met.
	 * 
	 * @see MoveCalculator
	 * @see MoveManager
	 * @see java.lang.Thread
	 */
	@Override
	public void run()
	{
		System.out.println("Thread starting");
		moveCalculator = new MoveCalculator(this);
		moveManager = new MoveManager(this);
		moveCalculator.start();
		moveManager.start();
	}
}
