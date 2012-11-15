import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

import java.util.*;

public class Bin extends FactoryObject
{

	int push;
	boolean vis;

	public Bin(int initialPosX, int initialPosY, String initialImage, boolean v){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		push = 0;
		vis = v;
	}
	
	public void addPush(){
		push++;
	}
	
	public int getPush(){
		return push;
	}

	public void setVis(boolean v){
		vis = v;
	}

	public boolean getVis(){
		return vis;
	}
}