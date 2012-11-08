
public class KittingStand 
{
	public double x;
	public double y;
	public static final double width = 50;
	public static final double height = 50;
	private int count = -1;
	public KittingStand(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void incParts()
	{
		count++;
	}
	
	public int getParts()
	{
		return count;
	}
}
