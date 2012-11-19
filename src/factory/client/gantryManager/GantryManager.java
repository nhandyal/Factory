package factory.client.gantryManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

// user packages
import factory.global.network.*;
import factory.global.data.*;

//bullshit comment

public class GantryManager extends JFrame implements ActionListener, NetworkManager {
	
	Timer t;
	TreeMap<Integer, FactoryObject> frameAnimationData = new TreeMap<Integer, FactoryObject>();
	ImageIcon bg = new ImageIcon("bin/factory/global/assets/GMBG.png");
    Image iBuffer;
	ImageArray images = new ImageArray();
    NetworkBridge nb;

	public GantryManager(){
			t = new Timer(25,this);
    nb = new NetworkBridge(this, "localhost", 8465, 2);
			nb.sync();
			t.start();
	}

	public static void main(String[] args){
		GantryManager l = new GantryManager();
		l.setVisible(true);
		l.setSize(400,670);
		l.createBufferStrategy(2);
		l.setTitle("Gantry Manager");
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Timer(50,l).start();
	} //end main

	public void actionPerformed(ActionEvent ae){
		repaint();
	}

	public void paint(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			bg.paintIcon(this, g2, 0, 0);
			for (Integer i : frameAnimationData.keySet()){
					FactoryObject t = frameAnimationData.get(i);
					if(t.getIsLine()){
							g2.setColor(Color.WHITE);
							g2.drawLine(t.getPositionX(), t.getPositionY(), t.getPositionXF(), t.getPositionYF());
					}	
					else{
							ImageIcon tmp = images.getIcon(t.getImageIndex());
							tmp.paintIcon(this, g2, t.getPositionX(), t.getPositionY());
					}
			}
	}

	public void update(Graphics g){  
			if(iBuffer == null){  
					iBuffer = this.createImage(400, 670);  
			}  
			Graphics gOffScreen = iBuffer.getGraphics();
			paint(gOffScreen);  
			g.drawImage(iBuffer, 0, 0, null);
	}


    // -------------------------------------------------------------------------------------- //
	// ----------------------------------- Network Manager ---------------------------------- //
	// -------------------------------------------------------------------------------------- //
	
	// server specific
	public void registerClientListener(NetworkBridge newBridge, int cID){}
	public void syncFrame(){}
	public void updatePartData(TreeMap<Integer, Parts> partData){}
	public void updateKitData(ArrayList<Kits> kitData){}
	
	// client specific
	public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray){
			
			if(mapArray.size() == 1){
					TreeMap<Integer, Boolean> changeMap = mapArray.get(0);
					TreeMap<Integer, FactoryObject> changeData = dataArray.get(0);
				
					// iterate over all the keys present in changeMap
					// after this loop is complete, the frameAnimationData map will be accurately synced with the server copy
					for(Integer key : changeMap.keySet()){
							// check the write direction of the change map key
							if(changeMap.get(key)){
									// write new factory object to animation frame
									FactoryObject newAnimationData = changeData.get(key);
									frameAnimationData.put(key,newAnimationData);
							}
							else{
									// delete the factory object from frameAnimationData
									frameAnimationData.remove(key);
							}
					}
			}
			else{
					System.out.println("Warning: Corrupt frame data");
			}
	}
	
	public void syncChanges(ArrayList<TreeMap<Integer,FactoryObject>> dataArray){
			if(dataArray.size() == 1){
					TreeMap<Integer, FactoryObject> changeData = dataArray.get(0);
					frameAnimationData = changeData;
					System.out.println("Sync complete");
			}
			else{
				System.out.println("Warning: Corrupt frame data");
			}
	}
	
	// global
	public void closeNetworkBridge(int bridgeID){
			nb.close();
	}
	
	// -------------------------------------------------------------------------------------- //
	// ----------------------------------- End Network Manager ------------------------------ //
	// -------------------------------------------------------------------------------------- //		
}
