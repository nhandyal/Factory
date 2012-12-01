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

public class LaneManager implements GuiManager{

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
	TreeMap<Integer,FactoryObject> purgeData;
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
		dividers.add(new Line(334,143,288,108,index));
		index++;
		dividers.add(new Line(334,267,288,232,index));
		index++;
		dividers.add(new Line(334,391,288,356,index));
		index++;
		dividers.add(new Line(334,515,288,480,index));
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
		index += 2;
		feeders.add(new Feeder(313,178,19,index));
		index += 2;
		feeders.add(new Feeder(313,302,19,index));
		index += 2;
		feeders.add(new Feeder(313,426,19,index));
		index += 2;

		// Create Camera
		cam = new Camera(264,28,13,Integer.MAX_VALUE);
		
		// Turn Off Lanes 0-7
		for(int i=0;i<8;i++)
			lanes.get(i).setActive(false);

		addBin(0,bins.get(0),36);
		
		// Create ImageList
		images = new ImageArray();

		// Start Counters
		counter = 0;
		counter2 = 0;
		
		// Initialize comparator
		foc = new FOComparator();
		
		// Initialize TreeMaps
		purgeData = new TreeMap<Integer,FactoryObject>();
		changeMap = new TreeMap<Integer,Boolean>();
		changeData = new TreeMap<Integer,FactoryObject>();
		temp = new TreeMap<Integer,FactoryObject>();
	}

	public void laneManagement(){

		for(int i=0;i<4;i++){
			if(lanes.get(i*2).getActive() == true || lanes.get((i*2)+1).getActive() == true){	// if a lane is on
				if(counter==24 && feeders.get(i).getPush() > 0 && feeders.get(i).getBroken() == false){								// every 25th instance of timer, if feeder is working and bin has parts
					if(dividers.get(i).getPositionY() > dividers.get(i).getPositionYF() && lanes.get(i*2).getActive() == true)		// if the divider is in the lower position
						lanes.get(i*2).addPart(feeders.get(i).getBin().getPart(),index);		// create a new part in upper lane
					else if(dividers.get(i).getPositionY() < dividers.get(i).getPositionYF() && lanes.get(i*2+1).getActive() == true)		// if the divider is in the lower position																		// if the divider is in the upper position
						lanes.get((i*2)+1).addPart(feeders.get(i).getBin().getPart(),index);	// create a new part in lower lane
					index++;																	// Add one to index
					feeders.get(i).pushPart();													// subtract 1 from feeder counter
					counter = 0;																// reset counter
				}
				counter++;

//				if(lanes.get(i*2).getActive() == true)
//					autoLaneSwitch(i*2);
//				else if(lanes.get(i*2+1).getActive() == true)
//					autoLaneSwitch(i*2+1);

				lanes.get(i*2).moveParts();
				lanes.get(i*2+1).moveParts();
				
//				takePicture(i*2);
//				takePicture(i*2+1);
			}
		}

		cam.move();											// move the camera
		if(cam.getTakenPicture() == true){					// if the camera has taken a picture
			lanes.get(cam.getNest()).setPicNeeded(false);	// tell the nest a picture is taken
			counter2 = 0;									// reset counter
		}
	}

	public void autoLaneSwitch(int i){
		// Determine if all parts on lane have stopped moving
		boolean partsStopped = true;
		for(int j=0;j<lanes.get(i).getLaneSize();j++){
			if(lanes.get(i).getLanePart(j).getIsMoving() == true)
				partsStopped = false;
		}
		if(feeders.get(i/2).getPush() == 0){// && partsStopped == true){	// if bin is empty and the parts have stopped moving
			laneSwitch(i,i+1);										// turn off lane, turn on next lane
			counter = 0;												// reset counter
		}
	}

	public void takePicture(int i){
		if(lanes.get(i).getPicNeeded() == true && cam.getHasPath() == false)			// If a nest needs a picture and the camera isn't busy
			cam.setPath(lanes.get(i).getPositionX(),lanes.get(i).getPositionY(),i);		// Tell the camera to go to the nest
	}

	public void laneSwitch(int x1, int x2){
		if(x1<8){										// if lane exists
			lanes.get(x1).setActive(false);				// turn lane off
//			feeders.get(x1/2).removeBin();				// remove bin from feeder
			removeBin(x1/2);
//			purgeNest(x1);
		}
		if(x2<8){										// if lane exists
			lanes.get(x2).setActive(true);				// turn lane on
//			feeders.get(x2/2).addBin(bins.get(x2));		// add bin to feeder
//			feeders.get(x2/2).setPush(pnum);			// set # of parts to make
//			addBin(x2/2,bins.get(x2),24);
			if(x2%2 == 0)								// if upper lane
				dividers.get(x2/2).dividerDown();		// put divider in lower position
			if(x2%2 == 1)								// if lower lane
				dividers.get(x2/2).dividerUp();			// put divider in upper position
		}
	}

	public void laneToggle(int i){
		if(lanes.get(i).getActive() == true)
			lanes.get(i).setActive(false);
		else
			lanes.get(i).setActive(true);
	}

	public void dividerToggle(int i){
		if(dividers.get(i).getPositionY() > dividers.get(i).getPositionYF())
			dividers.get(i).dividerUp();
		else
			dividers.get(i).dividerDown();
	}

	public void purgeLane(int i){
		lanes.get(i).purgeLane();

		sync(purgeData);
		Iterator k = purgeData.keySet().iterator();
		while(k.hasNext()){
			int j = (Integer) k.next();
			changeMap.put(j,false);
		}
	}

	public void purgeNest(int i){
		lanes.get(i).purgeNest();
	}

	public boolean testNest(int k){
		boolean result = true;
		ArrayList<Part> nest = lanes.get(k).getNest();
		int j = -1;
		if(nest.size() > 9)
			result = false;
		if(nest.size() > 0)
			j = nest.get(0).getImageIndex();
		for(int i=0;i<9;i++){
			if(j != nest.get(i).getImageIndex())
				result = false;
		}
		if(j < 0)
			result = false;

		return result;
	}

	public void addBin(int i, Bin b, int pnum){
		feeders.get(i).addBin(b);
		feeders.get(i).setPush(pnum);
	}

	public void addBin2(int i, int b, int pnum){
		feeders.get(i).addBin(bins.get(b));
		feeders.get(i).setPush(pnum);
	}

	public void removeBin(int i){
		feeders.get(i).removeBin();
	}

	public ArrayList<Part> getNest(int i){
		ArrayList<Part> nest = new ArrayList<Part>();
		for(int j=0;j<lanes.get(i).getNestSize();j++){
			nest.add(lanes.get(i).getNestPart(j));
		}
		return nest;
	}

	public void removePart(int i){
		ArrayList<Part> nest = lanes.get(i).getNest();
		if (nest.size() > 0)
			lanes.get(i).getNest().remove((nest.size() -1));
	}

	public void breakLane(int i){
		lanes.get(i).setLaneBroken(true);
//		System.out.println("Lane "+(i+1)+" broken "+lanes.get(i).getLaneBroken());
	}

	public void insertLanePart(int i){
		int j;
		if(i%2 == 0)
			j = i+1;
		else
			j = i-1;

		if(lanes.get(i).getLaneSize() > 0 && lanes.get(j).getLaneSize() > 0){
			if(lanes.get(i).getLanePart(0).getImageIndex() == lanes.get(j).getLanePart(0).getImageIndex()){
				lanes.get(j).getLanePart(0).setImage(lanes.get(i).getLanePart(0).getImageIndex());
			}
		}
	}

	public void breakNest(int i){
		lanes.get(i).setNestBroken(true);
//		System.out.println("Lane "+(i+1)+" broken "+lanes.get(i).getLaneBroken());
	}

	public void insertNestPart(int i){
		int j = -1;
		if(lanes.get(i).getNestSize() == 9){
			j = lanes.get(i).getNestPart(8).getImageIndex();
			if(j == 9)
				j = 0;
			lanes.get(i).getNestPart(8).setImage(j);
		}
		else if(lanes.get(i).getNestSize() >= 0){
			if(lanes.get(i).getNestSize() == 0 && lanes.get(i).getLaneSize() > 0)
				j = lanes.get(i).getLanePart(0).getImageIndex();
			else if(lanes.get(i).getNestSize() > 0)
				j = lanes.get(i).getNestPart(8).getImageIndex();
			if(j == 9)
				j = 0;
			if(j >= 0){
				lanes.get(i).getNest().add(new Part(lanes.get(i).getPositionX()+332,lanes.get(i).getPositionY()+16,j,index));
				index++;
			}
		}
	}

	public void breakCam(){
		cam.setBroken(true);
	}

	public void breakDivider(int i){
		dividers.get(i).setBroken(true);
	}

	public void breakFeeder(int i){
		feeders.get(i).setBroken(true);
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
		
		// Automated Mvmt
		laneManagement();

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
			if(changeData.containsKey(i) == false){		// If the current frame doesn't have an object from the previous frame
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
			// If parts are low and a lane is on
			if(feeders.get(i).getPush() <= feeders.get(i).getPartsLow() && (lanes.get(i*2).getActive() == true || lanes.get((i*2)+1).getActive() == true)){
				map.put(feeders.get(i).getIndex(),feeders.get(i));
			}
			if(feeders.get(i).getBroken() == true){
				// Add Caution Sign
				map.put((feeders.get(i).getIndex()+1),new FactoryObject());
				map.get(feeders.get(i).getIndex()+1).setPosition((feeders.get(i).getPositionX()+31),(feeders.get(i).getPositionY()+67));
				map.get(feeders.get(i).getIndex()+1).setIndex(feeders.get(i).getIndex()+1);
				map.get(feeders.get(i).getIndex()+1).setImage(21);
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
}
