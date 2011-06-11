import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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


public class StageSelectState extends BasicGameState
{
	private Image bg;
	private int level;
	private int stage;
	private ObjectInputStream ois = null;
	private FileInputStream fis = null;
	@SuppressWarnings("unused")
	private Image [][] img = { {}, {}, {} };

	public StageSelectState(){}

	@Override
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		bg = new Image("dat/BG.png");
		
		level = 1;
		stage = 0;
		try
		{
			fis = new FileInputStream("levelData/" + level+"."+stage+".dat");
		}
		catch (IOException e)
		{
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.drawImage(bg, 0, 0);
		g.drawString(level + " - " + stage,200,200);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		Input i = gc.getInput();
		if (i.isKeyPressed(Input.KEY_RIGHT)){
			while(true)
			{
				stage += 1;
				if (stage == 10)
				{
					stage = 0;
					level += 1;
					if (level == 4)
					{
						level = 1;
					}
				}
				try
				{
					fis = new FileInputStream("levelData/" + level+"."+stage+".dat");
				}
				catch (IOException e)
				{
					continue;
				}
				break;
			}
		}
		if (i.isKeyPressed(Input.KEY_LEFT))
		{
			while(true)
			{
				stage -= 1;
				if (stage == -1)
				{
					stage = 9;
					level -= 1;
					if (level == 0)
					{
						level =3;
					}
				}
				try
				{
					fis = new FileInputStream("levelData/" + level+"."+stage+".dat");
				}
				catch (IOException e)
				{
					continue;
				}
				break;
			}

		}
		if (i.isKeyDown(Input.KEY_ENTER))
		{
			try
			{
				ois = new ObjectInputStream (fis);
				((GameState)(parent.getState(3))).setWorld((World<GameObject>) ois.readObject());
				parent.enterState(3);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getID()
	{
		return 2;
	}


}
