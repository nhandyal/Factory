import java.awt.*; 

import javax.swing.*; 

public class ThirdColumn extends JPanel{
	public ThirdColumn(){
		JPanel thirdColumn = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		JLabel partSelectTitle = new JLabel("Select Part"); 
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		thirdColumn.add(partSelectTitle, gbc);  // adds Select Parts Title
		JButton firstPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		thirdColumn.add(firstPartButton, gbc); // adds JButtons to select
		
		JButton secondPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		thirdColumn.add(secondPartButton, gbc); // adds JButtons to select
		
		JButton thirdPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		thirdColumn.add(thirdPartButton, gbc); // adds JButtons to select
		
		JButton fourthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		thirdColumn.add(fourthPartButton, gbc); // adds JButtons to select
		
		JButton fifthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 5; 
		thirdColumn.add(fifthPartButton, gbc); // adds JButtons to select
		add(thirdColumn);
		
		JButton sixthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 6; 
		thirdColumn.add(sixthPartButton, gbc); // adds JButtons to select
		
		JButton seventhPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 7; 
		thirdColumn.add(seventhPartButton, gbc); // adds JButtons to select
		
		JButton eighthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 8; 
		thirdColumn.add(eighthPartButton, gbc); // adds JButtons to select
		
		JButton ninthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 9; 
		thirdColumn.add(ninthPartButton, gbc); // adds JButtons to select
		
		JButton tenthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 10; 
		thirdColumn.add(tenthPartButton, gbc); // adds JButtons to select
		
		
	}
	
}
