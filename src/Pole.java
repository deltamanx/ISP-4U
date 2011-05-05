
public final class Pole 
{
	public static final Pole NONE = new Pole(-1);
	public static final Pole NORTH = new Pole(0);
	public static final Pole SOUTH = new Pole(1);
	
	private int pole;
	
	private Pole(int pole)
	{
		this.pole = pole;
	}
	
	public int getPole()
	{
		return pole;
	}
	
	public boolean isAttracted(Pole p)
	{
		if (p.getPole() == -1 || getPole() == -1)
			return false;
		if (p.getPole() == getPole())
			return false;
		return true;
	}
	
	public boolean equals(Object o)
	{
		if (!(o instanceof Pole))
			return false;
		if (getPole() == ((Pole)o).getPole())
			return true;
		return false;
	}
}
