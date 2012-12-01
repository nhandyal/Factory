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
		ArrayList<Kits> factoryBuildData;

		public FactoryState(){}
		
		public void mergeParts(TreeMap<Integer, Parts> newParts){
				factoryPartData = newParts;
		}
		
		public void mergeKits(TreeMap<Integer, Kits> newKits){
				factoryKits = newKits;
		}
		
		public void mergeBuildData(ArrayList<Kits> newBuildData){
				factoryBuildData = newBuildData;
		}
		
		public TreeMap<Integer, Parts> getParts(){
				return factoryPartData;
		}
		
		public TreeMap<Integer, Kits> getKits(){
				return factoryKits;
		}
		
		public ArrayList<Kits> getBuildData(){
				return factoryBuildData;
		}
}

