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
	TreeMap<Integer, Parts> finalizedList; 
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
		pS.getBoxOfPart2().addItemListener(this); 
		pS.getBoxOfPart3().addItemListener(this); 
		pS.getBoxOfPart4().addItemListener(this); 
		pS.getBoxOfPart5().addItemListener(this); 
		pS.getBoxOfPart6().addItemListener(this); 
		pS.getBoxOfPart7().addItemListener(this); 
		pS.getBoxOfPart7().addItemListener(this); 

		currentList = new TreeMap<Integer, Parts>(); 		
		finalizedList = new TreeMap<Integer, Parts>();
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
			newKit = new Kits(kI.getKitNameText().getText(), finalizedList, kI.getKitDescription().getText(), kitIDNumber); 
			listOfKits.add(newKit); 
			//send to server code inserted here

			kI.getKitNameText().setText("Name");
			kI.getKitDescription().setText("Brief Description of Kit");
			kI.getKitIDNumber().setText("ID");
		}
	}

	public void itemStateChanged(ItemEvent ie){
		if(ie.getStateChange() == ItemEvent.SELECTED){
			
			 for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart1().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(0, newPart); 			 		 
			 	}
			 }



			for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart2().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(1, newPart); 			 		  
			 	}
			 }


			 for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart3().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(2, newPart); 			 		 
			 	}
			 }


			 for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart4().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(3, newPart); 			 		 
			 	}
			 }

			 for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart5().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(4, newPart); 			 		 
			 	}
			 }

			  for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart6().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(5, newPart); 			 		 
			 	}
			 }

			 for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart7().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(6, newPart); 			 		 
			 	}
			 }

			 for(int searchForRightPart=0; searchForRightPart<currentList.size(); searchForRightPart++){
			 	if(pS.getBoxOfPart8().getSelectedItem().toString()==currentList.get(searchForRightPart).getName()){
			 		 Parts newPart = new Parts(currentList.get(searchForRightPart).getPartNumber(), currentList.get(searchForRightPart).getName(), currentList.get(searchForRightPart).getDesc(), currentList.get(searchForRightPart).getImageIndex());
			 		 finalizedList.put(7, newPart); 			 		 
			 	}
			 }

		}

	}
		
	
	

}
