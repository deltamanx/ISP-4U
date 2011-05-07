public final class Engine
{
	private World<GameObject> world;
	
	public Engine(World<GameObject> world)
	{
		setWorld(world);
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
}
