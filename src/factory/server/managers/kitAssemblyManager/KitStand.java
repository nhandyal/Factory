package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;

import java.util.*;

public class KitStand extends FactoryObject{
	
	Kit currentKit;
	
	public KitStand(int xpos, int ypos, int image){
		super(xpos, ypos, image);
		setImage(image);
	}
	
	public Kit getKit(){
		return currentKit;
	}
	
	public void setCurrentKit(Kit k){
		currentKit = k;
	}
	
}
