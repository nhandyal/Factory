/*
** Authors: Nikhil Handyal
** Date: 11/07/12
** Project: Cs200-Factory
** Description: Factory State Class
** 
** Pre-Conditions: None
*/

package factory.server.managers.factoryState;

// Java Packages
import java.util.TreeMap;
import java.util.ArrayList;

// user packages
import factory.global.data.*;

public class FactoryState{
		TreeMap<Integer, Parts> factoryPartData;

		public FactoryState(){
				factoryPartData = new TreeMap<Integer, Parts>();
		}
		
		public void mergeParts(TreeMap<Integer, Parts> newParts){
				factoryPartData.putAll(newParts);
		}
		
		public TreeMap<Integer, Parts> getParts(){
				return factoryPartData;
		}
}

