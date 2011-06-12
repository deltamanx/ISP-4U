import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 * This state shows the high scores read from the HighScore.dat file.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0, June 11, 2011
 * @since June 11, 2011
 */
public class HighScoreState extends BasicGameState
implements ComponentListener
{
	private Image backImage;
	private MouseOverArea backButton;
	private int nextState = 0;
	
	/**
	 * Default constructor.
	 * 
	 */
	public HighScoreState() {  }

	@Override
	/**
	 * Initialises this state's back button and image.
	 * 
	 * @param gc the GameContainer that houses this state.
	 * @param parent the StateBasedGame that houses this state.
	 */
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		backImage = new Image("dat/Back.png");
		backButton = new MouseOverArea(gc, backImage, 550, 500, this);
	}

	@Override
	/**
	 * Draws the highscores table, as well as the button.
	 * 
	 * @param gc the GameContainer that houses this state.
	 * @param parent the StateBasedGame that houses this state.
	 * @param g the graphics context of the GameContainer.
	 */
	public void render(GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		ArrayList<HighScore> scores = HighScoreManager.getHighScores(0);
		for(int i = 0; i < scores.size(); ++i)
		{
			g.drawString((i < 9 ? " " : "") + (i + 1) + ": " + scores.get(i).toString(),
					50, 80 + (i * 25));
		}
		backButton.render(gc, g);
	}

	@Override
	/**
	 * Checks if the button's event has fired, if yes, goes back to menu.
	 * 
	 * @param gc the GameContainer that houses this state.
	 * @param parent the StateBasedGame that houses this state.
	 * @param delta the number of milliseconds since the last frame update.
	 */
	public void update(GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		if(nextState != 0)
			parent.enterState (nextState);
		nextState = 0;
	}
	
	@Override
	/**
	 * Checks if the key that was released was the hotkey for
	 * the back button. If yes fires the button's event.
	 * 
	 * @param key the key id of the released key.
	 * @param c the character of the released key.
	 */
	public void keyReleased(int key, char c)
	{
		if(key == Input.KEY_B)
			componentActivated(backButton);
	}

	@Override
	/**
	 * Returns this state's ID.
	 * 
	 * @return the ID of the state, 5.
	 */
	public int getID()
	{
		return 5;
	}

	@Override
	/**
	 * When the button's event is fired, set the next state to the
	 * menu state.
	 * 
	 * @param com the button that fired the event.
	 */
	public void componentActivated(AbstractComponent com)
	{
		if(com.equals(backButton))
			nextState = 1;
	}

}
