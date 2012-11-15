package factory.server.managers.kitAssemblyManager;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FactoryObject {

	int x;
	int y;
	boolean isLine;
	int xf;
	int yf;
	ImageIcon factoryObjectImage;

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
	
	public boolean isLine() {
		return isLine;
	}

	public void setImage(String newImage){
		factoryObjectImage = new ImageIcon(newImage);
	}

	public ImageIcon getImage(){
		return factoryObjectImage;
	}

}