/**
 * This is the abstract class for the Movable interface
 * that gives its subclasses all the functionality of a
 * normal GameObject, but it can also move around its
 * enclosing World Object.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornikov
 * @version 1.0.0.2 : April 11, 2011
 * @since April 6, 2011
 * @see GameObject
 * @see Movable
 */
public abstract class AbstractMovable extends AbstractGameObject
implements Movable
{

	/**
	 * Inherited constructor
	 * 
	 * @param pole the pole of the new Movable.
	 * @param initX the starting x position of the new Movable.
	 * @param initY the starting Y position of the new Movable.
	 * @param height the height of the new Movable.
	 * @param width the width of the new Movable.
	 */
	public AbstractMovable(Pole pole, int initX, int initY, int height,
			int width, double str) {
		super(pole, initX, initY, height, width,str);
	}

	/**
	 * The horizontal speed of the Player. Positive values indicate movement to the right.
	 * 
	 */
	private double xSpeed = 0.0;
	/**
	 * The vertical speed of the Player. Positive values indicate movement downwards.
	 * 
	 */
	private double ySpeed = 0.0;

	/**
	 * The gravitational constant. Used to calculate gravity
	 * 
	 */
	final static double G = 9.81;

	@Override
	/**
	 * Increases the speed at which the object is moving downwards to simulate gravity. This will increment
	 * the full acceleration once per second, not one per tick.
	 * 
	 * @param delta the number of milliseconds since the last frame.
	 */
	public void countGravity(int delta)
	{
		ySpeed += delta*G/1000;
	}

	protected double countXMagnetism(GameObject obj)
	{
		double x = 0;
		if (obj.getPole().equals(Pole.NONE))
			return 0;
		if (obj.getPole().equals(Pole.PARA))
			x = (this.getStrength()*obj.getStrength())/(4*Math.PI*(obj.getX()-getX()));
		if (obj.getPole().equals(Pole.DIA))
			x = (this.getStrength()*obj.getStrength())/(4*Math.PI*(getX()-obj.getX()));
		return x;
	}

	protected double countYMagnetism(GameObject obj)
	{
		double y = 0;
		if (obj.getPole().equals(Pole.NONE))
			return 0;
		if (obj.getPole().equals(Pole.PARA))
			y = (this.getStrength()*obj.getStrength())/(4*Math.PI*(obj.getY()-getY()));
		if (obj.getPole().equals(Pole.DIA))
			y = (this.getStrength()*obj.getStrength())/(4*Math.PI*(getY()-obj.getY()));
		return y;
	}

	@Override
	/**
	 * Increases or decreases the vertical and horizontal velocities based on the magnetic attraction to
	 * a certain object.
	 * 
	 * @param obj The object with relation to which magnetic forces will be calculated.
	 * @param delta the number of milliseconds since the last frame.
	 */
	public void countMagnetism(GameObject obj, int delta)
	{
		double x = 0;  //the x speed to be added.
		double y = 0;  //the y speed to be added.
		if (obj.getPole().equals(Pole.NONE))
			return;
		x = countXMagnetism(obj);
		y = countYMagnetism(obj);
		xSpeed += x*delta/1000;
		ySpeed += y*delta/1000;
	}

	@Override
	public double getXSpeed() {
		return xSpeed;
	}

	@Override
	public void setXSpeed(double speed) {
		xSpeed = speed;

	}

	@Override
	public double getYSpeed() {
		// TODO Auto-generated method stub
		return ySpeed;
	}

	@Override
	public void setYSpeed(double speed) {
		ySpeed = speed;

	}

	@Override
	public void moveTo(double x, double y)
	{
		setX(x);
		setY(y);
	}

	@Override
	public boolean canMoveTo(double x, double y)
	{
		if (x - getHeight() < 0 || y - getWidth() < 0)
			return false;
		if (x + getHeight() > getEnclosingWorld().getHeight() ||
				y + getWidth() > getEnclosingWorld().getWidth())
			return false;
		return true;
	}

	public boolean isMoving()
	{
		return getXSpeed() != 0 && getYSpeed() != 0;
	}
}