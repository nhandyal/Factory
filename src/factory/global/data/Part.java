/*
** Authors:			Timi Okuboyejo
** Date: 			11/01/12
** Project: 		Cs200-Factory
** Description: 	Contains information for parts(imageindex, points)
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;
import java.lang.*;

public class Part extends FactoryObject implements Serializable{

	boolean isMoving;
	int intlx, intly;

	public Part(int initialPosX, int initialPosY, int initialImage, int indx){
		x = initialPosX;
		y = initialPosY;
		intlx = initialPosX;
		intly = initialPosY;
		setImage(initialImage);
		index = indx;
	}

	public void moveLeft(){
		isMoving = true;
		x -= 2;
	}

	public void moveUp(){
		isMoving = true;
		y -= 2;
	}

	public void moveDown(){
		isMoving = true;
		y += 2;
	}

	public void moveTo(int newx, int newy){
		if(newx%2 == 1)
			newx--;
		if(newy%2 == 1)
			newy--;

		if(y > newy){
			y -= 2;
		}
		else if(y < newy){
			y += 2;
		}	
		if(x > newx){
			x -= 2;
		}
		else if(x < newx){
			x += 2;
		}

		if(Math.abs(x - newx) == 1){
			x = newx;
		}
		if(Math.abs(y - newy) == 1){
			y = newy;
		}
	}

	public void setIsMoving(boolean b){
		isMoving = b;
	}

	public boolean getIsMoving(){
		return isMoving;
	}
}
