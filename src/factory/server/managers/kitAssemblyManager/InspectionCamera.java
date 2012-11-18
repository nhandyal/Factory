package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;
import java.awt.event.*;
import javax.swing.*;

public class InspectionCamera extends FactoryObject
{	
	int count = 0;
	int defaultx;
	int defaulty;
	int initialx;
	int initialy;
	int finalx;
	int finaly;
	Kit k;
	boolean takePicture;
	boolean isMoving = false;
	UpdateServer us;
	int base;
	public InspectionCamera(int xpos, int ypos, int image, UpdateServer us){
		super(xpos, ypos, image);
		defaultx = x;
		defaulty = y;
		setImage(image);
		this.us = us;
	}
	//move to kit stand and take picture
	public void takePicture(KitStand s, int base){
		isMoving = true;
		this.base = base;
		k = s.getKit();
		initialx = x;
		initialy = y;
		finalx = s.getPositionX();
		finaly = s.getPositionY();
	}
	//move camera back to default position
	public void reset(){
		initialx = x;
		initialy = y;
		finalx = defaultx;
		finaly = defaulty;
	}
	
	public boolean isMoving(){
		return isMoving;
	}
	
	public void move()
	{
		count = us.getCount() - base;
		//make sure no flash
        us.setFlash(false);
        //move to stand
		if (count < 25){
			x += (finalx - initialx)/25;
			y += (finaly - initialy)/25;
			count++;
		}
		//stay over stand and show flash
		if (count >= 25 && count < 30)
		{	
			takePicture = true;
			count++;
		}
		//turn off flash
		else if (count == 30)
		{
			reset();
			takePicture = false;
			k.setPicTaken(true);
            us.setFlash(true);
			count++;
		}
		//move back
		else if (count >= 30 && count < 56){
			x += (finalx - initialx)/25;
			y += (finaly - initialy)/25;
			count++;
		}
		//done moving
		else if (count == 56)
		{
			isMoving = false;
		}
	}
}

