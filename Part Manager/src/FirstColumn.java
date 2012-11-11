 
import java.awt.*; 

import javax.swing.*; 
public class FirstColumn extends JPanel {

	public FirstColumn(){
		
		JPanel firstColumn = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		JLabel partsNameTitle = new JLabel("Parts Names");		
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		firstColumn.add(partsNameTitle, gbc);  // adds Parts Names title
		// 1
		JLabel partOneName = new JLabel("Part 1"); 
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		firstColumn.add(partOneName, gbc); // adds Parts Names
		// 2
		JLabel partTwoName = new JLabel("Part 2"); 
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		firstColumn.add(partTwoName, gbc); // adds Parts Names
		// 3
		JLabel partThreeName = new JLabel("Part 3"); 
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		firstColumn.add(partThreeName, gbc); // adds Parts Names
		// 4
		JLabel partFourName = new JLabel("Part 4"); 
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		firstColumn.add(partFourName, gbc); // adds Parts Names
		// 5
		JLabel partFiveName = new JLabel("Part 5"); 
		gbc.gridx = 0; 
		gbc.gridy = 5; 
		firstColumn.add(partFiveName, gbc); // adds Parts Names
		// 6
		JLabel partSixName = new JLabel("Part 6"); 
		gbc.gridx = 0; 
		gbc.gridy = 6; 
		firstColumn.add(partSixName, gbc); // adds Parts Names
		// 7
		JLabel partSevenName = new JLabel("Part 7"); 
		gbc.gridx = 0; 
		gbc.gridy = 7; 
		firstColumn.add(partSevenName, gbc); // adds Parts Names
		JLabel partEightName = new JLabel("Part 8"); 
		gbc.gridx = 0; 
		gbc.gridy = 8; 
		firstColumn.add(partEightName, gbc); // adds Parts Names
		//9
		JLabel partNineName = new JLabel("Part 9"); 
		gbc.gridx = 0; 
		gbc.gridy = 9; 
		firstColumn.add(partNineName, gbc); // adds Parts Names
		//10
		JLabel partTenName = new JLabel("Part 10"); 
		gbc.gridx = 0; 
		gbc.gridy = 10; 
		firstColumn.add(partTenName, gbc); // adds Parts Names
		
		add(firstColumn);
	}
}
