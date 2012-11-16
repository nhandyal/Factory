/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: test client code
** 
** Pre-Conditions: None
*/

package factory.client;

// Java packages
import java.util.TreeMap;
import java.util.ArrayList;

// user packages
import factory.global.network.*;
import factory.global.data.*;

public class Client implements NetworkManager{
		NetworkBridge nb1;
		TreeMap<Integer, FactoryObject> frameAnimationData;
		
		Client(){
				nb1 = new NetworkBridge(this,"localhost",8465,0);
				frameAnimationData = new TreeMap<Integer, FactoryObject>();
		}
		
		public static void main(String[] args){
				Client c = new Client();
		}
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- Network Manager ---------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		// server specific
		public void registerClientListener(NetworkBridge newBridge, int cID){}
		public void syncFrame(int cID){}
		
		
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
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- End Network Manager ------------------------------ //
		// -------------------------------------------------------------------------------------- //
}