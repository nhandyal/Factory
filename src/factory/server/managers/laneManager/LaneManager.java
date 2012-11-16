import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class LaneManager extends JFrame implements ActionListener
{
	ImageIcon background;
	ArrayList<ImageIcon> images;
	ArrayList<Lane> lanes;
	ArrayList<Bin> bins;
	ArrayList<Line> dividers;
	ArrayList<Feeder> feeders;
	int counter, index;
	FOComparator foc;
	
	TreeMap<Integer,Boolean> changeMap;
	TreeMap<Integer,FactoryObject> temp;
	TreeMap<Integer,FactoryObject> changeData;
	TreeMap<Integer,FactoryObject> animData;

	public LaneManager(){
	
		index = 1;
		
		// Create 8 lanes
		lanes = new ArrayList<Lane>();
		lanes.add(new Lane(16,59,index));
		index+=3;
		lanes.add(new Lane(16,94,index));
		index+=3;
		lanes.add(new Lane(16,183,index));
		index+=3;
		lanes.add(new Lane(16,218,index));
		index+=3;
		lanes.add(new Lane(16,307,index));
		index+=3;
		lanes.add(new Lane(16,342,index));
		index+=3;
		lanes.add(new Lane(16,431,index));
		index+=3;
		lanes.add(new Lane(16,466,index));
		index+=3;	
		
		// Create 4 Dividers
		dividers = new ArrayList<Line>();
		dividers.add(new Line(334,94,288,94,index));
		index++;
		dividers.add(new Line(334,218,288,218,index));
		index++;
		dividers.add(new Line(334,342,288,342,index));
		index++;
		dividers.add(new Line(334,466,288,466,index));
		index++;


		// Create 10 Bins
		bins = new ArrayList<Bin>();
		bins.add(new Bin(348,84,10,index,0));
		index++;
		bins.add(new Bin(348,84,10,index,1));
		index++;
		bins.add(new Bin(348,207,10,index,2));
		index++;
		bins.add(new Bin(348,207,10,index,3));
		index++;
		bins.add(new Bin(348,331,10,index,4));
		index++;
		bins.add(new Bin(348,331,10,index,5));
		index++;
		bins.add(new Bin(348,456,10,index,6));
		index++;
		bins.add(new Bin(348,456,10,index,7));
		index++;
		bins.add(new Bin(348,456,10,index,8));
		index++;
		bins.add(new Bin(348,456,10,index,9));
		index++;
		
		// Create 4 Feeders
		feeders = new ArrayList<Feeder>();
		feeders.add(new Feeder(lanes.get(0),lanes.get(1)));
		feeders.add(new Feeder(lanes.get(2),lanes.get(3)));
		feeders.add(new Feeder(lanes.get(4),lanes.get(5)));
		feeders.add(new Feeder(lanes.get(6),lanes.get(7)));
		
		// Turn On Lane 0, Off Lanes 1-7
		laneSwitch(8,0);
		for(int i=1;i<8;i++)
			lanes.get(i).setActive(false);

		// Create Backgroud Image
		background = new ImageIcon("bg.jpg");
		
		// Create ImageList
		images = new ArrayList<ImageIcon>();
		images.add(new ImageIcon("part1.png"));		// index 0		part 1
		images.add(new ImageIcon("part2.png"));		// index 1		part 2
		images.add(new ImageIcon("part3.png"));		// index 2		part 3
		images.add(new ImageIcon("part4.png"));		// index 3		part 4
		images.add(new ImageIcon("part5.png"));		// index 4		part 5
		images.add(new ImageIcon("part6.png"));		// index 5		part 6
		images.add(new ImageIcon("part7.png"));		// index 6		part 7
		images.add(new ImageIcon("part8.png"));		// index 7		part 8
		images.add(new ImageIcon("part9.png"));		// index 8		part 9
		images.add(new ImageIcon("part10.png"));	// index 9		part 10
		images.add(new ImageIcon("bin.png"));		// index 10		bin
		images.add(new ImageIcon("robot.png"));		// index 11		Kit/Part Picking/Gantry Robot
		images.add(new ImageIcon("kit.png"));		// index 12		Kit
		images.add(new ImageIcon("camera.png"));	// index 13		QA Cam
		images.add(new ImageIcon("camflash.png"));	// index 14		Cam Flash
		images.add(new ImageIcon("camera.png"));	// index 15		Nest Cam
		images.add(new ImageIcon("camline.png"));	// index 16		Nest Cam Line

		// Start Counter
		counter = 0;
		
		// Initialize comparator
		foc = new FOComparator();
		
		changeMap = new TreeMap<Integer,Boolean>();
		changeData = new TreeMap<Integer,FactoryObject>();
		animData = new TreeMap<Integer,FactoryObject>();
		temp = new TreeMap<Integer,FactoryObject>();
	}

	public static void main(String[] args){
		LaneManager l = new LaneManager();
		l.setVisible(true);
		l.setSize(400,530);
		l.createBufferStrategy(2);
		l.setTitle("Lane Manager");
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Timer(50,l).start();
	} //end main

	public void actionPerformed( ActionEvent ae ) {
		//This will be called by the Timer	
		for(int i=0;i<8;i++){
			if(lanes.get(i).getActive() == true){		// if lane is on
				if(counter==24){						// every 25th instance of timer
					int partindx = feeders.get(i/2).getBin().getPart();
					System.out.println(partindx);
					lanes.get(i).addPart(partindx,index);		// create a new part
					index++;							// add 1 to index
					feeders.get(1/2).pushPart();		// subtract 1 from feeder counter
					counter = 0;						// reset counter
				}
				counter++;
				if(feeders.get(i/2).getPush() == 0){	// if there are 36 parts on the lane
					laneSwitch(i,i+1);					// turn off lane, turn on next lane
					counter = 0;						// reset counter
				}
			}
		}
		update();
		repaint();
    }

	public void buildMap(TreeMap<Integer,FactoryObject> map){
		
		// Add Dividers
		for(int i=0;i<4;i++){
			map.put(dividers.get(i).getIndex(),dividers.get(i));
		}

		// Add Bins (if visible)
		for(int i=0;i<8;i++){
			if(bins.get(i).getVis() == true){
				map.put(bins.get(i).getIndex(),bins.get(i));
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
	}

	public void update(){
		
		buildMap(changeData);
		
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
				}
				else{
					temp.get(i).setImage(changeData.get(i).getImageIndex());
				}
			}
		}

		changeData.clear();
		changeMap.clear();
		
		// Move Elements
		for(int i=0;i<8;i++)
			lanes.get(i).moveParts();

		buildMap(changeData);
		
		k = changeData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(temp.containsKey(i) == true){
				if(foc.compare(temp.get(i),changeData.get(i)) == 0){
					changeMap.put(i,false);
				}
				else{
					changeMap.put(i,true);
				}
			}
			else
				changeMap.put(i,true);
		}
		
		k = temp.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(changeData.containsKey(i) == false){
				changeData.put(i, new FactoryObject());
				changeMap.put(i,true);
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

	public void laneSwitch(int x1, int x2){
		if(x1<8){									// if lane exists
			lanes.get(x1).setActive(false);			// turn lane off
//			bins.get(x1).setVis(false);				// turn bin off
			feeders.get(x1/2).removeBin();			// remove bin
			dividers.get(x1/2).dividerNeutral();	// put divider in neutral position
		}
		if(x2<8){									// if lane exists
			lanes.get(x2).setActive(true);			// turn lane on
//			bins.get(x2).setVis(true);				// turn bin on
			feeders.get(x2/2).addBin(bins.get(x2));
			feeders.get(x2/2).setPush(36);
			if(x2%2 == 0)							// if upper lane
				dividers.get(x2/2).dividerDown();	// put divider in lower position
			if(x2%2 == 1)							// if lower lane
				dividers.get(x2/2).dividerUp();		// put divider in upper position
		}
	}

    public void paint(Graphics g){
    	Graphics2D g2 = (Graphics2D)g;
    	
		buildMap(animData);
    	
    	Iterator k = changeMap.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(animData.containsKey(i) == false){
				animData.put(i,changeData.get(i));
			}
			else if(changeMap.get(i) == true){
				animData.put(i,changeData.get(i));
			}
		}

		background.paintIcon(this,g2,0,30);

		// Paint Updated List
		k = animData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(i != 0 && animData.get(i).getIndex()> 0){
				if(animData.get(i).getIsLine()== true)	// if object is a line draw a line
					g2.drawLine(animData.get(i).getPositionX(),animData.get(i).getPositionY(),animData.get(i).getPositionXF(),animData.get(i).getPositionYF());
				else{ 										//if object is not a line draw an ImageIcon
					int img = animData.get(i).getImageIndex();
					images.get(img).paintIcon(this,g2,animData.get(i).getPositionX(),animData.get(i).getPositionY());
				}
			}
		}
    }
}
