
public class MainProgram
{
	public MainProgram()
	{
		LimitedWorld<GameObject> w;
		Player p = new Player(10, 250, 10, 10);
		Engine e;
		w = new LimitedWorld<GameObject>(300, 500);
		p.addSelfToWorld(w);
		e = new Engine(w);
		e.start();
	}

	/**
	 * @param args Arguments taken as an Array of Strings from the JVM.
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new MainProgram();
	}

}
