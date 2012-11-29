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
import java.io.*;

// user packages
import factory.global.data.*;

public class FactoryState{
		TreeMap<Integer, Parts> factoryPartData;
		TreeMap<Integer, Kits> factoryKits;

		public FactoryState(){}
		
		public void mergeParts(TreeMap<Integer, Parts> newParts){
				factoryPartData = newParts;
				FileOutputStream fos = null;
				ObjectOutputStream oos = null;
				try{
					fos = new FileOutputStream("Parts");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(newParts);
					oos.close();
					fos.close();
				}catch(Exception ex){
				}
				for(Integer i : factoryPartData.keySet()){
						factoryPartData.get(i).print();
				}
				System.out.println();
				System.out.println();
		}
		
		public void mergeKits(TreeMap<Integer, Kits> newKits){
				System.out.println("mergre kits FS called");
				factoryKits = newKits;
				FileOutputStream fos = null;
				ObjectOutputStream oos = null;
				try{
					fos = new FileOutputStream("Kits");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(newKits);
					oos.close();
					fos.close();
				}catch(Exception ex){
				}
				for(Integer i : factoryKits.keySet()){
						factoryKits.get(i).print();
				}
				System.out.println("kits updated");
		}
		
		public TreeMap<Integer, Parts> getParts(){
				return factoryPartData;
		}
}

