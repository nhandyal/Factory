/*
** Author: 					Nikhil Handyal
** Date: 						11/11/12
** Project: 				Cs200-Factory
** Description: 		Network Transfer Object that encapsulates animation changeMap and changeData
** 									Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;
import java.util.TreeMap;

// user packages
import factory.global.data.*;

public class NetworkTransferObject implements java.io.Serializable{
		TreeMap<Integer, Boolean> changeMap;
		TreeMap<Integer, FactoryObject> changeData;
		
		public NetworkTransferObject(TreeMap<Integer, Boolean> changeMap, TreeMap<Integer, FactoryObject> changeData){
				this.changeMap = changeMap;
				this.changeData = changeData;
		}
}