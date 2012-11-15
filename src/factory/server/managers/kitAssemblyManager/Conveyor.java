package factory.server.managers.kitAssemblyManager;

import java.awt.event.*;
import javax.swing.*;

public class Conveyor extends FactoryObject
{
	
	Kit inKit;
	Kit outKit;
	int inCount = 0;
	int outCount = 0;
	int inStartx = 700;
	int inStarty = 50;
	int inFinishx = 400;
	int inFinishy = 50;
	int outStartx = 300;
	int outStarty = 50;
	int outFinishx = -100;
	int outFinishy = 50;
	UpdateServer us;
	public Conveyor(int xpos, int ypos, String image, UpdateServer us)
	{
		super(xpos, ypos, image);
		setImage(image);
		this.us = us;
	}
	
	public void bringKit()
	{
		inKit = new Kit(inStartx,inStarty,"images/kit.png");
		us.getKits().add(inKit);
	}
	
	public void moveKit()
	{
		for (int i = 0; i < us.getCurrentObjects().size(); i++)
		{	
			if (us.getCurrentObjects().get(i) == inKit)
				us.getCurrentObjects().get(i).setPosition(inKit.getPositionX() + (inFinishx - inStartx)/25, inKit.getPositionY() + (inFinishy - inStarty)/25);
		}
		
	}
	
	public void takeKit()
	{
		for (int i = 0; i < us.getCurrentObjects().size(); i++)
		{	
			if (us.getCurrentObjects().get(i) == outKit)
				us.getCurrentObjects().get(i).setPosition(outKit.getPositionX() + (outFinishx - outStartx)/25, outKit.getPositionY() + (outFinishy - outStarty)/25);
		}
	}
	
	public Kit getInKit()
	{
		return inKit;
	}
	
	public void setInKit(Kit k)
	{
		inKit = k;
	}
	
	public Kit getOutKit()
	{
		return outKit;
	}
	
	public void setOutKit(Kit k)
	{
		outKit = k;
	}
	
	public int getInFinishX()
	{
		return inFinishx;
	}
	
	public int getInFinishY()
	{
		return inFinishy;
	}
	
	public int getOutStartX()
	{
		return outStartx;
	}
	
	public int getOutStartY()
	{
		return outStarty;
	}
	
	public boolean kitArrived()
	{
		if (inKit == null)
			return false;
		if (inKit.getPositionX() == inFinishx && inKit.getPositionY() == inFinishy)
			return true;
		else
			return false;
	}
	
}
