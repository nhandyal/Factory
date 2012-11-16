package KitAssemblyManager;

import java.util.*;

public class KitStand extends FactoryObject{
	
	Kit currentKit;
	
	public KitStand(int xpos, int ypos, String image){
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
