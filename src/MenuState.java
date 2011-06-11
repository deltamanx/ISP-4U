import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	private Image newImage;
	private Image exitImage;
	private Image continueImage;
	private MouseOverArea newButton;
	private MouseOverArea exitButton;
	private MouseOverArea continueButton;
	private int nextState = 1;

	public MenuState(){}

	@Override
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		bg = new Image("dat/BG.png");
		newImage =  new Image ("dat/NewGame.png");
		newButton = new MouseOverArea(gc,newImage, 100, 100, this);
		exitImage =  new Image ("dat/Exit.png");
		exitButton = new MouseOverArea(gc,exitImage, 100, 200, this);
		continueImage =  new Image ("dat/Continue.png");
		continueButton = new MouseOverArea(gc,continueImage, 100, 300, this);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.drawImage(bg, 0, 0);
		newButton.render (gc, g);
		exitButton.render(gc, g);
		continueButton.render(gc,g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		if (nextState == 5)
		{
			parent.getState(3).init(gc, parent);
			parent.enterState(3);
		}
		else if (nextState > 1)
		{
			parent.enterState (nextState);
		}
		nextState = 0;
	}

	@Override
	public int getID()
	{
		return 1;
	}

	@Override
	public void componentActivated(AbstractComponent com)
	{
		if (com.equals(newButton))
			nextState = 5;
		if (com.equals(continueButton))
			nextState = 2;
		if (com.equals(exitButton))
			System.exit(0);
	}

}
