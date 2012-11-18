package factory.server.managers.laneManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

// user packages
import factory.global.data.*;
import factory.server.managers.GuiManager;

public class LaneManager implements GuiManager
{
	ImageIcon background;
	ArrayList<Lane> lanes;
	ArrayList<Bin> bins;
	ArrayList<Line> dividers;
	ArrayList<Feeder> feeders;
	int index, counter, counter2;
	FOComparator foc;
	ImageArray images;
	Camera cam;
	
	TreeMap<Integer,Boolean> changeMap;
	TreeMap<Integer,FactoryObject> temp;
	TreeMap<Integer,FactoryObject> changeData;

	public LaneManager(){
	
		index = 1;
		
		// Create 8 lanes
		lanes = new ArrayList<Lane>();
		lanes.add(new Lane(1,73,index));
		index+=3;
		lanes.add(new Lane(1,108,index));
		index+=3;
		lanes.add(new Lane(1,197,index));
		index+=3;
		lanes.add(new Lane(1,232,index));
		index+=3;
		lanes.add(new Lane(1,321,index));
		index+=3;
		lanes.add(new Lane(1,356,index));
		index+=3;
		lanes.add(new Lane(1,445,index));
		index+=3;
		lanes.add(new Lane(1,480,index));
		index+=3;	
		
		// Create 4 Dividers
		dividers = new ArrayList<Line>();
		dividers.add(new Line(334,108,288,108,index));
		index++;
		dividers.add(new Line(334,232,288,232,index));
		index++;
		dividers.add(new Line(334,356,288,356,index));
		index++;
		dividers.add(new Line(334,480,288,480,index));
		index++;


		// Create 10 Bins
		bins = new ArrayList<Bin>();
		bins.add(new Bin(348,98,10,index,0));
		index+=2;
		bins.add(new Bin(348,98,10,index,1));
		index+=2;
		bins.add(new Bin(348,221,10,index,2));
		index+=2;
		bins.add(new Bin(348,221,10,index,3));
		index+=2;
		bins.add(new Bin(348,345,10,index,4));
		index+=2;
		bins.add(new Bin(348,345,10,index,5));
		index+=2;
		bins.add(new Bin(348,470,10,index,6));
		index+=2;
		bins.add(new Bin(348,470,10,index,7));
		index+=2;
		bins.add(new Bin(348,470,10,index,8));
		index+=2;
		bins.add(new Bin(348,470,10,index,9));
		index+=2;
		
		// Create 4 Feeders
		feeders = new ArrayList<Feeder>();
		feeders.add(new Feeder(313,54,19,index));
		index++;
		feeders.add(new Feeder(313,178,19,index));
		index++;
		feeders.add(new Feeder(313,302,19,index));
		index++;
		feeders.add(new Feeder(313,426,19,index));
		index++;

		// Create Camera
		cam = new Camera(264,28,13,Integer.MAX_VALUE);
		
		// Turn On Lane 0, Off Lanes 1-7
		laneSwitch(8,0,36);
		for(int i=1;i<8;i++)
			lanes.get(i).setActive(false);

		// Create Backgroud Image
		background = new ImageIcon("LMBG.png");
		
		// Create ImageList
		images = new ImageArray();

		// Start Counters
		counter = 0;
		counter2 = 0;
		
		// Initialize comparator
		foc = new FOComparator();
		
		// Initialize TreeMaps
		changeMap = new TreeMap<Integer,Boolean>();
		changeData = new TreeMap<Integer,FactoryObject>();
		temp = new TreeMap<Integer,FactoryObject>();
	}

	public void laneSwitch(int x1, int x2, int pnum){
		if(x1<8){										// if lane exists
			lanes.get(x1).setActive(false);				// turn lane off
			bins.get(x1).setVis(false);					// turn bin off
			feeders.get(x1/2).removeBin();				// remove bin from feeder
			dividers.get(x1/2).dividerNeutral();		// put divider in neutral position
		}
		if(x2<8){										// if lane exists
			lanes.get(x2).setActive(true);				// turn lane on
			bins.get(x2).setVis(true);					// turn bin on
			feeders.get(x2/2).addBin(bins.get(x2));		// add bin to feeder
			feeders.get(x2/2).setPush(pnum);			// set # of parts to make
			if(x2%2 == 0)								// if upper lane
				dividers.get(x2/2).dividerDown();		// put divider in lower position
			if(x2%2 == 1)								// if lower lane
				dividers.get(x2/2).dividerUp();			// put divider in upper position
		}
	}

	public void sync(TreeMap<Integer,FactoryObject> map){
		
		// Add Dividers
		for(int i=0;i<4;i++){
			map.put(dividers.get(i).getIndex(),dividers.get(i));
		}

		// Add Bins (if visible)
		for(int i=0;i<4;i++){
			if(feeders.get(i).hasBin() == true){
				map.put(feeders.get(i).getBin().getIndex(),feeders.get(i).getBin());
				map.put(feeders.get(i).getBin().getPartIcon().getIndex(),feeders.get(i).getBin().getPartIcon());
			}
		}

		// Add Lane Content
		for(int i=0;i<8;i++){
			// Conveyor Lines
			for(int j=0;j<3;j++){
				if(lanes.get(i).getLaneLine(j).getPositionX() <= 334)
					map.put(lanes.get(i).getLaneLine(j).getIndex(),lanes.get(i).getLaneLine(j));
			}
			// Parts (Lanes and Nests)
			for(int j=0;j<lanes.get(i).getLaneSize();j++)
				map.put(lanes.get(i).getLanePart(j).getIndex(),lanes.get(i).getLanePart(j));
			for(int j=0;j<lanes.get(i).getNestSize();j++)
				map.put(lanes.get(i).getNestPart(j).getIndex(),lanes.get(i).getNestPart(j));
		}

		// Add Parts Low Lights
		for(int i=0;i<4;i++){
			// If parts are low and the lane is on
			if(feeders.get(i).getPush() <= feeders.get(i).getPartsLow() && (lanes.get(i*2).getActive() == true || lanes.get((i*2)+1).getActive() == true)){
				map.put(feeders.get(i).getIndex(),feeders.get(i));
			}
		}

		// Add Camera
		map.put(cam.getIndex(),cam);

		// Add Camera Brace
		map.put((Integer.MAX_VALUE - 1),new FactoryObject());
		map.get(Integer.MAX_VALUE - 1).setPosition((cam.getPositionX()+14),0);
		map.get(Integer.MAX_VALUE - 1).setIndex(Integer.MAX_VALUE - 1);
		map.get(Integer.MAX_VALUE - 1).setImage(15);

		// If picture is taken add camera flash
		if(cam.getTakenPicture() == true && counter2 == 0){
			map.put((Integer.MAX_VALUE - 2),new FactoryObject());
			map.get(Integer.MAX_VALUE - 2).setPosition(cam.getPositionX(),cam.getPositionY());
			map.get(Integer.MAX_VALUE - 2).setIndex(Integer.MAX_VALUE - 2);
			map.get(Integer.MAX_VALUE - 2).setImage(14);
			cam.setTakenPicture(false);
		}
	}

	public void update(TreeMap<Integer,Boolean> changeMap, TreeMap<Integer,FactoryObject> changeData){

		temp.clear();		
		sync(changeData); // Get Previous frame data
		
		// Create a hard copy of FO from previous frame
		Iterator k = changeData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeData.get(i).getIndex() > 0){
				temp.put(i,new FactoryObject());
				temp.get(i).setPosition(changeData.get(i).getPositionX(),changeData.get(i).getPositionY()); // Copy Position
				temp.get(i).setIndex(changeData.get(i).getIndex()); // Copy Index
				temp.get(i).setIsLine(changeData.get(i).getIsLine()); // Copy isLine boolean
				if(changeData.get(i).getIsLine() == true)	// If it's a line
					temp.get(i).setPositionF(changeData.get(i).getPositionXF(),changeData.get(i).getPositionYF()); // Copy the fixed point
				else										// if it's not a line
					temp.get(i).setImage(changeData.get(i).getImageIndex()); // Copy the image index
			}
		}

		changeData.clear();
		
		for(int i=0;i<8;i++){
			if(lanes.get(i).getActive() == true){										// if lane is on and feeder isn't empty
				if(counter==24 && feeders.get(i/2).getPush() > 0){						// every 25th instance of timer
					lanes.get(i).addPart(feeders.get(i/2).getBin().getPart(),index);	// create a new part
					index++;															// Add one to index
					feeders.get(i/2).pushPart();										// subtract 1 from feeder counter
					counter = 0;														// reset counter
				}
				counter++;

				// Determine if all parts on lane have stopped moving
				boolean partsStopped = true;
				for(int j=0;j<lanes.get(i).getLaneSize();j++){
					if(lanes.get(i).getLanePart(j).getIsMoving() == true)
						partsStopped = false;
				}

				if(feeders.get(i/2).getPush() == 0 && partsStopped == true){	// if bin is empty and the parts have stopped moving
					laneSwitch(i,i+1,36);										// turn off lane, turn on next lane
					counter = 0;												// reset counter
				}
			}
		}
		
		// Move Elements
		for(int i=0;i<8;i++){
			lanes.get(i).moveParts();
			if(lanes.get(i).getPicNeeded() == true && cam.getHasPath() == false){			// If a nest needs a picture and the camera isn't busy
				cam.setPath(lanes.get(i).getPositionX(),lanes.get(i).getPositionY(),i);		// Tell the camera to go to the nest
			}
		}

		cam.move();											// move the camera
		if(cam.getTakenPicture() == true){					// if the camera has taken a picture
			lanes.get(cam.getNest()).setPicNeeded(false);	// tell the nest a picture is taken
			counter2 = 0;									// reset counter
		}

		// Get current frame data
		sync(changeData);
		
		// Create changeMap
		k = changeData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(temp.containsKey(i) == true){							// if the previous frame has the object
				if(foc.compare(temp.get(i),changeData.get(i)) == 1)		// if the object has moved
					changeMap.put(i,true);								// tell the changeMap
			}
			else{														// if the previous frame doesn't have the object
				changeMap.put(i,true);									// tell the changeMap
			}
		}
		
		// Delete extra mappings
		k = temp.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeData.containsKey(i) == false){		// If the cuurent frame doesn't have an object from the previous frame
				changeMap.put(i,false);					// tell the changeMap
			}
		}
		
		// Trim changeData
		k = changeMap.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeMap.get(i) == false)				// if the changeMap says somethings been deleted
				changeData.remove(i);					// remove it from changeData
		}

		// Delete unchanged data
		k = temp.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeData.containsKey(i) == true){						// if the current frame has the object
				if(foc.compare(temp.get(i),changeData.get(i)) == 0)		// if the object hasn't moved
					changeData.remove(i);								// delete it from changeData
			}
		}

		// Control Camera Flash
		counter2++;
		if(counter2 > 3)										// if camera flash is on
			changeMap.put((Integer.MAX_VALUE - 2), false);		// tell the changeMap
		
		// Convert Lines to FOs
		k = changeData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeData.get(i).getIsLine() == true){ // if object is a line
				// Create a temporary copy
				Line tempLine = new Line(changeData.get(i).getPositionX(),changeData.get(i).getPositionY(),changeData.get(i).getPositionXF(),changeData.get(i).getPositionYF(),changeData.get(i).getIndex());
				changeData.put(i,new FactoryObject());
				changeData.get(i).setPosition(tempLine.getPositionX(),tempLine.getPositionY()); // copy position
				changeData.get(i).setPositionF(tempLine.getPositionXF(),tempLine.getPositionYF()); // copy fixed coordinates
				changeData.get(i).setIsLine(true); // tell it it's a line
				changeData.get(i).setIndex(tempLine.getIndex()); // copy the index
			}
		}
	}

	public ArrayList<Part> getNest(int i){
		ArrayList<Part> nest = new ArrayList<Part>();
		for(int j=0;j<lanes.get(i).getNestSize();j++){
			nest.add(lanes.get(i).getNestPart(j));
		}
		return nest;
	}
}
