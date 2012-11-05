import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PartsRobot extends JFrame implements ActionListener
{
	Rectangle StandSize = new Rectangle(100, 350);
	Point StandPos = new Point(100, 200);
	Point KitRobotPos = new Point(400, 300);
	Point ArmPos = new Point(KitRobotPos.x + 37, KitRobotPos.y + 37);
	int width = 50;
	int height = 50;
	Point FirstNest = new Point(600, 150);
	int count = 0;
	int nest = -1;
	Point vec1 = new Point();
	Point vec2 = new Point();
	Point NestMoving = new Point();
	boolean flash = false;
	PartsRobot()
	{
		setSize(800, 800);
		int diffx = KitRobotPos.x + 37 - FirstNest.x - 25;
		int diffy = KitRobotPos.y + 37 - FirstNest.y - 25;
		vec1.x = -diffx;
		vec1.y = -diffy;
		NestMoving.x =  FirstNest.x;
		NestMoving.y = FirstNest.y;
		diffx = KitRobotPos.x + 37 - StandPos.x - 25;
		diffy = KitRobotPos.y + 37 - StandPos.y - 25;
		vec2.x = -diffx;
		vec2.y = -diffy;
	}
	
	public static void main(String[] args) 
	{
		PartsRobot pr = new PartsRobot();
		pr.setVisible(true);
		pr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Timer(20, pr).start();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, 800, 800);
		g2.setColor(Color.BLUE);
		g2.fillRect(StandPos.x, StandPos.y, StandSize.width, StandSize.height);
		g2.setColor(Color.RED);
		for (int i = 0; i < 3; i++)
		{
			int x = StandPos.x + 25;
			int y = StandPos.y + 25 + i * 100;
			g2.fillRect(x, y, width, height);
		}
		g2.setColor(Color.BLUE);
		g2.fillOval(KitRobotPos.x, KitRobotPos.y, 75, 75);
		g2.setColor(Color.BLUE);
		g2.fillRect(FirstNest.x, FirstNest.y, 50, 8 * height);
		for (int i = 0; i < 9; i++)
		{
			g2.setColor(Color.WHITE);
			g2.drawLine(FirstNest.x, FirstNest.y + i * width, FirstNest.x + 50, FirstNest.y + i * width);
			int x = FirstNest.x;
			int y = FirstNest.y + i * width;
			g2.setColor(Color.YELLOW);
			if (i != 8 && i != nest)
				for (int j = 0; j < 9; j++)
				{
					g2.fillOval(x + j % 3 * 50 / 3, y + j / 3 * 50 / 3, 50 / 3, 50 / 3);
				}
		}
		
		if (nest >= 0)
		{
			int x = NestMoving.x;
			int y = NestMoving.y;
			g2.setColor(Color.YELLOW);
			for (int j = 0; j < 9; j++)
			{
				g2.fillOval(x + j % 3 * 50 / 3, y + j / 3 * 50 / 3, 50 / 3, 50 / 3);
			}
		}
		g2.setColor(Color.WHITE);
		g2.drawLine(KitRobotPos.x + 37, KitRobotPos.y + 37, ArmPos.x, ArmPos.y);
		if (flash)
		{
			g2.setColor(Color.YELLOW);
			g2.fillOval(StandPos.x + 10, StandPos.y + 10, width + 25, height + 25);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		int t = (count / 100) % 8;
		if (count % 100 < 25)
		{
			ArmPos.x += vec1.x / 24;
			ArmPos.y += vec1.y / 24;
		}
		if (count % 100 >= 25 && count % 100 < 50)
		{
			nest = t;
			ArmPos.x -= vec1.x / 24;
			ArmPos.y -= vec1.y / 24;
			NestMoving.x -= vec1.x / 24;
			NestMoving.y -= vec1.y / 24;
		}
		if (count % 100 >= 50 && count % 100 < 75)
		{
			ArmPos.x += (vec2.x + 25) / 24;
			ArmPos.y += (vec2.y + 25) / 24;
			if (t <= 3)
			{
				NestMoving.x += (vec2.x + 12) / 24;
				NestMoving.y += (vec2.y + 25)/ 24;
			}
			else
			{
				NestMoving.x += (vec2.x + 12) / 24;
				NestMoving.y += (vec2.y + 12)/ 24;
			}
		}
		if (count % 100 >= 75 && count % 100 <= 85)
		{
			flash = true;
		}
		if (count % 100 == 0)
		{
			ArmPos = new Point(KitRobotPos.x + 37, KitRobotPos.y + 37);
			NestMoving = new Point(FirstNest.x, FirstNest.y + t * 50);
			int diffx = KitRobotPos.x + 37 - NestMoving.x - 25;
			int diffy = KitRobotPos.y + 37 - NestMoving.y - 25;
			vec1.x = -diffx;
			vec1.y = -diffy;
			nest = -1;
			flash = false;
		}
		repaint();
		count = (count + 1) % 8000;
	}
}
