import org.newdawn.slick.Image;

/**
 * The Player Class.
 * <p>
 * This class represents the playing Movable GameObject
 * that is controlled by user input. It is stored within 
 * a World Object. It is Solid.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.0 : May 9, 2011
 * @since May 9, 2011
 * @see Movable
 * @see GameObject
 * @see Engine
 */
public class Player extends AbstractMovable 
implements Solid
{
	private static final long serialVersionUID = 7251306299153408638L;

	/**
	 * Create a new instance of a Player Object.
	 * This Object is controlled by the user during game play.
	 * 
	 * @param img The image for this Object to be rendered as.
	 * @param initX The initial X coordinate for this Object.
	 * @param initY The initial Y coordinate for this Object.
	 * @param height The vertical size for this Object.
	 * @param width The horizontal size for this Object.
	 */
	public Player(Image img, int initX, int initY, int height, int width)
	{
		super(Pole.PLAYER,img, initX, initY, height, width, 1000.0);
	}
}
