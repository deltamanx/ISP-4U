import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * This the Game Engine.
 * <p>
 * It handles most operations that are performed during run time.
 * It does both number processing and calculations as well as GameObject
 * movement and World handling.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornicov
 * @version 1.0.0.12 May 8, 2011
 * @since May 8, 2011
 */
public final class Engine extends Thread
{
	private World<GameObject> world;
	//Fields accessed from Threads
	private Thread moveCalculator;
	private Thread moveManager;
	private int numBounces;
	private int timePassed;

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
		timePassed = 0;
		numBounces = 0;
	}

	/**
	 * This method does numeric calculations for all Movable
	 * GameObjects within the handled World. This method handles
	 * magnetism calculations for all Movables. The speed calculated
	 * depends on the time since the last frame, and is in essence
	 * calculated for each millisecond passed.
	 * 
	 * @param delta the number of milliseconds since the last frame.
	 */
	public void recalculateMovement(int delta)
	{
		ArrayList<Movable> moving = getWorld().getAllMovable();
		ArrayList<GameObject> world = ((AbstractWorld<GameObject>)getWorld()).getWorld();
		//Go through all Movable Objects
		for (int i = 0; i < moving.size(); i++)
		{
			//Check to see if magnetism exists
			for (int j = 0; j < world.size(); j++)
			{
				//Count Magnetism for all Objects in the World.
				if (!moving.get(i).equals(world.get(j)))
					moving.get(i).countMagnetism(world.get(j), delta);

			}
			moving.get(i).countGravity(delta);
		}
	}
	/**
	 * This method handles GameObject movement in the handled World.
	 * This is movement for all GameObjects that implement Movable.
	 * Other Object are not handled by this method as they cannot move
	 * therefore need not be processed by this game.
	 */
	public void handleMovement(int delta)
	{
		ArrayList<Movable> moving = getWorld().getAllMovable();
		//Go through all Movable Objects
		timePassed += delta;
		for (int i = 0; i < moving.size(); i++)
		{
			//Check if the Object has an X and/or Y speed.
			if(moving.get(i).isMoving())
			{
				//If the speed is too high, cap it.
				if (moving.get(i).getXSpeed()>250.0){
					moving.get(i).setXSpeed (250.0);
				} else if (moving.get(i).getXSpeed()<-250.0){
					moving.get(i).setXSpeed (-250.0);
				} 
				if (moving.get(i).getYSpeed()>250.0){
					moving.get(i).setYSpeed (250.0);
				} else if (moving.get(i).getYSpeed()<-250.0){
					moving.get(i).setYSpeed (-250.0);
				}
				//Find coordinates for Objects next desired move spot.
				double newX = (moving.get(i).getX() + moving.get(i).getXSpeed() * delta / 1000);
				double newY = (moving.get(i).getY() + moving.get(i).getYSpeed() * delta / 1000);
				//Check if Object is able to move to those coordinates.
					if (moving.get(i).canMoveToX(newX) &&moving.get(i).canMoveToY(newY))
					{
						moving.get(i).moveTo(newX,newY);
					}
					else if (moving.get(i).canMoveToY(newY))
					{
						moving.get(i).moveTo(moving.get(i).getX(), newY);
						moving.get(i).setXSpeed(moving.get(i).getXSpeed()*-world.getSolidity());
						moving.get(i).setYSpeed(moving.get(i).getYSpeed()*world.getSolidity());
						numBounces+=1;
					}
					else if (moving.get(i).canMoveToX(newX))
					{
						moving.get(i).moveTo(newX, moving.get(i).getY());
						moving.get(i).setXSpeed(moving.get(i).getXSpeed()*world.getSolidity());
						moving.get(i).setYSpeed(moving.get(i).getYSpeed()*-world.getSolidity());
						numBounces+=1;
					}
					else
					{
						moving.get(i).setXSpeed(moving.get(i).getXSpeed()*-world.getSolidity());
						moving.get(i).setYSpeed(moving.get(i).getYSpeed()*-world.getSolidity());
					}
				}
			//Air Resistance
			moving.get(i).setYSpeed(moving.get(i).getYSpeed() * 0.9999);
			moving.get(i).setXSpeed(moving.get(i).getXSpeed() * 0.9999);
		}
	}

	public boolean isInGoal (Player p){
		double xDist = Math.abs(p.getX()+p.getWidth()/2-world.getGoalX());
		double yDist = Math.abs(p.getY()+p.getHeight()/2-world.getGoalY());
		
		if (Math.sqrt(xDist*xDist + yDist*yDist)<world.getGoalR()){
			return true;
		}
			return false;
	}
	
	public void renderImages(Graphics g) throws SlickException
	{
		for (GameObject i : ((AbstractWorld<GameObject>)getWorld()).getWorld())
		{
			 if(i instanceof Magnet)
			{
				if (i.getPole ().equals(Pole.DIA))
					g.setColor(Color.blue);
				else if (i.getPole ().equals(Pole.PARA))
					g.setColor(Color.red);
				g.fillRect((float)i.getX(),(float)i.getY(),(float)i.getWidth(),(float)i.getHeight());
			}
			else if (i instanceof Block)
			{
					g.setColor (Color.white);
				g.fillRect((float)i.getX(),(float)i.getY(),(float)i.getWidth(),(float)i.getHeight());
			}
			else if(i instanceof Player)
			{
				g.setColor (Color.white);
				g.drawString("  X: " + (int)i.getX() + " Y: " + (int)i.getY(), (float)i.getX(), (float)i.getY() - 15);
				g.drawString("  SpeedX: " + (int)((Movable)i).getXSpeed() + " SpeedY: " + (int)((Movable)i).getYSpeed(), (float)i.getX(), (float)i.getY());
				g.setColor (Color.green);
				g.fillOval((float)i.getX(),(float)i.getY(),(float)i.getWidth(),(float)i.getHeight());
			}
			g.setColor (Color.white);
			g.fillOval((float)(world.getGoalX()-world.getGoalR()),(float)(world.getGoalY()-world.getGoalR())
					,(float)world.getGoalR()*2,(float)world.getGoalR()*2);
		}
		
	}

	/**
	 * Counts the total score for the past level.
	 * 
	 * @return the resultant score
	 */
	public int getScore(){
		return numBounces + world.getBaseScore() -(timePassed/1000-5);		
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
	@Deprecated
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
	@Deprecated
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
	}
}
