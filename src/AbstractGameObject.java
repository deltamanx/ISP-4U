
public abstract class AbstractGameObject 
	implements GameObject 
{
	private World<GameObject> enclosingWorld;
	private Pole objPole;
	private int currentX;
	private int currentY;
	private int height;
	private int width;
	private double strength; //added for magnetic calculations.
	
	public AbstractGameObject(Pole pole, int initX, int initY, int height, int width, double str)
	{
		setPole(pole);
		setX(initX);
		setY(initY);
		this.height = height;
		this.width = width;
		strength = str;
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
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}

	public void setPole(Pole objPole)
	{
		this.objPole = objPole;
	}

	public Pole getPole()
	{
		return objPole;
	}
	
	public double getStrength() 
	{
		return strength;
	}
	
	@Override
	public void addSelfToWorld(World<GameObject> world)
	{
		enclosingWorld = world;
		if(world.existsInWorld(this))
			return;
		world.addToWorld(this);
	}

	@Override
	public World<GameObject> getEnclosingWorld()
	{
		return enclosingWorld;
	}
	
	@Override
	public void removeSelfFromWorld()
	{
		if (getEnclosingWorld() == null)
			return;
		getEnclosingWorld().removeFromWorld(this);
		enclosingWorld = null;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 8;
		hash = (getX() * 97) + hash;
		hash = (getY() * 97) + hash;
		hash = (getHeight() * 97) + hash;
		hash = (getWidth() * 97) + hash;
		hash = (getPole().getPole() * 97) + hash;
		return hash;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof AbstractGameObject))
			return false;
		if (hashCode() == o.hashCode())
			return true;
		return false;
	}
}
