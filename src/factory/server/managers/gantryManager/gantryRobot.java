package factory.server.managers.gantryRobot;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

// user packages
import factory.global.data.*;
import factory.server.managers.GuiManager;

public class gantryManager extends FactoryObject implements GuiManager
{
	ImageIcon background;
	ArrayList<Bin> bins;
	FOComparator foc;
	ImageArray images;
	
	TreeMap<Integer,Boolean> changeMap;
	TreeMap<Integer,FactoryObject> temp;
	TreeMap<Integer,FactoryObject> changeData;
	TreeMap<Integer,FactoryObject> animData;

	public gantryManager(){
	
		index = 1;


		// Create 10 Bins
		bins = new ArrayList<Bin>();
		bins.add(new Bin(348,98,10,index,0));
		index+=2;
		bins.add(new Bin(348,98,10,index,1));
		index+=2;
		bins.add(new Bin(348,221,10,index,2));
		index+=2;
		bins.add(new Bin(348,221,10,index,3));
		index+=2;
		bins.add(new Bin(348,345,10,index,4));
		index+=2;
		bins.add(new Bin(348,345,10,index,5));
		index+=2;
		bins.add(new Bin(348,470,10,index,6));
		index+=2;
		bins.add(new Bin(348,470,10,index,7));
		index+=2;
		bins.add(new Bin(348,470,10,index,8));
		index+=2;
		bins.add(new Bin(348,470,10,index,9));
		index+=2;

		// Create Backgroud Image
		background = new ImageIcon("LMBG.png");
		
		// Create ImageList
		images = new ImageArray();

		// Start Counter
		counter = 0;
		
		// Initialize comparator
		foc = new FOComparator();
		
		changeMap = new TreeMap<Integer,Boolean>();
		changeData = new TreeMap<Integer,FactoryObject>();
		animData = new TreeMap<Integer,FactoryObject>();
		temp = new TreeMap<Integer,FactoryObject>();
	}