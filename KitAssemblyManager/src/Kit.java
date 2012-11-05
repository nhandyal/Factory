
public class Kit 
{
	private int width;
	private int height;
	public int x;
	public int y;
	
	Kit(int x, int y, int width, int height)
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
