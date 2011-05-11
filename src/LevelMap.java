import java.util.HashMap;
import java.io.*;

/**
 * Deprecated due to lack of use.
 * Effectively replaced by World.
 * 
 * @author Mihail Kurbako
 *
 * @param <E>
 * @param <F>
 * 
 * @see World
 */
@Deprecated
public class LevelMap<E, F> extends HashMap<E, F>
	implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7012384883278341848L;

	public LevelMap()
	{
		super();
	}
}
