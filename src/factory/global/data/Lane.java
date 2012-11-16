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
	boolean laneActive;
	int counter = 0;

	public Lane(int initialPosX, int initialPosY, int indx){
		x = initialPosX;
		y = initialPosY;
		index = indx;
		
		lane = new ArrayList<Part>();
		nest = new ArrayList<Part>();
		
		// Create LaneLines
		lines = new ArrayList<Line>();
		lines.add(new Line((x+80),(y+1),(x+80),(y+35),(index)));
		lines.add(new Line((x+180),(y+1),(x+180),(y+35),(index+1)));
		lines.add(new Line((x+280),(y+1),(x+280),(y+35),(index+2)));
	}

	public void setActive(boolean b){
			laneActive = b;
	}
	
	public boolean getActive(){
		return laneActive;
	}
	
	public void addPart(int img ,int i){
		lane.add(new Part(x+317,y+16,img,i));
		counter++;
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

	public int getTotalLaneSize(){
		return counter;
	}
	
	public Line getLaneLine(int i){
		return lines.get(i);
	}

	public void moveParts(){														// All Relative to Lane
		if(laneActive == true){														// if lane is on
			for(int i=0;i<lane.size();i++){
				if(nest.size()<9){													// if nest is not full
					lane.get(i).moveLeft();											// move part left
					if(lane.get(i).getPositionX()<=(x+40)){							// if part is at 40
						nest.add(lane.get(i));										// move it to the nest
						lane.remove(i);
					}
				}
				if(nest.size()==9){													// if nest is full
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
				if(nest.get(i).getPositionX()<=((x+5)+((i/3)*15))){					// if nest part (in groups of 3) is at 5,20,35
					if(i%3 == 0 && nest.get(i).getPositionY()>=(y+6))				// if part is first in group and is greater than 6
						nest.get(i).moveUp();										// move up
					else if(i%3 == 1 && nest.get(i).getPositionY()<=(y+26))			// if part is second in group an is less than 26
						nest.get(i).moveDown();										//move down
				}
				else																// if part isn't at 5,20,35
					nest.get(i).moveLeft();											// move part left
			}
			for(int i=0;i<3;i++){
				lines.get(i).moveLeft();											// move lanelines left
				if(lines.get(i).getPositionX()<=(x+43))								// if laneline is at 43
					lines.get(i).reset();											// add 300 to its value
			}
		}
	}
}
