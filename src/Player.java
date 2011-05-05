
public class Player extends AbstractGameObject 
	implements Moveable
{
	public Player(Pole pole, int initX, int initY)
	{
		super(pole, initX, initY);
	}

	public void moveTo(int x, int y)
	{
		setX(x);
		setY(y);
	}
}
