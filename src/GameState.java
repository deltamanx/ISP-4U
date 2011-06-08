import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameState extends BasicGameState

{
	private World<GameObject> world;
	private Engine engine;
	private int gameStep;
	private Image anyKey;
	private Image pause;
	private float alpha;
	private float alphaChange;
	private Player p;
	private int score;

	public GameState()
	{
	}

	public void init (GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		pause = new Image ("dat/Pause.png");
		anyKey = new Image ("dat/AnyKey.png");
		world = new LimitedWorld<GameObject>(600, 800, 0.85, 700,300,50,50);
		world.addToWorld(new Magnet(Pole.DIA, 750, 275, 50, 50, 300));
		p = new Player(250, 300, 10, 10);
		p.addSelfToWorld(world);
		//p2.addSelfToWorld(world);
		//p3.addSelfToWorld(world);
		engine = new Engine(world);
		gameStep= 0;
		alpha = 0f;
		alphaChange = -1.0f;
	}

	public void update (GameContainer gc, StateBasedGame parent, int delta)
	throws SlickException
	{
		Input i = gc.getInput();

		if (gameStep == 0){

			if (i.isKeyDown (Input.KEY_ENTER))
				gameStep = 1;
			if (alpha >= 1.0f || alpha <= 0.0f)
				alphaChange = -alphaChange;

			alpha += alphaChange*delta/900;
			anyKey.setAlpha (alpha);
		}
		else if (gameStep == 1)
		{
			p.setXSpeed(i.getMouseX()-p.getX());
			p.setYSpeed(i.getMouseY()-p.getY());

			if (i.isMousePressed (Input.MOUSE_LEFT_BUTTON))
				gameStep = 2;
		}
		else if (gameStep == 2)
		{
			engine.recalculateMovement(2*delta);
			engine.handleMovement(2*delta);
			if (engine.isInGoal(p)){
				gameStep = 3;
				score = engine.getScore();
			}
		}
		else if (gameStep == 3){	
			if (i.isKeyPressed (Input.KEY_Q))
				parent.enterState(1);
		}
		else if (gameStep >= 10){
			if (i.isKeyPressed (Input.KEY_P))
				gameStep -= 10;
			if (i.isKeyPressed (Input.KEY_Q))
				parent.enterState(1);
			if (i.isKeyPressed (Input.KEY_R))
				init (gc, parent);
		}
		if (i.isKeyPressed (Input.KEY_P)&& gameStep <9)
			gameStep += 10;
	}



	public void render (GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		engine.renderImages(g);
		if (gameStep == 0)
			anyKey.draw (250,500);
		else if (gameStep == 3)
			g.drawString("A winner is you! Your score is : "+score + ". Press 'Q' to quit.", 250, 500);
		else if (gameStep >= 10)
			pause.draw (250, 260);
	}

	@Override
	public int getID()
	{
		return 3;
	}
}
