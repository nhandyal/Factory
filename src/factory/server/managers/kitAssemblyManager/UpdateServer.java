package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import factory.server.managers.GuiManager;
import java.io.*;

public class UpdateServer implements GuiManager, Serializable
{
	ArrayList<FactoryObject> CurrentObjects = new ArrayList<FactoryObject>();
	TreeMap<Integer, Boolean> ChangeMap = new TreeMap<Integer, Boolean>();
	TreeMap<Integer, FactoryObject> ChangeData = new TreeMap<Integer, FactoryObject>();
    InspectionCamera cam;
	Conveyor conv;
	KitRobot robot;
	PartRobot probot;
	ArrayList<FactoryObject> LineObjects = new ArrayList<FactoryObject>();
    ArrayList<FactoryObject> lastObjects = null;
	ArrayList<KitStand> stands;
	ArrayList<Kit> kits;
	ArrayList<Part> parts;
    ArrayList<Nest> nests;
	int count = 0;
	int countconv = 0;
	int partscount = 0;
	boolean isBringKit = true;
	boolean isMoveToStand = false;
	boolean isMovePartstoStand = false;
	boolean isMoveToInspection = false;
	boolean isTakePic = false;
	boolean isTakeToConveyor = false;
	boolean isTakeKit = false;
	@SuppressWarnings("unchecked")
	public UpdateServer()
	{
		cam = new InspectionCamera(100,500,13, this);
		conv = new Conveyor(0,20,-1, this);
		robot = new KitRobot(23,280,-1, this);
		probot = new PartRobot(253,280,-1, this);
		stands = new ArrayList<KitStand>();
		kits = new ArrayList<Kit>();
		parts = new ArrayList<Part>();
        nests = new ArrayList<Nest>();
		for (int i = 0; i < 3; i++)
		{
			KitStand ks = new KitStand(100,130 + i * 150,-1);
			stands.add(ks);
		}
        for (int i = 0; i < 4; i++){
			Nest n1 = new Nest(355,72+i*124,-1);
			Nest n2 = new Nest(355,72+i*124+35,-1);
			nests.add(n1);
			nests.add(n2);
		}
		LineObjects.add(new FactoryObject((int)robot.getX1(),(int)robot.getY1(),(int)robot.getX2(),(int)robot.getY2()));
		LineObjects.add(new FactoryObject((int)probot.getX1(),(int)probot.getY1(),(int)probot.getX2(),(int)probot.getY2()));
		setCurrentObjects();
	}
	
	public ArrayList<FactoryObject> getCurrentObjects()
	{
		return CurrentObjects;
	}
	public void sync(TreeMap<Integer, FactoryObject> changeData)																			// frame sync
	{
		for (int i = 0; i < CurrentObjects.size(); i++){
			changeData.put(i, CurrentObjects.get(i));
		}
	}
	
	public void setCurrentObjects()
	{
		CurrentObjects.clear();
		//CurrentObjects.add(conv);
		//CurrentObjects.add(robot);
		//CurrentObjects.add(probot);
		//for (int i = 0; i < stands.size(); i++)
			//CurrentObjects.add(stands.get(i));
		
		for (int i = 0; i < kits.size(); i++)
			CurrentObjects.add(kits.get(i));
		for (int i = 0; i < LineObjects.size(); i++)
			CurrentObjects.add(LineObjects.get(i));
		CurrentObjects.add(probot.getGripper());
		for (int i = 0; i < parts.size(); i++)
			CurrentObjects.add(parts.get(i));
		CurrentObjects.add(cam);	
	}
	
	public boolean emptyStand()
	{
		for (int i = 0; i < 2; i++)
		{
			if (stands.get(i).getKit() == null)
				return true;
		}
		return false;
	}
	
	public ArrayList<Kit> getKits()
	{
		return kits;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public TreeMap<Integer, Boolean> getChangeMap()
	{
		return ChangeMap;
	}
	
	public TreeMap<Integer, FactoryObject> getChangeData()
	{
		return ChangeData;
	}
	
	public void bringKit()
	{
		if (conv.getInKit() == null && count < 26)
			conv.bringKit();
		if (!conv.kitArrived() && count < 26)
		{
			conv.moveKit();
		}
		countconv++;
		if (countconv == 26)
		{
			countconv = 0;
			isBringKit = false;
			isMoveToStand = true;
		}
	}
	
	public void moveToStand(int k)
	{
		if (!robot.getIsMoving() && emptyStand() && conv.kitArrived())
		{
				robot.moveFromConveyorToStand(conv, stands.get(k), conv.getInKit(), 0);
		}
		if (robot.getIsMoving())
			robot.move();
		count++;
		if (count == 100)
		{
			count = 0;
			isMoveToStand = false;
			//isBringKit = true;
			isMovePartstoStand = true;
		}
	}
	
	public void movePartstoStand(int nest, int stand, int[] pos)
	{
		if (!probot.isMoving())
		{
			if (stands.get(stand).getKit() != null){
                if (!stands.get(stand).getKit().getIsComplete()){
                    Part[] p = new Part[4];
                    int[] indexes = new int[4];
                    for (int j = 0; j < p.length; j++){
                        Part p1 = new Part(nests.get(j+2).getPositionX()+5,nests.get(j+2).getPositionY()+5,1);
                        parts.add(p1);
                        p[j] = p1;
                    }
                    if (stands.get(stand).getKit().getParts()[0] == null){
                        for (int j = 0; j < indexes.length; j++){
                            indexes[j] = j;
                        }
                    }
                    else{
                        for (int j = 0; j < indexes.length; j++){
                            indexes[j] = j+4;
                        }
                    }
                    probot.moveFromNest(stands.get(stand),p,indexes,0);
                }
            }
		}
		if (probot.isMoving())
			probot.move();
		count++;
		if (count == 141)
		{
			count = 0;
			isMovePartstoStand = false;
			if (stands.get(stand).getKit().getIsComplete())
				isMoveToInspection = true;
		}
	}
	
	public void moveToInspection()
	{
		if (!robot.getIsMoving() && stands.get(2).getKit() == null)
		{
			for (int i = 0; i < 2; i++){
				if (stands.get(i).getKit() != null){
					robot.moveFromStandToStand(stands.get(i),stands.get(2), stands.get(i).getKit(), 0);
					break;
				}
			}
		}
		if (robot.getIsMoving())
			robot.move();
		count++;
		if (count == 100)
		{
			count = 0;
			isMoveToInspection = false;
			isTakePic = true;
			
		}
	}
	
	public void takePic()
	{
		if (!robot.getIsMoving() && stands.get(2).getKit() != null && !stands.get(2).getKit().getPicTaken() && !cam.isMoving())
			cam.takePicture(stands.get(2), 0);
		if (cam.isMoving)
			cam.move();
		count++;
		if (count == 57)
		{
			count = 0;
			isTakePic = false;
			isTakeToConveyor = true;
		}
	}
	
	public void takeToConveyor()
	{
		if (!robot.getIsMoving() && stands.get(2).getKit() != null && stands.get(2).getKit().getPicTaken())
			robot.moveFromStandToConveyor(stands.get(2), conv, stands.get(2).getKit(), 0);
		if (robot.getIsMoving())
			robot.move();
		count++;
		if (count == 100)
		{
			count = 0;
			isTakeToConveyor = false;
			isTakeKit = true;
		}
	}
	public void takeKit()
	{
		if (conv.getOutKit() != null)
			conv.takeKit();
		count++;
		if (count == 26)
		{
			count = 0;
			isTakeKit = false;
            conv.getOutKit().setIsMoving(false);
            conv.setOutKit(null);
		}
	}
    
    public void removeExtraKits(){
		for (int i = 0; i < kits.size(); i++){
			if (kits.get(i).getPositionX() < 0 && !kits.get(i).getIsMoving()){
				removeExtraParts();
				kits.remove(i);
				i--;
			}
		}
	}
	
	public void removeExtraParts(){
		for (int i = 0; i < parts.size(); i++){
			if (parts.get(i).getPositionX() < 0){
				parts.remove(i);
				i--;
			}
		}
	}
	
	public void update(TreeMap<Integer, Boolean> inputChangeMap, TreeMap<Integer, FactoryObject> inputChangeData)      													// ------------> update complete
	{
		ChangeMap = inputChangeMap;
		ChangeData = inputChangeData;
		
		move();
		 
        
		if (lastObjects.size() <= CurrentObjects.size())
		{
			for (int i = 0; i < lastObjects.size(); i++)
			{
				if (!lastObjects.get(i).equals(CurrentObjects.get(i)))
				{
					ChangeMap.put(i, true);
					ChangeData.put(i, CurrentObjects.get(i));
				}
			}
			for (int i = lastObjects.size(); i < CurrentObjects.size(); i++)
			{
				ChangeMap.put(i, true);
				ChangeData.put(i, CurrentObjects.get(i));
			}
		}
		else
		{
			for (int i = 0; i < CurrentObjects.size(); i++)
			{
				if (!lastObjects.get(i).equals(CurrentObjects.get(i)))
				{
					ChangeMap.put(i, true);
					ChangeData.put(i, CurrentObjects.get(i));
				}
			}
			for (int i = CurrentObjects.size(); i < lastObjects.size(); i++)
			{
				ChangeMap.put(i, false);
			}
		}
		//lastObjects = (ArrayList<FactoryObject>) CurrentObjects.clone();
	}
    
	
	public void move()
	{
		lastObjects = (ArrayList<FactoryObject>) CurrentObjects.clone();
        if (isBringKit)
			bringKit();
		if (isMoveToStand)
			moveToStand(0);
		if (isMovePartstoStand)
		{
			int a[] = {0, 1, 2, 3};
			movePartstoStand(200, 0, a);
		}
			
		if (isMoveToInspection)
		{
			moveToInspection();
		}
		if (isTakePic)
			takePic();
		if (isTakeToConveyor)
			takeToConveyor();
		if (isTakeKit)
		{
			
			takeKit();
		}
        removeExtraKits();
		LineObjects.set(0, new FactoryObject((int)robot.getX1(),(int)robot.getY1(),(int)robot.getX2(),(int)robot.getY2()));
		LineObjects.set(1, new FactoryObject((int)probot.getX1(),(int)probot.getY1(),(int)probot.getX2(),(int)probot.getY2()));
		setCurrentObjects();
	}
}
