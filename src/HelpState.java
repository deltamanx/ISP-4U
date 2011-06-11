import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class HelpState extends BasicGameState
{
	private Image [] slides = new Image [4];
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
		slideNum = 0;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException
	{
		bg.draw();
		slides [slideNum].draw(101,101);
		
	}
	
	@Override
	public void enter (GameContainer gc, StateBasedGame parent){
		slideNum = 0;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame parent, int delta)
			throws SlickException
	{
		Input i = gc.getInput();
		if (i.isKeyPressed(Input.KEY_RIGHT)){
			if (slideNum <3)
				slideNum ++;

			
		}
		if (i.isKeyPressed(Input.KEY_LEFT)){
			if (slideNum >0)
				slideNum --;
		}
		
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 4;
	}

}
