/*
** Author: Nikhil Handyal, Siddarth Ramesh
** Date: 11/26/12
** Project: Cs200-Factory
** Description: Kit Manager Code
** 
** Pre-Conditions: None
*/
package factory.client.kitManager;

// Java packages
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeMap;


// user packages
import factory.global.data.*; 
import factory.global.network.*;


public class KitManagerV2 extends JFrame implements ActionListener, ListSelectionListener{
		private static final int PAGE_WIDTH = 650;
		private static final int PAGE_HEIGHT = 600;
		private CardLayout c1;
		private JPanel activeKitsPanel, kitDataPanel, kitStructPanel, partsSelectPanel;
		private JPanel activeKitsContainer, createKitContainer, masterContainer;
		private DefaultListModel listModel;
		private JList kitList;
		private JButton createNewKit, saveNewKit, deletePart;
		private JTextField kitName, kitID;
		private JTextArea kitDesc;
		private TreeMap<Integer, Parts> parts;
		private ImageArray images;
		private Border greyLine;
		private boolean toggle;
		private ButtonGroup[] selectedParts;
		
		KitManagerV2(){
				// initialize class variables
				masterContainer = new JPanel();
				activeKitsContainer = new JPanel();
				createKitContainer = new JPanel();
				activeKitsPanel = new JPanel();
				kitStructPanel = new JPanel();
				partsSelectPanel = new JPanel();
				selectedParts = new ButtonGroup[10];
				parts = buildParts();
				images = new ImageArray();
				greyLine = BorderFactory.createLineBorder(Color.DARK_GRAY);
				c1 = new CardLayout();
				
				
				// set Frame and properties
				masterContainer.setLayout(c1);
				
				
				// build Active Kits Container
				activeKitsContainer.setLayout(new BoxLayout(activeKitsContainer, BoxLayout.X_AXIS));
				setComponentSize(activeKitsContainer,PAGE_WIDTH,PAGE_HEIGHT);
				buildActiveKits(activeKitsPanel);
				activeKitsContainer.add(activeKitsPanel);
				
				
				// build Create Kit Container
				createKitContainer.setLayout(new BoxLayout(createKitContainer, BoxLayout.X_AXIS));
				setComponentSize(createKitContainer,PAGE_WIDTH,PAGE_HEIGHT);
				setComponentSize(partsSelectPanel, 450, PAGE_HEIGHT);
				buildKitStruct(kitStructPanel);
				buildPartsSelect();
				createKitContainer.add(kitStructPanel);
				createKitContainer.add(partsSelectPanel);
				
				// add master containers to frame
				masterContainer.add(activeKitsContainer,"akc");
				masterContainer.add(createKitContainer,"ckc");
				this.add(masterContainer);
				c1.show(masterContainer,"ckc");
				
		}
		
		public static void main(String[] args){
				KitManagerV2 km = new KitManagerV2();
				
				// set frame properties
				km.setSize(PAGE_WIDTH,PAGE_HEIGHT);
				km.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				km.setTitle("Kit Manager");
				km.setResizable(false);
				km.setVisible(true);
		}
		
		public void valueChanged(ListSelectionEvent le){}
		public void actionPerformed(ActionEvent ae){
				if(ae.getSource() == deletePart){
						
				}
				else{
						if(toggle){
								c1.show(masterContainer,"akc");
						}
						else{
								c1.show(masterContainer,"ckc");
						}
						toggle = !toggle;
				}
		}
		
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
				
				
				createNewKit = new JButton("Create New Kit");
				createNewKit.addActionListener(this);
				setComponentSize(createNewKit,WIDTH,50);
				
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
				container.add(createNewKit);
		}
		
		private void buildKitStruct(JPanel container){		
				// initialize variables
				final int WIDTH = 200;
				final int ST_SPACE = 25;
				kitName = new JTextField();
				kitID = new JTextField();
				kitDesc = new JTextArea();
				saveNewKit = new JButton("Save Kit");
				deletePart = new JButton("Delete Part");
				JLabel kitLayout = new JLabel(images.getIcon(17));
				kitDesc.setBorder(greyLine);
				saveNewKit.addActionListener(this);
				deletePart.addActionListener(this);
				Box box = Box.createVerticalBox();
				
				// set container properties
				setComponentSize(container,WIDTH,600);
				
				// set field and button properties
				setComponentSize(kitName,WIDTH,25);
				setComponentSize(kitID,WIDTH,25);
				setComponentSize(kitDesc,WIDTH,100);
				setComponentSize(saveNewKit,WIDTH,50);
				setComponentSize(deletePart,WIDTH,50);
				
				resetKitStruct();
				
				// add elements to container
				box.add(kitName);
				box.add(Box.createVerticalStrut(ST_SPACE));
				box.add(kitID);
				box.add(Box.createVerticalStrut(ST_SPACE));
				box.add(kitDesc);
				box.add(Box.createVerticalStrut(ST_SPACE));
				box.add(kitLayout);
				box.add(Box.createVerticalStrut(ST_SPACE));
				box.add(saveNewKit);
				box.add(Box.createVerticalStrut(ST_SPACE));
				box.add(deletePart);
				container.add(box);
				//container.setBorder(blueLine);
		}
		
		private void buildPartsSelect(){
				final int H_WIDTH = 450;
				final int H_HEIGHT = 60;
				//final int n = parts.size()+1;
				int n = 10;
				Box box = Box.createVerticalBox();
				partsSelectPanel.removeAll();
				
				
				for(int i = 0; i < 8; i++){
						JPanel holder = new JPanel();
						holder.setLayout(new BorderLayout());
						setComponentSize(holder, H_WIDTH, H_HEIGHT);
						JLabel partLabel = new JLabel("   Part "+(i+1)+":   ");
						
						// build parts selector
						JPanel PSelectorContainer = new JPanel();
						PSelectorContainer.setLayout(new GridLayout(1,n));
						selectedParts[i] = new ButtonGroup();
						for(int j = 0; j < n; j++){
								Box box2 = Box.createVerticalBox();
								JLabel temp = new JLabel("  ",images.getIcon(j), SwingConstants.CENTER);
								temp.setHorizontalTextPosition(SwingConstants.LEFT);
								JRadioButton partButton = new JRadioButton();
								selectedParts[i].add(partButton);
								box2.add(temp);
								box2.add(partButton);
								PSelectorContainer.add(box2);
						}
						holder.add(partLabel, BorderLayout.WEST);
						holder.add(PSelectorContainer, BorderLayout.CENTER);
						partsSelectPanel.add(holder);
				}
				partsSelectPanel.revalidate();
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
		
		private void resetKitStruct(){
				kitName.setText("Enter Kit Name");
				kitID.setText("Enter Kit ID");
				kitDesc.setText("Enter a short kit description");
		}
		
		private TreeMap<Integer, Parts> buildParts(){
				TreeMap<Integer, Parts> partsMap = new TreeMap<Integer, Parts>();
				for(int i = 0; i < 5; i++){
						Parts temp = new Parts(i,"Test Part: "+i, "Test Part "+i+" description", i);
						temp.setMapIndex(i);
						partsMap.put(i,temp);
				}
				return partsMap;
		}
}