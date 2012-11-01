/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: Server code
** 
** Pre-Conditions: None
*/


import java.util.ArrayList;

public class Server{
		ArrayList<NetworkBridge> clientConnections = new ArrayList<NetworkBridge>();
		InboundConnectionManager icm = null;
		
		Server(){
				icm = new InboundConnectionManager(this);
		}
		
		public static void main(String[] args){
				Server si = new Server();
		}
		
		void addNetworkBridge(NetworkBridge newBridge){
				clientConnections.add(newBridge);
		}
}