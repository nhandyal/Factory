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

public class Part extends FactoryObject implements Serializable{

	boolean isMoving;

	public Part(int initialPosX, int initialPosY, int initialImage, int indx){
		x = initialPosX;
		y = initialPosY;
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

	public void setIsMoving(boolean b){
		isMoving = b;
	}

	public boolean getIsMoving(){
		return isMoving;
	}
}
