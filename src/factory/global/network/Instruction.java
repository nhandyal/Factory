package factory.global.network;

import java.io.*;

public class Instruction implements java.io.Serializable{
		String instruction;
		int x;
		int v;
		
		public Instruction(String i){
				instruction = i;
				x = -1;
		}
		
		public Instruction(String i, int z){
				instruction = i;
				x = z;
		}
		
		public Instruction(String i, int z, int y){
				instruction  = i;
				x = z;
				v = y;
		}
}