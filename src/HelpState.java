
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This state contains the help images in it. It will
 * cycle through these images at the user's input. These
 * demonstrate the rules and obstacles of the game play, and
 * give a short back story as to the plot.
 * 
 */
public class HelpState extends BasicGameState
{
	private Image [] slides = new Image [4];
	private Image bg;
	private int slideNum;
	
	@Override
	/**
	 * Initializes this state's images.
	 * 
	 * @param gc the GameContainer that houses this state.
	 * @param parent the StateBasedGame that houses this state.
	 */
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		try
		{
			for (int i = 1; i < 5; i++)
				slides [i-1] = new Image ("dat/help/help"+ i +".png");
			bg = new Image ("dat/help/helpbg.png");
			slideNum = 0;
		}
		catch(Exception e)
		{
			//Can't do much about the user deleting all the files.
			bg = new Image(800, 600);
			for (int i = 1; i < 5; i++)
				slides [i-1] = new Image (600, 400);
		}
	}

	@Override
	/**
	 * This method draws the slides for this stage.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 * @param g the graphics context of the GameContainer.
	 */
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException
	{
		bg.draw();
		slides [slideNum].draw(101,101);	
	}
	
	@Override
	/**
	 * Initializes the value for the current slide.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 */
	public void enter (GameContainer gc, StateBasedGame parent)
	{
		slideNum = 0;
	}
	
	@Override
	/**
	 * This updates the displayed slide based on user keyboard input.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 */
	public void update(GameContainer gc, StateBasedGame parent, int delta)
			throws SlickException
	{
		Input i = gc.getInput();
		if (i.isKeyPressed(Input.KEY_RIGHT))
		{
			if (slideNum <3)
				slideNum ++;
		}
		if (i.isKeyPressed(Input.KEY_LEFT))
		{
			if (slideNum >0)
				slideNum --;
		}
		
	}

	@Override
	/**
	 * This method returns the ID of the state.
	 * 
	 * @return the ID number of the state, used to identify it for transitions or calling.
	 */
	public int getID()
	{
		return 4;
	}

}
