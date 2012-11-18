/*
** Authors:			Timi Okuboyejo
** Date: 			11/14/12
** Project: 		Cs200-Factory
** Description: 	Compares two instances of a factory object to see if it moved
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
//package factory.global.data;

// Java packages
import java.io.*;
import java.util.*;

public class FOComparator implements Serializable, Comparator<FactoryObject>{
	
	@Override
    public int compare(FactoryObject x, FactoryObject y){
    	if (x.getPositionX() == y.getPositionX() && x.getPositionY() == y.getPositionY())
    		return 0;
    	else
    		return 1;
    } //end function
} //end class
