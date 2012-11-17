package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;

public class Nest extends FactoryObject{
	
	public Nest(int xpos, int ypos, int image){
		x = xpos;
		y = ypos;
		setImage(image);
	}
}
