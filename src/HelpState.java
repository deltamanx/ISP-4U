import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class HelpState extends BasicGameState
{
	private Image [] slides =  new Image [4];
	private Image bg;
	private int slideNum;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		for (int i = 1; i<5; i++){
			slides [i-1] = new Image ("dat/help/help"+ i +".png");
		}
		bg = new Image ("dat/help/helpbg.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
