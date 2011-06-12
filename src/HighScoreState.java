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

public class HighScoreState extends BasicGameState
implements ComponentListener
{
	private Image backImage;
	private MouseOverArea backButton;
	private int nextState = 0;
	
	public HighScoreState() {  }

	@Override
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		backImage = new Image("dat/Back.png");
		backButton = new MouseOverArea(gc, backImage, 550, 500, this);
	}

	@Override
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
	public void update(GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		if(nextState != 0)
			parent.enterState (nextState);
		nextState = 0;
	}
	
	@Override
	public void keyReleased(int key, char c)
	{
		if(key == Input.KEY_B)
			componentActivated(backButton);
	}

	@Override
	public int getID()
	{
		return 5;
	}

	@Override
	public void componentActivated(AbstractComponent com)
	{
		if(com.equals(backButton))
			nextState = 1;
	}

}
