/*
** Author: Nikhil Handyal
** Date: 10/31/12
** Project: Cs200-Factory
** Description: test client code
** 
** Pre-Conditions: None
*/

public class Client{
		NetworkBridge nb1;
		
		Client(){
				nb1 = new NetworkBridge(this,"localhost",8465);
		}
		
		public static void main(String[] args){
				Client c = new Client();
		}
}