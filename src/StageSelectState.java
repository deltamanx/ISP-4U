
import java.io.File;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;

import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
/**
 * This state is the state in which the player decides which
 * level to play. It displays information about the currently
 * selected stage, such as level name, difficulty, and layout.
 * It also sends information to the GameState state as to which
 * level to load.
 * 
 * @author Dan Zapornikov
 * @version 1.0.2.0 : June 12, 2011
 * @since June 10, 2011
 * 
 */
public class StageSelectState extends BasicGameState
{
	private int level;
	private int stage;
	private Engine e;
	private File f;
	private UnicodeFont font;
	private String [][] names;


	public StageSelectState(){}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * This method initialises the fonts, the level names, and other local variables that
	 * are required to run the state.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 * 
	 */
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
		try
		{
			font = new UnicodeFont ("dat/segoe.ttf",20,false,false);
		}
		catch(Exception e)
		{
			font = new UnicodeFont (new Font("sans-serif", Font.PLAIN, 12));
		}
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.white));
		font.loadGlyphs();
		level = 1;
		stage = 0;
	}

	@Override
	/**
	 * This method provides a graphical representation of the state on the GameContainer.
	 * It draws the current selected Level, it's name, as well as the base score and some
	 * simple instructions for navigating this screen.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 * @param g the graphics context of the GameContainer.
	 */
	public void render(GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.setFont (font);
		e.renderImages (g);
		g.drawString(names [level-1][stage],200,200);
		g.drawString("LEFT AND RIGHT ARROW KEYS TO CHOOSE",200, 240);
		g.drawString("ENTER TO SELECT",200, 280);
	}

	@Override
	/**
	 * This method contains the game logic of the screen. It take the input to change or confirm
	 * a level selection.
	 * 
	 * @param gc the GameContainer that is displaying this state (the window)
	 * @param parent the StateBasedGame that contains this state.
	 */
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
				f = new File ("levelData/" + level + "." + stage + ".dat");
				if (f.exists())
					break;
			}
			World<GameObject> w = LevelWriter.readWorld(level + "." + stage);
			if (w == null)
			{
				JOptionPane.showMessageDialog(null, "Error! Level file missing!\n" +
						"Program will now terminate! Please re-install!", "Error!", 
						JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			e = new Engine (w);
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
						level = 3;
					}
				}
				f = new File ("levelData/" + level + "." + stage + ".dat");
				if (f.exists())
					break;
			}
			World<GameObject> w = LevelWriter.readWorld(level + "." + stage);
			if (w == null)
			{
				JOptionPane.showMessageDialog(null, "Error! Level file missing!\n" +
						"Program will now terminate! Please re-install!", "Error!", 
						JOptionPane.ERROR_MESSAGE);
				System.exit(-1);
			}
			e = new Engine (w);
		}
		if (i.isKeyDown(Input.KEY_ENTER)){
				((GameState)(parent.getState(3))).setWorld(level + "." + stage);
				i.clearKeyPressedRecord();
				parent.enterState(3);
		}
	}

	@Override
	/**
	 * This method returns the ID of the state.
	 * 
	 * @return the ID number of the state, used to identify it for transitions or calling.
	 */
	public int getID()
	{
		return 2;
	}
}
