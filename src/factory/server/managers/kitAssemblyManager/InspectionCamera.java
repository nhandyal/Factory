package KitAssemblyManager;
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
	public InspectionCamera(int xpos, int ypos, String image, UpdateServer us){
		super(xpos, ypos, image);
		defaultx = x;
		defaulty = y;
		setImage(image);
		this.us = us;
	}
	
	public void takePicture(KitStand s, int base){
		isMoving = true;
		this.base = base;
		k = s.getKit();
		initialx = x;
		initialy = y;
		finalx = s.getPositionX();
		finaly = s.getPositionY();
	}
	
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
		if (count < 25){
			x += (finalx - initialx)/25;
			y += (finaly - initialy)/25;
			count++;
		}
		if (count >= 25 && count < 30)
		{	
			takePicture = true;
			count++;
		}
		else if (count == 30)
		{
			reset();
			takePicture = false;
			k.setPicTaken(true);
			count++;
		}
		else if (count >= 30 && count < 56){
			x += (finalx - initialx)/25;
			y += (finaly - initialy)/25;
			count++;
		}
		else if (count == 56)
		{
			isMoving = false;
		}
	}
}

