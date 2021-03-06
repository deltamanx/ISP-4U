
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
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
	private static Music m;

	/**
	 * The default constructor.
	 */
	public MainProgram ()
	{
		super ("Interrobang!?");
	}

	@Override
	// documented in interface.
	public void keyPressed (int key, char c){
		if (key == Input.KEY_M){
			if (m.playing())
			{
				m.stop();
			}
			else
			{
				m.loop();
			}
		}
		if (key == Input.KEY_Q)
			enterState(1);
	}

	@Override
	/**
	 * This method initialises the various states of the game.
	 * 
	 */
	public void initStatesList(GameContainer container) 
	throws SlickException
	{
		addState (new SplashState());
		addState (new MenuState());
		addState (new StageSelectState());
		addState (new GameState());
		addState (new HelpState());
		addState (new HighScoreState());
	}

	/**
	 * The main method for this program. Invoked by the JVM
	 * to run this application.
	 * 
	 * @param args Arguments taken from the JVM.
	 */
	public static void main (String[] args)
	{
		AppGameContainer app;
		try
		{
			app = new AppGameContainer (new MainProgram ());
			try
			{
				m = new Music ("dat/bgm.ogg");
				m.loop();
			}
			catch(Exception e)
			{
				//The user just moved or delete the files. Now what?
			}
			app.setDisplayMode (800, 600, false);
			app.setSmoothDeltas(true);
			app.setVSync(true);
			app.start ();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}
