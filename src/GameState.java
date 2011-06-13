

import java.awt.Color;

import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The GameState Class.
 * <p>
 * This class handles functionality of the program during
 * game play. This is when the user actually is manipulating
 * an instance of a World Object that this class is serving
 * as a handler for.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornicov
 * @version 1.0.1.1 : June 12, 2011
 * @since ~ May 21, 2011
 * @see BasicGameState
 * @see World
 * @see Engine
 */
public class GameState extends BasicGameState
{
	private World<GameObject> world;
	private Engine engine;
	private int gameStep;
	private Image anyKey;
	private Image pause;
	private Image arrow;
	private float alpha;
	private float alphaChange;
	private Player p;
	private String WorldID = "1.0";
	private int score;
	private UnicodeFont font;

	/**
	 * The default constructor for GameState.
	 * It's sole purpose is to create a new instance
	 * of this Object. It has no further functionality. 
	 */
	public GameState() { }

	/**
	 * Invoked elsewhere to set the value for the name
	 * of the World that the user is about to play.
	 * 
	 * @param s The name for the World Object to be used.
	 */
	public void setWorld(String s)
	{
		WorldID = s;
	}

	/**
	 * This method is invoked in order to create
	 * the GameState World Object's data. It also
	 * serves as the outer encompassing error-trap. 
	 */
	public void enter (GameContainer gc, StateBasedGame parent)
	{
		try
		{
			init(gc, parent);
		} 
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes the game itself. This is called every time
	 * the state is entered, and every time it is reset. It loads the level,
	 * and sets up the necessary variables to manipulate the world.
	 * 
	 * @param gc the GameContainer that this state is drawn on
	 * @param parent the StateBasedGame that contains this state.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void init (GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		pause = new Image ("dat/Pause.png");
		anyKey = new Image ("dat/AnyKey.png");
		arrow = new Image ("dat/arrow.png");
		world = LevelWriter.readWorld(WorldID);
		font = new UnicodeFont ("dat/segoe.ttf", 20, false, false);
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.white));
		font.loadGlyphs();
		p = world.getPlayer();
		engine = new Engine(world);
		gameStep = 0;
		alpha = 0f;
		alphaChange = -1.0f;
	}

	@Override
	/**
	 * This method contains the game logic of the state. It will check for the
	 * internal state (what part of the game you are at), and look for input accordingly.
	 * It will also in some steps notify the engine that action must be taken.
	 * 
	 * @param gc the GameContainer that this state is drawn on
	 * @param parent the StateBasedGame that contains this state.
	 * @param delta the number of milliseconds since the last update.
	 * 
	 */
	public void update (GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		Input i = gc.getInput();

		if (gameStep == 0)
		{
			if (i.isKeyPressed (Input.KEY_ENTER))
				gameStep = 1;
			if (alpha >= 1.0f || alpha <= 0.0f)
				alphaChange = -alphaChange;

			alpha += alphaChange * delta / 900;
			anyKey.setAlpha (alpha);
		}
		else if (gameStep == 1)
		{
			p.setXSpeed(i.getMouseX() - p.getX());
			p.setYSpeed(i.getMouseY() - p.getY());

			if (i.isMousePressed (Input.MOUSE_LEFT_BUTTON))
				gameStep = 2;
		}
		else if (gameStep == 2)
		{
			engine.recalculateMovement(2 * delta);
			engine.handleMovement(2 * delta);
			if (engine.isInGoal(p))
			{
				gameStep = 3;
				score = engine.getScore();
			}
			if (engine.isDoubleBounce())
			{
				gameStep = 4;
			}
		}
		else if (gameStep >= 10)
		{
			if (i.isKeyPressed (Input.KEY_P))
				gameStep -= 10;
		}
		if (i.isKeyPressed(Input.KEY_H) && gameStep == 3)
		{
			String name = JOptionPane.showInputDialog("Please enter your name:");
			HighScore hs = new HighScore(score, name, WorldID);
			int place = HighScoreManager.addHighScore(hs);
			if(place != -1)
			{
				String ending;
				if(place == 1)
					ending = "st";
				else if(place == 2)
					ending = "nd";
				else if(place == 3)
					ending = "rd";
				else
					ending = "th";
				JOptionPane.showMessageDialog(null,
						"Congratulations, you scored " + place + ending + " place!");
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Sorry, your score wasn't high enough to be in the HighScores table.");
			}
			parent.enterState(1);
		}
		if (i.isKeyPressed (Input.KEY_P) && gameStep <9)
			gameStep += 10;
		if (i.isKeyPressed (Input.KEY_R))
			init (gc, parent);
	}

	@Override
	/**
	 * This method renders the world, as well as any other messages/images that need
	 * to be rendered in accordance with the update method above.
	 * 
	 * @param gc the GameContainer that this state is drawn on
	 * @param parent the StateBasedGame that contains this state.
	 * @param g the graphics context of the GameContainer
	 * 
	 */
	public void render (GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.setFont (font);
		engine.renderImages(g);
		if (gameStep == 0)
		{
			anyKey.draw (250,500);
		}
		else if (gameStep == 1)
		{
			Input i = gc.getInput();
			double angle = -Math.atan((i.getMouseX()-p.getX())/(i.getMouseY()-p.getY()));
			if (i.getMouseY() > p.getY()|| Math.abs(i.getMouseY()-p.getY())<0.0001)
				angle += Math.PI;
			angle = angle*180/Math.PI;
			Image temp  = arrow.getScaledCopy((float)Math.min(Math.sqrt(p.getXSpeed()*p.getXSpeed() + 
					p.getYSpeed() * p.getYSpeed()) / 250, 1.0));
			temp.setRotation ((float) angle);
			temp.drawCentered ((float)(p.getX()+p.getWidth()/2),(float)(p.getY()+p.getHeight()/2));
		}
		else if (gameStep == 3)
		{
			g.setColor (org.newdawn.slick.Color.black);
			g.fillRect(150,500, 500, 70);
			g.setColor (org.newdawn.slick.Color.white);
			g.drawString("STAGE CLEAR!", 150, 500);
			g.drawString("PRESS 'R' TO RETRY OR 'Q' TO EXIT TO MENU" +
					"\nPRESS 'H' TO SUBMIT YOUR SCORE (THIS WILL EXIT)", 150, 540);
		}
		else if (gameStep == 4)
		{
			g.setColor (org.newdawn.slick.Color.black);
			g.fillRect(150,500, 500, 70);
			g.setColor (org.newdawn.slick.Color.white);
			g.drawString("STAGE LOST : STOPPED MOVING", 150, 500);
			g.drawString("PRESS 'R' TO RETRY OR 'Q' TO EXIT TO MENU",150, 540);
		}
		else if (gameStep >= 10)
			pause.draw (250, 260);
	}

	@Override
	/**
	 * This method returns the id of the state, in this case 3.
	 * 
	 * @ return the id of the state.
	 */
	public int getID()
	{
		return 3;
	}
}
