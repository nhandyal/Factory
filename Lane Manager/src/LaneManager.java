import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class LaneManager extends JFrame implements ActionListener
{
	ImageIcon img, background;
	ArrayList<Lane> lanes;
	ArrayList<Bin> bins;
	int counter;
	
	TreeMap<Integer,Boolean> bMap = new TreeMap<Integer,Boolean>();
	TreeMap<Integer,FactoryObject> fMap = new TreeMap<Integer,FactoryObject>();

	Image dbImage;
	Graphics dbg;

	public LaneManager(){
		
		// Create lanes
		lanes = new ArrayList<Lane>();
		lanes.add(new Lane(20,59,"part.png"));
		lanes.add(new Lane(20,94,"part2.png"));
		lanes.add(new Lane(20,183,"part3.png"));
		lanes.add(new Lane(20,218,"part4.png"));
		lanes.add(new Lane(20,307,"part5.png"));
		lanes.add(new Lane(20,342,"part6.png"));
		lanes.add(new Lane(20,431,"part7.png"));
		lanes.add(new Lane(20,466,"part8.png"));	

		// Create Bins
		bins = new ArrayList<Bin>();
		bins.add(new Bin(445, 84, "bin1.png"));
		bins.add(new Bin(445, 84, "bin2.png"));
		bins.add(new Bin(445, 207, "bin3.png"));
		bins.add(new Bin(445, 207, "bin4.png"));
		bins.add(new Bin(445, 331, "bin5.png"));
		bins.add(new Bin(445, 331, "bin6.png"));
		bins.add(new Bin(445, 456, "bin7.png"));
		bins.add(new Bin(445, 456, "bin8.png"));

		// Fill Bins
		for(int i=0;i<8;i++){
			bins.get(i).fillBin(100,(1+i));
		}

		// Turn On/Off Lanes
		lanes.get(0).setActive(0);
		for(int i=1;i<8;i++)
			lanes.get(i).setActive(1);

		background = new ImageIcon("bg.jpg");

		// Start Counters
		counter = 0;
	}

	public static void main(String[] args){
		LaneManager l = new LaneManager();
		l.setVisible(true);
		l.setSize(500,530);
		l.createBufferStrategy(2);
		l.setTitle("Lane Manager");
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Timer(50,l).start();
	} //end main

	public void actionPerformed( ActionEvent ae ) {
		//This will be called by the Timer	
		for(int i=0;i<8;i++){
			if(lanes.get(i).getActive() == true){
				if(counter==24){
					lanes.get(i).addPart();
					bins.get(i).parts.remove(bins.get(i).getPush());
					bins.get(i).addPush();
					counter = 0;
				}
				counter++;
				if(lanes.get(i).getLaneSize() == 25){
					lanes.get(i).setActive(1);
					if(i!=7)
						lanes.get(i+1).setActive(0);
					counter = 0;
				}
			}
		}
		update(bMap,fMap);
		repaint();
    }

	public void update(TreeMap<Integer,Boolean> bMap, TreeMap<Integer,FactoryObject> fMap){
		for(int i=0;i<8;i++)
			lanes.get(i).moveParts();
	}

    public void paint(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;

		background.paintIcon(this,g2,0,30);

		// Draw Dividers & Bins
		if(lanes.get(0).getActive() == true){
			g2.drawLine(384,94,430,128);
			bins.get(0).getImage().paintIcon(this,g2,bins.get(0).getPositionX(),bins.get(0).getPositionY());
		}

		else if(lanes.get(1).getActive() == true){
			g2.drawLine(384,94,430,59);
			bins.get(1).getImage().paintIcon(this,g2,bins.get(1).getPositionX(),bins.get(1).getPositionY());
		}

		else
			g2.drawLine(384,94,430,94);
		
		if(lanes.get(2).getActive() == true){
			g2.drawLine(384,218,430,253);
			bins.get(2).getImage().paintIcon(this,g2,bins.get(2).getPositionX(),bins.get(2).getPositionY());
		}

		else if(lanes.get(3).getActive() == true){
			g2.drawLine(384,218,430,183);
			bins.get(3).getImage().paintIcon(this,g2,bins.get(3).getPositionX(),bins.get(3).getPositionY());
		}

		else
			g2.drawLine(384,218,430,218);

		if(lanes.get(4).getActive() == true){
			g2.drawLine(384,342,430,376);
			bins.get(4).getImage().paintIcon(this,g2,bins.get(4).getPositionX(),bins.get(4).getPositionY());
		}

		else if(lanes.get(5).getActive() == true){
			g2.drawLine(384,342,430,307);
			bins.get(5).getImage().paintIcon(this,g2,bins.get(5).getPositionX(),bins.get(5).getPositionY());
		}

		else
			g2.drawLine(384,342,430,342);

		if(lanes.get(6).getActive() == true){
			g2.drawLine(384,466,430,501);
			bins.get(6).getImage().paintIcon(this,g2,bins.get(6).getPositionX(),bins.get(6).getPositionY());
		}

		else if(lanes.get(7).getActive() == true){
			g2.drawLine(384,466,430,431);
			bins.get(7).getImage().paintIcon(this,g2,bins.get(7).getPositionX(),bins.get(7).getPositionY());
		}

		else
			g2.drawLine(384,466,430,466);
		
		for(int j=0;j<8;j++){
			//Draw Lines
			for(int i=0;i<4;i++){
				int tempx = lanes.get(j).getLaneLine(i).getPositionX();
				int tempy = lanes.get(j).getLaneLine(i).getPositionY();
				if(tempx<=430)
					g2.drawLine(tempx,tempy,tempx,(tempy+34));
//					lanes.get(j).getLaneLine(i).getImage().paintIcon(this,g2,tempx,tempy);
			}
			//Draw Lanes
			for(int i=0;i<lanes.get(j).getLaneSize();i++)
				lanes.get(j).getLanePart(i).getImage().paintIcon(this,g2,lanes.get(j).getLanePart(i).getPositionX(),lanes.get(j).getLanePart(i).getPositionY());
			for(int i=0;i<lanes.get(j).getNestSize();i++)
				lanes.get(j).getNestPart(i).getImage().paintIcon(this,g2,lanes.get(j).getNestPart(i).getPositionX(),lanes.get(j).getNestPart(i).getPositionY());
		}
    }
}
