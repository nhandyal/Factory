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
import factory.global.data.*;

public class FactoryManagerGUI extends JPanel implements ActionListener{
		FactoryManager parent;
		CardLayout c1;
		ArrayList<Kits> factoryKits;
		ArrayList<JTextField> buildInfo;
		ImageArray images;
		
		FactoryManagerGUI(FactoryManager parent){
				c1 = new CardLayout();
				this.parent = parent;
				this.setLayout(c1);
				images = parent.getImageArray();
				buildMasterContainer();
		}
		
		public void mergeKits(ArrayList<Kits> newKits){
				factoryKits = newKits;
		}
				
		public void actionPerformed(ActionEvent ae){}
		
		private void initializeMasterContainer(JPanel masterContainer){
				masterContainer.setLayout(new BoxLayout(masterContainer,BoxLayout.Y_AXIS));
		}
		
		private void buildMasterContainer(){
				if(factoryKits == null)
						return;
				
				JPanel masterContainer = new JPanel();
				initializeMasterContainer(masterContainer);
				
				for(Kits currentKit : factoryKits){
						JPanel kitContainer = new JPanel();
						JPanel kitOrders = new JPanel();
						JPanel kitData = new JPanel();
						TreeMap<Integer, Parts> kitParts = currentKit.getListOfParts();
						
						// set panel properties
						kitContainer.setLayout(new BorderLayout());
						kitOrders.setLayout(new GridLayout(4,1));
						kitData.setLayout(new GridLayout(kitParts.size(), 4));
						
						// build Kit Orders
						JLabel kid = new JLabel("ID: "+currentKit.getKitID());
						JLabel kname = new JLabel("Name: "+currentKit.getName());
						JLabel kdesc = new JLabel("Desc: "+currentKit.getDescription());
						JTextField buildAmount = new JTextField("# To Build");
						buildInfo.add(buildAmount);
						// add to kitOrders
						kitOrders.add(kid);
						kitOrders.add(kname);
						kitOrders.add(kdesc);
						kitOrders.add(buildAmount);
						
						// build kitData
						for(Integer pID : kitParts.keySet()){
								Parts currentPart = kitParts.get(pID);
								JLabel pid = new JLabel("ID: "+currentPart.getPartNumber());
								JLabel pname = new JLabel("Name: "+currentPart.getName());
								JLabel pdesc = new JLabel("desc: "+currentPart.getDesc());
								JLabel icon = new JLabel(images.getIcon(currentPart.getImageIndex()));
								// add to partData
								kitData.add(pid);
								kitData.add(pname);
								kitData.add(pdesc);
								kitData.add(icon);
						}
						
						// add kitOrders and kitData to kitContainer
						kitContainer.add(kitOrders, BorderLayout.WEST);
						kitContainer.add(kitData, BorderLayout.CENTER);
						masterContainer.add(kitContainer);
				}
		}
}