/*
** Author: 					Nikhil Handyal
** Date: 						11/11/12
** Project: 				Cs200-Factory
** Description: 		Network Instruction that is part of the network comm protocol.
** 									Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.network;

import java.io.*;

public class Instruction implements java.io.Serializable{
		String instruction;
		int x;
		int v;
		
		public Instruction(String i){
				instruction = i;
				x = -1;
				v = -1;
		}
		
		public Instruction(String i, int x){
				instruction = i;
				this.x = x;
				v = -1;
		}
		
		public Instruction(String i, int x, int v){
				instruction  = i;
				this.x = x;
				this.v = v;
		}
}