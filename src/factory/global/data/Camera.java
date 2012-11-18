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

public class Camera extends FactoryObject implements Serializable{

	boolean takenPicture;

	public Camera(int initialPosX, int initialPosY, int initialImage, int indx){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		index = indx;
		takenPicture = false;
	}

	public void moveLeft(){
		x -= 2;
	}

	public void moveRight(){
		x += 2;
	}

	public void moveUp(){
		y -= 2;
	}

	public void moveDown(){
		y += 2;
	}

	public void takePicture(){
		takenPicture = true;
	}

	public void moveTo(int newX, int newY){
		if(newX%2 == 1)
			newX++;
		if(newY%2 == 1)
			newY++;

		if(x > newX){
			x -= 2;
		}
		else if(x < newX){
			x += 2;
		}

		if(y > newY){
			y -= 2;
		}
		else if(y < newY){
			y += 2;
		}

	}
}
