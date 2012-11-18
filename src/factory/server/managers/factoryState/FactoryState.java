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
		ArrayList<Kits> factoryKits;

		public FactoryState(){}
		
		public void mergeParts(TreeMap<Integer, Parts> newParts){
				factoryPartData = newParts;
				for(Integer i : factoryPartData.keySet()){
						factoryPartData.get(i).print();
				}
				System.out.println();
				System.out.println();
		}
		
		public void mergeKits(ArrayList<Kits> newKits){
				System.out.println("mergre kits FS called");
				factoryKits = newKits;
				for(Kits current : factoryKits){
						current.print();
				}
				System.out.println("kits updated");
		}
		
		public TreeMap<Integer, Parts> getParts(){
				return factoryPartData;
		}
}

