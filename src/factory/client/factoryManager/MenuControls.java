/*
** Author: Nikhil Handyal
** Date: 11/30/12
** Project: Cs200-Factory
** Description: Menu Controls for factory manager
** 
** Pre-Conditions: None
*/
package factory.client.factoryManager;

// Java Packages
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MenuControls extends JMenuBar implements ActionListener{
		private FactoryManager parent;
		private JMenuItem configure, observe;
		
		MenuControls(FactoryManager parent){
				// initialize class variables
				this.parent = parent;
				
				// create the menu bar
				JMenu view = new JMenu("View");
				configure = new JMenuItem("Configure");
				observe = new JMenuItem("Observe");
				
				// add action listeners
				configure.addActionListener(this);
				observe.addActionListener(this);
				
				view.add(configure);
				view.add(observe);
				
				this.add(view);
		}
		
		public void actionPerformed(ActionEvent ae){
				if(ae.getSource() == configure){
						parent.showControls();
				}
				else if(ae.getSource() == observe){
						parent.showAnimation();
				}
		}
		
}