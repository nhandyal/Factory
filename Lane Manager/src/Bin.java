public class Bin extends FactoryObject
{

	boolean vis = false;
	Part part;
	int partindx;

	public Bin(int initialPosX, int initialPosY, int initialImage, int indx, int p){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		index = indx;
		partindx = p;
	}
	
	public void setVis(boolean v){
		vis = v;
//		if(v == true)
//			part = new Part((x+8),(y+9),p,i);
	}

	public boolean getVis(){
		return vis;
	}
	
	public void setPart(){
//		part = new Part((x+8),(y+9),p,i);
	}
	
	public int getPart(){
		return partindx;
	}
}
