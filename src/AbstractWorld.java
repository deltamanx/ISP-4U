import java.util.ArrayList;

public class AbstractWorld<E>
	implements World<E>
{
	private ArrayList<E> world;
	private int height;
	private int width;
	
	/**
	 * Creates an instance of the World Object with given parameters
	 * (namely width and height). These parameter values are set in
	 * the constructor along with the instantiation of the private field
	 * world, which is an <code>ArrayList\<E\></code> Object in which
	 * data for all GameObjects in this world is stored.
	 * 
	 * @param h The initial height value for the World
	 * @param w The initial width value for the World
	 */
	public AbstractWorld(int h, int w)
	{
		setWorld(new ArrayList<E>());
		setHeight(h);
		setWidth(w);
	}
	
	@Override
	public boolean addToWorld(E obj)
	{
		return world.add(obj);
	}

	@Override
	public boolean existsInWorld(E obj)
	{
		return world.contains(obj);
	}

	@Override
	public E removeFromWorld(E obj)
	{
		return (world.indexOf(obj) != -1 ? world.remove(world.indexOf(obj)): null);
	}

	/**
	 * 
	 * @param world The new value for this field.
	 */
	public void setWorld(ArrayList<E> world)
	{
		this.world = world;
	}

	/**
	 * 
	 * @return The current value for this field.
	 */
	public ArrayList<E> getWorld()
	{
		return world;
	}

	/**
	 * 
	 * @param height The new value for the height of this World Object.
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getHeight()
	{
		return height;
	}

	/**
	 * 
	 * @param width The new value for the width of this World Object.
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getWidth()
	{
		return width;
	}
	
	/**
	 * 
	 * @param x The X value the Object's bounds exist within.
	 * @param y The Y value the Object's bounds exist within.
	 * @return The first instance of a GameObject within the area of (X, Y), <code>null</code> otherwise.
	 */
	public Object getAtCoords(int x, int y)
	{
		for(int i = 0; i < world.size(); i++)
		{
			if(world.get(i) instanceof GameObject)
			{
				GameObject go = (GameObject)world.get(i);
				int minX = go.getX() - go.getHeight();
				int maxX = go.getX() + go.getHeight();
				int minY = go.getY() - go.getWidth();
				int maxY = go.getY() + go.getWidth();
				if((minX < x && maxX > x) && (minY < y && maxY > y))
					return go;
			}
		}
		return null;
	}

	public ArrayList<Movable> getAllMovable()
	{
		ArrayList<Movable> movableObjects = new ArrayList<Movable>();
		for (int i = 0; i < getWorld().size(); i++)
			if (getWorld().get(i) instanceof Movable)
				movableObjects.add((Movable)getWorld().get(i));
		return movableObjects;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 6;
		hash = (getWidth() * 97) + hash;
		hash = (getHeight() * 97) + hash;
		hash = world.hashCode() + hash;
		return hash;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof AbstractWorld))
			return false;
		if (hashCode() == o.hashCode())
			return true;
		return false;
	}
}
