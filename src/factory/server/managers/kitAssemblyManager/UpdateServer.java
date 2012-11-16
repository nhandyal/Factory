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
    ArrayList<Nest> nests;
	Timer t;
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
	public UpdateServer()
	{
		cam = new InspectionCamera(100,500,"images/camera.jpg", this);
		conv = new Conveyor(0,20,"images/conveyor.jpg", this);
		robot = new KitRobot(23,280,"images/kitrobot.png", this);
		probot = new PartRobot(253,280,"images/partrobot.png", this);
		stands = new ArrayList<KitStand>();
		kits = new ArrayList<Kit>();
		parts = new ArrayList<Part>();
        nests = new ArrayList<Nest>();
		for (int i = 0; i < 3; i++)
		{
			KitStand ks = new KitStand(100,130 + i * 150,"images/kitstand.jpg");
			stands.add(ks);
		}
        for (int i = 0; i < 4; i++){
			Nest n1 = new Nest(355,72+i*124,"images/nest.jpg");
			Nest n2 = new Nest(355,72+i*124+35,"images/nest.jpg");
			nests.add(n1);
			nests.add(n2);
		}
		LineObjects.add(new FactoryObject((int)robot.getX1(),(int)robot.getY1(),(int)robot.getX2(),(int)robot.getY2()));
		LineObjects.add(new FactoryObject((int)probot.getX1(),(int)probot.getY1(),(int)probot.getX2(),(int)probot.getY2()));
		setCurrentObjects();
		t = new Timer(50,this);
		t.start();
	}
	
	public ArrayList<FactoryObject> getCurrentObjects()
	{
		return CurrentObjects;
	}
	public TreeMap<Integer, FactoryObject> getFactoryObjects()
	{
		TreeMap<Integer, FactoryObject> t = new TreeMap<Integer, FactoryObject>();
		for (int i = 0; i < CurrentObjects.size(); i++)
			t.put(i, CurrentObjects.get(i));
		return t;
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
                        Part p1 = new Part(nests.get(j+2).getPositionX()+5,nests.get(j+2).getPositionY()+5,"images/part2.png");
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
    
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
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
			}
		}
	}
}
