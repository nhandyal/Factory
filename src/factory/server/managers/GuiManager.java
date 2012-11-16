/*
** Author: 					Nikhil Handyal
** Date: 						11/11/12
** Project: 				Cs200-Factory
** Description: 		GuiManager interface that enforces the incramental update policy used on the server
** 
** Pre-Conditions: 	None
** Post-Conditions: changeMap is a map that indicated the write direction of the corresponding FO in changeData.
** 									changeData contains updated FO objects reflecting the current state of the factory.
*/

package factory.server.managers;

// Java packages
import java.util.TreeMap;

// user packages
import factory.global.data.*;

public interface GuiManager{
		public void update(TreeMap<Integer, Boolean> changeMap, TreeMap<Integer, FactoryObject> changeData);
		public void sync(TreeMap<Integer, FactoryObject> changeData);
}