/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: Provides a simple to use asynchronous network communication protocol
**
** Pre-Conditions: None
*/

package factory.global.network;
import java.io.*;
import java.net.*;

public class NetworkBridge{
		ObjectOutputStream oos;
		InputStreamListener isl;
		int id = -1;
		Socket s = null;
		
		// ---------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------CONSTRUCTORS------------------------------------------------ //
		// ---------------------------------------------------------------------------------------------------------- //
		
		// Client Side Constructor
		public NetworkBridge(NetworkManager parent, String remoteURL, int port, int cID){
				try{
						s = new Socket(remoteURL,port);
				}catch(Exception e){
						System.out.println("Fatal Error: Server Connection Failed");
						System.exit(1);
				}
				
				establishStreams(parent, s);
				registerClient(cID);
		}
		
		// Server Side Constructor
		public NetworkBridge(NetworkManager parent, Socket s){
				this.s = s;
				establishStreams(parent, s);
		}
		
		// ---------------------------------------------------------------------------------------------------------- //
		// ----------------------------------------------PRIVATE METHODS--------------------------------------------- //
		// ---------------------------------------------------------------------------------------------------------- //
		
		void establishStreams(NetworkManager parent, Socket s){
				ObjectInputStream ois;
				
				try{
						s.setSoTimeout(500);
						
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
		
		public void writeData(Object data){
				try{
						oos.writeObject(data);
				}catch(IOException ie){
						ie.printStackTrace();
				}
		}
		
		public void setBridgeID(int id){
				this.id = id;
		}
		
		public int getBridgeID(){
				return id;
		}
		
		public void close(){
				try{
						s.close();
						isl.kill();
				}catch(IOException ie){
						ie.printStackTrace();
				}
		}
}

// ------------------------------------- END CLASS NETWORK BRIDGE ---------------------------------//

class InputStreamListener extends Thread{
		private NetworkManager parent;
		private NetworkBridge nb;
		private ObjectInputStream ois;
		private boolean killThread;
		
		
		InputStreamListener(NetworkManager parent, ObjectInputStream ois, NetworkBridge nb){
				this.parent = parent;
				this.ois = ois;
				this.nb = nb;
				killThread = false;
		}
		
		public void run(){
				Instruction instr = null;
				
				// listen for a request from the client
				while(true){
						if(killThread)
								return;
						
						try{
								instr = (Instruction)ois.readObject();
										parseInstruction(instr);
						}catch(IOException i){
								String message = i.toString();
								if(message.equals("java.io.EOFException")){
										System.out.println("Connection lost");
										parent.closeNetworkBridge(nb.getBridgeID());
								}
								else if(message.equals("java.net.SocketTimeoutException: Read timed out")){}
								else{
										i.printStackTrace();		
								}
						}catch(ClassNotFoundException c){
								c.printStackTrace();
						}
				}
		}
		
		public void kill(){
				killThread = true;
		}
		
		void parseInstruction(Instruction instr){
				String instruction = instr.instruction;
				if(instruction.equals("register-client")){
						int cID = instr.x;
						parent.registerClientListener(nb, cID);
						nb.setBridgeID(cID);
				}
		}
		
}
