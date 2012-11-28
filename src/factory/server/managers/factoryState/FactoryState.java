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
import java.util.*;
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
				FileInputStream fis = null;
				ObjectInputStream ois = null;
				ArrayList<Integer> index = new ArrayList<Integer>();
				ArrayList<Integer> removeIndex = new ArrayList<Integer>();
				try{
					fos = new FileOutputStream("Parts");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(newParts);
					oos.close();
					fos.close();
				}catch(Exception ex){
				}
				for(Integer i : factoryPartData.keySet()){
						index.add(factoryPartData.get(i).getImageIndex());
				}
				try{
					fis = new FileInputStream("Kits");
					ois = new ObjectInputStream(fis);
					factoryKits = (TreeMap<Integer, Kits>) ois.readObject();
					fis.close();
					fos.close();
				}catch(Exception ex){
				}
				for(Integer i : factoryKits.keySet()){
					TreeMap<Integer, Parts> q = factoryKits.get(i).getListOfParts();
					for (Integer j : q.keySet()){
						if (!index.contains(q.get(j).getImageIndex())){
							removeIndex.add(i);
							break;
						}
					}
				}
				for (Integer i : removeIndex)
					factoryKits.remove(i);
				try{
					fos = new FileOutputStream("Kits");
					oos = new ObjectOutputStream(fos);
					oos.writeObject(factoryKits);
					oos.close();
					fos.close();
				}catch(Exception ex){
				}
				factoryKits = null;
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

