/*
** Authors:			Timi Okuboyejo
** Date: 			11/01/12
** Project: 		Cs200-Factory
** Description: 	Contains information for lines(coordinates, fixed points)
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;

public class Line extends FactoryObject implements Serializable{

	boolean broken;

	public Line(int initialPosX, int initialPosY, int xFixed, int yFixed, int indx){
		x = initialPosX;
		y = initialPosY;
		xf = xFixed;
		yf = yFixed;
		isLine = true;
		index = indx;
		broken = false;
	}

	public void moveLeft(){
		x -= 2;
		xf = x;
	}
	
	public void reset(){
		x += 300;
		xf = x;
	}

	public void dividerUp(){
		if(broken == false)
			y = yf - 35;
	}

	public void dividerNeutral(){
		if(broken == false)
			y = yf;
	}

	public void dividerDown(){
		if(broken == false)
			y = yf + 35;
	}

	public void setBroken(boolean b){
		broken = b;
	}
	public boolean getBroken(){
		return broken;
	}
}
