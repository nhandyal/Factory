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
	}

	public boolean getVis(){
		return vis;
	}
	
	public int getPart(){
		return partindx;
	}
}
