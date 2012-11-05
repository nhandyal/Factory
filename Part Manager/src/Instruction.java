import java.io.*;

public class Instruction implements java.io.Serializable{
		String instruction;
		int x;
		int v;
		
		Instruction(String i){
				instruction = i;
				x = -1;
		}
		
		Instruction(String i, int z){
				instruction = i;
				x = z;
		}
		
		Instruction(String i, int z, int y){
				instruction  = i;
				x = z;
				v = y;
		}
}