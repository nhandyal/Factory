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
//package factory.global.data;

// Java packages
import java.io.*;

public class Part extends FactoryObject implements Serializable{

	public Part(int initialPosX, int initialPosY, int initialImage, int indx){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		index = indx;
	}

	public void moveLeft(){
		x -= 2;
	}

	public void moveUp(){
		y -= 2;
	}

	public void moveDown(){
		y += 2;
	}
}
