/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: Server code
** 
** Pre-Conditions: None
*/
package server;

import factory.global.network.*;
import java.util.TreeMap;
import java.awt.event.*;

public class Server implements NetworkManager{
		TreeMap<Integer, NetworkBridge> clientConnections = new TreeMap<Integer, NetworkBridge>();
		InboundConnectionManager icm = null;
		
		Server(){
				icm = new InboundConnectionManager(this);
				icm.start();
		}
		
		public static void main(String[] args){
				Server si = new Server();
		}
		
		public void registerClientListener(NetworkBridge newBridge, int cID){
				clientConnections.put(cID, newBridge);
		}
		
		public void closeNetworkConnection(){}
		
		public void closeNetworkBridge(int bridgeID){
				NetworkBridge nb = clientConnections.get(bridgeID);
				nb.close();
				clientConnections.remove(bridgeID);
		}	
}