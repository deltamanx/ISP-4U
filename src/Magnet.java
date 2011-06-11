import org.newdawn.slick.Image;

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
	private static final long serialVersionUID = 1645847507298592810L;

	/**
	 * The Sole construct for a Magnet Object.
	 * It creates a magnet with a given Pole Object that
	 * has a given strength of its magnetic attraction
	 * or repulsion characteristics.
	 */
	public Magnet(Pole pole,Image img,int initX, int initY, int width, int height, double str)
	{
		super(pole,img,  initX, initY, width, height, str);
	}

}
