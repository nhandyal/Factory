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
        hasBin = false;
        if(bins.get(bin).getPositionX() > x)
        {
        	nextDestX = bins.get(bin).getPositionX() - 40;
        	nextDestY = bins.get(bin).getPositionY() - 20;

			if (x < nextDestX){

				if((x+4) > nextDestX)
				{
					x = nextDestX;
				}

				x+=4;
			}

			else if (x > nextDestX){

				if((x-4) > nextDestX)
				{
					x = nextDestX;
				}

				x-=4;
			}

			if(y < nextDestY){

				if((y+4) > nextDestY)
				{
					y = nextDestY;
				}

				y+=4;
			}

			else if (y > nextDestY){

				if((y-4) < nextDestY)
				{
					y = nextDestY;
				}

				y-=4;
			}

			if(x == nextDestX && y == nextDestY){
				pickupBin(bin);
				f.isMoveToBin = false;
	            if (f.isRemoveBin)
	                moveToPoint(340, binYArray[bin]);
			}
		}
	}

	public void moveToBinPurge(int bin){
		System.out.println("moving to the bin");
        f.bin = bin;
        f.isMoveToBin = true;
        hasBin = false;
        nextDestX = bins.get(bin).getPositionX() + 40;
		nextDestY = bins.get(bin).getPositionY() - 20;

		if (x < nextDestX){

			if((x+4) > nextDestX)
			{
				x = nextDestX;
			}

			x+=4;
		}

		else if (x > nextDestX){

			if((x-4) > nextDestX)
			{
				x = nextDestX;
			}

			x-=4;
		}

		if(y < nextDestY){

			if((y+4) > nextDestY)
			{
				y = nextDestY;
			}

			y+=4;
		}

		else if (y > nextDestY){

			if((y-4) < nextDestY)
			{
				y = nextDestY;
			}

			y-=4;
		}

		if(x == nextDestX && y == nextDestY){
			pickupBin(bin);
			f.isMoveToBin = false;
            if (f.isRemoveBin)
                moveToPoint(340, binYArray[bin]);
		}
	}

	public void moveToPoint(int newX, int newY){
		System.out.println("moving to point");
		hasBin = true;
        f.x = newX;
        f.y = newY;
        f.isMoveToPoint = true;
		nextDestX = newX - 40;
		nextDestY = newY - 20;
		if (x < nextDestX){

			if((x+4) > nextDestX)
			{
				x = nextDestX;
				possessedBin.x = x + 10;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			x+=4;
			possessedBin.x+=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
		}

		else if (x > nextDestX){

			if((x-4) < nextDestX)
			{
				x = nextDestX;
				possessedBin.x = x + 10;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			x-=4;
			possessedBin.x-=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;

		}

		if(y < nextDestY){

			if((y+4) > nextDestY)
			{
				y = nextDestY;
				possessedBin.y = y;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			y+=4;
			possessedBin.y+=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
		}

		else if (y > nextDestY){

			if((y-4) < nextDestY)
			{
				y = nextDestY;
				possessedBin.y = y;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			y-=4;
			possessedBin.y-=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
		}

		if(x == nextDestX && y == nextDestY){
			possessedBin.x = 340;
			possessedBin.y = newY;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
			hasBin = false;
			f.isRemoveBin = false;
            f.isMoveToPoint = false;
		}
	
	}

	public void moveToFeeder(int feeder){
		System.out.println("moving to the feeder");
        f.feeder = feeder;
        f.isMoveToFeeder = true;
        for(int i=0;i<10;i++){
        	if(bins.get(i).getPositionX() == feeders.get(feeder).getPositionX())
			{
				f.isMoveToFeeder = false;
				moveToPoint(200, 335);
			}
        }

        nextDestX = feeders.get(feeder).getPositionX() + 40;
		nextDestY = feeders.get(feeder).getPositionY() - 20;
		if (x < nextDestX){

			if((x+4) > nextDestX)
			{
				x = nextDestX;
				possessedBin.x = x + 10;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			x+=4;
			possessedBin.x+=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
		}

		else if (x > nextDestX){

			if((x-4) < nextDestX)
			{
				x = nextDestX;
				possessedBin.x = x + 10;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			x-=4;
			possessedBin.x-=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
		}

		if(y < nextDestY){

			if((y+4) > nextDestY)
			{
				y = nextDestY;
				possessedBin.y = y;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			y+=4;
			possessedBin.y+=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
		}

		else if (y > nextDestY){

			if((y-4) < nextDestY)
			{
				y = nextDestY;
				possessedBin.y = y;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

			y-=4;
			possessedBin.y-=4;
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
		}

		if(x == nextDestX && y == nextDestY){
			possessedBin.x = feeders.get(feeder).getPositionX();
			possessedBin.y = feeders.get(feeder).getPositionY();
			possessedBin.getPartIcon().x = possessedBin.x + 8;
			possessedBin.getPartIcon().y = possessedBin.y + 9;
			hasBin = false;
			f.isMoveToFeeder = false;
		}
	}

	public void removeBin(int bin){
		System.out.println("removing bin");
        f.isRemoveBin = true;
		if(hasBin == false){
			moveToBinPurge(bin);
		}
		
    }

	public void pickupBin(int grabbedBin){
		System.out.println("pickup bin");
		hasBin = true;
		possessedBin = bins.get(grabbedBin);
		possessedBin.x = x + 10;
		possessedBin.y = y;
		possessedBin.getPartIcon().x = possessedBin.x + 8;
		possessedBin.getPartIcon().y = possessedBin.y + 9;
	}
}