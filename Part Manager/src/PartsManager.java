
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class PartsManager extends JFrame implements ActionListener {
	JPanel partsGrid, secondColumn, thirdColumn; 
	JLabel partsIconTitle, partsIcon,  partsNameTitle, partsName, partSelectTitle; 
	JButton firstPartButton; 
	FirstColumn fC; SecondColumn sC; ThirdColumn tC; 

	public PartsManager(){
		partsGrid = new JPanel(new GridLayout(1, 3)); 		
		//First Column
		fC = new FirstColumn();		
		partsGrid.add(fC);		
		//Second Column
		sC = new SecondColumn(); 
		partsGrid.add(sC); 		
		//Third Column		
		add(partsGrid); 
		tC = new ThirdColumn(); 
		partsGrid.add(tC); 
		
		
		
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
