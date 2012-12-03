package factory.global.data;
import java.io.*;
import javax.swing.*;
import java.util.*; 
public class Kits implements java.io.Serializable{

	String name; 
	TreeMap<Integer, Parts> listOfParts; 
	String description; 
	int kitID;
	int MI;
	int buildNumber;
	public Kits(String name, TreeMap<Integer, Parts> listOfParts,String description, int kitID, int MI){
		this.name = name; 
		this.listOfParts = listOfParts; 
		this.description = description; 
		this.kitID = kitID;
		this.MI = MI;
		this.buildNumber = 0;
	}
	
	public String getName(){
		return name;
	}

	public TreeMap<Integer, Parts> getListOfParts(){
		return listOfParts;
	}

	public void setBuildNumber(int buildNumber){
		this.buildNumber = buildNumber;
	}
	
	public int getBuildNumber(){
		return buildNumber;
	}
	
	public String getDescription(){
		return description; 
	}

	public int getKitID(){
		return kitID;
	}
	
	public int getMapIndex(){
		return MI;
	}
	
	
	
	public void print(){
		System.out.print("name: "+name+" description: "+description+" kitid: "+kitID);
	}
	
	@Override public String toString() {
		return("Kit # "+kitID+" - "+name);
	}

}