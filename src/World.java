
public interface World<E>
{
	boolean addToWorld(E obj);
	
	boolean existsInWorld(E obj);
	
	E removeFromWorld(E obj);
	
	int getHeight();
	
	int getWidth();
}