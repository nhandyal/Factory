/*
** Author: 					Nikhil Handyal
** Date: 						10/32/12
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
		public String instruction;
		public String breakCommand;
		public int x;
		public int v;
		
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
		
		public Instruction(String i, String b, int x, int v){
				instruction  = i;
				breakCommand = b;
				this.x = x;
				this.v = v;
		}
		
		public void setBreak(String b){
				breakCommand = b;
		}
		
		public void setX(int x){
				this.x = x;
		}
		
		public void setV(int v){
				this.v = v;
		}
}