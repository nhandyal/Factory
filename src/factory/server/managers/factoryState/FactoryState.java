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
				System.out.println("merge parts called");
				factoryPartData = newParts;
				System.out.println("parts updated");
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

