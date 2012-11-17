package factory.client.kitManager;
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class KitManager extends JFrame {
	JPanel masterGrid;
	KitSelector kS; 
	ListOfPartsPanel lP; 
	int numberOfButtonsDecider; 
	

	public KitManager(){
		numberOfButtonsDecider = 0; 
		masterGrid = new JPanel(new GridLayout(3, 1)); 
		kS = new KitSelector(); 
		lP = new ListOfPartsPanel();
		kS.createAButton( numberOfButtonsDecider); 
		masterGrid.add(lP); 
		masterGrid.add(kS); 		
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
