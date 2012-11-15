package KitAssemblyManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;
public class UpdateServer implements ActionListener 
{
	ArrayList<FactoryObject> CurrentObjects = new ArrayList<FactoryObject>();
	TreeMap<Integer, Boolean> ChangeMap = new TreeMap<Integer, Boolean>();
	TreeMap<Integer, FactoryObject> ChangeData = new TreeMap<Integer, FactoryObject>();
	InspectionCamera cam;
	Conveyor conv;
	KitRobot robot;
	PartRobot probot;
	ArrayList<FactoryObject> LineObjects = new ArrayList<FactoryObject>();
	ArrayList<KitStand> stands;
	ArrayList<Kit> kits;
	ArrayList<Part> parts;
	Timer t;
	int count = 0;
	int partscount = 0;
	public UpdateServer()
	{
		cam = new InspectionCamera(100,700,"images/camera.jpg", this);
		conv = new Conveyor(0,50,"images/conveyor.jpg", this);
		robot = new KitRobot(200,400,"images/kitrobot.png", this);
		probot = new PartRobot(400,400,"images/partrobot.png", this);
		stands = new ArrayList<KitStand>();
		kits = new ArrayList<Kit>();
		parts = new ArrayList<Part>();
		for (int i = 0; i < 3; i++)
		{
			KitStand ks = new KitStand(100,200 + i * 200,"images/kitstand.jpg");
			stands.add(ks);
		}
		Kit k = new Kit(100,400,"images/kit.png");
		LineObjects.add(new FactoryObject((int)robot.getX1(),(int)robot.getY1(),(int)robot.getX2(),(int)robot.getY2()));
		LineObjects.add(new FactoryObject((int)probot.getX1(),(int)probot.getY1(),(int)probot.getX2(),(int)probot.getY2()));
		stands.get(1).setCurrentKit(k);
	//	kits.add(k);
		setCurrentObjects();
		t = new Timer(50,this);
		t.start();
	}
	
	public ArrayList<FactoryObject> getCurrentObjects()
	{
		return CurrentObjects;
	}
	public void setCurrentObjects()
	{
		CurrentObjects.removeAll(CurrentObjects);
		CurrentObjects.add(conv);
		CurrentObjects.add(robot);
		CurrentObjects.add(probot);
		for (int i = 0; i < stands.size(); i++)
			CurrentObjects.add(stands.get(i));
		for (int i = 0; i < kits.size(); i++)
			CurrentObjects.add(kits.get(i));
		for (int i = 0; i < parts.size(); i++)
			CurrentObjects.add(parts.get(i));
		CurrentObjects.add(cam);
		for (int i = 0; i < LineObjects.size(); i++)
			CurrentObjects.add(LineObjects.get(i));
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
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if (conv.getInKit() == null && count < 26)
			conv.bringKit();
		if (!conv.kitArrived() && count < 26)
		{
			conv.moveKit();
		}
		
		if (!robot.getIsMoving() && emptyStand() && conv.kitArrived() && count >= 50 && count <= 150)
		{
			for (int i = 0; i < 2; i++)
			{
				if (stands.get(i).getKit() == null)
				{
					robot.moveFromConveyorToStand(conv, stands.get(i), conv.getInKit(), 50);
					break;
				}
			}
		}
		if (robot.getIsMoving() && count >= 50 && count < 150)
			robot.move();
		if (!probot.isMoving() && count >= 150 && count <= 150 + 81){
				Part p = new Part(860,300,"images/part2.png");
				System.out.println(count);
				parts.add(p);
				probot.moveFromNest(200 ,stands.get(0),p, 1, 150);
		}
		if (probot.isMoving() && count >= 150 && count <= 150 + 81)
			probot.move();
		
	    if (!robot.getIsMoving() && stands.get(2).getKit() == null && count >= 232 && count < 332)
		{
			for (int i = 0; i < 2; i++){
				if (stands.get(i).getKit() != null){
					robot.moveFromStandToStand(stands.get(i),stands.get(2), stands.get(i).getKit(), 232);
					break;
				}
			}
		}
		if (robot.getIsMoving() && count >= 232 && count < 332)
			robot.move();
		if (!robot.getIsMoving() && stands.get(2).getKit() != null && !stands.get(2).getKit().getPicTaken() && !cam.isMoving())
			if (count >= 332 && count < 389)
				cam.takePicture(stands.get(2), 332);
		if (cam.isMoving && count >= 332 && count < 389)
			cam.move();
		if (!robot.getIsMoving() && stands.get(2).getKit() != null && count >= 389 && stands.get(2).getKit().getPicTaken())
			robot.moveFromStandToConveyor(stands.get(2), conv, stands.get(2).getKit(), 389);
		if (robot.getIsMoving() && count >= 389)
			robot.move();
		if (conv.getOutKit() != null && count >= 489)
			conv.takeKit();
		LineObjects.set(0, new FactoryObject((int)robot.getX1(),(int)robot.getY1(),(int)robot.getX2(),(int)robot.getY2()));
		LineObjects.set(1, new FactoryObject((int)probot.getX1(),(int)probot.getY1(),(int)probot.getX2(),(int)probot.getY2()));
		count = (count + 1) % 1000;
		ArrayList<FactoryObject> t = (ArrayList<FactoryObject>) CurrentObjects.clone();
		setCurrentObjects();
		if (t.size() <= CurrentObjects.size())
		{
			for (int i = 0; i < t.size(); i++)
			{
				if (!t.get(i).equals(CurrentObjects.get(i)))
				{
					ChangeMap.put(i, true);
					ChangeData.put(i, CurrentObjects.get(i));
				}
			}
			for (int i = t.size(); i < CurrentObjects.size(); i++)
			{
				ChangeMap.put(i, true);
				ChangeData.put(i, CurrentObjects.get(i));
			}
		}
		else
		{
			for (int i = 0; i < CurrentObjects.size(); i++)
			{
				if (!t.get(i).equals(CurrentObjects.get(i)))
				{
					ChangeMap.put(i, true);
					ChangeData.put(i, CurrentObjects.get(i));
				}
			}
			for (int i = CurrentObjects.size(); i < t.size(); i++)
			{
				ChangeMap.put(i, false);
				ChangeData.put(i, CurrentObjects.get(i));
			}
		}
	}
}