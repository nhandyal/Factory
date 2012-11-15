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
	
	
	
	public JPanel getSecondColumn() {
		return secondColumn;
	}
	public void setSecondColumn(JPanel secondColumn) {
		this.secondColumn = secondColumn;
	}
	public JLabel getPartsIconTitle() {
		return partsIconTitle;
	}
	public void setPartsIconTitle(JLabel partsIconTitle) {
		this.partsIconTitle = partsIconTitle;
	}
	public JLabel getPartOneIcon() {
		return partOneIcon;
	}
	public void setPartOneIcon(JLabel partOneIcon) {
		this.partOneIcon = partOneIcon;
	}
	public JLabel getPartTwoIcon() {
		return partTwoIcon;
	}
	public void setPartTwoIcon(JLabel partTwoIcon) {
		this.partTwoIcon = partTwoIcon;
	}
	public JLabel getPartThreeIcon() {
		return partThreeIcon;
	}
	public void setPartThreeIcon(JLabel partThreeIcon) {
		this.partThreeIcon = partThreeIcon;
	}
	public JLabel getPartFourIcon() {
		return partFourIcon;
	}
	public void setPartFourIcon(JLabel partFourIcon) {
		this.partFourIcon = partFourIcon;
	}
	public JLabel getPartFiveIcon() {
		return partFiveIcon;
	}
	public void setPartFiveIcon(JLabel partFiveIcon) {
		this.partFiveIcon = partFiveIcon;
	}
	public JLabel getPartSixIcon() {
		return partSixIcon;
	}
	public void setPartSixIcon(JLabel partSixIcon) {
		this.partSixIcon = partSixIcon;
	}
	public JLabel getPartSevenIcon() {
		return partSevenIcon;
	}
	public void setPartSevenIcon(JLabel partSevenIcon) {
		this.partSevenIcon = partSevenIcon;
	}
	public JLabel getPartEightIcon() {
		return partEightIcon;
	}
	public void setPartEightIcon(JLabel partEightIcon) {
		this.partEightIcon = partEightIcon;
	}
	public JLabel getPartNineIcon() {
		return partNineIcon;
	}
	public void setPartNineIcon(JLabel partNineIcon) {
		this.partNineIcon = partNineIcon;
	}
	public JLabel getPartTenIcon() {
		return partTenIcon;
	}
	public void setPartTenIcon(JLabel partTenIcon) {
		this.partTenIcon = partTenIcon;
	}
	
	
}