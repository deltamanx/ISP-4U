import java.io.File;

import java.awt.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StageSelectState extends BasicGameState
{
	private int level;
	private int stage;
	Engine e;
	private File f;
	private UnicodeFont font;
	private String [][] names;

	public StageSelectState(){}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		names = new String [3][10];
		
		names [0][0] = "EASY - 1 : DIAMAGNETS";
		names [0][1] = "EASY - 2 : PARAMAGNETS";
		names [0][2] = "EASY - 3 : LEVITATION";
		names [1][0] = "NORMAL - 1 : THE HOOK";
		names [1][1] = "NORMAL - 2 : BOUNCE, NO BOUNCE";
		names [1][2] = "NORMAL - 3 : CASCADE";
		names [2][0] = "HARD - 1 : THE WALL";
		names [2][1] = "HARD - 2 : PARAMAGNETS PART 2";
		names [2][2] = "HARD - 3 : SLINGSHOT";
		
		e = new Engine (LevelWriter.readWorld("1.0"));
		font = new UnicodeFont ("dat/segoe.ttf",20,false,false);
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.white));
		font.loadGlyphs();
		level = 1;
		stage = 0;
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.setFont (font);
		e.renderImages (g);
		g.setColor (org.newdawn.slick.Color.black);
		g.fillRect(250,500, 400, 70);
		g.setColor (org.newdawn.slick.Color.white);
		g.drawString(names [level-1][stage],200,200);
		g.drawString("LEFT AND RIGHT ARROW KEYS TO CHOOSE",200, 240);
		g.drawString("ENTER TO SELECT",200, 280);
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
				f = new File ("levelData/" + level+"."+stage+".dat");
				if (f.exists())
					break;
			}
			e = new Engine (LevelWriter.readWorld(level + "." + stage));
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
				f = new File ("levelData/" + level+"."+stage+".dat");
				if (f.exists())
					break;
			}
			e = new Engine (LevelWriter.readWorld(level + "." + stage));
		}
		if (i.isKeyDown(Input.KEY_ENTER)){
				((GameState)(parent.getState(3))).setWorld(level + "." + stage);
				i.clearKeyPressedRecord();
				parent.enterState(3);
		}
	}

	@Override
	public int getID()
	{
		return 2;
	}


}
