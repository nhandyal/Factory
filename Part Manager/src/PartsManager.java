
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class PartsManager extends JFrame implements ActionListener {
 
	JPanel partsGrid; 
	FirstColumn fC; 
	SecondColumn sC; 
	ThirdColumn tC; 

	public PartsManager(){
		partsGrid = new JPanel(new GridLayout(1, 3)); 		
		//First Column
		fC = new FirstColumn();		
		partsGrid.add(fC);		
		//Second Column
		sC = new SecondColumn(); 
		partsGrid.add(sC); 		
		//Third Column			
		tC = new ThirdColumn(); 
		partsGrid.add(tC); 	
		
		
		tC.getFirstPartButton().addActionListener(this); 
		tC.getFirstPartButton().setActionCommand("1"); 
		tC.getSecondPartButton().addActionListener(this); 
		tC.getSecondPartButton().setActionCommand("2"); 
		tC.getThirdPartButton().addActionListener(this); 
		tC.getThirdPartButton().setActionCommand("3"); 
		tC.getFourthPartButton().addActionListener(this); 
		tC.getFourthPartButton().setActionCommand("4"); 
		tC.getFifthPartButton().addActionListener(this); 
		tC.getFifthPartButton().setActionCommand("5"); 
		tC.getSixthPartButton().addActionListener(this); 
		tC.getSixthPartButton().setActionCommand("6"); 
		tC.getSeventhPartButton().addActionListener(this); 
		tC.getSeventhPartButton().setActionCommand("7"); 
		tC.getEighthPartButton().addActionListener(this); 
		tC.getEighthPartButton().setActionCommand("8"); 
		tC.getNinthPartButton().addActionListener(this); 
		tC.getNinthPartButton().setActionCommand("9"); 
		tC.getTenthPartButton().addActionListener(this); 
		tC.getTenthPartButton().setActionCommand("10"); 
		
		//main panel
		add(partsGrid); 
	}
	
	
	public static void main(String[] args) {
		PartsManager thisPartsManager = new PartsManager(); 
		thisPartsManager.setTitle("Parts Manager");
	    thisPartsManager.setSize(450, 350);	
	    thisPartsManager.setLocationRelativeTo(null);	    
	    thisPartsManager.setVisible(true); 
	    thisPartsManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
	
	
	public void actionPerformed(ActionEvent ae){

		
	}
	

}
