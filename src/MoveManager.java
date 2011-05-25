
/**
 * This class is responsible for Managing movement for all Objects in
 * a given World. This class is a subclass of Thread and therefore runs
 * in a Thread. It is created by the Engine class, within another Thread
 * which manages this class as well as its mutual partner and counterpart:
 * MoveCalculator. The Thread is this class will continue to run as long
 * as its parent Thread (The Engine Object it was created in) is Alive.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.0 : April 11, 2011
 * @since April 11, 2011
 * @see java.lang.Thread
 * @see Engine
 * @see MoveCalculator
 */
@Deprecated
public final class MoveManager extends Thread
{
	private Engine parentEngine;
	private boolean gameRunning;

	public MoveManager(Engine parentEngine)
	{
		super("Movement Manager Thread");
		this.parentEngine = parentEngine;
		setGameRunning(true);
	}

	public void setGameRunning(boolean gameRunning)
	{
		this.gameRunning = gameRunning;
	}

	public boolean getGameRunning()
	{
		return gameRunning;
	}

	@Override
	public void run()
	{
		while(getGameRunning() && parentEngine.isAlive())
		{
			try
			{
				wait(2000);
				parentEngine.handleMovement(0);
				parentEngine.notifyMoveCalculator();
			}
			catch(InterruptedException e)
			{ /* Not handled. */ }
		}
	}
}
