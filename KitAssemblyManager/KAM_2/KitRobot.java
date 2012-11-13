import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class KitRobot extends FactoryObject implements ActionListener
{
	
	KitAssemblyManager m;
	double x1,x2,y1,y2;
	Timer csTimer;
	int csCount = 0;
	Kit holdObj;
	double xdes1, ydes1, xdes2, ydes2;
	Conveyor c1,c2;
	KitStand s1,s2;
	boolean isMoving = false;
	
	public KitRobot(int xpos, int ypos, String image, KitAssemblyManager k)
	{
		x = xpos;
		y = ypos;
		setImage(image);
		m = k;
		x1 = x + getImage().getIconWidth()/2;
		x2 = x + getImage().getIconWidth()/2;
		y1 = y + getImage().getIconHeight()/2;
		y2 = y + getImage().getIconHeight()/2;
	}
	
	public void moveFromConveyorToStand(Conveyor c, KitStand ks, Kit k){
		isMoving = true;
		csTimer = new Timer(50,this);
		holdObj = k;
		c1 = c;
		s2 = ks;
		xdes1 = c.getInFinishX();
		ydes1 = c.getInFinishY();
		xdes2 = ks.getPositionX();
		ydes2 = ks.getPositionY();
		csCount = 0;
		csTimer.start();
	}
	
	public void moveFromStandToConveyor(KitStand ks, Conveyor c, Kit k){
		isMoving = true;
		csTimer = new Timer(50,this);
		c2 = c;
		s1 = ks;
		holdObj = k;
		xdes2 = c.getOutStartX();
		ydes2 = c.getOutStartY();
		xdes1 = ks.getPositionX();
		ydes1 = ks.getPositionY();
		csCount = 0;
		csTimer.start();
	}
	
	public void moveFromStandToStand(KitStand ks1, KitStand ks2, Kit k){
		isMoving = true;
		csTimer = new Timer(50,this);
		s1 = ks1;
		s2 = ks2;
		holdObj = k;
		xdes2 = ks2.getPositionX();
		ydes2 = ks2.getPositionY();
		xdes1 = ks1.getPositionX();
		ydes1 = ks1.getPositionY();
		csCount = 0;
		csTimer.start();
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		if (csCount < 25){
			x2 += (xdes1-x1)/25;
			y2 += (ydes1-y1)/25;
			csCount++;
			m.repaint();
		}
		else if (csCount >= 25 && csCount < 50){
			if (csCount == 25){
				if (c1 != null)
					c1.setInKit(null);
				else if (s1 != null){
					s1.setCurrentKit(null);
				}
			}
			x2 += (x1 - xdes1)/25;
			y2 += (y1 - ydes1)/25;
			holdObj.setPosition((int)x2,(int)y2);
			csCount++;
			m.repaint();
		}
		else if (csCount >= 50 && csCount < 75){
			x2 += (xdes2 - x1)/25;
			y2 += (ydes2 - y1)/25;
			holdObj.setPosition((int)x2, (int)y2);
			csCount++;
			m.repaint();
		}
		else if (csCount >= 75 && csCount < 100){
			if (csCount == 75){
				if (c2 != null)
					c2.setOutKit(holdObj);
				else if (s2 != null)
					s2.setCurrentKit(holdObj);	
			}
			x2 += (x1 - xdes2)/25;
			y2 += (y1 - ydes2)/25;
			csCount++;
			m.repaint();
		}
		else if (csCount == 100){
			csCount = 0;
			s1 = null;
			c1 = null;
			s2 = null;
			c2 = null;
			csTimer.stop();
			holdObj = null;
			isMoving = false;
		}
	}
	
	public double getX1(){
		return x1;
	}
	
	public double getX2(){
		return x2;
	}
	
	public double getY1(){
		return y1;
	}
	
	public double getY2(){
		return y2;
	}
	
	public boolean getIsMoving(){
		return isMoving;
	}

}