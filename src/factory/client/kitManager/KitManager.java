package factory.client.kitManager;
import factory.global.data.*; 
import factory.global.network.*;
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class KitManager extends JFrame implements ActionListener, ItemListener, NetworkManager {

	JPanel kitPanel; 
	KitList kL; 
	KitInfo kI; 
	PartSelector pS; 
	Kits newKit; 
	TreeMap<Integer, Parts> currentList;
	TreeMap<Integer, Parts> finalizedList; 
	ArrayList<Kits> listOfKits; 
	NetworkBridge nb1;
	public KitManager(){
		nb1 = new NetworkBridge(this, "aludra.usc.edu", 8465, 1);
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
		pS.getBoxOfPart8().addItemListener(this); 

		currentList = new TreeMap<Integer, Parts>();

 
		pS.getBoxOfPart1().setEnabled(false); 
		//pS.getBoxOfPart1().removeAllItems();

		for(int i = 0; i<currentList.size(); i++){
			String nextName = currentList.get(i).getName(); 			
			pS.getPartNamesBox1().add(nextName); //adds names to arraylist		
			pS.getBoxOfPart1().addItem(pS.getPartNamesBox1().get(i)); //updates ComboBox

		}
		pS.getBoxOfPart1().setEnabled(true); 
		pS.getPartsPanel().revalidate(); 
		
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
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- Network Manager ---------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		// server specific
		public void registerClientListener(NetworkBridge newBridge, int cID){}
		public void syncFrame(){}
		
		
		// client specific
		public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray){}
		public void updatePartData(TreeMap<Integer, Parts> partData){
				currentList = partData;
				for(Integer i : currentList.keySet()){
						currentList.get(i).print();
				}
		}
		
		public void syncChanges(ArrayList<TreeMap<Integer,FactoryObject>> dataArray){}
		
		// global
		public void closeNetworkBridge(int bridgeID){
				nb1.close();
		}
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- End Network Manager ------------------------------ //
		// -------------------------------------------------------------------------------------- //
		
	
	

}
