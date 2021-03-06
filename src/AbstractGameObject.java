
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class is the abstract class that is used as the super
 * class for all GameObjects. Though no methods in this class
 * are specifically abstract, some subclasses may have to, but
 * not necessarily will override methods in this class. This
 * method overrides all inherited methods from GameObject with
 * the default code that will be used by most if not all instances
 * and subclasses of GameObjects. Subclasses of this class
 * do not have mutator methods X and Y values, as they will never
 * be moved. In order for a GameObject to be movable, they must
 * either implement the Movable interface, of be subclasses of
 * AbstractMovable.
 * 
 * @author Mihail Kurbako
 * @author Dan Zapornikov
 * @version 1.0.0.1 : April 11, 2011
 * @since April 5, 2011
 * @see GameObject
 * @see AbstractMovable
 * @see World
 */
public abstract class AbstractGameObject 
implements GameObject 
{
	private static final long serialVersionUID = -1640215226459075153L;
	private World<GameObject> enclosingWorld;
	private Pole objPole;
	private double currentX;
	private double currentY;
	private int height;
	private int width;
	private String path;
	private transient Image img;
	private double strength; //added for magnetic calculations.

	/**
	 * The sole Constructor for AbstractGameObject.
	 * This construct a new instance of the AbstractGameObject
	 * class. A call to this Constructor is made by subclasses
	 * of this class with given parameters to set the values
	 * for inherited attributes.
	 * 
	 * @param pole The Pole Object assigned to this GameObject.
	 * @param initX The initial X value for this GameObject. 
	 * @param initY The initial Y value for this GameObject.
	 * @param height The vertical size attribute for this GameObject.
	 * @param width The horizontal size attribute for this GameObject.
	 * @param str The magnetic strength value for this GameObject.
	 */
	public AbstractGameObject(Pole pole, Image img, int initX, int initY, int width, int height, double str)
	{
		setPole(pole);
		setX(initX);
		setY(initY);
		this.height = height;
		this.width = width;
		strength = str;
		this.img = img;
		path = img.getResourceReference();
	}

	//Documented in interface
	public double getX() 
	{
		return currentX;
	}
	/**
	 * Sets the value for the X coordinate of this
	 * GameObject.
	 * 
	 * @param x The new value for the X coordinate of this GameObject.
	 */
	public void setX(double x)
	{
		currentX = x;
	}

	//Documented in interface
	public double getY() 
	{
		return currentY;
	}
	/**
	 * Sets the value for the Y coordinate of this
	 * GameObject.
	 * 
	 * @param y The new value for the Y coordinate of this GameObject.
	 */
	public void setY(double y)
	{
		currentY = y;
	}
	//Documented in interface
	public int getHeight()
	{
		return height;
	}
	//Documented in interface
	public int getWidth()
	{
		return width;
	}

	/**
	 * Sets the value for the Pole Object assigned
	 * to this GameObject.
	 * 
	 * @param objPole The new value for the Pole Object for this GameObject.
	 */
	public void setPole(Pole objPole)
	{
		this.objPole = objPole;
	}
	//Documented in interface
	public Pole getPole()
	{
		return objPole;
	}

	//Documented in interface
	public Image getImage()
	{
		if(img != null)
			return img;
		else
		{
			try
			{
				Image i =  new Image(path);
				return i;
			} 
			catch (SlickException e)
			{
				e.printStackTrace();
				try
				{
					return new Image(10, 10);
				} 
				catch (SlickException e1)
				{
					e1.printStackTrace();
					return null;
				}
			}
		}
	}

	//Documented in interface
	public double getStrength() 
	{
		return strength;
	}

	//Documented in interface
	@Override
	public void addSelfToWorld(World<GameObject> world)
	{
		enclosingWorld = world;
		if(world.existsInWorld(this))
			return;
		world.addToWorld(this);
	}

	//Documented in interface
	@Override
	public World<GameObject> getEnclosingWorld()
	{
		return enclosingWorld;
	}

	//Documented in interface
	@Override
	public void removeSelfFromWorld()
	{
		if (getEnclosingWorld() == null)
			return;
		getEnclosingWorld().removeFromWorld(this);
		enclosingWorld = null;
	}

	/**
	 * Overrides the hashCode method from java.lang.Object.
	 * 
	 * @return The hash code for this GameObject.
	 */
	@Override
	public int hashCode()
	{
		int hash = 8;
		hash = (int) ((getX() * 97) + hash);
		hash = (int) ((getY() * 97) + hash);
		hash = (getHeight() * 97) + hash;
		hash = (getWidth() * 97) + hash;
		hash = (getPole().getPole() * 97) + hash;
		return hash;
	}

	/**
	 * Overrides the equals method from java.lang.Object.
	 * Compares two Objects.
	 * 
	 * @return <code>true</code> if the Objects are equal, <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof AbstractGameObject))
			return false;
		if (hashCode() == o.hashCode())
			return true;
		return false;
	}
}
