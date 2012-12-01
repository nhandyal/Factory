/*
** Author: Nikhil Handyal
** Date: 11/17/12
** Project: Cs200-Factory
** Description: Factory Manager
** 
** Pre-Conditions: None
*/

package factory.client.factoryManager;

// Java packages
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;


// user packages
import factory.global.network.*;
import factory.global.data.*;
import factory.client.factoryManager.*;


/*
** 0 -- KASM
** 1 -- Lane
** 2 -- Gantry
*/

public class FactoryManager extends JFrame implements ActionListener, NetworkManager{
		private static final int PAGE_WIDTH = 1150;
		private static final int PAGE_HEIGHT = 670;
		private MenuControls mc;
		private FactoryManagerGUI fmg;
		JPanel masterPanel, animationContainer;
		CardLayout c1;
		ArrayList<JPanel> animationFrames;
		ArrayList<TreeMap<Integer, FactoryObject>> factoryAnimationData;
		ImageArray images = new ImageArray();
		Timer t;
		NetworkBridge nb;
		
		FactoryManager(){
				fmg = new FactoryManagerGUI(this, PAGE_WIDTH, PAGE_HEIGHT);
				nb = new NetworkBridge(this, "localhost", 8465, 5);
				mc = new MenuControls(this);
				
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
				animationFrames = new ArrayList<JPanel>();
				animationFrames.add(new KASM(this));
				animationFrames.add(new LM(this));
				animationFrames.add(new GM(this));
				t = new Timer(25,this);
				
				// add animation managers to animationContainer
				for(JPanel frame : animationFrames){
						animationContainer.add(frame);
				}
				
				// add the container panels to the JFrame
				masterPanel.add(animationContainer,"ac");
				masterPanel.add(fmg,"gc");
				
				this.add(masterPanel);
				
				// add the menu bar
				this.setJMenuBar(mc);
				
				// start threads
				t.start();
				nb.sync();
				
				c1.show(masterPanel, "ac");
		}
		
		public static void main(String[] args){
				FactoryManager fm = new FactoryManager();
				
				// set frame properties
				fm.setSize(PAGE_WIDTH,PAGE_HEIGHT);
				fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fm.setTitle("FactoryManager");
				fm.setVisible(true);
		}
		
		public TreeMap<Integer, FactoryObject> getFrameData(int mNumber){
				return factoryAnimationData.get(mNumber);
		}
		
		public ImageArray getImageArray(){
				return images;
		}
		
		public void showAnimation(){
				c1.show(masterPanel,"ac");
		}
		
		public void showControls(){
				c1.show(masterPanel,"gc");
		}
		
		public void actionPerformed(ActionEvent ae){
				for(JPanel frame : animationFrames){
						frame.repaint();
				}
		}
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- Network Manager ---------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		// server specific
		public void registerClientListener(NetworkBridge newBridge, int cID){}
		public void syncFrame(){}
		
		
		// client specific
		public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray){
				if(mapArray.size() == 3){
						TreeMap<Integer, Boolean> changeMap;
						TreeMap<Integer, FactoryObject> changeData, currentFrameData;
						
						for(int i = 0; i < mapArray.size(); i++){
								changeMap = mapArray.get(i);
								changeData = dataArray.get(i);
								currentFrameData = factoryAnimationData.get(i);
								// iterate over all the keys present in changeMap
								// after this loop is complete, the frameAnimationData map will be accurately synced with the server copy
								for(Integer key : changeMap.keySet()){
										// check the write direction of the change map key
										if(changeMap.get(key)){
												// write new factory object to animation frame
												FactoryObject newAnimationData = changeData.get(key);
												currentFrameData.put(key,newAnimationData);
										}
										else{
												// delete the factory object from currentFrame
												currentFrameData.remove(key);
										}
								}
						}
				}
				else{
						System.out.println("Warning: Corrupt frame data");
				}
		}
		
		public void syncChanges(ArrayList<TreeMap<Integer,FactoryObject>> dataArray){
				if(dataArray.size() == 3){
						for(int i = 0; i < dataArray.size(); i++){
								factoryAnimationData.set(i, dataArray.get(i));
						}
				}
				else{
					System.out.println("Warning: Corrupt frame data");
				}
		}
		
		// global
		public void updatePartData(TreeMap<Integer, Parts> partData){}
		public void updateKitData(TreeMap<Integer, Kits> kitData){
				if(kitData != null)
						fmg.setKitData(kitData);
		}
		public void closeNetworkBridge(int bridgeID){
				nb.close();
		}
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- End Network Manager ------------------------------ //
		// -------------------------------------------------------------------------------------- //
		
}
