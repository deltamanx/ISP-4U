
public final class Pole 
{
	public static final Pole PLAYER = new Pole(-2);
	public static final Pole NONE = new Pole(-1);
	public static final Pole DIA = new Pole(0);
	public static final Pole PARA = new Pole(1);
	
	private int pole;
	
	private Pole(int pole)
	{ this.pole = pole; }
	
	/**
	 * This method accesses the <code>int</code> PDT value that stores the
	 * polar value of a particular Pole Object. This field is set by the
	 * (private) constructor during program start up, as no new Pole Objects
	 * can be created, since the only necessary Pole values are stored in
	 * the <code>static final</code> Pole Objects within this class.
	 * 
	 * @return The int value associated with this Pole Object.
	 */
	public int getPole()
	{
		return pole;
	}
	
	/**
	 * This method returns a value that dictates whether a given Pole
	 * and this Pole are attracted to each other.
	 * 
	 * @param p The given Pole Object for this method to compare.
	 * @return <code>true</code> if the Objects are attracted, <code>false</code> otherwise.
	 */
	public boolean isAttracted(Pole p)
	{
		if (!(getPole()== -2))
			return false;
		if (p.getPole()<0 )
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
