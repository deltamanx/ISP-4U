import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StageSelectState extends BasicGameState
implements ComponentListener
{
	private Image bg;
	private Image stage1;
	private Image stage2;
	private Image stage3;
	private MouseOverArea stage1B;
	private MouseOverArea stage2B;
	private MouseOverArea stage3B;
	private int nextState = 1;

	public StageSelectState(){}

	@Override
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		bg = new Image("dat/BG.png");
		stage1 =  new Image ("dat/NewGame.png");
		stage1B = new MouseOverArea(gc,stage1, 100, 100, this);
		stage2 =  new Image ("dat/Exit.png");
		stage2B = new MouseOverArea(gc,stage2 , 100, 200, this);
		stage3 =  new Image ("dat/Continue.png");
		stage3B = new MouseOverArea(gc,stage3, 100, 300, this);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.drawImage(bg, 0, 0);
		stage1B.render (gc, g);
		stage2B.render(gc, g);
		stage3B.render(gc,g);
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
		if (com.equals(stage1B));
		if (com.equals(stage2B));
		if (com.equals(stage3B));
	}

}
