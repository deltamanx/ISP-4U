
import org.newdawn.slick.Image;

/**
 * <h1>The Block Class.</h1>
 * <p>
 * This is an immobile block. It doesn't move, but is in fact
 * Solid, and therefore the Player Object will bounce off of this
 * Object.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.0 : May 5, 2011
 * @since April 5, 2011
 * @see Solid
 * @see Movable
 */
public class Block extends AbstractGameObject
implements Solid
{
	private static final long serialVersionUID = 6424598912580064571L;

	/**
	 * This GameObject is a non-moving block. It has no magnetic properties
	 * and cannot move as it does not implement the Movable interface, and
	 * is not a subclass of AbstractMovable. It is however Solid therefore,
	 * its space cannot be intruded upon.
	 * 
	 * @param initX The X Coordinate for this Object.
	 * @param initY The Y Coordinate for this Object.
	 * @param height The Object's vertical size.
	 * @param width The Object's horizontal size.
	 */
	public Block(Image img, int initX, int initY, int width, int height) 
	{
		super(Pole.NONE,img,initX, initY, width, height,0);
	}
}
