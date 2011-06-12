
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The HighScoreManager Class.
 * <p>
 * This Class is used to manage, retrieve and manipulate
 * the HighScore table. It's default constructor is hidden
 * as this class does not require instantiation as all methods
 * are declared in static context. This is done because they
 * may be accessed from numerous locations, but they will only
 * ever be invoked once, and the result are not instance
 * dependent.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.0 : April 11, 2011
 * @since April 11, 2011
 * @see HighScore
 * @see HighScoreState
 */
public class HighScoreManager
{	
	private HighScoreManager() 
	{ /*Suppress Default Constructor*/ }
	
	/**
	 * This method attempt to retrieve the HighScores
	 * from a File. If it unsuccessful, which is likely
	 * due to the File not existing or being corrupt, it
	 * will create a new blank File filled with Empty slots
	 * and retry. It will attempt to do so up to a maximum
	 * of 3 times, after which point, if unsuccessful it will
	 * return null.
	 * 
	 * @param retries The number of retries it has attempted.
	 * @return The HighScores from a File, or <code>null</code>.
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<HighScore> getHighScores(int retries)
	{
		if(retries >= 3)
			return null;
		try
		{
			ObjectInputStream ois = new ObjectInputStream (
					new FileInputStream("dat/HighScores.dat"));
			return (ArrayList<HighScore>)ois.readObject();
		}
		catch (FileNotFoundException e)
		{
			createHighScores();
			//e.printStackTrace();
			return getHighScores(retries++);
		}
		catch (IOException e)
		{
			//e.printStackTrace();
			return getHighScores(retries++);
		} 
		catch (ClassNotFoundException e)
		{
			//File is probably corrupt or intended for an
			//earlier release of the program.
			createHighScores();
			e.printStackTrace();
			return getHighScores(retries++);
		}
	}
	
	/**
	 * This method attempts to add a HighScore to the
	 * list of existing HighScores. It will only do so if
	 * the given HigScore is sufficiently High. If 2 scores
	 * on the HighScore table are equal, the HighScore that
	 * was added second will be placed after the one added
	 * first, this is because the player to achieve the HighScore
	 * first was able to achieve such a feat at an earlier
	 * time and therefore has priority. When a HighScore is
	 * added, any trailing HighScores, (entries beyond 10th
	 * place) are cut off and removed. If a HighScore is not
	 * high enough to end up on the HighScore table, this method
	 * returns -1, otherwise it returns the place on the HighScore
	 * table that they have earned with this score. HighScores
	 * are immediately saved to a File if changes where made.
	 * 
	 * @param highScore The HighScore to be potentially added.
	 * @return A value between 1 and 10, or -1 if no place was achieved.
	 */
	public static int addHighScore(HighScore highScore)
	{
		ArrayList<HighScore> scores = getHighScores(0);
		int place = -2;
		for(int i = 0; i < scores.size(); i++)
		{
			if(scores.get(i).getScore() < highScore.getScore())
			{
				scores.add(i, highScore);
				place = i;
				trim(scores);
				break;
			}
		}
		//Update file
		if(place != -2)
			writeHighScores(scores);
		return place + 1;
	}
	
	private static void writeHighScores(ArrayList<HighScore> highScores)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("dat/HighScores.dat"));
			oos.writeUnshared(highScores);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void createHighScores()
	{
		ArrayList<HighScore> scores = new ArrayList<HighScore>(10);
		for(int i = 0; i < 10; i++)
			scores.add(new HighScore(0, "Empty", "Empty"));
		writeHighScores(scores);
	}
	
	private static void trim(ArrayList<HighScore> highScores)
	{
		if(highScores.size() <= 10)
			return;
		for(int i = 10; i < highScores.size(); i++)
			highScores.remove(i);
	}
}
