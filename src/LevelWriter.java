
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
/**
 * This class facilitates the reading and writing of level data. It
 * is used to retrieve level data, as well as to write said level
 * data. This class is used only in the GameState class.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0, June 11, 2011
 * @since June 11, 2011
 * 
 */
public class LevelWriter
{
	
	/**
	 * Writes a world with a given reference id to a file.
	 * 
	 * @param name the world-id at which to save it. Formated as Difficulty (1-3).Level (0-9)
	 * @param world the world to save into the file.
	 * 
	 * @return if succeed in writing, return <code>true</code>, else return <code>false</code>
	 */
	public static boolean writeWorld(String name, World<GameObject> world)
	{
		try
		{
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(new FileOutputStream("levelData/" + name + ".dat"));
			oos.writeUnshared(world);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Reads a world from a file with given reference id.
	 * 
	 * @param name the world-id from which to read. Formated as Difficulty (1-3).Level (0-9)
	 * @return the world object read from the file.
	 */
	@SuppressWarnings("unchecked")
	public static World<GameObject>readWorld(String name)
	{
		try
		{
			ObjectInputStream ois;
			ois = new ObjectInputStream(new FileInputStream("levelData/" + name + ".dat"));
			return (World<GameObject>) ois.readUnshared();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
