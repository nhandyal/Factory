package KitAssemblyManager;
public class Gripper extends FactoryObject{
	
	Part[] parts;
	
	public Gripper(int xpos, int ypos, String image){
		x = xpos;
		y = ypos;
		setImage(image);
		parts = new Part[4];
	}
	
	public void addPart(Part p){
		for (int i = 0; i < parts.length; i++){
			if (parts[i] == null){
				parts[i] = p;
				break;
			}
		}
	}
	
	public void updateParts(){
		for (int i = 0; i < parts.length; i++){
			if (parts[i] != null)
				parts[i].setPosition(x+10+20*i,y+5);
		}
	}
	
	public Part removePart(int i){
		Part p = parts[i];
		parts[i] = null;
		return p;
	}
}
