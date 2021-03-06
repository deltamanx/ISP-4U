

/**
 * A simple World that can enclose GameObject or any
 * other class that is specified by <b>E</b>.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornikov
 * @version 1.0.0.0 : April 9, 2011
 * @since April 9, 2011
 * @see World
 * @see AbstractWorld
 * @see GameObject
 * @param <E> Usually GameObject
 */
public class LimitedWorld<E> extends AbstractWorld<E>
{
	private static final long serialVersionUID = 8150098151025721822L;

	/**
	 * A constructor that builds this World.
	 * 
	 * @param w The width value for this world.
	 * @param h The height value for this world.
	 */
	public LimitedWorld(int w, int h, double solidity, double gx, double gy, double gr, int s)
	{
		super(w, h, solidity,gx,gy,gr,s);
	}
}
