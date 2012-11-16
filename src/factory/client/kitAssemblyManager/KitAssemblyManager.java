package factory.client.kitAssemblyManager;

import java.awt.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;
import factory.global.network.*;
import factory.global.data.*;
//import factory.server.managers.kitAssemblyManager.FactoryObject;
public class KitAssemblyManager extends JFrame implements ActionListener, NetworkManager {
	
	Rectangle r = new Rectangle(800,800);
	Timer t;
    //	UpdateServer us = new UpdateServer();
	TreeMap<Integer, Boolean> ChangeMap;
	TreeMap<Integer, FactoryObject> ChangeData;
        TreeMap<Integer, FactoryObject> fos;
        NetworkBridge nb;
	public KitAssemblyManager()
        {
	        nb = new NetworkBridge(this, "aludra.usc.edu", 8465, 4);  
		t = new Timer(50,this);
		//fos = us.getCurrentObjects();
		t.start();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		//fos = us.getCurrentObjects();
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GRAY);
		g2.fillRect(0,0,800,800);
		for (Integer i : fos.keySet())
		{
			FactoryObject t = fos.get(i);
			if(t.isLine())
			{
				g2.setColor(Color.WHITE);
				g2.drawLine(t.getPositionX(), t.getPositionY(), t.getXF(), t.getYF());
			}	
			else
				t.getImage().paintIcon(this, g2, t.getPositionX(), t.getPositionY());
		}
	}

    public void registerClientListener(NetworkBridge newBridge, int cID){}


    // client specific
    public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray)
    {

	if(mapArray.size() == 1)
	{
	    TreeMap<Integer, Boolean> changeMap = mapArray.get(0);
	    TreeMap<Integer, FactoryObject> changeData = dataArray.get(0);

	    // iterate over all the keys present in changeMap
	    // after this loop is complete, the frameAnimationData map will be accurately synced with the server copy
	    for(Integer key : changeMap.keySet())
	    {
		// check the write direction of the change map key
		if (ChangeMap.get(key))
		    fos.put(key, ChangeData.get(key));
		else
		    fos.remove(key);
	    }
	}
	else
	{
	    System.out.println("Warning: Corrupt frame data");
	}
    }


    // global
    public void closeNetworkBridge(int bridgeID) 
    {
	nb.close();
    }

}
