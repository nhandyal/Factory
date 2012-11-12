/*
** Author: 					David Cox
** Date: 						11/01/12
** Last Modified:		Nikhil Handyal -- 11/11/12
** Project: 				Cs200-Factory
** Description: 		FactoryObject that contains animation information for item on factory floor.
** 									Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;

public class FactoryObject implements java.io.Serializable{

		int x;
		int y;
		int imageIndex;
	
		
		public FactoryObject(){
				x = 3;
				y = 4;
		}
		
		public FactoryObject (int x, int y, int imageIndex){
				this.x = x;
				this.y = y;
				this.imageIndex = imageIndex;
		}

		public  void setPosition(int newPosX, int newPosY){
				x = newPosX;
				y = newPosY;
		}

		public int getPositionX(){
				return x;
		}

		public int getPositionY(){
				return y;
		}

		public void setImage(int newImageIndex){
				imageIndex = newImageIndex;
		}

		public int getImageIndex(){
				return imageIndex;
		}
		
}