
import java.io.Serializable;

public final class HighScore implements Serializable
{
	private static final long serialVersionUID = 5885196539486389475L;
	private String name;
	private int score;
	
	public HighScore(int score, String name)
	{
		setScore(score);
		setName(name);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getScore()
	{
		return score;
	}
	
	public int hashCode()
	{
		return (getScore() * 92) + getName().hashCode();
	}
	
	public boolean equals(Object o)
	{
		if(!(o instanceof HighScore))
			return false;
		return hashCode() == o.hashCode();
	}
	
	public String toString()
	{
		return getName() + ": " + getScore();
	}
}
