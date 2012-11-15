package KitAssemblyManager;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class PartRobot extends FactoryObject
{
	
	double x1,x2,y1,y2;
	int csCount = 0;
	Part holdObj;
	KitStand s2;
	double xdes1, ydes1, xdes2, ydes2;
	boolean isMoving = false;
	int base;
	UpdateServer us;
	public PartRobot(int xpos, int ypos, String image, UpdateServer us)
	{
		super(xpos, ypos, image);
		setImage(image);
		this.us = us;
		x1 = x + getImage().getIconWidth()/2;
		x2 = x + getImage().getIconWidth()/2;
		y1 = y + getImage().getIconHeight()/2;
		y2 = y + getImage().getIconHeight()/2;
	}
	
	public void moveFromNest(int ycoord, KitStand ks, Part p, int i, int base){
		if (ks.getKit() != null){
			isMoving = true;
			this.base = base;
			holdObj = p;
			s2 = ks;
			ydes1 = ycoord;
			xdes1 = p.getPositionX();
			if (i > 3)
				ydes2 = ks.getKit().getPositionY() + 25;
			else
				ydes2 = ks.getKit().getPositionY() + 5;
			if (i == 0 || i == 4)
				xdes2 = ks.getKit().getPositionX() + 10;
			else if (i == 1 || i == 5)
				xdes2 = ks.getKit().getPositionX() + 30;
			else if (i == 2 || i == 6)
				xdes2 = ks.getKit().getPositionX() + 50;
			else if (i == 3 || i == 7)
				xdes2 = ks.getKit().getPositionX() + 70;
		}
	}
	
	public void move()
	{
		csCount = us.getCount() - base;
		System.out.println(csCount);
		if (csCount < 20){
				x2 += (xdes1-x1)/20;
				y2 += (ydes1-y1)/20;
				csCount++;
			}
			else if (csCount >= 20 && csCount < 40){
				x2 += (x1 - xdes1)/20;
				y2 += (y1 - ydes1)/20;
				holdObj.setPosition((int)x2,(int)y2);
				csCount++;
			}
			else if (csCount >= 40 && csCount < 60){
				x2 += (xdes2 - x1)/20;
				y2 += (ydes2 - y1)/20;
				holdObj.setPosition((int)x2, (int)y2);
				csCount++;
			}
			else if (csCount >= 60 && csCount < 80){
				if (csCount == 60){
					s2.getKit().getParts()[0] = holdObj;
				}
				x2 += (x1 - xdes2)/20;
				y2 += (y1 - ydes2)/20;
				csCount++;
			}
			else if (csCount == 80){
				csCount = 0;
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
	
	public boolean isMoving(){
		return isMoving;
	}

}