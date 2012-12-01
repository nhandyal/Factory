/*
** Authors:			Timi Okuboyejo
** Date: 			11/15/12
** Project: 		Cs200-Factory
** Description: 	Contains information for feeders(lanes, bins)
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;

public class Feeder extends FactoryObject implements Serializable{

	Bin bin;
	int push, intlpush;
	boolean broken;

	public Feeder(int xPos, int yPos, int image, int i){
		push = 0;
		x = xPos;
		y = yPos;
		index = i;
		setImage(image);
		broken = false;
	}
	
	public void pushPart(){
		push--;
	}
	
	public int getPush(){
		return push;
	}
	
	public void setPush(int p){
		push = p;
		intlpush = p;
	}
	
	public void setBroken(boolean b){
		broken = b;
	}
	
	public boolean getBroken(){
		return broken;
	}

	public void addBin(Bin b){
		bin = b;
		bin.setVis(true);
		bin.setPosition(x+34,y+43);
	}
	
	public void removeBin(){
		bin.setVis(false);
		bin = null;
	}
	
	public Bin getBin(){
		return bin;
	}
	
	public boolean hasBin(){
		if(bin == null)
			return false;
		else
			return true;
	}
	
	public int getPartsLow(){
		return (intlpush/10);
	}
}
