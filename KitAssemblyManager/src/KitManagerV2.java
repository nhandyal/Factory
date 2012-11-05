import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
public class KitManagerV2 extends JFrame implements ActionListener
{

	Rectangle ConveyorSize = new Rectangle(600, 100);
	Point ConveyorPos = new Point(100, 50);
	Rectangle Screen = new Rectangle(800, 800);
	ArrayList<Point> KitPos = new ArrayList<Point>();
	Rectangle StandSize = new Rectangle(100, 350);
	Point StandPos = new Point(100, 200);
	Point KitRobotPos = new Point(425, 400);
	Point Part = new Point(450, 75);
	Point InKit = new Point(700, 75);
	Point ArmPos = new Point(475, 100);
	int offset = 50; 
	int width = 50;
	int height = 50;
	int count = 0;
	int radius = 100;
	double r1, r2, r3;
	double theta;
	KitManagerV2()
	{
		setSize(800, 800);
		for (int i = 0; i <= 2; i++)
		{
			int x = 450;
			int y = ConveyorPos.y;
			KitPos.add(new Point(x + (offset + width) * i, y + height / 2));
		}
		double diffx = Part.getX() + width / 2.0 - KitRobotPos.x - radius / 2.0;
		double diffy = Part.getY() + width / 2.0 - KitRobotPos.y - radius / 2.0;
		r1 = Math.sqrt(diffx*diffx+diffy*diffy);
		diffx = StandPos.getX() + width/1.0 - KitRobotPos.x - radius / 2.0;
		diffy = StandPos.getY() + height/1.0 + 2.0 * (height + offset) - KitRobotPos.x - radius / 2.0;
		r2 = Math.sqrt(diffx*diffx+diffy*diffy);
		diffx = Part.getX() + width / 2.0 - (StandPos.getX() + width/1.0);
		diffy = Part.getY() + width / 2.0 - (StandPos.getY() + height/1.0 + 2.0 * (height + offset));
		r3 = Math.sqrt(diffx*diffx+diffy*diffy);
		double test = (r3*r3-r1*r1-r2*r2)/r1/r2/2;
		theta = Math.acos(test);
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, 800, 800);
		g2.setColor(Color.BLUE);
		g2.fillRect(ConveyorPos.x, ConveyorPos.y, ConveyorSize.width, ConveyorSize.height);
		g2.setColor(Color.RED);
		for (int i = 0; i < KitPos.size(); i++)
		{
			int x = KitPos.get(i).x;
			int y = KitPos.get(i).y;
			g2.fillRect(x, y, width, height);
		}
		g2.setColor(Color.BLUE);
		g2.fillRect(StandPos.x, StandPos.y, StandSize.width, StandSize.height);
		g2.setColor(Color.WHITE);
		for (int i = 0; i < 3; i++)
		{
			int x = StandPos.x + width / 2;
			int y = StandPos.y + height / 2 + i * (height + offset);
			g2.fillRect(x, y, width, height);
		}
		g2.setColor(Color.BLUE);
		g2.fillOval(KitRobotPos.x, KitRobotPos.y, radius, radius);
		g2.setColor(Color.WHITE);
		g2.setColor(Color.RED);
		g2.fillRect(Part.x, Part.y, width, height);
		g2.fillRect(InKit.x, InKit.y, 700 - InKit.x, height);
		g2.setColor(Color.WHITE);
		g2.drawLine(KitRobotPos.x + radius / 2, KitRobotPos.y + radius / 2, ArmPos.x, ArmPos.y);
	}
	
	public static void main(String[] args) 
	{
		KitManagerV2 km = new KitManagerV2();
		km.setVisible(true);
		km.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Timer(20, km).start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (count < 50)
		{
			int tcount = count - 1;
			double r = r1 + (r2 - r1)/49.0*(50 - tcount);
			double x = r*Math.cos(theta/49.0*(50 - tcount));
			double y = r*Math.sin(theta/49.0*(50 - tcount));
			ArmPos.x = 500 - (int)x;
			ArmPos.y = 475 - (int)y;
			Part.x = ArmPos.x - 27;
			Part.y = ArmPos.y - 28;
			repaint();
			if (count == 0)
				KitPos.remove(0);
			for (int i = 0; i < KitPos.size(); i++)
			{
				KitPos.get(i).x += (KitPos.get(0).x - KitPos.get(1).x) / 50;
			}
			InKit.x -= 1;
		}
		
		count = (count + 1) % 500;
	}

}
