/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: Server code
** 
** Pre-Conditions: None
*/


import java.util.TreeMap;

public class Server implements NetworkManager{
		TreeMap<Integer, NetworkBridge> clientConnections = new TreeMap<Integer, NetworkBridge>();
		InboundConnectionManager icm = null;
		
		Server(){
				icm = new InboundConnectionManager(this);
		}
		
		public static void main(String[] args){
				Server si = new Server();
		}
		
		public void registerClientListener(NetworkBridge newBridge, int cID){
				clientConnections.put(cID, newBridge);
		}
}