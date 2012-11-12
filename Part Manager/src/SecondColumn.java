import java.awt.*; 

import javax.swing.*; 


public class SecondColumn extends JPanel {
	private JPanel secondColumn; 
	private JLabel partsIconTitle, partOneIcon, partTwoIcon, partThreeIcon, partFourIcon, partFiveIcon, partSixIcon,
	partSevenIcon, partEightIcon, partNineIcon, partTenIcon; 
	public SecondColumn(){
	JPanel secondColumn = new JPanel(new GridBagLayout()); 
	GridBagConstraints gbc = new GridBagConstraints(); 
	//0
	partsIconTitle = new JLabel("Parts Icon"); 
	gbc.gridx = 1; 
	gbc.gridy = 0; 
	secondColumn.add(partsIconTitle, gbc);  // adds Parts Icon title
	//1
	
	partOneIcon = new JLabel(new ImageIcon("src/part.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 1; 
	secondColumn.add(partOneIcon, gbc); // adds Parts Icons 
	//2
	partTwoIcon = new JLabel(new ImageIcon("src/part2.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 2; 
	secondColumn.add(partTwoIcon, gbc); // adds Parts Icons 
	//3
	partThreeIcon = new JLabel(new ImageIcon("src/part3.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 3; 
	secondColumn.add(partThreeIcon, gbc); // adds Parts Icons 
	//4
	partFourIcon = new JLabel(new ImageIcon("src/part4.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 4; 
	secondColumn.add(partFourIcon, gbc); // adds Parts Icons 
	//5
	partFiveIcon = new JLabel(new ImageIcon("src/part5.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 5; 
	secondColumn.add(partFiveIcon, gbc); // adds Parts Icons 
	//6
	partSixIcon = new JLabel(new ImageIcon("src/part6.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 6; 
	secondColumn.add(partSixIcon, gbc); // adds Parts Icons 
	//7
	partSevenIcon = new JLabel(new ImageIcon("src/part7.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 7; 
	secondColumn.add(partSevenIcon, gbc); // adds Parts Icons 
	//8
	partEightIcon = new JLabel(new ImageIcon("src/part8.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 8; 
	secondColumn.add(partEightIcon, gbc); // adds Parts Icons 
	//9
	partNineIcon = new JLabel(new ImageIcon("src/part.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 9; 
	secondColumn.add(partNineIcon, gbc); // adds Parts Icons 
	//10
	partTenIcon = new JLabel(new ImageIcon("src/part2.png")); 
	gbc.gridx = 1; 
	gbc.gridy = 10; 
	secondColumn.add(partTenIcon, gbc); // adds Parts Icons 
	//secondColumn.setBackground(Color.white);
	add(secondColumn); 
	}
}