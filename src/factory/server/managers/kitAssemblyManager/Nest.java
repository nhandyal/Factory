package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;

public class Nest extends FactoryObject{
	public int index;
	public Nest(int xpos, int ypos, int image){
		x = xpos;
		y = ypos;
		setImage(image);
	}
    public void setIndex(int index)
    {
        this.index = index;
    }
}
