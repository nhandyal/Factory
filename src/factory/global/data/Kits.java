package factory.global.data;
import java.io.*;
import javax.swing.*;
import java.util.*; 
public class Kits implements java.io.Serializable{

	String name; 
	TreeMap<Integer, Parts> listOfParts; 
	String description; 
	int kitID; 
	public Kits(String name, TreeMap<Integer, Parts> listOfParts,String description, int kitID){
		this.name = name; 
		this.listOfParts = listOfParts; 
		this.description = description; 
		this.kitID = kitID; 
	}
	
	public String getName(){
		return name;
	}

	public TreeMap<Integer, Parts> getListOfParts(){
		return listOfParts;
	}

	public String getDescription(){
		return description; 
	}

	public int getKitID(){
		return kitID;
	}
	
	public void print(){
		System.out.print("name: "+name+" description: "+description+" kitid: "+kitID);
	}

}