
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HighScoreManager
{
	private HighScoreManager() 
	{ /*Suppress Default Constructor*/ }
	
	@SuppressWarnings("unchecked")
	public static ArrayList<HighScore> getHighScores()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream (
					new FileInputStream("dat/HighScores.dat"));
			return (ArrayList<HighScore>)ois.readObject();
		}
		catch (FileNotFoundException e)
		{
			createHighScores();
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e)
		{
			//File is probably corrupt or intended for an
			//earlier release of the program.
			createHighScores();
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean addHighScore(HighScore highScore)
	{
		ArrayList<HighScore> scores = getHighScores();
		boolean hasAdded = false;
		for(int i = 0; i < scores.size(); i++)
		{
			if(scores.get(i).getScore() < highScore.getScore())
			{
				scores.add(i, highScore);
				trim(scores);
				break;
			}
		}
		//Update file
		if(hasAdded)
			writeHighScores(scores);
		return hasAdded;
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
			scores.add(new HighScore(0, "Empty"));
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
