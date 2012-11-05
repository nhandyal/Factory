import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
public class KitRobot extends JFrame implements ActionListener
{

	Rectangle ConveyorSize = new Rectangle(600, 100);
	Point ConveyorPos = new Point(100, 50);
	Rectangle Screen = new Rectangle(800, 800);
	ArrayList<Point> KitPos = new ArrayList<Point>();
	Rectangle StandSize = new Rectangle(100, 350);
	Point StandPos = new Point(100, 200);
	Point KitRobotPos = new Point(400, 300);
	Point ArmPos = new Point(KitRobotPos.x + 37, KitRobotPos.y + 37);
	Point Part = new Point(450, 75);
	Point InKit = new Point(700, 75);
	int offset = 50; 
	int width = 50;
	int height = 50;
	Point vec1;
	Point vec2;
	Point vec3;
	Point vec4;
	int count = 0;
	KitRobot()
	{
		setSize(800, 800);
		for (int i = 0; i <= 2; i++)
		{
			int x = 450;
			int y = ConveyorPos.y;
			KitPos.add(new Point(x + 100 * i, y + 25));
		}
		int diffx = KitRobotPos.x - KitPos.get(0).x + 12;
		int diffy = KitRobotPos.y - KitPos.get(0).y + 12;
		vec1 = new Point(-diffx, -diffy);
		diffx = KitRobotPos.x - StandPos.x + 12;
		diffy = KitRobotPos.y - (StandPos.y + 75 + 2 * 100) + 37;
		vec2 = new Point(-diffx, -diffy);
		diffx = KitPos.get(0).x - KitPos.get(1).x;
		vec3 = new Point(diffx, 0);
		diffx = KitRobotPos.x + 37 - 300 -25;
		diffy = KitRobotPos.y + 37 - 75 - 25;
		vec4 = new Point(-diffx, -diffy);
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
			int x = StandPos.x + 25;
			int y = StandPos.y + 25 + i * 100;
			g2.fillRect(x, y, width, height);
		}
		g2.setColor(Color.BLUE);
		g2.fillOval(KitRobotPos.x, KitRobotPos.y, 75, 75);
		g2.setColor(Color.WHITE);
		g2.drawLine(KitRobotPos.x + 37, KitRobotPos.y + 37, ArmPos.x, ArmPos.y);
		g2.setColor(Color.RED);
		g2.fillRect(Part.x, Part.y, width, height);
		g2.fillRect(InKit.x, InKit.y, 700 - InKit.x, height);
	}
	
	public static void main(String[] args) 
	{
		KitRobot kr = new KitRobot();
		kr.setVisible(true);
		kr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Timer(20, kr).start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (count < 100)
		{
			if (count < 25)
			{
				ArmPos.x += vec1.x / 25;
				ArmPos.y += vec1.y / 25;
				repaint();
			}
			if (count >= 25 && count < 50)
			{
				if (count % 500 == 25)
					KitPos.remove(0);
				InKit.x -= 2;
				ArmPos.x -= vec1.x / 25;
				ArmPos.y -= vec1.y / 25;
				Part.x -= vec1.x / 25;
				Part.y -= vec1.y / 25;
				for (int i = 0; i < KitPos.size(); i++)
				{
					KitPos.get(i).x += vec3.x/25;
				}
				repaint();
			}
			if (count >= 50 && count < 75)
			{
				ArmPos.x += vec2.x / 25;
				ArmPos.y += vec2.y / 25;
				Part.x += vec2.x / 25;
				Part.y += vec2.y / 25;
				repaint();
			}
			if (count >= 75 && count < 100)
			{
				ArmPos.x -= vec2.x / 25;
				ArmPos.y -= vec2.y / 25;
				repaint();
			}
		}
		
		if (count > 400 && count <= 425)
		{
			ArmPos.x += vec2.x / 25;
			ArmPos.y += vec2.y / 25;
			repaint();
		}
		
		if (count > 425 && count <= 450)
		{
			ArmPos.x -= vec2.x / 25;
			ArmPos.y -= vec2.y / 25;
			Part.x -= vec2.x / 25;
			Part.y -= vec2.y / 25;
			repaint();
		}
		
		if (count > 450 && count <= 475)
		{
			ArmPos.x += vec4.x / 25;
			ArmPos.y += vec4.y / 25;
			Part.x += vec4.x / 25;
			Part.y += vec4.y / 25;
			repaint();
		}
		if (count > 475 && count <= 500)
		{
			ArmPos.x -= vec4.x / 25;
			ArmPos.y -= vec4.y / 25;
			Part.x -= 200/25;
			repaint();
		}
		if (count == 499)
		{
			KitPos.removeAll(KitPos);
			for (int i = 0; i <= 2; i++)
			{
				int x = 450;
				int y = ConveyorPos.y;
				KitPos.add(new Point(x + 100 * i, y + 25));
			}
			Part.x = KitPos.get(0).x;
			Part.y = KitPos.get(0).y;
			InKit.x = 700;
			ArmPos = new Point(KitRobotPos.x + 37, KitRobotPos.y + 37);
		}
		count = (count + 1) % 500;
	}

}
