package factory.client.laneManager;

// Java packages
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*; 
import javax.swing.Timer;
import java.util.*;

// user packages
import factory.global.network.*;
import factory.global.data.*;

public class LaneManager extends JFrame implements ActionListener, NetworkManager{

		NetworkBridge nb1;
		TreeMap<Integer,FactoryObject> frameAnimationData;

		ImageIcon background;
		ImageArray images;
		
		public LaneManager(){

				// Create Backgroud Image
				background = new ImageIcon("bin/factory/global/assets/LMBG.png");
		
				images = new ImageArray();
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

				new Timer(50,l).start();
		} //end main

		public void actionPerformed( ActionEvent ae ) {
				repaint();
    }

    
    public void paint(Graphics g){
				Graphics2D g2 = (Graphics2D)g;
				background.paintIcon(this,g2,0,0);

				// Paint Updated List
				Iterator k = frameAnimationData.keySet().iterator();
				while(k.hasNext()){
						int i = (Integer) k.next();
						if(i != 0 && frameAnimationData.get(i).getIndex()> 0){
								if(frameAnimationData.get(i).getIsLine()== true){	// if object is a line draw a line
									if(frameAnimationData.get(i).getPositionX() == frameAnimationData.get(i).getPositionXF())
										g2.setColor(Color.black);
									else
										g2.setColor(Color.gray);
									g2.drawLine(frameAnimationData.get(i).getPositionX(),frameAnimationData.get(i).getPositionY(),frameAnimationData.get(i).getPositionXF(),frameAnimationData.get(i).getPositionYF());
								}
								else{ 										//if object is not a line draw an ImageIcon
										int img = frameAnimationData.get(i).getImageIndex();
										images.getIcon(img).paintIcon(this,g2,frameAnimationData.get(i).getPositionX(),frameAnimationData.get(i).getPositionY());
								}
						}
				}	
    }
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- Network Manager ---------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		// server specific
		public void registerClientListener(NetworkBridge newBridge, int cID){}
		public void syncFrame(){}
		public void updatePartData(TreeMap<Integer, Parts> partData){}
		public void updateKitData(TreeMap<Integer, Kits> kitData){}{}
		
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
				nb1.close();
		}
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- End Network Manager ------------------------------ //
		// -------------------------------------------------------------------------------------- //
		
}
