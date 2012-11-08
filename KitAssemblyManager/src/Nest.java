import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
public class Nest 
{
	public static final double width = 50;
	public static final double height = 50;
	public double x;
	public double y;
	public ArrayList<Part> parts = new ArrayList<Part>();
	
	Nest(double x, double y)
	{
		this.x = x;
		this.y = y;
		for (int i = 0; i < 9; i++)
			parts.add(new Part(x + i % 3 * 50 / 3, y + i / 3 * 50 / 3));
	}
	
}
