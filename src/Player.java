
public class Player extends AbstractMovable 
	implements Movable, Solid
{	
	
	public Player(int initX, int initY, int height, int width)
	{
		super(Pole.PLAYER, initX, initY, height, width,10.0);
	}



}
