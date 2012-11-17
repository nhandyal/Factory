package factory.client.kitManager;
import factory.global.data.*; 
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class KitManager extends JFrame implements ActionListener, ItemListener {

	JPanel kitPanel; 
	KitList kL; 
	KitInfo kI; 
	PartSelector pS; 
	Kits newKit; 
	TreeMap<Integer, Parts> currentList;
	ArrayList<Kits> listOfKits; 
	public KitManager(){
		
		kL = new KitList(); 
		kI = new KitInfo(); 
		pS = new PartSelector();
		kitPanel = new JPanel();
		kitPanel.setLayout(new BoxLayout(kitPanel, BoxLayout.X_AXIS));
		kitPanel.add(kL); 
		kitPanel.add(kI); 
		kitPanel.add(pS);

		 

		kL.getCreateKit().setActionCommand("Create"); 
		kL.getCreateKit().addActionListener(this); 


		pS.getBoxOfPart1().addItemListener(this); 

		currentList = new TreeMap<Integer, Parts>(); 
		listOfKits = new ArrayList<Kits>(); 
		add(kitPanel);
		
		
	}
	
	
	public static void main(String[] args) {
		KitManager thisKitManager = new KitManager(); 
		thisKitManager.setTitle("Kit Manager");
	    thisKitManager.setSize(750, 350);	
	    thisKitManager.setLocationRelativeTo(null);
	    thisKitManager.setVisible(true); 
	    thisKitManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
	
	
	public void actionPerformed(ActionEvent ae){
		if("Create".equals(ae.getActionCommand())){
			String kitIDString = kI.getKitIDNumber().getText();
			int kitIDNumber = 0; 
			try{
				kitIDNumber = Integer.parseInt(kitIDString);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				kI.getKitIDNumber().setText("ID");
			}
			newKit = new Kits(kI.getKitNameText().getText(), currentList, kI.getKitDescription().getText(), kitIDNumber); 
			listOfKits.add(newKit); 
			//send to server code inserted here

			kI.getKitNameText().setText("Name");
			kI.getKitDescription().setText("Brief Description of Kit");
			kI.getKitIDNumber().setText("ID");
		}
	}

	public void itemStateChanged(ItemEvent ie){
		if(ie.getStateChange() == ItemEvent.SELECTED){
			System.out.println("New Item Selected"); 

		}

	}
		
	
	

}
