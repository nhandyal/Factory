package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;
public class Gripper extends FactoryObject{
	
	Part[] parts;
	
	public Gripper(int xpos, int ypos, int image){
		x = xpos;
		y = ypos;
		setImage(image);
		parts = new Part[4];
	}
	
	public void addPart(Part p){
		//check for first available opening in gripper
		for (int i = 0; i < parts.length; i++){
			if (parts[i] == null){
				parts[i] = p;
				break;
			}
		}
	}
	
	public void updateParts(){
		//make sure all the parts in the gripper move as the gripper moves
		for (int i = 0; i < parts.length; i++){
			if (parts[i] != null)
				parts[i].setPosition(x+5,y+10+20*i);
		}
	}
	
	public Part removePart(int i){
		Part p = parts[i];
		parts[i] = null;
		return p;
	}
}
