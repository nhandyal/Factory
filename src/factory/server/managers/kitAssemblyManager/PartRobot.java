package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class PartRobot extends FactoryObject
{
	
	double x1,x2,y1,y2;
	int csCount = 0;
	int[] kitIndex = new int[4];
	Part[] holdObj = new Part[4];
	KitStand s2;
    Gripper g;
	double[] xdes1, ydes1;
	double[] xdes2, ydes2;
	boolean isMoving = false;
	int base;
	UpdateServer us;
    int totalParts;
    int imageHeight = 51;
    int imageWidth = 50;
	public PartRobot(int xpos, int ypos, int image, UpdateServer us)
	{
		super(xpos, ypos, image);
		setImage(image);
		this.us = us;
		x1 = x + imageWidth/2;
		x2 = x + imageWidth/2;
		y1 = y + imageHeight/2;
		y2 = y + imageHeight/2;
        g = new Gripper((int)x2,(int)y2,16);
		xdes1 = new double[4];
		ydes1 = new double[4];
		xdes2 = new double[4];
		ydes2 = new double[4];
	}
    
    public int findTotalParts(Part[] p){
		for (int m = 0; m < p.length; m++){
			if (p[m] == null){
				totalParts = m;
				return m;
			}
		}
		return 4;
	}
	
	public void moveFromNest(KitStand ks, Part[] p, Nest[] n, int[] i, int base){
		if (ks.getKit() != null){
			isMoving = true;
			this.base = base;
			for (int k = 0; k < p.length; k++)
				holdObj[k] = p[k];
			s2 = ks;
			for (int m = 0; m < p.length; m++){
				if (p[m] != null){
					xdes1[m] = n[m].getPositionX() - 20;
					ydes1[m] = n[m].getPositionY();
				}
			}
            totalParts = findTotalParts(p);
			for (int j = 0; j < i.length; j++)
				kitIndex[j] = i[j];
			for (int l = 0; l < kitIndex.length; l++){
				if (kitIndex[l] > 3)
					ydes2[l] = ks.getKit().getPositionY() + 25;
				else
					ydes2[l] = ks.getKit().getPositionY() + 5;
				if (kitIndex[l] == 0 || kitIndex[l] == 4)
					xdes2[l] = ks.getKit().getPositionX() + 10;
				else if (kitIndex[l] == 1 || kitIndex[l] == 5)
					xdes2[l] = ks.getKit().getPositionX() + 30;
				else if (kitIndex[l] == 2 || kitIndex[l] == 6)
					xdes2[l] = ks.getKit().getPositionX() + 50;
				else if (kitIndex[l] == 3 || kitIndex[l] == 7)
					xdes2[l] = ks.getKit().getPositionX() + 70;
			}
		}
	}
	
	public void move()
	{
		csCount = us.getCount() - base;
		//System.out.println(csCount);
		if (csCount < 20){
            x2 += (xdes1[0]-x1)/20;
            y2 += (ydes1[0]-y1)/20;
            g.setPosition((int)x2,(int)y2);
            if (csCount == 19)
                g.addPart(holdObj[0]);
            csCount++;
        }
        else if (csCount >= 20 && csCount < 40){
            if (totalParts >= 2)
            {
                x2 += (xdes1[1] - xdes1[0])/20;
                y2 += (ydes1[1] - ydes1[0])/20;
                g.setPosition((int)x2,(int)y2);
                if (csCount == 39)
                    g.addPart(holdObj[1]);
                g.updateParts();
                csCount++;
            }
            else
                csCount = 80;
        }
        else if (csCount >= 40 && csCount < 60){
            if (totalParts >= 3){
                x2 += (xdes1[2] - xdes1[1])/20;
                y2 += (ydes1[2] - ydes1[1])/20;
                g.setPosition((int)x2,(int)y2);
                if (csCount == 59)
                    g.addPart(holdObj[2]);
                g.updateParts();
                csCount++;
            }
            else
                csCount = 80;
        }
        else if (csCount >= 60 && csCount < 80){
            if (totalParts >= 4){
                x2 += (xdes1[3] - xdes1[2])/20;
                y2 += (ydes1[3] - ydes1[2])/20;
                g.setPosition((int)x2,(int)y2);
                if (csCount == 79)
                    g.addPart(holdObj[3]);
                g.updateParts();
                csCount++;
            }
            else
                csCount = 80;
        }
        else if (csCount >= 80 && csCount < 100){
            x2 += (x1 - xdes1[totalParts-1])/20;
            y2 += (y1 - ydes1[totalParts-1])/20;
            g.setPosition((int)x2,(int)y2);
            g.updateParts();
            csCount++;
        }
        else if (csCount >= 100 && csCount < 120){
            x2 += (xdes2[0] - x1)/20;
            y2 += (ydes2[0] - y1)/20;
            g.setPosition((int)x2,(int)y2);
            g.updateParts();
            csCount++;
           // m.repaint();
        }
        else if (csCount >= 120 && csCount < 140){
            if (csCount == 120){
                for (int j = 0; j < totalParts; j++){
                    s2.getKit().addPart(holdObj[j],kitIndex[j]);
                    g.removePart(j).setPosition((int)xdes2[j], (int)ydes2[j]);
                }
            }
            //s2.getKit().setIsComplete(true);
            x2 += (x1 - xdes2[0])/20;
            y2 += (y1 - ydes2[0])/20;
            g.setPosition((int)x2,(int)y2);
            g.updateParts();
            csCount++;
           // m.repaint();
        }
        else if (csCount == 140){
            csCount = 0;
            isMoving = false;
        }
    }
	
	public double getX1(){
		return x1;
	}
	
	public double getX2(){
		return x2;
	}
	
	public double getY1(){
		return y1;
	}
	
	public double getY2(){
		return y2;
	}
	
	public boolean isMoving(){
		return isMoving;
	}
    
    public Gripper getGripper(){
		return g;
	}

}
