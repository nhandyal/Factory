import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class LaneManager extends JFrame implements ActionListener
{
//	Rectangle2D.Double backgroundRectangle;
	ImageIcon img, background;
	ArrayList<Part> parts, nest;
	int counter;
	boolean nestFull;

	public LaneManager(){
		parts = new ArrayList<Part>();
		nest = new ArrayList<Part>();
		nestFull = false;
		parts.add(new Part(430,75,"part.png"));
		//img = new ImageIcon("part.png");
		background = new ImageIcon("bg.jpg");
//		img.getProperty("image.png",null);
		counter = 0;

		//Make the background rectangle to "erase" the screen
//		backgroundRectangle = new Rectangle2D.Double( 0, 0, 500, 500 );
	}

	public static void main(String[] args){
		LaneManager l = new LaneManager();
		l.setVisible(true);
		l.setSize(500,530);
		l.setTitle("Lane Manager");
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Timer(50,l).start();
	} //end main

	public void actionPerformed( ActionEvent ae ) {
	//This will be called by the Timer
	if(counter==24){
		parts.add(new Part(430,75,"part.png"));
		counter = 0;
	}


	for(int i=0;i<parts.size();i++){
		if(nestFull==false){
			if(parts.get(i).getPositionX()<=60){
				nest.add(parts.get(i));
				System.out.println("Part Added to Nest\nNest Size: "+nest.size());
				parts.remove(i);
				if(nest.size()==9)
					nestFull = true;
			}
			parts.get(i).moveLeft();
		}
		if(nestFull==true){
			if(parts.get(i).getPositionX()<=(70+((i/3)*10))){
				if(i%3 == 0 && parts.get(i).getPositionY()>=65)
					parts.get(i).moveUp();
				else if(i%3 == 1 && parts.get(i).getPositionY()<=85)
					parts.get(i).moveDown();
			}
			else
				parts.get(i).moveLeft();
		}
	}

	for(int i=0;i<nest.size();i++){
		if(i==0 && nest.get(i).getPositionX()<=25 && nest.get(i).getPositionY()>=65){
			nest.get(i).moveUp();
		}
		else if(i==1 && nest.get(i).getPositionX()<=25 && nest.get(i).getPositionY()<=85){
			nest.get(i).moveDown();
		}
		else if(i<3 && nest.get(i).getPositionX()<=25){}
		else if(i==3 && nest.get(i).getPositionX()<=40 && nest.get(i).getPositionY()>=65){
			nest.get(i).moveUp();
		}
		else if(i==4 && nest.get(i).getPositionX()<=40 && nest.get(i).getPositionY()<=85){
			nest.get(i).moveDown();
		}
		else if(i<6 && i>2 && nest.get(i).getPositionX()<=40){}
		else if(i==6 && nest.get(i).getPositionX()<=55 && nest.get(i).getPositionY()>=65){
			nest.get(i).moveUp();
		}
		else if(i==7 && nest.get(i).getPositionX()<=55 && nest.get(i).getPositionY()<=85){
			nest.get(i).moveDown();
		}
		else if(i<9 && i>5 && nest.get(i).getPositionX()<=55){}
		else
			nest.get(i).moveLeft();
	}

	counter++;
	repaint();
    }

    public void paint(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;

		background.paintIcon(this,g2,0,30);

		for(int i=0;i<parts.size();i++){
			parts.get(i).getImage().paintIcon(this,g2,parts.get(i).getPositionX(),parts.get(i).getPositionY());
		}

		for(int i=0;i<nest.size();i++){
			nest.get(i).getImage().paintIcon(this,g2,nest.get(i).getPositionX(),nest.get(i).getPositionY());
		}
    }
}