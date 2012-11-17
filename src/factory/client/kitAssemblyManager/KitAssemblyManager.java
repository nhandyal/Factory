package factory.client.kitAssemblyManager;

import java.awt.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;
import factory.global.network.*;
import factory.global.data.*;

public class KitAssemblyManager extends JFrame implements ActionListener, NetworkManager {
	
	Timer t;
	//UpdateServer us = new UpdateServer();
	TreeMap<Integer, Boolean> ChangeMap;
	TreeMap<Integer, FactoryObject> ChangeData;
	TreeMap<Integer, FactoryObject> fos;
	ImageIcon bg = new ImageIcon("../assets/KMBG.png");
	Image iBuffer;
	ImageArray images = new ImageArray();
    NetworkBridge nb;
	public KitAssemblyManager(){
		t = new Timer(25,this);
        nb = new NetworkBridge(this, "aludra.usc.edu", 8465, 4);  
		//fos = us.getFactoryObjects();
		t.start();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		//fos = us.getCurrentObjects();
		/*us.update();
		ChangeMap = us.getChangeMap();
		ChangeData = us.getChangeData();
		Set<Integer> t = ChangeMap.keySet();
		for (Integer i : t)
		{
			if (ChangeMap.get(i))
				fos.put(i, ChangeData.get(i));
			else
				fos.remove(i);
		}*/
		Graphics g = this.getGraphics();
		update(g);
		//repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		bg.paintIcon(this, g2, 0, 0);
		for (Integer i : fos.keySet())
		{
			FactoryObject t = fos.get(i);
			if(t.getIsLine())
			{
				g2.setColor(Color.WHITE);
				g2.drawLine(t.getPositionX(), t.getPositionY(), t.getPositionXF(), t.getPositionYF());
			}	
			else
			{
				ImageIcon tmp = images.getIcon(t.getImageIndex());
				tmp.paintIcon(this, g2, t.getPositionX(), t.getPositionY());
			}
		}
	}
	
	public void update(Graphics g) 
	{  
		  if(iBuffer == null)
		  {  
		      iBuffer = this.createImage(400, 670);  
		  }  
		  Graphics gOffScreen = iBuffer.getGraphics();  
		  Color c = gOffScreen.getColor();  
		  gOffScreen.setColor(Color.BLUE);  
		  gOffScreen.fillRect(0, 0, 800, 800);  
		  gOffScreen.setColor(c);  
		  paint(gOffScreen);  
		  g.drawImage(iBuffer, 0, 0, null);
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
            Set<Integer> t = ChangeMap.keySet();
            for (Integer i : t)
            {
                if (ChangeMap.get(i))
                    fos.put(i, ChangeData.get(i));
                else
                    fos.remove(i);
            }
        }
        else
        {
            System.out.println("Warning: Corrupt frame data");
        }
    }
    
    public void syncChanges(ArrayList<TreeMap<Integer, FactoryObject>> dataArray)
    {
        //dataArray.get(0);
    }
    
    public void syncFrame(int cID)
    {
        
    }
    public void updatePartData(TreeMap<Integer, Parts> partData)
    {
        
    }
    
    // global
    public void closeNetworkBridge(int bridgeID)
    {
        nb.close();
    }

}
