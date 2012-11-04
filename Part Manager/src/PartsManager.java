
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class PartsManager extends JFrame {
	JPanel partsGrid, firstColumn, secondColumn, thirdColumn; 
	JLabel partsIconTitle, partsIcon,  partsNameTitle, partsName, partSelectTitle; 
	JButton firstPartButton; 

	public PartsManager(){
		partsGrid = new JPanel(new BorderLayout()); 
		 
		
		
		//First Column
		firstColumn = new JPanel(new GridBagLayout()); 
		GridBagConstraints fgbc = new GridBagConstraints(); 
		partsIconTitle = new JLabel("Parts Icon"); 
		fgbc.gridx = 0; 
		fgbc.gridy = 0; 
		firstColumn.add(partsIconTitle, fgbc);  // adds Parts Icon title
		partsIcon = new JLabel("Images"); 
		fgbc.gridx = 0; 
		fgbc.gridy = 1; 
		firstColumn.add(partsIcon, fgbc); // adds Parts Icons 
	
		partsGrid.add(firstColumn, BorderLayout.CENTER); 
		
		
		//Second Column
		secondColumn = new JPanel(new GridBagLayout()); 
		GridBagConstraints sgbc = new GridBagConstraints(); 
		partsNameTitle = new JLabel("Parts Names"); 
		sgbc.gridx = 0; 
		sgbc.gridy = 0; 
		secondColumn.add(partsNameTitle, sgbc);  // adds Parts Names title
		partsName = new JLabel("Names"); 
		sgbc.gridx = 0; 
		sgbc.gridy = 1; 
		secondColumn.add(partsName, sgbc); // adds Parts Names
		secondColumn.setBackground(Color.white);
		partsGrid.add(secondColumn, BorderLayout.LINE_START); //which column is which
		
		
		//Third Column
		thirdColumn = new JPanel(new GridBagLayout()); 
		GridBagConstraints tgbc = new GridBagConstraints(); 
		partSelectTitle = new JLabel("Select Part"); 
		tgbc.gridx = 0; 
		tgbc.gridy = 0; 
		thirdColumn.add(partSelectTitle, tgbc);  // adds Select Parts Title
		firstPartButton = new JButton("N"); 
		tgbc.gridx = 0; 
		tgbc.gridy = 1; 
		thirdColumn.add(firstPartButton, tgbc); // adds JButtons to select
		partsGrid.add(thirdColumn, BorderLayout.LINE_END);
		
		add(partsGrid); 
		 
		
		
		
	}
	
	
	public static void main(String[] args) {
		PartsManager thisPartsManager = new PartsManager(); 
		thisPartsManager.setTitle("Parts Manager");
	    thisPartsManager.setSize(900, 700);	
	    thisPartsManager.setLocationRelativeTo(null);
	    
	    thisPartsManager.setVisible(true); 
	    thisPartsManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
	
	
	public void actionPerformed(ActionEvent ae){
		
		
	}
	

}
