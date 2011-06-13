
import java.io.Serializable;

/**
 * The HighScore Class.
 * <p>
 * This class serves a wrapper for data relevant to
 * user high scores. This includes the submitted name
 * of the user, their score and the level the score was
 * attained on. This class is serialized in order to
 * allow for storage within a file as an Object.
 * 
 * @author Mihail Kurbako
 * @version 1.0.0.1 :  June 12, 2011
 * @since June 11, 2011
 * @see HighScoreManager
 * @see HighScoreState
 */
public final class HighScore implements Serializable
{
	private static final long serialVersionUID = 5885196539486389475L;
	private String name;
	private int score;
	private String levelName;
	
	/**
	 * The sole constructor for HighScore.
	 * This creates an instance of the HighScore
	 * Object with all its fields populated with
	 * relevant data.
	 * 
	 * @param score The score attained by the user.
	 * @param name The submitted name of the user.
	 * @param levelName The name of the level the score was attained on.
	 */
	public HighScore(int score, String name, String levelName)
	{
		setScore(score);
		setName(name);
		setLevelName(levelName);
	}

	/**
	 * This method sets the value for the field name.
	 *
	 * @param name The new value for the name.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * This method accesses the stored value for name.
	 * 
	 * @return The value for name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * This method sets the value for score within this Object.
	 * 
	 * @param score The new value for score within this Object.
	 */
	public void setScore(int score)
	{
		this.score = score;
	}

	/**
	 * This method accesses the value for score within this Object.
	 * 
	 * @return The value for score.
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * This method sets the value for the level name that
	 * the score was achieved on within this Object.
	 * 
	 * @param levelName The level name.
	 */
	public void setLevelName(String levelName)
	{
		this.levelName = levelName;
	}

	/**
	 * This method accesses the value for the name of the
	 * level that the score was achieved on.
	 * 
	 * @return The level name for this Object.
	 */
	public String getLevelName()
	{
		return levelName;
	}

	/**
	 * This method is used to get the hash code for this
	 * Object. It is used in Object comparison to check
	 * for equality.
	 * 
	 * @return The hashCode for this Object.
	 */
	public int hashCode()
	{
		return (getScore() * 92) + getName().hashCode() + getLevelName().hashCode();
	}
	
	/**
	 * This method check whether this HighScore is
	 * equal to a specified Object. That Object must
	 * also be an instance of HighScore to return true.
	 * 
	 * @return <code>true</code> if the Objects are equal, <code>false</code> otherwise.
	 */
	public boolean equals(Object o)
	{
		return o instanceof HighScore & hashCode() == o.hashCode();
	}
	
	/**
	 * Returns a String representation of this HighScore
	 * in the format of:
	 * <p>
	 * [Name]: [Score] ([Level Name])
	 * 
	 * @return The String value for this HighScore.
	 */
	public String toString()
	{
		return getName() + ": " + getScore() + " (Level: " + getLevelName() + ")";
	}
}
