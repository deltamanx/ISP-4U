
public class Magnet extends AbstractGameObject
	implements Movable, Solid
{
	public Magnet(Pole pole, int initX, int initY, int height, int width)
	{
		super(pole, initX, initY, height, width);
	}

	public void moveTo(int x, int y) 
	{
		setX(x);
		setY(y);
	}
	
	@Override
	public boolean canMoveTo(int x, int y)
	{
		if (x - getHeight() < 0 || y - getWidth() < 0)
			return false;
		if (x + getHeight() > getEnclosingWorld().getHeight() ||
			y + getWidth() > getEnclosingWorld().getWidth())
			return false;
		return true;
	}
}
