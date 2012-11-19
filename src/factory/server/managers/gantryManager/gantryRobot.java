package factory.server.managers.gantryManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;
import java.io.*;
// user packages
import factory.global.data.*;
import factory.server.managers.GuiManager;

public class gantryRobot extends FactoryObject implements Serializable
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
	GantryManager f;
	public gantryRobot(int initialPosX, int initialPosY, int initialImage, ArrayList<Bin> binlist, ArrayList<Feeder> feederlist, int i, GantryManager f){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		bins = binlist;
		feeders = feederlist;
		hasBin = false;
		index = i;
        this.f = f;
	}

	public void moveToBin(int bin){
		System.out.println("moving to the bin");
        f.bin = bin;
        f.isMoveToBin = true;
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
				f.isMoveToBin = false;
                if (f.isRemoveBin)
                    moveToPoint(340, binYArray[bin]);
			}
	}

	public void moveToPoint(int x, int y){
		hasBin = true;
        f.x = x;
        f.y = y;
        f.isMoveToPoint = true;
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
                f.isMoveToPoint = false;
			}
		
	}

	public void moveToFeeder(int feeder){
		
            f.feeder = feeder;
            f.isMoveToFeeder = true;
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
				f.isMoveToFeeder = false;
			}
	}

	public void removeBin(int bin){
        f.isRemoveBin = true;
		if(hasBin == false){
			moveToBin(bin);
		}
		
        }

	public void pickupBin(int grabbedBin){
		possessedBin = bins.get(grabbedBin);
		possessedBin.x = x + 10;
		possessedBin.y = y;
	}
}