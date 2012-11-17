package factory.client.laneManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

// user packages
import factory.global.network.*;
import factory.global.data.*;

public class LaneManager extends JFrame implements ActionListener, NetworkManager
{
	NetworkBridge nb1;
	TreeMap<Integer,FactoryObject> frameAnimationData;

	ImageIcon background;
	ImageArray images;
	
	TreeMap<Integer,Boolean> changeMap;
	TreeMap<Integer,FactoryObject> changeData;
	TreeMap<Integer,FactoryObject> temp;
	TreeMap<Integer,FactoryObject> animData;

	public LaneManager(){

		// Create Backgroud Image
		background = new ImageIcon("LMBG.png");

		images = new ImageArray();
		
		changeMap = new TreeMap<Integer,Boolean>();
		changeData = new TreeMap<Integer,FactoryObject>();
		animData = new TreeMap<Integer,FactoryObject>();
		temp = new TreeMap<Integer,FactoryObject>();
		frameAnimationData = new TreeMap<Integer,FactoryObject>();
		nb1 = new NetworkBridge(this,"localhost",8465,3);
		nb1.sync();
	}

	public static void main(String[] args){
		LaneManager l = new LaneManager();
		l.setVisible(true);
		l.setSize(400,670);
		l.createBufferStrategy(2);
		l.setTitle("Lane Manager");
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Timer(5,l).start();
	} //end main

	public void actionPerformed( ActionEvent ae ) {
		repaint();
    }

    public void registerClientListener(NetworkBridge newBridge, int cID){}
		public void syncFrame(int cID){}
		public void updatePartData(TreeMap<Integer, Parts> partData){}
		
		// client specific
		public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray){
				
				if(mapArray.size() == 1){
						TreeMap<Integer, Boolean> changeMap = mapArray.get(0);
						TreeMap<Integer, FactoryObject> changeData = dataArray.get(0);
						
						// --- change in this block --- //
						
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
						
						// --- End block -- //
				}
				else{
						System.out.println("Warning: Corrupt frame data");
				}
		}
		
		public void syncChanges(ArrayList<TreeMap<Integer,FactoryObject>> dataArray){
				if(dataArray.size() == 1){
						TreeMap<Integer, FactoryObject> changeData = dataArray.get(0);
						frameAnimationData.putAll(changeData);
				}
				else{
						System.out.println("Warning: Corrupt frame data");
				}
		}
		
		// global
		public void closeNetworkBridge(int bridgeID){
				nb1.close();
		}

    public void paint(Graphics g){
    	Graphics2D g2 = (Graphics2D)g;
    	
//		buildMap(animData);
    	
    	Iterator k = changeMap.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(animData.containsKey(i) == false){
				animData.put(i,changeData.get(i));
			}
			else if(changeMap.get(i) == true){
				animData.put(i,changeData.get(i));
			}
		}

		background.paintIcon(this,g2,0,0);

		// Paint Updated List
		k = animData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(i != 0 && animData.get(i).getIndex()> 0){
				if(animData.get(i).getIsLine()== true)	// if object is a line draw a line
					g2.drawLine(animData.get(i).getPositionX(),animData.get(i).getPositionY(),animData.get(i).getPositionXF(),animData.get(i).getPositionYF());
				else{ 										//if object is not a line draw an ImageIcon
					int img = animData.get(i).getImageIndex();
					images.getIcon(img).paintIcon(this,g2,animData.get(i).getPositionX(),animData.get(i).getPositionY());
				}
			}
		}
    }
}
