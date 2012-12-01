package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class KitRobot extends FactoryObject
{
	
	double x1,x2,y1,y2;
	Timer csTimer;
	int csCount = 0;
	Kit holdObj;
	double xdes1, ydes1, xdes2, ydes2;
	Conveyor c1,c2;
	KitStand s1,s2;
	boolean isMoving = false;
	UpdateServer us;
	int base = 0;
	int imageWidth = 50;
	int imageHeight = 51;
	public KitRobot(int xpos, int ypos, int image, UpdateServer us)
	{
		super(xpos, ypos, image);
		setImage(image);
		this.us = us;
		//set one point of arm to be in center of robot
		x1 = x + imageWidth/2;
		x2 = x + imageWidth/2;
		y1 = y + imageHeight/2;
		y2 = y + imageHeight/2;
	}
	
	public void moveFromConveyorToStand(Conveyor c, KitStand ks, Kit k, int base){
		isMoving = true;
		holdObj = k;
		c1 = c;
		s2 = ks;
		this.base = base;
		//initial position is conveyor, final is stand
		xdes1 = c.getInFinishX();
		ydes1 = c.getInFinishY();
		xdes2 = ks.getPositionX();
		ydes2 = ks.getPositionY();
		//move();
	}
	
	public void moveFromStandToConveyor(KitStand ks, Conveyor c, Kit k, int base){
		isMoving = true;
		c2 = c;
		s1 = ks;
		holdObj = k;
		this.base = base;
		//moving from stand to conveyor
		xdes2 = c.getOutStartX();
		ydes2 = c.getOutStartY();
		xdes1 = ks.getPositionX();
		ydes1 = ks.getPositionY();
		//move();
	}
	
	public void moveFromStandToStand(KitStand ks1, KitStand ks2, Kit k, int base){
		isMoving = true;
		s1 = ks1;
		s2 = ks2;
		holdObj = k;
		this.base = base;
		//moving from conveyor to stand
		xdes2 = ks2.getPositionX();
		ydes2 = ks2.getPositionY();
		xdes1 = ks1.getPositionX();
		ydes1 = ks1.getPositionY();
		//move();
	}
	
	public void trashKit(KitStand ks1, Kit k, int base){
		this.base = base;
		isMoving = true;
		s1 = ks1;
		holdObj = k;
		xdes1 = ks1.getPositionX();
		ydes1 = ks1.getPositionY();
		xdes2 = -100;
		ydes2 = 50;
	}
	
	public void move()
	{
		csCount = us.getCount() - base;
		//move to first destination
		if (csCount < 25){
			x2 += (xdes1-x1)/25;
			y2 += (ydes1-y1)/25;
		}
		//move back to robot center
		else if (csCount >= 25 && csCount < 50){
			//when you first take the object, transfer ownership
			if (csCount == 25){
				if (c1 != null)
					c1.setInKit(null);
				else if (s1 != null){
					s1.setCurrentKit(null);
				}
				holdObj.setIsMoving(true);
			}
			x2 += (x1 - xdes1)/25;
			y2 += (y1 - ydes1)/25;
			holdObj.setPosition((int)x2,(int)y2);
			holdObj.updateParts();
		}
		//move to second destination
		else if (csCount >= 50 && csCount < 75){
			x2 += (xdes2 - x1)/25;
			y2 += (ydes2 - y1)/25;
			holdObj.setPosition((int)x2, (int)y2);
			holdObj.updateParts();
		}
		//move back to robot center
		else if (csCount >= 75 && csCount < 100){
			//when you first arrive, transfer ownership
			if (csCount == 75){
				if (c2 != null)
					c2.setOutKit(holdObj);
				else if (s2 != null)
					s2.setCurrentKit(holdObj);	
				holdObj.setIsMoving(false);
			}
			x2 += (x1 - xdes2)/25;
			y2 += (y1 - ydes2)/25;
			//at end reset everything
			if (csCount == 99)
			{
				x2 = this.getPositionX() + imageWidth/2;
				y2 = this.getPositionY() + imageHeight/2;
				s1 = null;
				c1 = null;
				s2 = null;
				c2 = null;
			//	csTimer.stop();
				holdObj = null;
				isMoving = false;
			}
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