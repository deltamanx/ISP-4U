import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class SplashState extends BasicGameState
{
	private Image bg;
	private Image anyKey;
	private float alpha;
	private float alphaChange;

	public SplashState()
	{
		// TODO Auto-generated constructor stub
	}

	public void init (GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		bg = new Image("dat/BG.png");
		anyKey = new Image ("dat/AnyKey.png");
		alpha = 0f;
		alphaChange = -1.0f;
	}

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
