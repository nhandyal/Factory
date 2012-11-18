package factory.server.managers.gantryManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

// user packages
import factory.global.data.*;
import factory.server.managers.GuiManager;

public class gantryRobot extends FactoryObject
{
	ImageIcon robot;
	ImageArray images;

	int nextDestX;
	int nextDestY;

	boolean hasBin;

	ArrayList<Bin> bins;

	int[] binYArray = {105, 146, 187, 229, 270, 311, 353, 393, 434, 475};

	ArrayList<Feeder> feeders;

	Bin possessedBin;
	
	public gantryRobot(int initialPosX, int initialPosY, int initialImage, ArrayList<Bin> binlist, ArrayList<Feeder> feederlist, int i){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		bins = binlist;
		feeders = feederlist;
		hasBin = false;
		index = i;
	}

	public void moveToBin(int bin){
		System.out.println("moving to the bin");
		hasBin = false;
		while(hasBin == false){
			nextDestX = bins.get(bin).getPositionX() - 40;
			nextDestY = bins.get(bin).getPositionY() - 20;
			if (x < nextDestX){
				x+=2;
			}

			else if (x > nextDestX){
				x-=2;
			}

			if(y < nextDestY){
				y+=2;
			}

			else if (y > nextDestY){
				y-=2;
			}

			if(x == nextDestX && y == nextDestY){
				pickupBin(bin);
				hasBin = true;
			}
		}
	}

	public void moveToPoint(int x, int y){
		hasBin = true;
		while(hasBin == true){
			nextDestX = x + 40;
			nextDestY = y - 20;
			if (x < nextDestX){
				x+=2;
				possessedBin.x+=2;
			}

			else if (x > nextDestX){
				x-=2;
				possessedBin.x-=2;
			}

			if(y < nextDestY){
				y+=2;
				possessedBin.y+=2;
			}

			else if (y > nextDestY){
				y-=2;
				possessedBin.y-=2;
			}

			if(x == nextDestX && y == nextDestY){
				possessedBin.x = x;
				possessedBin.y = y;
			}
		}
		
	}

	public void moveToFeeder(int feeder){
		hasBin = true;
		while(hasBin == true){
			nextDestX = feeders.get(feeder).getPositionX() + 40;
			nextDestY = feeders.get(feeder).getPositionY() - 20;
			if (x < nextDestX){
				x+=2;
				possessedBin.x+=2;
			}

			else if (x > nextDestX){
				x-=2;
				possessedBin.x-=2;
			}

			if(y < nextDestY){
				y+=2;
				possessedBin.y+=2;
			}

			else if (y > nextDestY){
				y-=2;
				possessedBin.y-=2;
			}

			if(x == nextDestX && y == nextDestY){
				possessedBin.x = feeders.get(feeder).getPositionX();
				possessedBin.y = feeders.get(feeder).getPositionY();
				hasBin = false;
			}
		}
	}

	public void removeBin(int bin){
		if(hasBin == false){
			moveToBin(bin);
		}
		
		else if(hasBin == true){
			moveToPoint(340, binYArray[bin]);
		}

	}

	public void pickupBin(int grabbedBin){
		possessedBin = bins.get(grabbedBin);
		possessedBin.x = x + 10;
		possessedBin.y = y;
	}
}