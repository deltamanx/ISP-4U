
public class Magnet extends AbstractGameObject
	implements Moveable
{
	public Magnet(Pole pole, int initX, int initY)
	{
		super(pole, initX, initY);
	}

	public void moveTo(int x, int y) 
	{
		setX(x);
		setY(y);
	}
}
