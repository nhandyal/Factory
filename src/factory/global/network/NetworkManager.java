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
		
		// client specific
		public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray);
		public void syncChanges(ArrayList<TreeMap<Integer, FactoryObject>> dataArray);
		
		// general
		public void closeNetworkBridge(int bridgeID);
		public void updatePartData(TreeMap<Integer, Parts> partData);
		public void updateKitData(TreeMap<Integer, Kits> kitData);
}