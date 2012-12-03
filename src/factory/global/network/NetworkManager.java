/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: Network Manager Interface
** 
** Pre-Conditions: None
*/

package factory.global.network;

// Java classes
import java.util.TreeMap;
import java.util.ArrayList;

// user classes
import factory.global.data.*;

public interface NetworkManager{
		// server specific
		public void registerClientListener(NetworkBridge newBridge, int cID);
		public void syncFrame();
		public void updateBuildData(ArrayList<Kits> buildData);
		public void updateBreakData(String breakCommand, int cID, int x);
		
		// client specific
		public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray);
		public void syncChanges(ArrayList<TreeMap<Integer, FactoryObject>> dataArray);
		
		// general
		public void closeNetworkBridge(int bridgeID);
		public void updatePartData(TreeMap<Integer, Parts> partData);
		public void updateKitData(TreeMap<Integer, Kits> kitData);
}