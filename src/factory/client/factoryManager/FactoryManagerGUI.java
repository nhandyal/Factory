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
import javax.swing.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Enumeration;

// import user classes
import factory.global.data.*;

public class FactoryManagerGUI extends JPanel implements ActionListener, ListSelectionListener{
		private FactoryManager parent;
		private JPanel activeKitsPanel, kitDataPanel;
		private JPanel activeKitsContainer;
		private DefaultListModel listModel;
		private JList kitList;
		private TreeMap<Integer, Kits> kits;
		private ImageArray images;
		private Border greyLine;
		private int PAGE_WIDTH, PAGE_HEIGHT;
		
		FactoryManagerGUI(FactoryManager parent, int pWidth, int pHeight){
				this.parent = parent;
				this.PAGE_WIDTH = pWidth;
				this.PAGE_HEIGHT = pHeight;
				
				// initialize class variables
				activeKitsContainer = new JPanel();				
				activeKitsPanel = new JPanel();
				kitDataPanel = new JPanel();
				kits = new TreeMap<Integer, Kits>();
				images = parent.getImageArray();
				greyLine = BorderFactory.createLineBorder(Color.DARK_GRAY);

				
				// build Active Kits Container
				activeKitsContainer.setLayout(new BoxLayout(activeKitsContainer, BoxLayout.X_AXIS));
				setComponentSize(activeKitsContainer,PAGE_WIDTH,PAGE_HEIGHT);
				setComponentSize(kitDataPanel,450,PAGE_HEIGHT);
				buildActiveKits(activeKitsPanel);
				activeKitsContainer.add(activeKitsPanel);
				activeKitsContainer.add(kitDataPanel);
				
				
				// add master containers to frame
				this.add(activeKitsContainer);
		}
		
		public void valueChanged(ListSelectionEvent le){
				if(!le.getValueIsAdjusting()){
						Kits currentKit = (Kits)kitList.getSelectedValue();
						if(currentKit != null)
								buildKitData(currentKit);
				}
		}
		
		public void actionPerformed(ActionEvent ae){}
		
		// -------------------------------------------------------------------------------------- //
		// ---------------------------------- Constructor Helpers ------------------------------- //
		// -------------------------------------------------------------------------------------- //
		private void buildActiveKits(JPanel container){
				// initialize variable
				final int WIDTH = 150;
				
				// set containment panel properties
				container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
				setComponentSize(container,150,PAGE_HEIGHT);
				
				
				JLabel header = new JLabel("Active Kits");
				header.setHorizontalAlignment(header.CENTER);
				header.setFont(new Font("Serif", Font.BOLD, 18));
				setComponentSize(header,WIDTH,25);

				
				// create list model and list
				listModel = new DefaultListModel();
				kitList = new JList(listModel);
				kitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        kitList.addListSelectionListener(this);
				kitList.setFixedCellHeight(25);
        JScrollPane listScrollPane = new JScrollPane(kitList);
				
				// add elements to containment panel
				container.add(header);
				container.add(listScrollPane);
		}
		
		private void populateActiveKitList(){
				// populate the active kits list with the current kits available in the factory
				listModel.removeAllElements();
				for(Integer i : kits.keySet()){
						Kits currentKit = kits.get(i);
						listModel.addElement(currentKit);
				}
		}
		
		private void buildKitData(Kits selectedKit){
				final int ST_SPACE = 25;
				kitDataPanel.removeAll();
				Box container = Box.createVerticalBox();
				setComponentSize(container, 400, PAGE_HEIGHT);
				JLabel kitID = new JLabel(Integer.toString(selectedKit.getKitID()));
				JLabel kitName = new JLabel(selectedKit.getName());
				JLabel kitDesc = new JLabel(selectedKit.getDescription());
				JLabel lp = new JLabel("----- Listed Parts -----");
				
				// align elements to the left collum
				// all the arguments can be left as KitID.LEFT because we only need any instance of a JComponent to use the LEFT keyword
				kitID.setHorizontalAlignment(kitID.LEFT);
				kitName.setHorizontalAlignment(kitID.LEFT);
				kitDesc.setHorizontalAlignment(kitID.LEFT);
				lp.setHorizontalAlignment(kitID.LEFT);
				
				// add elements to container
				container.add(kitID);
				container.add(kitName);
				container.add(kitDesc);
				container.add(Box.createVerticalStrut(ST_SPACE));
				container.add(lp);
				
				
				// add part data
				TreeMap<Integer, Parts> kitParts = selectedKit.getListOfParts();
				for(Integer i : kitParts.keySet()){
						Parts selectedPart = kitParts.get(i);
						Box holder = Box.createHorizontalBox();
						Box section1 = Box.createVerticalBox();
						Box section2 = Box.createVerticalBox();
						int imageIndex = selectedPart.getImageIndex();
						String PID = Integer.toString(selectedPart.getPartNumber());
						String PName = selectedPart.getName();
						String PDesc = selectedPart.getDesc();
						
						setComponentSize(holder, 400, 60);
						
						// add elements to section1
						section1.add(new JLabel("Part "+i));
						section1.add(new JLabel(images.getIcon(imageIndex)));
						
						// add elements to section2
						section2.add(new JLabel("Part #: "+PID));
						section2.add(new JLabel("Part name: "+PName));
						section2.add(new JLabel("Part desc: "+PDesc));
						
						// add sections to holder
						holder.add(section1);
						holder.add(Box.createHorizontalStrut(ST_SPACE));
						holder.add(section2);
						
						// add holder to container
						container.add(holder);
				}
				
				// add container to kitDataPanel
				kitDataPanel.add(container);
				kitDataPanel.revalidate();
		}
		
		// -------------------------------------------------------------------------------------- //
		// ---------------------------------------------- Helpers ------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		private void setComponentSize(JComponent component, int w, int h){
				component.setMinimumSize(new Dimension(w,h));
				component.setMaximumSize(new Dimension(w,h));
				component.setPreferredSize(new Dimension(w,h));
				component.setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		
		public void setKitData(TreeMap<Integer, Kits> newKits){
				newKits = kits;
				populateActiveKitList();
		}
}