/*
** Authors: 		David Cox
** Date: 			11/01/12
** Project: 		Cs200-Factory
** Description: 	FactoryObject that contains animation information for item on factory floor.
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;
<<<<<<< HEAD
import javax.swing.ImageIcon;
=======
import javax.swing.*;
>>>>>>> 2df4efe23067f64db17ccf97e8bb44ba43999e82

public class FactoryObject implements Serializable{

	int x, y, xf, yf, index = -5;
	boolean isLine = false;
	int imageIndex;
	
	public FactoryObject(){
		x = 3;
		y = 4;
	}

	public  void setPosition(int newPosX, int newPosY){
		x = newPosX;
		y = newPosY;
	}
	
	public  void setPositionF(int newPosXF, int newPosYF){
		xf = newPosXF;
		yf = newPosYF;
	}

	public int getPositionX(){
		return x;
	}

	public int getPositionY(){
		return y;
	}

	public int getPositionXF(){
		return xf;
	}

	public int getPositionYF(){
		return yf;
	}
	
	public void setIsLine(boolean b){
		isLine = b;
	}

	public boolean getIsLine(){
		return isLine;
	}

	public void setImage(int newImageIndex){
		imageIndex = newImageIndex;
	}

	public int getImageIndex(){
		return imageIndex;
	}
	
	public void setIndex(int i){
		index = i;
	}
	
	public int getIndex(){
		return index;
	}
<<<<<<< HEAD

}
=======
}
>>>>>>> ba44d0db5ad57fefcd73f002516c02bc7ff81bb9
