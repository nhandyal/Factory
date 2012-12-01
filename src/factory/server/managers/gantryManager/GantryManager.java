package factory.server.managers.gantryManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.util.*;

// user packages
import factory.global.data.*;
import factory.server.managers.GuiManager;

//bullshit comment

public class GantryManager implements GuiManager, Serializable
{
	//ImageIcon background;
	ArrayList<Bin> bins;
	ArrayList<Feeder> feeders;
	//FOComparator foc;
	//ImageArray images;
	public gantryRobot robot;
	int counter, index;
    public boolean isMoveToBin = false;
    public boolean isMoveToFeeder = false;
    public boolean isMoveToPoint = false;
    public boolean isRemoveBin = false;
    public boolean isMoveToBinPurge = false;
    public int bin;
	public int feeder;
    public int x;
    public int y;
	TreeMap<Integer,Boolean> changeMap;
	TreeMap<Integer,FactoryObject> temp;
	TreeMap<Integer,FactoryObject> changeData;
	TreeMap<Integer,FactoryObject> animData;

	public GantryManager(){
	
		index = 1;


		// Create 10 Bins
		bins = new ArrayList<Bin>();
		bins.add(new Bin(340,105,10,index,0));
		index+=2;
		bins.add(new Bin(340,146,10,index,1));
		index+=2;
		bins.add(new Bin(340,187,10,index,2));
		index+=2;
		bins.add(new Bin(340,229,10,index,3));
		index+=2;
		bins.add(new Bin(340,270,10,index,4));
		index+=2;
		bins.add(new Bin(340,311,10,index,5));
		index+=2;
		bins.add(new Bin(340,353,10,index,6));
		index+=2;
		bins.add(new Bin(340,393,10,index,7));
		index+=2;
		bins.add(new Bin(340,434,10,index,8));
		index+=2;
		bins.add(new Bin(340,475,10,index,9));
		index+=2;

		for(int i=0;i<10;i++){
			bins.get(i).setVis(true);
		}

		feeders = new ArrayList<Feeder>();

		feeders.add(new Feeder(28,98,19,index));
		index += 2;
		feeders.add(new Feeder(28,222,19,index));
		index += 2;
		feeders.add(new Feeder(28,346,19,index));
		index += 2;
		feeders.add(new Feeder(28,470,19,index));
		index += 2;

		robot = new gantryRobot(200,335,18,bins,feeders, index, this);

		// Create Backgroud Image
		//background = new ImageIcon("GMBG.png");
		
		// Create ImageList
		//images = new ImageArray();

		// Start Counter
		counter = 0;
		
		// Initialize comparator
		//foc = new FOComparator();
		
		//changeMap = new TreeMap<Integer,Boolean>();
		//changeData = new TreeMap<Integer,FactoryObject>();
		animData = new TreeMap<Integer,FactoryObject>();
		temp = new TreeMap<Integer,FactoryObject>();
	}

	
	public void sync(TreeMap<Integer,FactoryObject> map){
			
		// Add Bins
		for(int i=0;i<10;i++){
			map.put(bins.get(i).getIndex(),bins.get(i));
			map.put(bins.get(i).getPartIcon().getIndex(), bins.get(i).getPartIcon());
		}

		/*// Add Feeders
		for(int i=0;i<4;i++){
			map.put(feeders.get(i).getIndex(),feeders.get(i));
		}*/

		map.put(robot.getIndex(), robot);

	}
    public void move()
    {
        if (isMoveToBin)
            robot.moveToBin(bin);
        if (isMoveToBinPurge)
            robot.moveToBinPurge(bin);
        if (isMoveToFeeder)
            robot.moveToFeeder(feeder);
        if (isMoveToPoint)
            robot.moveToPoint(x, y);
    }
	public void update(TreeMap<Integer,Boolean> changeMap, TreeMap<Integer,FactoryObject> changeData){
		
		sync(changeData);
		move();
		temp.clear();
		
		Iterator k = changeData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeData.get(i).getIndex() > 0){
				temp.put(i,new FactoryObject());
				temp.get(i).setPosition(changeData.get(i).getPositionX(),changeData.get(i).getPositionY());
				temp.get(i).setIndex(changeData.get(i).getIndex());
				temp.get(i).setIsLine(changeData.get(i).getIsLine());
				if(changeData.get(i).getIsLine() == true){
					temp.get(i).setPositionF(changeData.get(i).getPositionXF(),changeData.get(i).getPositionYF());
					temp.get(i).setIsLine(true);
				}
				else{
					temp.get(i).setImage(changeData.get(i).getImageIndex());
				}
			}
		}

		changeData.clear();
	//		changeMap.clear();

		/*if(robot.hasBin == false){
			robot.moveToBin(1);
		}

		else if(robot.hasBin == true){
			robot.moveToFeeder(3);
		}*/
		

		sync(changeData);
		
		k = changeData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(temp.containsKey(i) == true){
				if(temp.get(i).equals(changeData.get(i))){
	//					changeMap.put(i,false);
				}
				else{
					changeMap.put(i,true);
				}
			}
			else{
				changeMap.put(i,true);
			}
		}
		
		// Delete extra mappings
		k = temp.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeData.containsKey(i) == false){
	//				changeData.put(i, new FactoryObject());
				changeMap.put(i,false);
			}
		}
		
		k = changeMap.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeMap.get(i) == false)
				changeData.remove(i);
		}
		
		k = changeData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
	//			System.out.println("changeData "+changeData.get(i).getIndex());
		}
	}		

	public void bindManager(GuiManager bindManager){}
	public void breakPart(String b, int x){}

}
