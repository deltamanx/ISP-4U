import java.util.ArrayList;

public final class Engine
{
	private World<GameObject> world;
	
	public Engine(World<GameObject> world)
	{
		setWorld(world);
	}
	
	/**
	 * This method will be invoked by a Thread.
	 * This method will invoke methods to update velocity values.
	 */
	public void recalculateMovement()
	{
		//Place holder
	}
	/**
	 * This method will be invoked by a Thread.
	 * For now, a place holder and a reminder!
	 */
	public void handleMovement()
	{
		ArrayList<Movable> moving = ((AbstractWorld<GameObject>)getWorld()).getAllMovable();
		for (int i = 0; i < moving.size(); i++)
		{
			if(moving.get(i).isMoving())
			{
				//Need methods to be able to calculate displacement based on speed of Movable.
				//Implement those methods!
			}
		}
	}

	public void setWorld(World<GameObject> world)
	{
		this.world = world;
	}

	public World<GameObject> getWorld()
	{
		return world;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean canMoveObject(GameObject go, int x, int y)
	{
		if (!go.getEnclosingWorld().equals(getWorld()))
			return false;
		if (!(go instanceof Movable))
			return false;
		if (!((Movable)go).canMoveTo(x, y))
			return false;
		if (go instanceof Solid && ((AbstractWorld)go.getEnclosingWorld()).getAtCoords(x, y) != null)
			return false;
		return true;
	}
	
	public void performMove(GameObject go, int x, int y)
	{
		if (go instanceof Movable)
		{
			((Movable)go).moveTo(x, y);
		}
		else
			return;
	}
}
