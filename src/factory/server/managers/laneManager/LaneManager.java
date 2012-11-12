/*
** Authors: David Cox, Timi Okuboyejo
** Date: 11/07/12
** Project: Cs200-Factory
** Description: Gui Lane Manager class
** 
** Pre-Conditions: None
*/
package factory.server.managers.laneManager;

// Java packages
import java.util.TreeMap;

// user packages
import factory.global.data.*;
import factory.server.managers.GuiManager;

public class LaneManager implements GuiManager{
		int z;
		
		// constructor
		public LaneManager(){}
		
		// -------------------------------------------------------------------------------------- //
		// ------------------------------------- GuiManager ------------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		public void update(TreeMap<Integer, Boolean> changeMap, TreeMap<Integer, FactoryObject> changeData){}
}

