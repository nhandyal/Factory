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
{

	Lane lane1, lane2;
	Bin bin;
	int push;

	public Feeder(Lane l1, Lane l2){
		push = 0;
		lane1 = l1;
		lane2 = l2;
	}
	
	public void pushPart(){
		push--;
	}
	
	public int getPush(){
		return push;
	}
	
	public void setPush(int p){
		push = p;
	}
	
	public void addBin(Bin b){
		bin = b;
		bin.setVis(true);
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
		return (push/4);
	}
}
