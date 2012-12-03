/*
** Authors:			David Cox, Timi Okuboyejo
** Date: 			11/01/12
** Project: 		Cs200-Factory
** Description: 	Contains information for bins(parts, coordinates, etc)
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;

public class Bin extends FactoryObject implements Serializable{

	boolean vis = false;
	Part part;
	int partindx;
	int index2;

	public Bin(int initialPosX, int initialPosY, int initialImage, int indx, int p){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		index = indx;
		index2 = indx;
		partindx = p;
		part = new Part((x+8),(y+9),p,(index+1));
	}
	
	public void setVis(boolean v){
		vis = v;
		if(v == true){
			index = index2;
			part.setIndex(index+1);
		}
		else{
			index = -5;
			part.setIndex(-5);
		}
	}

	public boolean getVis(){
		return vis;
	}
	
	public void setPart(){
		part.setPosition((x+8),(y+9));
	}
	
	public int getPart(){
		part.setPosition((x+8),(y+9));
		return partindx;
	}

	public Part getPartIcon(){
		return part;
	}
}
