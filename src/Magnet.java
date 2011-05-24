/**
 * the Magnet class.
 * <p>
 * This Object is assigned a Pole Object during construction.
 * It is a stationary Solid Object that affects the Player Object's
 * trajectory.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.0 : May 9, 2011
 * @since May 9, 2011
 * @see GameObject
 * @see Solid
 */
public class Magnet extends AbstractGameObject
implements Solid
{
	/**
	 * The Sole construct for a Magnet Object.
	 * It creates a magnet with a given Pole Object that
	 * has a given strength of its magnetic attraction
	 * or repulsion characteristics.
	 */
	public Magnet(Pole pole, int initX, int initY, int height, int width, double str)
	{
		super(pole, initX, initY, height, width, str);
	}

}
