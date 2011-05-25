import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The MainProgram.
 * <p>
 * This Class contains the static method main that is invoked
 * by the JVM to run the program. It is a subclass of Slick2Ds
 * BasicGame class and Overrides many of the inherited methods
 * for code implementation.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornikov
 * @version 1.0.0.0 : May 18,2011
 * @since May 18, 2011
 * @see GameObject
 * @see World
 * @see Engine
 */
public class MainProgram extends BasicGame
{
	private World<GameObject> world;
	private Engine engine;

	public MainProgram ()
	{
		super ("Interrobang!?");
	}

	public void init (GameContainer gc)
	throws SlickException
	{
		Player p;
		world = new LimitedWorld<GameObject>(600, 800);
		world.addToWorld(new Magnet(Pole.DIA, 20, 20, 20, 80, 150));
		world.addToWorld(new Magnet(Pole.DIA, 520, 580, 20, 80, 1500));
		world.addToWorld(new Magnet(Pole.DIA, 20, 580, 20, 80, 1500));
		world.addToWorld(new Magnet(Pole.DIA, 520, 20, 20, 80, 150));
		p = new Player(200, 300, 10, 10);
		p.setXSpeed(0);
		p.setYSpeed(100);
		p.addSelfToWorld(world);
		engine = new Engine(world);
	}

	public void update (GameContainer gc, int delta)
	throws SlickException
	{
		engine.recalculateMovement(delta);
		engine.handleMovement(delta);
	}


	public void render (GameContainer gc, Graphics g)
	throws SlickException
	{
		engine.renderImages(g);
	}


	public static void main (String[] args)
	throws SlickException
	{
		AppGameContainer app = new AppGameContainer (new MainProgram ());

		app.setDisplayMode (800, 600, false);
		app.start ();
	}
}
