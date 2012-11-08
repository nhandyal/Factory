/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: test client code
** 
** Pre-Conditions: None
*/

package client;

import factory.global.network.*;

public class Client implements NetworkManager{
		NetworkBridge nb1;
		
		Client(){
				nb1 = new NetworkBridge(this,"localhost",8465,0);
		}
		
		public static void main(String[] args){
				Client c = new Client();
		}
		
		public void registerClientListener(NetworkBridge newBridge, int cID){}
		
		public void closeNetworkBridge(int bridgeID){
				nb1.close();
		}
}