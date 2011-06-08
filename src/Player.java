

/**
 * The Player Class.
 * <p>
 * This class represents the playing Movable GameObject
 * that is controlled by user input. This class's full
 * implementation will be added at a later time, most
 * likely during or closer to code testing.
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
	public Player(int initX, int initY, int height, int width)
	{
		super(Pole.PLAYER, initX, initY, height, width, 1000.0);
	}
}
