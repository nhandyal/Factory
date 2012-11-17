package factory.global.data;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class FactoryObject implements java.io.Serializable{

	public int x;
	public int y;
	public boolean isLine;
	public int xf;
	public int yf;
	public int index;
	public int imageIndex;
	//ImageIcon factoryObjectImage;

	public FactoryObject (int initialPosX, int initialPosY, int initialImage){
		x = initialPosX;
		y = initialPosY;
		//setImage(initialImage);
		isLine = false;
	}
	
	public FactoryObject(int x, int y, int xf, int yf) {
		this.x = x;
		this.y = y;
		this.xf = xf;
		this.yf = yf;
		isLine = true;
	}
	
	public FactoryObject(){
		x = 3;
		y = 4;
	}

	/*public void setImage(String newImage)
	{
		factoryObjectImage = new ImageIcon(newImage);
	}

	public ImageIcon getImage()
	{
		return factoryObjectImage;
	}*/

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

	public int getPositionYF()
	{
		return yf;
	}
	
	public void setIsLine(boolean b)
	{
		isLine = b;
	}

	public boolean getIsLine(){
		return isLine;
	}

	public void setImage(int newImageIndex)
	{
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