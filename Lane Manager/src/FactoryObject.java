import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FactoryObject {

	int x, y, xf, yf;
	boolean isLine = false;
	ImageIcon factoryObjectImage;

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

	public int getPositionXF(){
		return xf;
	}

	public int getPositionYF(){
		return yf;
	}

	public boolean getIsLine(){
		return isLine;
	}

	public void setImage(String newImage){
		factoryObjectImage = new ImageIcon(newImage);
	}

	public ImageIcon getImage(){
		return factoryObjectImage;
	}

}