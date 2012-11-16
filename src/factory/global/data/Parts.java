/*
** Authors: David Cox, Timi Okuboyejo, Nikhil Handyal
** Date: 11/07/12
** Project: Cs200-Factory
** Description: Parts Class
** 
** Pre-Conditions: None
*/

//package factory.global.data;

// Java imports
package factory.global.data;
import java.io.*;
import javax.swing.*;

public class Parts implements java.io.Serializable{
		int partNumber;
		String name;
		String desc;
		int imageIndex; 
		
		public Parts(int partNumber, String name, String desc, int imageIndex){
			this.partNumber = partNumber; 
			this.name = name; 
			this.desc = desc; 
			this.imageIndex = imageIndex; 
		}
}

		