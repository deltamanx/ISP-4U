import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
public class MainProgram extends StateBasedGame
{
	private final int SPLASH_STATE = 0;
	private final int MENU_STATE = 1;
	private final int STAGE_SELECT_STATE = 2;
	private final int GAME_STATE = 3;
	private final int HIGHSCORE_STATE = 4;
	
	public MainProgram ()
	{
		super ("Interrobang!?");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		addState (new MenuState());
		addState (new GameState());
	}

	public static void main (String[] args)
	throws SlickException
	{
		AppGameContainer app = new AppGameContainer (new MainProgram ());

		app.setDisplayMode (800, 600, false);
		app.setSmoothDeltas(true);
		app.setVSync(true);
		app.start ();
	}
}
