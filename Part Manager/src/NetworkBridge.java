/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: Provides a simple to use asynchronous network communication protocol
** 
** 
*/

import java.io.*;
import java.net.*;

public class NetworkBridge{
		ObjectOutputStream oos;
		InputStreamListener isl;
		
		// Client Constructor
		NetworkBridge(Object parent, String remoteURL, int port){
				Socket s = null;
				
				try{
						s = new Socket(remoteURL,port);
				}catch(Exception e){
						System.out.println("Fuck");
						System.exit(1);
				}
				
				establishStreams(parent, s);
		}
		
		// Server Constructor
		NetworkBridge(Object parent, Socket s){
				establishStreams(parent, s);
		}
		
		void establishStreams(Object parent, Socket s){
				ObjectInputStream ois;
				
				try{
						oos = new ObjectOutputStream(s.getOutputStream());			// create output stream
						ois = new ObjectInputStream(s.getInputStream());				// create input stream - this will be passed to the threaded listener
						
						isl = new InputStreamListener(parent, ois);
						isl.start();
						
						System.out.println("OOS and OIS initialized. ISL started.");
				}catch(IOException i){
						i.printStackTrace();
						System.exit(0);
				}
		}

}

class InputStreamListener extends Thread{
		Object parent;
		ObjectInputStream ois;
		
		InputStreamListener(Object parent, ObjectInputStream ois){
				this.parent = parent;
				this.ois = ois;
		}
		
		public void run(){
				Instruction instr = null;
				
				// listen for a request from the client
				while(true){
						try{
								instr = (Instruction)ois.readObject();
								parseInstruction(instr);
						}catch(IOException i){
								i.printStackTrace();
						}catch(ClassNotFoundException c){
								c.printStackTrace();
						}
				}
		}
		
		void parseInstruction(Instruction instr){
				String i = instr.instruction;
		}
		
}
