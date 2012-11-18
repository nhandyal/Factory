//package factory.server.managers.gantryManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

// user packages
//import factory.global.data.*;
//import factory.server.managers.GuiManager;

public class gantryManager extends JFrame implements ActionListener //implements GuiManager
{
	ImageIcon background;
	ArrayList<Bin> bins;
	ArrayList<Feeder> feeders;
	FOComparator foc;
	ImageArray images;
	gantryRobot robot;
	int counter, index;
	
	TreeMap<Integer,Boolean> changeMap;
	TreeMap<Integer,FactoryObject> temp;
	TreeMap<Integer,FactoryObject> changeData;
	TreeMap<Integer,FactoryObject> animData;

	public gantryManager(){
	
		index = 18;


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

		feeders = new ArrayList<Feeder>();

		feeders.add(new Feeder(30, 107));
		feeders.add(new Feeder(30, 232));
		feeders.add(new Feeder(30, 357));
		feeders.add(new Feeder(30, 480));

		robot = new gantryRobot(200,335,18,bins, feeders);

		// Create Backgroud Image
		background = new ImageIcon("GMBG.png");
		
		// Create ImageList
		images = new ImageArray();

		// Start Counter
		counter = 0;
		
		// Initialize comparator
		foc = new FOComparator();
		
		//changeMap = new TreeMap<Integer,Boolean>();
		//changeData = new TreeMap<Integer,FactoryObject>();
		//animData = new TreeMap<Integer,FactoryObject>();
		//temp = new TreeMap<Integer,FactoryObject>();
	}

	public static void main(String[] args){
		gantryManager l = new gantryManager();
		l.setVisible(true);
		l.setSize(400,670);
		l.createBufferStrategy(2);
		l.setTitle("Gantry Manager");
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Timer(50,l).start();
	} //end main

	
	public void actionPerformed( ActionEvent ae ) {
		//This will be called by the Timer	
		/*for(int i=0;i<8;i++){
			if(lanes.get(i).getActive() == true){		// if lane is on
				if(counter==24){						// every 25th instance of timer
					int partindx = feeders.get(i/2).getBin().getPart();
//					System.out.println(partindx);
					lanes.get(i).addPart(partindx,index);		// create a new part
					index++;							// add 1 to index
					feeders.get(i/2).pushPart();		// subtract 1 from feeder counter
//					System.out.println(feeders.get());
					counter = 0;						// reset counter
				}
				counter++;
				if(feeders.get(i/2).getPush() == 0){	// if there are 36 parts on the lane
					laneSwitch(i,i+1);					// turn off lane, turn on next lane
					counter = 0;						// reset counter
				}
			}
		}
		update(changeMap,changeData);*/
		if(robot.hasBin == false){
			robot.moveToBin(1);
		}

		else if(robot.hasBin == true){
			robot.moveToFeeder(3);
		}
		
		repaint();
    }

	/*public void sync(TreeMap<Integer,FactoryObject> map){
		
		// Add Dividers
		for(int i=0;i<4;i++){
			map.put(dividers.get(i).getIndex(),dividers.get(i));
		}

		// Add Bins (if visible)
		for(int i=0;i<4;i++){
			if(feeders.get(i).hasBin() == true){
				map.put(feeders.get(i).getBin().getIndex(),feeders.get(i).getBin());
				map.put(feeders.get(i).getBin().getPartIcon().getIndex(),feeders.get(i).getBin().getPartIcon());
				System.out.println("Feeder"+i+"s bin painted");
//		for(int i=0;i<8;i++){
//			if(bins.get(i).getVis() == true){
//				map.put(bins.get(i).getIndex(),bins.get(i));
//				map.put(bins.get(i).getPartIcon().getIndex(),bins.get(i).getPartIcon());
			}
		}

		// Add Lane Content
		for(int i=0;i<8;i++){
			// LaneLines
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
	}*/

	/*public void update(TreeMap<Integer,Boolean> changeMap, TreeMap<Integer,FactoryObject> changeData){
		
		
	}*/

    public void paint(Graphics g){
    	Graphics2D g2 = (Graphics2D)g;
    	background.paintIcon(this,g2,0,0);

    	for (int i=0; i<10; i++){
			images.getIcon(10).paintIcon(this,g2,bins.get(i).getPositionX(),bins.get(i).getPositionY());
		}

		images.getIcon(18).paintIcon(this,g2,robot.getPositionX(),robot.getPositionY());


		//images.getIcon(10).paintIcon(this,g2,bins.get(0).getPositionX(),bins.get(0).getPositionY());
    	
		//sync(animData);
    	
    	/*Iterator k = changeMap.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(animData.containsKey(i) == false){
				animData.put(i,changeData.get(i));
			}
			else if(changeMap.get(i) == true){
				animData.put(i,changeData.get(i));
			}
		}*/

		// Paint Updated List
		/*k = animData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(i != 0 && animData.get(i).getIndex()> 0){
				if(animData.get(i).getIsLine()== true)	// if object is a line draw a line
					g2.drawLine(animData.get(i).getPositionX(),animData.get(i).getPositionY(),animData.get(i).getPositionXF(),animData.get(i).getPositionYF());
				else{ 										//if object is not a line draw an ImageIcon
					int img = animData.get(i).getImageIndex();
					images.getIcon(img).paintIcon(this,g2,animData.get(i).getPositionX(),animData.get(i).getPositionY());
				}
			}
		}*/
    }		
}
