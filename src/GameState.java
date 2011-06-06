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
	private float alpha;
	private float alphaChange;
	private Player p;

	public GameState()
	{
	}

	public void init (GameContainer gc, StateBasedGame parent)
	throws SlickException
	{
		anyKey = new Image ("dat/AnyKey.png");
		world = new LimitedWorld<GameObject>(600, 800, 0.85);
		world.addToWorld(new Magnet(Pole.PARA, 20, 20, 80, 20, 150));
		world.addToWorld(new Magnet(Pole.PARA, 400, 300, 80, 20, 150));
		//world.addToWorld(new Magnet(Pole.DIA, 20, 580, 20, 80, 1500));
		//world.addToWorld(new Magnet(Pole.DIA, 520, 20, 20, 80, 1500));
		p = new Player(200, 300, 10, 10);
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
		else
		{
			engine.recalculateMovement(2*delta);
			engine.handleMovement(2*delta);
		}
		if (i.isKeyDown (Input.KEY_ESCAPE))
			parent.enterState(1);
	}



	public void render (GameContainer gc, StateBasedGame parent, Graphics g)
	throws SlickException
	{
		if (gameStep == 0)
			anyKey.draw (250,500);
		engine.renderImages(g);
	}

	@Override
	public int getID()
	{
		return 3;
	}
}
