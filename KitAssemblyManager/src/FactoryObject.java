import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class FactoryObject 
{
	public Rectangle StandSize = new Rectangle(100, 350);
	public Point StandPos = new Point(100, 200);
	public Point KitRobotPos = new Point(400, 300);
	public ArrayList<Point2D.Double> ArmPos = new ArrayList<Point2D.Double>();
	public Point FirstNest = new Point(600, 150);
	public int width;
	public int height;
	public ArrayList<Nest> Nests = new ArrayList<Nest>();
	public ArrayList<Part> LastPart = new ArrayList<Part>();
	public ArrayList<KittingStand> ks = new ArrayList<KittingStand>();
	public ArrayList<Point2D.Double> vectors = new ArrayList<Point2D.Double>();
	public int count;
	public int current;
	public boolean flash;
	public ArrayList<Integer> NestsIndex = new ArrayList<Integer>();
	public FactoryObject()
	{
		for (int i = 0; i < 3; i++)
		{
			int x = StandPos.x + 25;
			int y = StandPos.y + 25 + i * 100;
			ks.add(new KittingStand(x, y));
		}
		Initialize();
	}
	public void MoveToStand(int i, int k)
	{
		if (count >=0 && count < 25)
		{
			ArmPos.get(i).x -= vectors.get(NestsIndex.get(i)).x / 25;
			LastPart.get(NestsIndex.get(i)).x -= vectors.get(NestsIndex.get(i)).x / 25;
			ArmPos.get(i).y -= vectors.get(NestsIndex.get(i)).y / 25;
			LastPart.get(NestsIndex.get(i)).y -= vectors.get(NestsIndex.get(i)).y / 25;
			if (count == 0)
			{
				Nests.get(NestsIndex.get(i)).parts.remove(8);
				ks.get(k).incParts();
			}
		}
		
		if (count >= 25 && count < 50)
		{
			double diffx = StandPos.x + 25 + ks.get(k).getParts() % 3 * 50 / 3.0;
			double diffy = StandPos.y  + k * 100 + 25 + ks.get(k).getParts() / 3 * 50 / 3.0;
			diffx -= KitRobotPos.x + 75 / 2.0;
			diffy -= KitRobotPos.y + 75 / 2.0;
			if (count == 25)
			{
				LastPart.get(NestsIndex.get(i)).x = KitRobotPos.x + 75 / 2.0;
				LastPart.get(NestsIndex.get(i)).y = KitRobotPos.y + 75 / 2.0;
				System.out.println(ArmPos.get(i));
			}
			ArmPos.get(i).x += (diffx + 50 / 6) / 25;
			ArmPos.get(i).y += (diffy + 50 / 6) / 25;
			LastPart.get(NestsIndex.get(i)).x += diffx / 25;
			LastPart.get(NestsIndex.get(i)).y += diffy / 25;				
		}
		
		if (count >= 50 && count < 75)
		{
			double diffx = StandPos.x + 25 + ks.get(k).getParts() % 3 * 50 / 3.0;
			double diffy = StandPos.y + k * 100 + 25 + ks.get(k).getParts() / 3 * 50 / 3.0;
			diffx -= KitRobotPos.x + 75 / 2.0;
			diffy -= KitRobotPos.y + 75 / 2.0;
			ArmPos.get(i).x -= (diffx + 50 / 6) / 25;
			ArmPos.get(i).y -= (diffy + 50 / 6) / 25;
		}
	}
	
	public void Initialize()
	{
		count = -25;
		current = 0;
		flash = false;
		width = Part.width;
		height = Part.height;
		for (int i = 0; i < 4; i++)
			ArmPos.add(new Point2D.Double(KitRobotPos.x + 75 / 2.0, KitRobotPos.y +  75 / 2.0));
		for (int i = 0; i < 8; i++)
		{
			Nests.add(new Nest(FirstNest.x, FirstNest.y + i * width));
			for (int j = 0; j < 9; j++)
			{
				double x = Nests.get(i).parts.get(j).x;
				double y = Nests.get(i).parts.get(j).y;
				if (j == 8)
						LastPart.add(new Part(x, y));
			}
		}
		for (int i = 0; i < LastPart.size(); i++)
		{
			double diffx = LastPart.get(i).x + 50 / 6.0 - KitRobotPos.x - 75 / 2.0;
			double diffy = LastPart.get(i).y + 50 / 6.0 - KitRobotPos.y - 75 / 2.0;
			vectors.add(new Point2D.Double(diffx, diffy));
		}
	}
	
	public void getParts(ArrayList<Integer> pi)
	{
		NestsIndex = pi;
		for (int i = 0; i < pi.size(); i++)
		{
			ArmPos.get(i).x += vectors.get(NestsIndex.get(i)).x / 25;
			ArmPos.get(i).y += vectors.get(NestsIndex.get(i)).y / 25;
		}
	}
}
