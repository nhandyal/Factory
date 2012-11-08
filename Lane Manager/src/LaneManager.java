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
	ArrayList<Part> lane1, lane2, lane3, lane4, lane5, lane6, lane7, lane8;
	ArrayList<Part> nest1, nest2, nest3, nest4, nest5, nest6, nest7, nest8;
	int lane1LineX, lane2LineX, lane3LineX, lane4LineX, lane5LineX, lane6LineX, lane7LineX, lane8LineX;
	int counter1, counter2, counter3, counter4, counter5, counter6, counter7, counter8;
	int binPush1, binPush2, binPush3, binPush4, binPush5, binPush6, binPush7, binPush8;
	boolean lane1Active, lane2Active, lane3Active, lane4Active, lane5Active, lane6Active, lane7Active, lane8Active;
	Bin bin1, bin2, bin3, bin4, bin5, bin6, bin7, bin8;

	Image dbImage;
	Graphics dbg;

	public LaneManager(){
		
		// Initialize lanes and nests
		lane1 = new ArrayList<Part>();
		lane2 = new ArrayList<Part>();
		lane3 = new ArrayList<Part>();
		lane4 = new ArrayList<Part>();
		lane5 = new ArrayList<Part>();
		lane6 = new ArrayList<Part>();
		lane7 = new ArrayList<Part>();
		lane8 = new ArrayList<Part>();

		nest1 = new ArrayList<Part>();
		nest2 = new ArrayList<Part>();
		nest3 = new ArrayList<Part>();
		nest4 = new ArrayList<Part>();
		nest5 = new ArrayList<Part>();
		nest6 = new ArrayList<Part>();
		nest7 = new ArrayList<Part>();
		nest8 = new ArrayList<Part>();

		bin1 = new Bin(445, 84, "bin1.png");
		bin2 = new Bin(445, 84, "bin2.png");
		bin3 = new Bin(445, 207, "bin3.png");
		bin4 = new Bin(445, 207, "bin4.png");
		bin5 = new Bin(445, 331, "bin5.png");
		bin6 = new Bin(445, 331, "bin6.png");
		bin7 = new Bin(445, 456, "bin7.png");
		bin8 = new Bin(445, 456, "bin8.png");

		bin1.fillBin(100, 1);
		bin2.fillBin(100, 2);
		bin3.fillBin(100, 3);
		bin4.fillBin(100, 4);
		bin5.fillBin(100, 5);
		bin6.fillBin(100, 6);
		bin7.fillBin(100, 7);
		bin8.fillBin(100, 8);

		binPush1 = 0;
		binPush2 = 0;
		binPush3 = 0;
		binPush4 = 0;
		binPush5 = 0;
		binPush6 = 0;
		binPush7 = 0;
		binPush8 = 0;

		lane1Active = true;
		lane2Active = false;
		lane3Active = false;
		lane4Active = false;
		lane5Active = false;
		lane6Active = false;
		lane7Active = false;
		lane8Active = false;

		background = new ImageIcon("bg.jpg");
		
		// Lane Line X coordinates
		lane1LineX = 100;
		lane2LineX = 100;
		lane3LineX = 100;
		lane4LineX = 100;
		lane5LineX = 100;
		lane6LineX = 100;
		lane7LineX = 100;
		lane8LineX = 100;

		// Start Counters
		counter1 = 0;
		counter2 = 0;
		counter3 = 0;
		counter4 = 0;
		counter5 = 0;
		counter6 = 0;
		counter7 = 0;
		counter8 = 0;
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
		// Lane 1
		if(lane1Active == true){
			if(counter1==24){
				lane1.add(new Part(430,75,"part.png"));
				bin1.parts.remove(binPush1);
				binPush1++;
				counter1 = 0;
			}


			for(int i=0;i<lane1.size();i++){
				if(nest1.size()<9){
					if(lane1.get(i).getPositionX()<=60){
						nest1.add(lane1.get(i));
						System.out.println("Part Added to Nest1\nNest1 Size: "+nest1.size());
						lane1.remove(i);
					}
					lane1.get(i).moveLeft();
				}
				if(nest1.size()==9){
					if(lane1.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane1.get(i).getPositionY()>=65)
							lane1.get(i).moveUp();
						else if(i%3 == 1 && lane1.get(i).getPositionY()<=85)
							lane1.get(i).moveDown();
					}
					else
						lane1.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest1.size();i++){
				if(nest1.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest1.get(i).getPositionY()>=65)
							nest1.get(i).moveUp();
						else if(i%3 == 1 && nest1.get(i).getPositionY()<=85)
							nest1.get(i).moveDown();
					}
					else
						nest1.get(i).moveLeft();
			}
			counter1++;
			if(lane1.size()==25){
				lane1Active = false;
				lane2Active = true;
			}
		}

		// Lane 2
		if(lane2Active == true){
			if(counter2==24){
				lane2.add(new Part(430,110,"part2.png"));
				bin2.parts.remove(binPush1);
				binPush2++;
				counter2 = 0;
			}


			for(int i=0;i<lane2.size();i++){
				if(nest2.size()<9){
					if(lane2.get(i).getPositionX()<=60){
						nest2.add(lane2.get(i));
						System.out.println("Part Added to Nest2\nNest2 Size: "+nest2.size());
						lane2.remove(i);
					}
					lane2.get(i).moveLeft();
				}
				if(nest2.size()==9){
					if(lane2.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane2.get(i).getPositionY()>=100)
							lane2.get(i).moveUp();
						else if(i%3 == 1 && lane2.get(i).getPositionY()<=120)
							lane2.get(i).moveDown();
					}
					else
						lane2.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest2.size();i++){
				if(nest2.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest2.get(i).getPositionY()>=100)
							nest2.get(i).moveUp();
						else if(i%3 == 1 && nest2.get(i).getPositionY()<=120)
							nest2.get(i).moveDown();
					}
					else
						nest2.get(i).moveLeft();
			}
			counter2++;
			if(lane2.size()==25){
				lane2Active = false;
				lane3Active = true;
			}
		}

		// Lane 3
		if(lane3Active == true){
			if(counter3==24){
				lane3.add(new Part(430,199,"part3.png"));
				bin3.parts.remove(binPush1);
				binPush3++;
				counter3 = 0;
			}


			for(int i=0;i<lane3.size();i++){
				if(nest3.size()<9){
					if(lane3.get(i).getPositionX()<=60){
						nest3.add(lane3.get(i));
						System.out.println("Part Added to Nest3\nNest3 Size: "+nest3.size());
						lane3.remove(i);
					}
					lane3.get(i).moveLeft();
				}
				if(nest3.size()==9){
					if(lane3.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane3.get(i).getPositionY()>=189)
							lane3.get(i).moveUp();
						else if(i%3 == 1 && lane3.get(i).getPositionY()<=209)
							lane3.get(i).moveDown();
					}
					else
						lane3.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest3.size();i++){
				if(nest3.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest3.get(i).getPositionY()>=189)
							nest3.get(i).moveUp();
						else if(i%3 == 1 && nest3.get(i).getPositionY()<=209)
							nest3.get(i).moveDown();
					}
					else
						nest3.get(i).moveLeft();
			}
			counter3++;
			if(lane3.size()==25){
				lane3Active = false;
				lane4Active = true;
			}
		}

		// Lane 4
		if(lane4Active == true){
			if(counter4==24){
				lane4.add(new Part(430,234,"part4.png"));
				bin4.parts.remove(binPush1);
				binPush4++;
				counter4 = 0;
			}


			for(int i=0;i<lane4.size();i++){
				if(nest4.size()<9){
					if(lane4.get(i).getPositionX()<=60){
						nest4.add(lane4.get(i));
						System.out.println("Part Added to Nest4\nNest4 Size: "+nest4.size());
						lane4.remove(i);
					}
					lane4.get(i).moveLeft();
				}
				if(nest4.size()==9){
					if(lane4.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane4.get(i).getPositionY()>=224)
							lane4.get(i).moveUp();
						else if(i%3 == 1 && lane4.get(i).getPositionY()<=244)
							lane4.get(i).moveDown();
					}
					else
						lane4.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest4.size();i++){
				if(nest4.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest4.get(i).getPositionY()>=224)
							nest4.get(i).moveUp();
						else if(i%3 == 1 && nest4.get(i).getPositionY()<=244)
							nest4.get(i).moveDown();
					}
					else
						nest4.get(i).moveLeft();
			}
			counter4++;
			if(lane4.size()==25){
				lane4Active = false;
				lane5Active = true;
			}
		}

		// Lane 5
		if(lane5Active == true){
			if(counter5==24){
				lane5.add(new Part(430,323,"part5.png"));
				bin5.parts.remove(binPush1);
				binPush5++;
				counter5 = 0;
			}


			for(int i=0;i<lane5.size();i++){
				if(nest5.size()<9){
					if(lane5.get(i).getPositionX()<=60){
						nest5.add(lane5.get(i));
						System.out.println("Part Added to Nest5\nNest5 Size: "+nest5.size());
						lane5.remove(i);
					}
					lane5.get(i).moveLeft();
				}
				if(nest5.size()==9){
					if(lane5.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane5.get(i).getPositionY()>=313)
							lane5.get(i).moveUp();
						else if(i%3 == 1 && lane5.get(i).getPositionY()<=333)
							lane5.get(i).moveDown();
					}
					else
						lane5.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest5.size();i++){
				if(nest5.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest5.get(i).getPositionY()>=313)
							nest5.get(i).moveUp();
						else if(i%3 == 1 && nest5.get(i).getPositionY()<=333)
							nest5.get(i).moveDown();
					}
					else
						nest5.get(i).moveLeft();
			}
			counter5++;
			if(lane5.size()==25){
				lane5Active = false;
				lane6Active = true;
			}
		}

		// Lane 6
		if(lane6Active == true){
			if(counter6==24){
				lane6.add(new Part(430,358,"part6.png"));
				bin6.parts.remove(binPush1);
				binPush6++;
				counter6 = 0;
			}


			for(int i=0;i<lane6.size();i++){
				if(nest6.size()<9){
					if(lane6.get(i).getPositionX()<=60){
						nest6.add(lane6.get(i));
						System.out.println("Part Added to Nest6\nNest6 Size: "+nest6.size());
						lane6.remove(i);
					}
					lane6.get(i).moveLeft();
				}
				if(nest6.size()==9){
					if(lane6.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane6.get(i).getPositionY()>=348)
							lane6.get(i).moveUp();
						else if(i%3 == 1 && lane6.get(i).getPositionY()<=368)
							lane6.get(i).moveDown();
					}
					else
						lane6.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest6.size();i++){
				if(nest6.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest6.get(i).getPositionY()>=348)
							nest6.get(i).moveUp();
						else if(i%3 == 1 && nest6.get(i).getPositionY()<=368)
							nest6.get(i).moveDown();
					}
					else
						nest6.get(i).moveLeft();
			}
			counter6++;
			if(lane6.size()==25){
				lane6Active = false;
				lane7Active = true;
			}
		}

		// Lane 7
		if(lane7Active == true){
			if(counter7==24){
				lane7.add(new Part(430,447,"part7.png"));
				bin7.parts.remove(binPush1);
				binPush7++;
				counter7 = 0;
			}


			for(int i=0;i<lane7.size();i++){
				if(nest7.size()<9){
					if(lane7.get(i).getPositionX()<=60){
						nest7.add(lane7.get(i));
						System.out.println("Part Added to Nest7\nNest7 Size: "+nest7.size());
						lane7.remove(i);
					}
					lane7.get(i).moveLeft();
				}
				if(nest7.size()==9){
					if(lane7.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane7.get(i).getPositionY()>=437)
							lane7.get(i).moveUp();
						else if(i%3 == 1 && lane7.get(i).getPositionY()<=457)
							lane7.get(i).moveDown();
					}
					else
						lane7.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest7.size();i++){
				if(nest7.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest7.get(i).getPositionY()>=437)
							nest7.get(i).moveUp();
						else if(i%3 == 1 && nest7.get(i).getPositionY()<=457)
							nest7.get(i).moveDown();
					}
					else
						nest7.get(i).moveLeft();
			}
			counter7++;
			if(lane7.size()==25){
				lane7Active = false;
				lane8Active = true;
			}
		}

		//Lane 8
		if(lane8Active == true){
			if(counter8==24){
				lane8.add(new Part(430,482,"part8.png"));
				bin8.parts.remove(binPush1);
				binPush8++;
				counter8 = 0;
			}


			for(int i=0;i<lane8.size();i++){
				if(nest8.size()<9){
					if(lane8.get(i).getPositionX()<=60){
						nest8.add(lane8.get(i));
						System.out.println("Part Added to Nest8\nNest8 Size: "+nest8.size());
						lane8.remove(i);
					}
					lane8.get(i).moveLeft();
				}
				if(nest8.size()==9){
					if(lane8.get(i).getPositionX()<=(70+((i/3)*10))){
						if(i%3 == 0 && lane8.get(i).getPositionY()>=472)
							lane8.get(i).moveUp();
						else if(i%3 == 1 && lane8.get(i).getPositionY()<=492)
							lane8.get(i).moveDown();
					}
					else
						lane8.get(i).moveLeft();
				}
			}

			for(int i=0;i<nest8.size();i++){
				if(nest8.get(i).getPositionX()<=(25+((i/3)*15))){
						if(i%3 == 0 && nest8.get(i).getPositionY()>=472)
							nest8.get(i).moveUp();
						else if(i%3 == 1 && nest8.get(i).getPositionY()<=492)
							nest8.get(i).moveDown();
					}
					else
						nest8.get(i).moveLeft();
			}
			counter8++;
			if(lane8.size()==25){
				lane8Active = false;
			}
		}
	repaint();
    }

    public void paint(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;

		background.paintIcon(this,g2,0,30);

		// Move Lines
		if(lane1Active == true){
			lane1LineX -= 2;
			if(lane1LineX <= 63)
				lane1LineX += 100;
		}
		if(lane2Active == true){
			lane2LineX -= 2;
			if(lane2LineX <= 63)
				lane2LineX += 100;
		}
		if(lane3Active == true){
			lane3LineX -= 2;
			if(lane3LineX <= 63)
				lane3LineX += 100;
		}
		if(lane4Active == true){
			lane4LineX -= 2;
			if(lane4LineX <= 63)
				lane4LineX += 100;
		}
		if(lane5Active == true){
			lane5LineX -= 2;
			if(lane5LineX <= 63)
				lane5LineX += 100;
		}
		if(lane6Active == true){
			lane6LineX -= 2;
			if(lane6LineX <= 63)
				lane6LineX += 100;
		}
		if(lane7Active == true){
			lane7LineX -= 2;
			if(lane7LineX <= 63)
				lane7LineX += 100;
		}
		if(lane8Active == true){
			lane8LineX -= 2;
			if(lane8LineX <= 63)
				lane8LineX += 100;
		}

		// Draw Lane Lines
		for(int temp = lane1LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,60,temp,93);
		for(int temp = lane2LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,95,temp,127);
		for(int temp = lane3LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,184,temp,217);
		for(int temp = lane4LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,219,temp,252);
		for(int temp = lane5LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,308,temp,341);
		for(int temp = lane6LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,343,temp,376);
		for(int temp = lane7LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,432,temp,465);
		for(int temp = lane8LineX;temp <= 430;temp += 100)
			g2.drawLine(temp,467,temp,499);

		// Draw Dividers
		if(lane1Active == true){
			g2.drawLine(384,94,430,128);
			bin1.getImage().paintIcon(this,g2,bin1.getPositionX(),bin1.getPositionY());
		}

		else if(lane2Active == true){
			g2.drawLine(384,94,430,59);
			bin2.getImage().paintIcon(this,g2,bin2.getPositionX(),bin2.getPositionY());
		}

		else
			g2.drawLine(384,94,430,94);
		
		if(lane3Active == true){
			g2.drawLine(384,218,430,253);
			bin3.getImage().paintIcon(this,g2,bin3.getPositionX(),bin3.getPositionY());
		}

		else if(lane4Active == true){
			g2.drawLine(384,218,430,183);
			bin4.getImage().paintIcon(this,g2,bin4.getPositionX(),bin4.getPositionY());
		}

		else
			g2.drawLine(384,218,430,218);

		if(lane5Active == true){
			g2.drawLine(384,342,430,376);
			bin5.getImage().paintIcon(this,g2,bin5.getPositionX(),bin5.getPositionY());
		}

		else if(lane6Active == true){
			g2.drawLine(384,342,430,307);
			bin6.getImage().paintIcon(this,g2,bin6.getPositionX(),bin6.getPositionY());
		}

		else
			g2.drawLine(384,342,430,342);

		if(lane7Active == true){
			g2.drawLine(384,466,430,501);
			bin7.getImage().paintIcon(this,g2,bin7.getPositionX(),bin7.getPositionY());
		}

		else if(lane8Active == true){
			g2.drawLine(384,466,430,431);
			bin8.getImage().paintIcon(this,g2,bin8.getPositionX(),bin8.getPositionY());
		}

		else
			g2.drawLine(384,466,430,466);


		// Draw Parts
		//Lane 1
		for(int i=0;i<lane1.size();i++)
			lane1.get(i).getImage().paintIcon(this,g2,lane1.get(i).getPositionX(),lane1.get(i).getPositionY());
		for(int i=0;i<nest1.size();i++)
			nest1.get(i).getImage().paintIcon(this,g2,nest1.get(i).getPositionX(),nest1.get(i).getPositionY());
		//Lane 2
		for(int i=0;i<lane2.size();i++)
			lane2.get(i).getImage().paintIcon(this,g2,lane2.get(i).getPositionX(),lane2.get(i).getPositionY());
		for(int i=0;i<nest2.size();i++)
			nest2.get(i).getImage().paintIcon(this,g2,nest2.get(i).getPositionX(),nest2.get(i).getPositionY());
		//Lane 3
		for(int i=0;i<lane3.size();i++)
			lane3.get(i).getImage().paintIcon(this,g2,lane3.get(i).getPositionX(),lane3.get(i).getPositionY());
		for(int i=0;i<nest3.size();i++)
			nest3.get(i).getImage().paintIcon(this,g2,nest3.get(i).getPositionX(),nest3.get(i).getPositionY());
		//Lane 4
		for(int i=0;i<lane4.size();i++)
			lane4.get(i).getImage().paintIcon(this,g2,lane4.get(i).getPositionX(),lane4.get(i).getPositionY());
		for(int i=0;i<nest4.size();i++)
			nest4.get(i).getImage().paintIcon(this,g2,nest4.get(i).getPositionX(),nest4.get(i).getPositionY());
		//Lane 5
		for(int i=0;i<lane5.size();i++)
			lane5.get(i).getImage().paintIcon(this,g2,lane5.get(i).getPositionX(),lane5.get(i).getPositionY());
		for(int i=0;i<nest5.size();i++)
			nest5.get(i).getImage().paintIcon(this,g2,nest5.get(i).getPositionX(),nest5.get(i).getPositionY());
		//Lane 6
		for(int i=0;i<lane6.size();i++)
			lane6.get(i).getImage().paintIcon(this,g2,lane6.get(i).getPositionX(),lane6.get(i).getPositionY());
		for(int i=0;i<nest6.size();i++)
			nest6.get(i).getImage().paintIcon(this,g2,nest6.get(i).getPositionX(),nest6.get(i).getPositionY());
		//Lane 7
		for(int i=0;i<lane7.size();i++)
			lane7.get(i).getImage().paintIcon(this,g2,lane7.get(i).getPositionX(),lane7.get(i).getPositionY());
		for(int i=0;i<nest7.size();i++)
			nest7.get(i).getImage().paintIcon(this,g2,nest7.get(i).getPositionX(),nest7.get(i).getPositionY());
		//Lane 8
		for(int i=0;i<lane8.size();i++)
			lane8.get(i).getImage().paintIcon(this,g2,lane8.get(i).getPositionX(),lane8.get(i).getPositionY());
		for(int i=0;i<nest8.size();i++)
			nest8.get(i).getImage().paintIcon(this,g2,nest8.get(i).getPositionX(),nest8.get(i).getPositionY());

    }

    /*public void update(Graphics g){
        // initialize buffer 
		if (dbImage == null) 
		{
			dbImage = createImage(this.getSize().width, this.getSize().height); 
			dbg = dbImage.getGraphics(); 
		} 

		// clear screen in background 
		dbg.setColor(getBackground ()); 
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height); 

		// draw elements in background 
		dbg.setColor(getForeground()); 
		paint(dbg); 
    }*/
}