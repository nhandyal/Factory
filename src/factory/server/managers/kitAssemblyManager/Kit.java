package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;


public class Kit extends FactoryObject
{
	Part[] parts;
	boolean picTaken;
    boolean isMoving;
	boolean isComplete;
	public Kit(int xpos, int ypos, int image)
	{
		super(xpos, ypos, image);
		setImage(image);
		parts = new Part[8];
        isComplete = false;
	}
	
	public Part[] getParts(){
		return parts;
	}
	//check if kit is same as k
	public boolean isSame(Kit k){
		if (x != k.getPositionX() || y == k.getPositionY())
			return false;
		for (int i = 0; i < parts.length; i++){
			if (parts[i] != k.getParts()[i])
				return false;
		}
		return true;
	}
	
	public boolean getPicTaken(){
		return picTaken;
	}
	
	public void setPicTaken(boolean b){
		picTaken = b;
	}
	
    public void addPart(Part p, int i){
		parts[i] = p;
	}
	//move parts in kit as kit moves
	public void updateParts(){
		for (int i = 0; i < parts.length; i++){
			if (parts[i] != null){
				if (i < 4)
					parts[i].setPosition(x+10+20*i,y+5);
				else
					parts[i].setPosition(x+10+20*(i-4),y+25);
			}
		}
	}
	
	public boolean getIsMoving(){
		return isMoving;
	}
	
	public void setIsMoving(boolean b){
		isMoving = b;
	}
	
	public boolean getIsComplete(){
		return isComplete;
	}
	
	public void setIsComplete(boolean b){
		isComplete = b;
	}

}