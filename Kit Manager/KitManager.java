
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class KitManager extends JFrame {
	JPanel masterGrid;
	JTextField nameEnter; 
	ListOfPartsPanel lopp = new ListOfPartsPanel(); 
	JButton kitCreator; 

	public KitManager(){
		masterGrid = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		
		nameEnter = new JTextField("Enter Name of Kit"); 
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		masterGrid.add(nameEnter, gbc); 
		lopp = new ListOfPartsPanel(); 
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		masterGrid.add(lopp, gbc); 
		kitCreator = new JButton("Create Kit"); 
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		masterGrid.add(kitCreator, gbc); 
		
		add(masterGrid); 
		
		
		
	}
	
	
	public static void main(String[] args) {
		KitManager thisKitManager = new KitManager(); 
		thisKitManager.setTitle("Kit Manager");
	    thisKitManager.setSize(900, 700);	
	    thisKitManager.setLocationRelativeTo(null);
	    thisKitManager.setVisible(true); 
	    thisKitManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
	
	
	public void actionPerformed(ActionEvent ae){

		
	}
	

}
