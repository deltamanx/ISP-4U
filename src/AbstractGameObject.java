
public abstract class AbstractGameObject 
	implements GameObject 
{
	private Pole objPole;
	private int currentX;
	private int currentY;
	
	public AbstractGameObject(Pole pole, int initX, int initY)
	{
		setPole(pole);
		currentX = initX;
		currentY = initY;
	}

	public int getX() 
	{
		return currentX;
	}
	public void setX(int x)
	{
		currentX = x;
	}

	public int getY() 
	{
		return currentY;
	}
	public void setY(int y)
	{
		currentY = y;
	}

	public void setPole(Pole objPole)
	{
		this.objPole = objPole;
	}

	public Pole getPole()
	{
		return objPole;
	}

}
