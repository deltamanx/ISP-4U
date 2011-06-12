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

	public MenuState() {  }

	@Override
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		bg = new Image("dat/BG.png");
		helpImage =  new Image ("dat/Instructions.png");
		helpButton = new MouseOverArea(gc, helpImage, 50, 100, this);
		exitImage =  new Image ("dat/Exit.png");
		exitButton = new MouseOverArea(gc, exitImage, 50, 200, this);
		selectImage =  new Image ("dat/StageSelect.png");
		selectButton = new MouseOverArea(gc, selectImage, 300, 100, this);
		printImage = new Image("dat/Print.png");
		printButton = new MouseOverArea(gc, printImage, 300, 200, this);
		highScoreImage = new Image("dat/HighScore.png");
		highScoreButton = new MouseOverArea(gc, highScoreImage, 300, 300, this);
	}

	@Override
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
	public int getID()
	{
		return 1;
	}

	@Override
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
