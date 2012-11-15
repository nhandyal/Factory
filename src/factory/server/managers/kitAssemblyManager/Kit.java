package factory.server.managers.kitAssemblyManager;


public class Kit extends FactoryObject
{
	Part[] parts;
	boolean picTaken;
	public Kit(int xpos, int ypos, String image)
	{
		super(xpos, ypos, image);
		setImage(image);
		parts = new Part[8];
	}
	
	public Part[] getParts(){
		return parts;
	}
	
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
	

}