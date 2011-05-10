
public class Block extends AbstractGameObject
	implements Solid
{
	public Block(int initX, int initY, int height, int width)
	{
		super(Pole.NONE, initX, initY, height, width, 0);
	}
}
