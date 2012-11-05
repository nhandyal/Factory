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
		
		// ---------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------CONSTRUCTORS------------------------------------------------ //
		// ---------------------------------------------------------------------------------------------------------- //
		
		// Client Constructor
		NetworkBridge(NetworkManager parent, String remoteURL, int port, int cID){
				Socket s = null;
				
				try{
						s = new Socket(remoteURL,port);
				}catch(Exception e){
						System.out.println("Fuck");
						System.exit(1);
				}
				
				establishStreams(parent, s);
				registerClient(cID);
		}
		
		// Server Constructor
		NetworkBridge(NetworkManager parent, Socket s){
				establishStreams(parent, s);
		}
		
		// ---------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------PRIVATE METHODS--------------------------------------------- //
		// ---------------------------------------------------------------------------------------------------------- //
		
		void establishStreams(NetworkManager parent, Socket s){
				ObjectInputStream ois;
				
				try{
						oos = new ObjectOutputStream(s.getOutputStream());			// create output stream
						ois = new ObjectInputStream(s.getInputStream());				// create input stream - this will be passed to the threaded listener
						
						isl = new InputStreamListener(parent, ois, this);
						isl.start();
						
						System.out.println("OOS and OIS initialized. ISL started.");
				}catch(IOException i){
						i.printStackTrace();
						System.exit(0);
				}
		}
		
		void registerClient(int cID){
				try{
						Instruction instr = new Instruction("register-client",cID);
						oos.writeObject(instr);
				}catch(IOException ie){
						ie.printStackTrace();
				}
		}
		
		// ---------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------PUBLIC METHODS---------------------------------------------- //
		// ---------------------------------------------------------------------------------------------------------- //
		
		
}

class InputStreamListener extends Thread{
		NetworkManager parent;
		NetworkBridge nb;
		ObjectInputStream ois;
		
		InputStreamListener(NetworkManager parent, ObjectInputStream ois, NetworkBridge nb){
				this.parent = parent;
				this.ois = ois;
				this.nb = nb;
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
				String instruction = instr.instruction;
				if(instruction.equals("register-client")){
						int cID = instr.x;
						parent.registerClientListener(nb, cID);
				}
		}
		
}
