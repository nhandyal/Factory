
public class Nest 
{
	private int width;
	private int height;
	public int x;
	public int y;
	
	Nest(int x, int y, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
