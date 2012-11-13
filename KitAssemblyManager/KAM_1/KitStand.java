import java.util.*;

public class KitStand extends FactoryObject{
	
	Kit currentKit;
	KitAssemblyManager m;
	
	public KitStand(int xpos, int ypos, String image, KitAssemblyManager k){
		x = xpos;
		y = ypos;
		m = k;
		setImage(image);
	}
	
	public Kit getKit(){
		return currentKit;
	}
	
	public void setCurrentKit(Kit k){
		currentKit = k;
	}
	
}
