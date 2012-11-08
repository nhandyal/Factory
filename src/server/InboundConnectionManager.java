/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: Listens for inbound connections on the server socket and creates a new socket instance
** 
** Pre-Conditions: None
*/
package server;

import factory.global.network.*;
import java.net.*;

public class InboundConnectionManager extends Thread{
		Server server = null;
		ServerSocket ss = null;
		
		InboundConnectionManager(Server server){
				this.server = server;
				
				try{
						ss = new ServerSocket(8465);
				}catch(Exception e){
						System.out.println("Fatal Error: server initialization failed");
						e.printStackTrace();
						System.exit(1);
				}
		}
		
		public void run(){
				while(true){
						Socket s = null;
						try{
								s = ss.accept();
								NetworkBridge nb = new NetworkBridge(server, s);
						}catch(Exception e){
								System.out.println("Warning: There was an error accepting the client connection");
						}
				}
		}
}