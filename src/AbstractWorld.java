import java.util.ArrayList;

public class AbstractWorld<E>
	implements World<E>
{
	private ArrayList<E> world;
	private int height;
	private int width;
	
	/**
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
	
	/**
	 * 
	 * @param obj The Object to be added to the World.
	 * @return <code>true</code>.
	 */
	@Override
	public boolean addToWorld(E obj)
	{
		return world.add(obj);
	}

	/**
	 * 
	 * @param obj The Object that this method searches for.
	 * @return <code>true</code> if the Object exists in the World, <code>false</code> otherwise.
	 */
	@Override
	public boolean existsInWorld(E obj)
	{
		return world.contains(obj);
	}

	/**
	 * 
	 * @param obj The Object to remove from the World.
	 * @return The Object that was removed from the World if it existed in the World, <code>null</code otherwise.
	 */
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

	/**
	 * 
	 * @return The current value for the height of this World Object.
	 */
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

	/**
	 * 
	 * @return The current value for the width of this World Object.
	 */
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
