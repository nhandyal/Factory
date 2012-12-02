/*
** Authors:			Timi Okuboyejo
** Date: 			11/01/12
** Project: 		Cs200-Factory
** Description: 	Contains information for lanes(lanes, nests, etc)
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;
import java.util.*;

public class Lane extends FactoryObject implements Serializable{

	ArrayList<Part> lane, nest;
	ArrayList<Line> lines;
	boolean laneActive, picTaken, nestFull;
	int counter = 0;
	boolean laneBroken, nestBroken;

	public Lane(int initialPosX, int initialPosY, int indx){
		x = initialPosX;
		y = initialPosY;
		index = indx;
		
		lane = new ArrayList<Part>();
		nest = new ArrayList<Part>();

		picTaken = false;
		nestFull = false;
		
		// Create LaneLines
		lines = new ArrayList<Line>();
		lines.add(new Line((x+80),(y+1),(x+80),(y+35),(index)));
		lines.add(new Line((x+180),(y+1),(x+180),(y+35),(index+1)));
		lines.add(new Line((x+280),(y+1),(x+280),(y+35),(index+2)));

		// Set Broken Booleans to false
		laneBroken = false;
		nestBroken = false;
	}

	public void setActive(boolean b){
			laneActive = b;
	}
	
	public boolean getActive(){
		return laneActive;
	}
	
	public void addPart(int img ,int i){
		lane.add(new Part(x+332,y+16,img,i));
	}
	
	public Part getLanePart(int i){
		return lane.get(i);
	}
	
	public Part getNestPart(int i){
		return nest.get(i);
	}

	public int getLaneSize(){
		return lane.size();
	}
	
	public int getNestSize(){
		return nest.size();
	}

	public ArrayList<Part> getLane(){
		return lane;
	}

	public ArrayList<Part> getNest(){
		return nest;
	}

	public boolean getNestFull(){
		return nestFull;
	}

	public void purgeLane(){
		lane.clear();
		laneBroken = false;
	}

	public void purgeNest(){
		nest.clear();
		picTaken = false;
		nestBroken = false;
		nestFull = false;
	}
	
	public Line getLaneLine(int i){
		return lines.get(i);
	}

	public void setPicTaken(boolean b){
		picTaken = b;
	}

	public boolean getPicTaken(){
		return picTaken;
	}

	public void setLaneBroken(boolean b){
		laneBroken = b;
	}

	public void setNestBroken(boolean b){
		nestBroken = b;
		if (b == true)
			nestFull = false;
	}

	public boolean getLaneBroken(){
		return laneBroken;
	}

	public boolean getNestBroken(){
		return nestBroken;
	}

	public void moveParts(){														// All Relative to Lane
		for(int i=0;i<lane.size();i++)
			lane.get(i).setIsMoving(false);
		for(int i=0;i<nest.size();i++)
			nest.get(i).setIsMoving(false);

		if(nest.size() == 0){
			nestFull = false;
			picTaken = false;
			counter = 0;
		}

		if(laneActive == true && laneBroken == false){														// if lane is on
			for(int i=0;i<lane.size();i++){
				if(nestFull == false){												// if nest is not full
					lane.get(i).moveLeft();											// move part left
					if(lane.get(i).getPositionX()<=(x+40)){							// if part is at 40
						nest.add(lane.get(i));										// move it to the nest
//						System.out.println("Part "+counter+" added\nnestFull: "+nestFull);
//						counter++;
						lane.remove(i);
						if(nest.size() == 9 && nestBroken == false){
							nestFull = true;
						}
					}
				}
				if(nestFull == true){												// if nest is full
					if(lane.get(i).getPositionX()<=((x+50)+((i/3)*10))){			// if part (in groups of 3) is at 50,60,70,...
						if(i%3 == 0 && lane.get(i).getPositionY()>=(y+6))			// if part is first in group and is greater than 6
							lane.get(i).moveUp();									// move up
						else if(i%3 == 1 && lane.get(i).getPositionY()<=(y+26))		// if part is second in group an is less than 26
							lane.get(i).moveDown();									// move down
					}
					else															// if part isn't at 50,60,70,...
						lane.get(i).moveLeft();										// move part left
				}
			}
			for(int i=0;i<nest.size();i++){
/*				if(nest.get(i).getPositionX()<=((x+5)+((i/3)*15))){					// if nest part (in groups of 3) is at 5,20,35
					if(i%3 == 0 && nest.get(i).getPositionY()>=(y+6))				// if part is first in group and is greater than 6
						nest.get(i).moveUp();										// move up
					else if(i%3 == 1 && nest.get(i).getPositionY()<=(y+26))			// if part is second in group an is less than 26
						nest.get(i).moveDown();										//move down
				}
				else																// if part isn't at 5,20,35
					nest.get(i).moveLeft();											// move part move left 	*/

				if(nestBroken == false){	
					switch(i){
						case 0:
							nest.get(i).moveTo((x+5),(y+6));
							break;
						case 1:
							nest.get(i).moveTo((x+5),(y+16));
							break;
						case 2:
							nest.get(i).moveTo((x+5),(y+26));
							break;
						case 3:
							nest.get(i).moveTo((x+20),(y+6));
							break;
						case 4:
							nest.get(i).moveTo((x+20),(y+16));
							break;
						case 5:
							nest.get(i).moveTo((x+20),(y+26));
							break;
						case 6:
							nest.get(i).moveTo((x+35),(y+6));
							break;
						case 7:
							nest.get(i).moveTo((x+35),(y+16));
							break;
						case 8:	
							nest.get(i).moveTo((x+35),(y+26));
							break;
					}
				}
				else
					nest.get(i).moveTo((x+5),(y+16));
			}
		}

		if(laneActive == true){
			for(int i=0;i<3;i++){
				lines.get(i).moveLeft();											// move lanelines left
				if(lines.get(i).getPositionX()<=(x+43))								// if laneline is at 43
					lines.get(i).reset();											// add 300 to its value
			}
		}
	}

	public void removeNest(){
		lane.get(0).setPosition(nest.get(8).getPositionX(),nest.get(8).getPositionY());
		nest.remove(8);
		nest.add(lane.get(0));
		lane.remove(0);
	}
}
