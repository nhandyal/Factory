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

	boolean takenPicture, hasPath, broken;
	int newX, newY, intlx, intly;
	int nest;

	public Camera(int initialPosX, int initialPosY, int initialImage, int indx){
		x = initialPosX;
		y = initialPosY;
		intlx = initialPosX;
		intly = initialPosY;
		setImage(initialImage);
		index = indx;
		takenPicture = false;
		broken = false;
	}

	public void setTakenPicture(boolean b){
		takenPicture = b;
	}

	public boolean getTakenPicture(){
		return takenPicture;
	}

	public int getNest(){
		return nest;
	}

	public void setBroken(boolean b){
		broken = b;
	}
	public boolean getBroken(){
		return broken;
	}

	public void setPath(int xPos,int yPos, int n){
		hasPath = true;

		newX = xPos;
		newY = yPos;
		nest = n;

		// Make newX and newY multiples of 4
		while(newX%4 != 0)
			newX++;
		while(newY%4 != 0)
			newY++;
	}

	public boolean getHasPath(){
		return hasPath;
	}

	public void move(){

		if(hasPath == false && broken == false){
			if(y > intly){
				y -= 4;
			}
			else if(y < intly){
				y += 4;
			}	

			if(y == intly){
				if(x > intlx){
					x -= 4;
				}
				else if(x < intlx){
					x += 4;
				}	
			}
		}

		else if(broken == false){
			if(x > newX){
				x -= 4;
			}
			else if(x < newX){
				x += 4;
			}

			if(x == newX){
				if(y > newY){
					y -= 4;
				}
				else if(y < newY){
					y += 4;
				}

				if(y == newY){
					hasPath = false;
					takenPicture = true;
				}
			}
		}
	}
}
