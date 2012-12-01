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

		public NetworkBridge nb;
		TreeMap<Integer,FactoryObject> frameAnimationData;

		ImageIcon background;
		ImageArray images;

		JPanel masterPanel, animationContainer, animData, inputData;
		CardLayout c1;
		LMANIM anim;

		ArrayList<JPanel> animationFrames;
		ArrayList<TreeMap<Integer, FactoryObject>> factoryAnimationData;
		LMGUI gui;
		Timer t;

		LaneManager(){

				images = new ImageArray();

				frameAnimationData = new TreeMap<Integer,FactoryObject>();

				// initialize JPanels and CardLayout
				masterPanel = new JPanel();
				animationContainer = new JPanel();
				c1 = new CardLayout();
				
				// set Panel and Frame properties
				masterPanel.setLayout(c1);
				animationContainer.setLayout(new BoxLayout(animationContainer,BoxLayout.X_AXIS));
				
				// initialize class variables
				factoryAnimationData = new ArrayList<TreeMap<Integer, FactoryObject>>();
				for(int i = 0; i < 3; i++){
						factoryAnimationData.add(new TreeMap<Integer, FactoryObject>());
				}
				nb = new NetworkBridge(this, "localhost", 8465, 3);
//				gui = new LMGUI(this);
				animationFrames = new ArrayList<JPanel>();
				animationFrames.add(new LMANIM(this));
				animationFrames.add(new LMGUI(this));
//				animationFrames.add(new GM(this));
//				t = new Timer(25,this);
				
				// add animation managers to animationContainer
				for(JPanel frame : animationFrames){
						animationContainer.add(frame);
				}
				
				// add the container panels to the JFrame
				masterPanel.add(animationContainer,"ac");
//				masterPanel.add(gui,"gc");
				
				this.add(masterPanel);
				
				// start threads
//				t.start();
				nb.sync();
				
				c1.show(masterPanel, "ac");
		}

		public static void main(String[] args){
				LaneManager l = new LaneManager();
				l.setVisible(true);
				l.setSize(700,670);
				l.createBufferStrategy(2);
				l.setTitle("Lane Manager");
				l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				new Timer(50,l).start();
		} //end main

		public void actionPerformed( ActionEvent ae ) {
				for(JPanel frame : animationFrames){
						frame.repaint();
				}

//				anim.repaint();
    }

    public TreeMap<Integer, FactoryObject> getMap(){
    	return frameAnimationData;
    }

		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- Network Manager ---------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		// server specific
		public void registerClientListener(NetworkBridge newBridge, int cID){}
		public void syncFrame(){}
		public void updatePartData(TreeMap<Integer, Parts> partData){}
		public void updateKitData(TreeMap<Integer, Kits> kitData){}
		public void updateBuildData(ArrayList<Kits> buildData){}
		public void updateBreakData(String breakCommand, int cID, int x){}
		
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
