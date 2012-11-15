import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class LaneManager extends JFrame implements ActionListener
{
	ImageIcon background;
	ArrayList<Lane> lanes;
	ArrayList<Bin> bins;
	ArrayList<Line> dividers;
	ArrayList<FactoryObject> updateList;
	int counter;
	
	TreeMap<Integer,Boolean> bMap = new TreeMap<Integer,Boolean>();
	TreeMap<Integer,FactoryObject> fMap = new TreeMap<Integer,FactoryObject>();

	public LaneManager(){
		
		// Create 8 lanes
		lanes = new ArrayList<Lane>();
		lanes.add(new Lane(16,59,"part.png"));
		lanes.add(new Lane(16,94,"part2.png"));
		lanes.add(new Lane(16,183,"part3.png"));
		lanes.add(new Lane(16,218,"part4.png"));
		lanes.add(new Lane(16,307,"part5.png"));
		lanes.add(new Lane(16,342,"part6.png"));
		lanes.add(new Lane(16,431,"part7.png"));
		lanes.add(new Lane(16,466,"part8.png"));	

		// Create 8 Bins
		bins = new ArrayList<Bin>();
		bins.add(new Bin(348, 84, "bin1.png", false));
		bins.add(new Bin(348, 84, "bin2.png", false));
		bins.add(new Bin(348, 207, "bin3.png", false));
		bins.add(new Bin(348, 207, "bin4.png", false));
		bins.add(new Bin(348, 331, "bin5.png", false));
		bins.add(new Bin(348, 331, "bin6.png", false));
		bins.add(new Bin(348, 456, "bin7.png", false));
		bins.add(new Bin(348, 456, "bin8.png", false));

		// Create 4 Dividers
		dividers = new ArrayList<Line>();
		dividers.add(new Line(334,94,288,94));
		dividers.add(new Line(334,218,288,218));
		dividers.add(new Line(334,342,288,342));
		dividers.add(new Line(334,466,288,466));

		// Create update list
		updateList = new ArrayList<FactoryObject>();

		// Turn On Lane 0, Off Lanes 1-7
		laneSwitch(8,0);
		for(int i=1;i<8;i++)
			lanes.get(i).setActive(false);

		// Create Backgroud Image
		background = new ImageIcon("bg.jpg");

		// Start Counter
		counter = 0;
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
					lanes.get(i).addPart();				// create a new part
					bins.get(i).addPush();				// add 1 to bin counter
					counter = 0;						// reset counter
				}
				counter++;
				if(lanes.get(i).getLaneSize() == 25){	// if there are 25 parts on the lane
					laneSwitch(i,i+1);					// turn off lane, turn on next lane
					counter = 0;						// reset counter
				}
			}
		}
		update(bMap,fMap);
		repaint();
    }

	public void update(TreeMap<Integer,Boolean> bMap, TreeMap<Integer,FactoryObject> fMap){
		
		// Move Elements
		for(int i=0;i<8;i++)
			lanes.get(i).moveParts();
	
		updateList.clear();

		// Add Dividers
		for(int i=0;i<4;i++){
			updateList.add(dividers.get(i));
		}

		// Add Bins (if visible)
		for(int i=0;i<8;i++){
			if(bins.get(i).getVis() == true)
				updateList.add(bins.get(i));
		}

		// Add Lane Content
		for(int i=0;i<8;i++){
			// LaneLines
			for(int j=0;j<3;j++){
				if(lanes.get(i).getLaneLine(j).getPositionX() <= 334)
					updateList.add(lanes.get(i).getLaneLine(j));
			}
			// Parts (Lanes and Nests)
			for(int j=0;j<lanes.get(i).getLaneSize();j++)
				updateList.add(lanes.get(i).getLanePart(j));
			for(int j=0;j<lanes.get(i).getNestSize();j++)
				updateList.add(lanes.get(i).getNestPart(j));
		}
	}	

	public void laneSwitch(int x1, int x2){
		if(x1<8){									// if lane exists
			lanes.get(x1).setActive(false);			// turn lane off
			bins.get(x1).setVis(false);				// turn bin off
			dividers.get(x1/2).dividerNeutral();	// put divider in neutral position
		}
		if(x2<8){									// if lane exists
			lanes.get(x2).setActive(true);			// turn lane on
			bins.get(x2).setVis(true);				// turn bin on
			if(x2%2 == 0)							// if upper lane
				dividers.get(x2/2).dividerDown();	// put divider in lower position
			if(x2%2 == 1)							// if lower lane
				dividers.get(x2/2).dividerUp();		// put divider in upper position
		}
	}

    public void paint(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;

		background.paintIcon(this,g2,0,30);

		// Paint Updated List
		for(int i=0;i<updateList.size();i++){
			if(updateList.get(i).getIsLine()== true)	// if object is a line draw a line
				g2.drawLine(updateList.get(i).getPositionX(),updateList.get(i).getPositionY(),updateList.get(i).getPositionXF(),updateList.get(i).getPositionYF());
			else 										//if object is not a line draw an ImageIcon
				updateList.get(i).getImage().paintIcon(this,g2,updateList.get(i).getPositionX(),updateList.get(i).getPositionY());
		}
    }
}
