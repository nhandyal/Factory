/*
** Author: Nikhil Handyal
** Date: 11/17/12
** Project: Cs200-Factory
** Description: Helper class for factory manager that will allow user controls. Does not contain non-normative controls
** 
** Pre-Conditions: None
*/

package factory.client.factoryManager;

// Java classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeMap;

// import user classes

public class FactoryManagerGUI extends JPanel implements ActionListener{
		JPanel masterContainer;
		FactoryManager parent;
		CardLayout c1;
		TreeMap<Integer, Parts> factoryParts;
		ArrayList<Kits> factoryKits;
		
		FactoryManagerGUI(FactoryManager parent){
				// set panel properties
				this.parent = parent;
				masterContainer.setLayout
		}
		
		public void mergeParts(TreeMap<Integre, Parts> newParts){
				factoryParts = newParts;
		}
		
		public void mergeKits(ArrayList<Kits> newKits){
				factoryKits = newKits;
		}
		
		
		
		public void actionPerformed(ActionEvent ae){}
}