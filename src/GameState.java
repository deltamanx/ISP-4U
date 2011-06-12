

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

	public void init (GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		pause = new Image ("dat/Pause.png");
		anyKey = new Image ("dat/AnyKey.png");
		arrow = new Image ("dat/arrow.png");
		/*Image playerImg = new Image ("dat/player.png");
		Image dSH = new Image ("dat/Dia-Large.png");
		Image pLH = new Image ("dat/Para-Large-Horiz.png");
		Image pL = new Image ("dat/Para-Large.png");
		Image bS = new Image ("dat/WOOD-Small.png");
		Image bL = new Image ("dat/WOOD-Large.png");
		Image bLH = new Image ("dat/WOOD-Large-Horiz.png");
		pause = new Image ("dat/Pause.png");
		anyKey = new Image ("dat/AnyKey.png");
		world = new LimitedWorld<GameObject>(600, 800, 0.85, 270, 550, 50, 100);

		world.addToWorld(new Block(bLH, 210, 220, 120, 30));
		world.addToWorld(new Block(bL, 320, 0, 30, 120));
		world.addToWorld(new Block(bL, 320, 220, 30, 120));
		world.addToWorld(new Block(bL, 320, 330, 30, 120));
		world.addToWorld(new Block(bL, 320, 440, 30, 120));
		world.addToWorld(new Block(bL, 320, 550, 30, 120));
		world.addToWorld(new Magnet(Pole.DIA, dSH,  450, 110, 30, 120,300));
		world.addToWorld(new Block(bL, 450, 220, 30, 120));
		world.addToWorld(new Block(bL, 450, 330, 30, 120));
		world.addToWorld(new Block(bL, 450, 440, 30, 120));
		world.addToWorld(new Block(bL, 450, 550, 30, 120));
		world.addToWorld(new Block(bL, 450, 0, 30, 120));


		p = new Player(playerImg, 400, 50, 10, 10);
		p.addSelfToWorld(world);
		System.out.println(LevelWriter.writeWorld("3.2", world));*/

		world = LevelWriter.readWorld(WorldID);
		font = new UnicodeFont ("dat/segoe.ttf",20,false,false);
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
	public void update (GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		Input i = gc.getInput();

		if (gameStep == 0){

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
			if (engine.isDoubleBounce()){
				gameStep = 4;
			}
		}
		else if (gameStep >= 10)
		{
			if (i.isKeyPressed (Input.KEY_P))
				gameStep -= 10;
		}
		if (i.isKeyPressed (Input.KEY_P) && gameStep <9)
			gameStep += 10;
		if (i.isKeyPressed (Input.KEY_R))
			init (gc, parent);
	}

	@Override
	public void render (GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		g.setFont (font);
		engine.renderImages(g);
		if (gameStep == 0){
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
		else if (gameStep == 3){
			g.setColor (org.newdawn.slick.Color.black);
			g.fillRect(150,500, 500, 70);
			g.setColor (org.newdawn.slick.Color.white);
			g.drawString("STAGE CLEAR!", 150, 500);
			g.drawString("PRESS 'R' TO RETRY OR 'Q' TO EXIT TO MENU", 150, 540);
		}
		else if (gameStep == 4){
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
	public int getID()
	{
		return 3;
	}
}
