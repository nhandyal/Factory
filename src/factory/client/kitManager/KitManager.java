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
	TreeMap<Integer, Parts> previousList;
	ArrayList<Kits> listOfKits; 
	NetworkBridge nb1;
	int globalCounter; 
	
	public KitManager(){
		nb1 = new NetworkBridge(this, "localhost", 8465, 1);
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
		previousList = currentList;

		updateBoxes(); 



		
		finalizedList = new TreeMap<Integer, Parts>();
		listOfKits = new ArrayList<Kits>(); 
		
		
		globalCounter = 0; 
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
			if(finalizedList.size()<4){
				System.out.println("Sorry - less than 4 parts"); 
			}
			else{
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
			kL.getKitLister().add(new JLabel(newKit.getName()));
			kL.getKitLister().revalidate(); 
			System.out.println("The Kit, " + listOfKits.get(0).getName() + " was just created. Congratulations."); 
			kI.getKitNameText().setText("Name");
			kI.getKitDescription().setText("Brief Description of Kit");
			kI.getKitIDNumber().setText("ID");

				nb1.sendKitData(listOfKits);
			}
		}
	}

	public void itemStateChanged(ItemEvent ie){
		if(ie.getStateChange() == ItemEvent.SELECTED){
			// box 1
			int[] values = new int[8];
			for(int i = 0; i < 8; i++){
				values[i] = -2;
			}
			
			JComboBox box1 = pS.getBoxOfPart1();
			JComboBox box2 = pS.getBoxOfPart2();
			JComboBox box3 = pS.getBoxOfPart3();
			JComboBox box4 = pS.getBoxOfPart4();
			JComboBox box5 = pS.getBoxOfPart5();
			JComboBox box6 = pS.getBoxOfPart6();
			JComboBox box7 = pS.getBoxOfPart7();
			JComboBox box8 = pS.getBoxOfPart8();
			
			String b1 = "";
			String b2 = "";
			String b3 = "";
			String b4 = "";
			String b5 = "";
			String b6 = "";
			String b7 = "";
			String b8 = "";
			try{
				if(box1 != null)
						 b1 = box1.getSelectedItem().toString();
				if(box2 != null)
						b2 = box2.getSelectedItem().toString();
				if(box3 != null)
						b3 = box3.getSelectedItem().toString();
				if(box4 != null)
						b4 = box4.getSelectedItem().toString();
				if(box5 != null)
						b5 = box5.getSelectedItem().toString();
				if(box6 != null)
						b6 = box6.getSelectedItem().toString();
				if(box7 != null)
						b7 = box7.getSelectedItem().toString();
				if(box8 != null)
						b8 = box8.getSelectedItem().toString();
			}catch(Exception e){
				
			}
			
			for(Integer i : currentList.keySet()){
				Parts temp = currentList.get(i);
				String pName = temp.getName();
				if(b1.equals(pName))
						values[0] = i;
				else if(b2.equals(pName))
						values[1] = i;
				else if(b3.equals(pName))
						values[2] = i;
				else if(b4.equals(pName))
						values[3] = i;
				else if(b5.equals(pName))
						values[4] = i;
				else if(b6.equals(pName))
						values[5] = i;
				else if(b7.equals(pName))
						values[6] = i;
				else if(b8.equals(pName))
						values[7] = i;
			}
		
		
				for(int i = 0; i < 8; i++){
					if(values[i] != -2){
							// add the part to the finalized list
							finalizedList.put(i, currentList.get(values[i]));
					}
				}
				System.out.println(finalizedList.size());
		}
	}
		
		public void updateBoxes(){ 
			//COMBO BOX 1
				// first for loop removes old list
				
				pS.getBoxOfPart1().removeAllItems();
				pS.getBoxOfPart2().removeAllItems();
				pS.getBoxOfPart3().removeAllItems();
				pS.getBoxOfPart4().removeAllItems();
				pS.getBoxOfPart5().removeAllItems();
				pS.getBoxOfPart6().removeAllItems();
				pS.getBoxOfPart7().removeAllItems();
				pS.getBoxOfPart8().removeAllItems(); 
		
			
			for(Integer i : currentList.keySet()){
				String nextName = currentList.get(i).getName();
				pS.getPartNamesBox1().add(nextName);
				pS.getPartNamesBox2().add(nextName);
				pS.getPartNamesBox3().add(nextName);
				pS.getPartNamesBox4().add(nextName);
				pS.getPartNamesBox5().add(nextName);
				pS.getPartNamesBox6().add(nextName);
				pS.getPartNamesBox7().add(nextName);
				pS.getPartNamesBox8().add(nextName);
				pS.getBoxOfPart1().addItem(nextName);
				pS.getBoxOfPart2().addItem(nextName);
				pS.getBoxOfPart3().addItem(nextName);
				pS.getBoxOfPart4().addItem(nextName);
				pS.getBoxOfPart5().addItem(nextName);
				pS.getBoxOfPart6().addItem(nextName);
				pS.getBoxOfPart7().addItem(nextName);
				pS.getBoxOfPart8().addItem(nextName);
			}
			
			previousList = currentList;
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
				System.out.println();
				System.out.println();
				updateBoxes(); // call combo box validation method
				currentList.clear(); 
				
		}

		public void updateKitData(ArrayList<Kits>kitData){};
		
		public void syncChanges(ArrayList<TreeMap<Integer,FactoryObject>> dataArray){}
		
		// global
		public void closeNetworkBridge(int bridgeID){
				nb1.close();
		}
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- End Network Manager ------------------------------ //
		// -------------------------------------------------------------------------------------- //
		
	
	

}
