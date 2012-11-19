package factory.client.kitManager;
import factory.global.data.*; 
import factory.global.network.*;
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeMap;


public class KitManagerV2 extends JFrame implements ActionListener{
		JPanel masterPanel, currentKits, createKit, addParts;
		TreeMap<Integer, Parts> currentList;
		NetworkBridge nb1;
		JTextField name, id, description;
		JButton create;
		ArrayList<Kits> factoryKits;
		ImageArray images;
		ImageIcon kitStruct;
		ButtonGroup[] buttonGroups;
		
		public KitManagerV2(){
				masterPanel = new JPanel();
				currentKits = new JPanel();
				createKit = new JPanel();
				addParts = new JPanel();
				images = new ImageArray();
				kitStruct = images.getIcon(17);
				create = new JButton("Create Kit");
				name = new JTextField("Kit Name");
				id = new JTextField("Kit ID");
				description = new JTextField("Kit Description");
				masterPanel.setLayout(new BoxLayout(masterPanel,BoxLayout.X_AXIS));
				buttonGroups = new ButtonGroup[8];
				
				
		}
		
		public static void main(String[] args) {
		KitManager thisKitManager = new KitManager(); 
		thisKitManager.setTitle("Kit Manager");
	    thisKitManager.setSize(750, 350);	
	    thisKitManager.setLocationRelativeTo(null);
	    thisKitManager.setVisible(true); 
	    thisKitManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
		
		public void buildKits(){
				JPanel newFrame = new JPanel();
				newFrame.setLayout(new BoxLayout(newFrame, BoxLayout.Y_AXIS));
				JLabel temp = new JLabel("Current Kits");
				newFrame.add(temp);
				for(Kits kits : factoryKits){
						JLabel temp2 = new JLabel(kits.getName());
						newFrame.add(temp2);
				}
				currentKits.add(newFrame);
				currentKits.revalidate();
				masterPanel.revalidate();
		}
		
		public void buildNewKit(){
				JPanel newFrame = new JPanel();
				JLabel temp = new JLabel(kitStruct);
				newFrame.setLayout(new BoxLayout(newFrame, BoxLayout.Y_AXIS));
				name.setText("Kit Name");
				id.setText("Kit ID");
				description.setText("Kit Description");
				newFrame.add(name);
				newFrame.add(id);
				newFrame.add(description);
				newFrame.add(temp);
				newFrame.add(create);
				createKit.add(newFrame);
				createKit.revalidate();
				masterPanel.revalidate();
		}
		
		public void buildPartSelector(){
				for(int i = 0; i < 8; i++){
						JPanel container = new JPanel();
						container.setLayout(new BorderLayout());
						JLabel temp1 = new JLabel("Part "+i);
						container.add(temp1, BorderLayout.WEST);
						buttonGroups[i] = new ButtonGroup();
						
						for(Integer i : currentList.keySet()){
								Parts part = currentList.get(i);
								String name = part.getName();
								JPanel holder = new JPanel();
								holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
								JButton 
						}
				}
		}
		
		public void actionPerformed(ActionEvent ae){}
}