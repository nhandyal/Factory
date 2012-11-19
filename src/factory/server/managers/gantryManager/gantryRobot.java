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
		hasBin = false;
		nextDestX = bins.get(bin).getPositionX() - 40;
		nextDestY = bins.get(bin).getPositionY() - 20;
		if (x < nextDestX){
			x++;
		}

		else if (x > nextDestX){
			x--;
		}

		if(y < nextDestY){
			y++;
		}

		else if (y > nextDestY){
			y--;
		}

		if(x == nextDestX && y == nextDestY){
			hasBin = true;
			pickupBin(bin);
		}
	}

	public void moveToFeeder(int feeder){
		nextDestX = feeders.get(feeder).getPositionX() + 40;
		nextDestY = feeders.get(feeder).getPositionY() - 20;
		if (x < nextDestX){
			x++;
			possessedBin.x = possessedBin.x + 1;
		}

		else if (x > nextDestX){
			x--;
			possessedBin.x--;
		}

		if(y < nextDestY){
			y++;
			possessedBin.y++;
		}

		else if (y > nextDestY){
			y--;
			possessedBin.y--;
		}

		if(x == nextDestX && y == nextDestY){
			possessedBin.x = feeders.get(feeder).getPositionX();
			possessedBin.y = feeders.get(feeder).getPositionY();
		}
	}

	public void pickupBin(int grabbedBin){
		possessedBin = bins.get(grabbedBin);
		possessedBin.x = x + 10;
		possessedBin.y = y;
	}
}