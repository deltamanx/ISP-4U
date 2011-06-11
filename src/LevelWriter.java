
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LevelWriter
{
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
