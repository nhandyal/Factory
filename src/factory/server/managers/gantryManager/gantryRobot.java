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
import factory.server.managers.laneManager.*;

public class gantryRobot extends FactoryObject implements Serializable
{
	ImageIcon robot;
	ImageArray images;

	int nextDestX;
	int nextDestY;

	boolean hasBin;
	boolean neutralPos;

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
		neutralPos = false;
		index = i;
        this.f = f;
	}

	public void moveToBin(int bin){
		//System.out.println("moving to the bin");
        f.bin = bin;
        f.isMoveToFeeder = false;
        f.isMoveToPoint = false;
        f.isMoveToBinPurge = false;
        f.isMoveToBin = true;
        if(hasBin == true)
        {
        	neutralPos = true;
        	moveToPoint(200,335);
        }

        else
        {
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
				}
			}
        }   
	}

	public void moveToBinPurge(int pbin, int feeder){
		
        f.bin = pbin;
        f.isMoveToFeeder = false;
        f.isMoveToPoint = false;
        f.isMoveToBin = false;
        f.isMoveToBinPurge = true;
        hasBin = false;
        nextDestX = bins.get(pbin).getPositionX() + 40;
		nextDestY = bins.get(pbin).getPositionY() - 20;

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
			pickupBin(pbin);
			f.lm.removeBin(feeder);
			f.isMoveToBin = false;
            if (f.isRemoveBin)
                moveToPoint(340, binYArray[pbin]);
		}
	}

	public void moveToPoint(int newX, int newY){
		//System.out.println("moving to point");
		//hasBin = true;
        f.x = newX;
        f.y = newY;
        f.isMoveToBin = false;
        f.isMoveToFeeder = false;
        f.isMoveToBinPurge = false;
        f.isMoveToPoint = true;
		nextDestX = newX - 40;
		nextDestY = newY - 20;
		if (x < nextDestX){

			if((x+4) > nextDestX)
			{
				x = nextDestX;

				if(possessedBin != null)
				{
					possessedBin.x = x + 10;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
				
			}

			x+=4;

			if(possessedBin != null)
			{
				
				possessedBin.x+=4;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}
		}

		else if (x > nextDestX){

			if((x-4) < nextDestX)
			{
				x = nextDestX;

				if(possessedBin != null)
				{
					possessedBin.x = x + 10;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
			}

			x-=4;

			if(possessedBin != null)
			{
				possessedBin.x-=4;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}

		}

		if(y < nextDestY){

			if((y+4) > nextDestY)
			{
				y = nextDestY;
				if(possessedBin != null)
				{
					possessedBin.y = y;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
			}

			y+=4;
			if(possessedBin != null)
			{
				possessedBin.y+=4;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}
		}

		else if (y > nextDestY){

			if((y-4) < nextDestY)
			{
				y = nextDestY;
				if(possessedBin != null)
				{
					possessedBin.y = y;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
			}

			y-=4;
			if(possessedBin != null)
			{
				possessedBin.y-=4;
				possessedBin.getPartIcon().x = possessedBin.x + 8;
				possessedBin.getPartIcon().y = possessedBin.y + 9;
			}
		}

		if(neutralPos == false)
		{
			if(x == nextDestX && y == nextDestY){
				System.out.println("drop bin");
				if(possessedBin != null)
				{
					possessedBin.x = 340;
					possessedBin.y = newY;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
					possessedBin = null;
				}
				System.out.println("no bin");
				hasBin = false;
	            f.isMoveToPoint = false;
			}
		}

		else
		{
			if(x == nextDestX && y == nextDestY){
				neutralPos = false;
				if(possessedBin != null)
				{
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
	            f.isMoveToPoint = false;
	            f.isRemoveBin = false;
			}
		}
	
	}

	public void moveToFeeder(int feeder){
		//System.out.println("moving to the feeder");
        f.feeder = feeder;
        f.isMoveToBin = false;
        f.isMoveToPoint = false;
        f.isMoveToBinPurge = false;
        f.isMoveToFeeder = true;

        if(hasBin == true){
        	for(int i=0;i<10;i++){
	        	if(bins.get(i).getPositionY() == feeders.get(feeder).getPositionY())
				{
					f.isMoveToFeeder = false;
					neutralPos = true;
					moveToPoint(200, 335);
				}
	        }

	        nextDestX = feeders.get(feeder).getPositionX() + 40;
			nextDestY = feeders.get(feeder).getPositionY() - 20;
			if (x < nextDestX){

				if((x+4) > nextDestX)
				{
					x = nextDestX;
					if(possessedBin != null)
					{
						possessedBin.x = x + 10;
						possessedBin.getPartIcon().x = possessedBin.x + 8;
						possessedBin.getPartIcon().y = possessedBin.y + 9;
					}
				}

				x+=4;
				if(possessedBin != null)
				{
					possessedBin.x+=4;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
			}

			else if (x > nextDestX){

				if((x-4) < nextDestX)
				{
					x = nextDestX;
					if(possessedBin != null)
					{
						possessedBin.x = x + 10;
						possessedBin.getPartIcon().x = possessedBin.x + 8;
						possessedBin.getPartIcon().y = possessedBin.y + 9;
					}
				}

				x-=4;
				if(possessedBin != null)
				{
					possessedBin.x-=4;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
			}

			if(y < nextDestY){

				if((y+4) > nextDestY)
				{
					y = nextDestY;
					if(possessedBin != null)
					{
						possessedBin.y = y;
						possessedBin.getPartIcon().x = possessedBin.x + 8;
						possessedBin.getPartIcon().y = possessedBin.y + 9;
					}
				}

				y+=4;
				if(possessedBin != null)
				{
					possessedBin.y+=4;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
			}

			else if (y > nextDestY){

				if((y-4) < nextDestY)
				{
					y = nextDestY;
					if(possessedBin != null)
					{
						possessedBin.y = y;
						possessedBin.getPartIcon().x = possessedBin.x + 8;
						possessedBin.getPartIcon().y = possessedBin.y + 9;
					}
				}

				y-=4;
				if(possessedBin != null)
				{
					possessedBin.y-=4;
					possessedBin.getPartIcon().x = possessedBin.x + 8;
					possessedBin.getPartIcon().y = possessedBin.y + 9;
				}
			}

			if (neutralPos == false)
			{
				if(x == nextDestX && y == nextDestY){
					if(possessedBin != null)
					{
						System.out.println("drop bin");
						possessedBin.x = feeders.get(feeder).getPositionX();
						possessedBin.y = feeders.get(feeder).getPositionY();
						possessedBin.getPartIcon().x = possessedBin.x + 8;
						possessedBin.getPartIcon().y = possessedBin.y + 9;
						f.lm.addToFeeder(feeder, possessedBin.getPart(), 50);
						possessedBin = null;
					}
					System.out.println("no bin");
					hasBin = false;
					f.isMoveToFeeder = false;
				}
			}
        }

        
	}

	public void removeBin(int feeder){
		System.out.println("removing bin");
        f.isRemoveBin = true;
		if(hasBin == false){
			for(int i=0;i<10;i++){
				if(feeders.get(feeder).getPositionY() == bins.get(i).getPositionY())
				{
					moveToBinPurge(i, feeder);
				}
			}
		}
    }

	public void pickupBin(int grabbedBin){
		System.out.println("pickup bin");
		hasBin = true;
		f.isMoveToPoint = false;
		possessedBin = bins.get(grabbedBin);
		possessedBin.x = x + 10;
		possessedBin.y = y;
		possessedBin.getPartIcon().x = possessedBin.x + 8;
		possessedBin.getPartIcon().y = possessedBin.y + 9;
	}
}