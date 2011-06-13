
import org.newdawn.slick.Color;
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
 * This state is the menu state, and is the first major screen of the program.
 * Here, the player chooses their next screen, which can either be the game, the highscore screen,
 * or the help screen. The user may also choose to print out the high scores or to 
 * exit the program.
 * 
 * Pressing Q at any time leads to this screen.
 * @author Dan Zapornikov
 * @version 1.0.0 : May 25, 2011
 * @since May 25, 2011
 * 
 * 
 */
public class MenuState extends BasicGameState
implements ComponentListener
{
	private Image bg;
	private Image helpImage;
	private Image exitImage;
	private Image selectImage;
	private Image printImage;
	private Image highScoreImage;
	private MouseOverArea helpButton;
	private MouseOverArea exitButton;
	private MouseOverArea selectButton;
	private MouseOverArea printButton;
	private MouseOverArea highScoreButton;
	private int nextState = 1;

	/**
	 * Default Constructor.
	 */
	public MenuState() {  }

	@Override
	/**
	 * Initializes the various menu buttons and their respective images.
	 * 
	 * @param gc the GameContainer that contains this state.
	 * @param parent the StateBasedGame that contains this state.
	 * 
	 */
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		bg = new Image("dat/BG.png");
		try
		{
			helpImage =  new Image ("dat/Instructions.png");
		}
		catch(Exception e)
		{
			helpImage = new Image(220, 66);
			helpImage.getGraphics().setBackground(Color.green);
		}
		helpButton = new MouseOverArea(gc, helpImage, 50, 100, this);
		try
		{
			exitImage =  new Image ("dat/Exit.png");
		}
		catch(Exception e)
		{
			exitImage = new Image(220, 66);
			exitImage.getGraphics().setBackground(Color.red);
		}
		exitButton = new MouseOverArea(gc, exitImage, 50, 200, this);
		try
		{
			selectImage =  new Image ("dat/StageSelect.png");
		}
		catch(Exception e)
		{
			selectImage = new Image(220, 66);
			selectImage.getGraphics().setBackground(Color.blue);
		}
		selectButton = new MouseOverArea(gc, selectImage, 300, 100, this);
		try
		{
			printImage = new Image("dat/Print.png");
		}
		catch(Exception e)
		{
			printImage = new Image(220, 66);
			printImage.getGraphics().setBackground(Color.magenta);
		}
		printButton = new MouseOverArea(gc, printImage, 300, 200, this);
		try
		{
			highScoreImage = new Image("dat/HighScore.png");
		}
		catch(Exception e)
		{
			highScoreImage = new Image(220, 66);
			highScoreImage.getGraphics().setBackground(Color.yellow);
		}
		highScoreButton = new MouseOverArea(gc, highScoreImage, 300, 300, this);
	}

	@Override
	/**
	 * Draws the buttons and background image onto the GameContainer.
	 * 
	 	 * @param gc the GameContainer that contains this state.
	 * @param parent the StateBasedGame that contains this state.
	 * @param g the graphics context of the GameContainer.
	 * 
	 */
	public void render(GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.drawImage(bg, 0, 0);
		exitButton.render(gc, g);
		selectButton.render(gc, g);
		helpButton.render (gc, g);
		printButton.render(gc, g);
		highScoreButton.render(gc, g);
	}

	/**
	 * Checks if a button was pressed and if one was, goes to the corresponding state.
	 * 
	 	 * @param gc the GameContainer that contains this state.
	 * @param parent the StateBasedGame that contains this state.
	 * @param delta the number of milliseconds since the last frame update.
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		if (nextState > 1)
		{
			parent.enterState (nextState);
		}
		nextState = 0;
	}

	@Override
	/**
	 * Checks if the released key was one of the hotkeys. If
	 * it was, then call the related button listener.
	 * 
	 * @param key the key id of the pressed key.
	 * @param c the character of the pressed key.
	 */
	public void keyReleased(int key, char c) 
	{
		if(key == Input.KEY_I)
			componentActivated(helpButton);
		else if(key == Input.KEY_E)
			componentActivated(exitButton);
		else if(key == Input.KEY_S)
			componentActivated(selectButton);
		else if(key == Input.KEY_P)
			componentActivated(printButton);
		else if(key == Input.KEY_H)
			componentActivated(highScoreButton);
		else return;
	}

	@Override
	/**
	 * Returns the id of the state.
	 * 
	 * @return the ID of the state, in this case 1.
	 * 
	 */
	public int getID()
	{
		return 1;
	}

	@Override
	/**
	 * Checks which component was activated, and sets the next state to be opened to be 
	 * the relevant state.
	 * 
	 * @param com the component that was activated.
	 * 
	 */
	public void componentActivated(AbstractComponent com)
	{
		if (com.equals(helpButton))
			nextState = 4;
		else if (com.equals(selectButton))
			nextState = 2;
		else if (com.equals(exitButton))
			System.exit(0);
		else if (com.equals(printButton))
			PrintDriver.print("dat/Rules.txt");
		else if (com.equals(highScoreButton))
			nextState = 5;
		else return;
	}

}
