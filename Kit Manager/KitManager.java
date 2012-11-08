
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class KitManager extends JFrame {
	JPanel masterGrid;
	JTextField nameEnter; 
	JLabel listOfParts; 
	JButton kitCreator; 

	public KitManager(){
		masterGrid = new JPanel(new BorderLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		
		nameEnter = new JTextField("Enter Name of Kit"); 
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		masterGrid.add(nameEnter, BorderLayout.NORTH); 
		listOfParts = new JLabel("Parts Listed Here"); 
		gbc.gridx = 0; 
		gbc.gridy = 1;
		masterGrid.add(listOfParts, BorderLayout.CENTER); 
		kitCreator = new JButton("Create Kit"); 
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		masterGrid.add(kitCreator, BorderLayout.SOUTH); 
		
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
