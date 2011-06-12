import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class is the "splash screen" of the program. While not a splash
 * screen in the traditional sense of the word, it guarantees user
 * participation in the menu screen itself.
 * 
 * @author Dan Zapornikov
 * @version 1.0.0 : May 25, 2011
 * @since May 25, 2011
 * 
 */
public class SplashState extends BasicGameState
{
	private Image bg;
	private Image anyKey;
	private float alpha;
	private float alphaChange;

	
	/**
	 * Default Constructor.
	 * 
	 */
	public SplashState()
	{
	}
	/**
	 * This method initialises the images and other variables that this
	 * state needs.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 * 
	 */
	public void init (GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		bg = new Image("dat/BG.png");
		anyKey = new Image ("dat/AnyKey.png");
		alpha = 0f;
		alphaChange = -1.0f;
	}

	/**
	 * This method calculates the vales needed to draw the blinking image.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 * @param delta the number of milliseconds since the last frame.
	 */
	public void update (GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		Input input = gc.getInput();
		if (input.isKeyDown (Input.KEY_ENTER))
			parent.enterState(1);

		if (alpha >= 1.0f || alpha <= 0.0f)
			alphaChange = -alphaChange;

		alpha += alphaChange*delta/900;
		anyKey.setAlpha (alpha);

	}

	/**
	 * This method provides a graphical representation of the state on the GameContainer.
	 * It draws a blinking image on the main background.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 * @param g the graphics context of the GameContainer.
	 */
	public void render (GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		bg.draw(0,0);
		anyKey.draw (250,300);
	}

	public int getID()
	{
		return 0;
	}
}
