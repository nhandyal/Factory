<<<<<<< HEAD
/*
** Date: 						11/01/12
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
import javax.swing.ImageIcon;

public class FactoryObject implements java.io.Serializable{

	int x;
	int y;
	boolean isLine;
	int xf;
	int yf;
	ImageIcon factoryObjectImage;
=======
public class FactoryObject {

	int x, y, xf, yf, index = -5;
	boolean isLine = false;
	int imageIndex;
	
	public FactoryObject(){
		x = 3;
		y = 4;
	}
>>>>>>> 2df4efe23067f64db17ccf97e8bb44ba43999e82

	public FactoryObject (int initialPosX, int initialPosY, String initialImage){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		isLine = false;
	}
	public FactoryObject() {
		x = 0;
		y = 0;
		xf = 0;
		yf = 0;
		isLine = false;
	}
	
	public FactoryObject(int x, int y, int xf, int yf) {
		this.x = x;
		this.y = y;
		this.xf = xf;
		this.yf = yf;
		isLine = true;
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
	
	public int getXF() {
		return xf;
	}
	
	public int getYF() {
		return yf;
	}
	
	public void setIsLine(boolean b){
		isLine = b;
	}
	
	public boolean isLine() {
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
}
