import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.geom.Point2D;
public class PartsRobot extends JFrame implements ActionListener
{
	
	private Image iBuffer;
	private FactoryObject fo = new FactoryObject();
	private ArrayList<Integer> t = new ArrayList<Integer>();
	PartsRobot()
	{
		setSize(800, 800);
		t.add(4);
		t.add(6);
		t.add(2);
		t.add(7);
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
		g2.fillRect(fo.StandPos.x, fo.StandPos.y, fo.StandSize.width, fo.StandSize.height);
		g2.setColor(Color.RED);
		for (int i = 0; i < fo.ks.size(); i++)
			g2.fillRect((int)fo.ks.get(i).x, (int)fo.ks.get(i).y, (int)KittingStand.width, (int)KittingStand.height);
		g2.setColor(Color.BLUE);
		g2.fillOval(fo.KitRobotPos.x, fo.KitRobotPos.y, 75, 75);
		g2.setColor(Color.BLUE);
		g2.fillRect(fo.FirstNest.x, fo.FirstNest.y, fo.width, 8 * fo.height);
		for (int i = 0; i < 8; i++)
		{
			g2.setColor(Color.WHITE);
			g2.drawLine((int)fo.Nests.get(i).x, (int)fo.Nests.get(i).y, (int)fo.Nests.get(i).x + 50, (int)fo.Nests.get(i).y);
			if (i < 8)
				for (int j = 0; j < 9; j++)
				{
					try
					{
						double x = fo.Nests.get(i).parts.get(j).x;
						double y = fo.Nests.get(i).parts.get(j).y;
						g2.setColor(Color.YELLOW);
						g2.fillOval((int)x, (int)y, fo.width / 3, fo.height / 3);
					}catch(Exception e)
					{
					}
					
				}
		}
		g2.setColor(Color.YELLOW);
		for (int i = 0; i < fo.LastPart.size(); i++)
			g2.fillOval((int)fo.LastPart.get(i).x, (int)fo.LastPart.get(i).y, fo.width / 3, fo.height / 3);
			
		g2.setColor(Color.WHITE);
		for (int i = 0; i < fo.ArmPos.size(); i++)
			g2.drawLine((int)(fo.KitRobotPos.x + 75 / 2.0), (int)(fo.KitRobotPos.y + 75 / 2.0), (int)fo.ArmPos.get(i).x, (int)fo.ArmPos.get(i).y);
		if (fo.flash)
		{
			g2.setColor(Color.YELLOW);
			g2.fillOval(fo.StandPos.x + 10, fo.StandPos.y + 10, fo.width + 25, fo.height + 25);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) 
	{
		if (fo.count < 0)
		{
			fo.getParts(t);
		}
		if (fo.current < t.size())
			fo.MoveToStand(fo.current, 1);
		else
		{
			t.removeAll(t);
			t.add(0);
			t.add(1);
			fo.count = -25;
			fo.current = 0;
			fo.flash = false;
			fo.getParts(t);	
		}
		if (fo.count < 0)
			fo.count++;
		else
		{
			fo.count = (fo.count + 1) % 75;
			if (fo.count == 0)
				fo.current++;
		}
		Graphics g = this.getGraphics();
		update(g);
	}
	
	public void update(Graphics g) 
	{  
		  if(iBuffer == null)
		  {  
		      iBuffer = this.createImage(800, 800);  
		  }  
		  Graphics gOffScreen = iBuffer.getGraphics();  
		  Color c = gOffScreen.getColor();  
		  gOffScreen.setColor(Color.BLUE);  
		  gOffScreen.fillRect(0, 0, 800, 800);  
		  gOffScreen.setColor(c);  
		  paint(gOffScreen);  
		  g.drawImage(iBuffer, 0, 0, null);  
	} 
}
