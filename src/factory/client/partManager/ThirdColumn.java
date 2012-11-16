package factory.client.partManager;
import java.awt.*; 
import factory.global.data.*;
import javax.swing.*; 

public class ThirdColumn extends JPanel{
	private JPanel thirdColumn; 
	private JLabel partSelectTitle; 
	private JButton firstPartButton, secondPartButton, thirdPartButton, fourthPartButton,
	fifthPartButton, sixthPartButton, seventhPartButton, eighthPartButton, ninthPartButton, tenthPartButton; 
	public ThirdColumn(){
		thirdColumn = new JPanel(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints(); 
		partSelectTitle = new JLabel("Select Part"); 
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		thirdColumn.add(partSelectTitle, gbc);  // adds Select Parts Title
		firstPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		thirdColumn.add(firstPartButton, gbc); // adds JButtons to select
		
		secondPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		thirdColumn.add(secondPartButton, gbc); // adds JButtons to select
		
		thirdPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		thirdColumn.add(thirdPartButton, gbc); // adds JButtons to select
		
		fourthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		thirdColumn.add(fourthPartButton, gbc); // adds JButtons to select
		
		fifthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 5; 
		thirdColumn.add(fifthPartButton, gbc); // adds JButtons to select
		add(thirdColumn);
		
		sixthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 6; 
		thirdColumn.add(sixthPartButton, gbc); // adds JButtons to select
		
		seventhPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 7; 
		thirdColumn.add(seventhPartButton, gbc); // adds JButtons to select
		
		eighthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 8; 
		thirdColumn.add(eighthPartButton, gbc); // adds JButtons to select
		
		ninthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 9; 
		thirdColumn.add(ninthPartButton, gbc); // adds JButtons to select
		
		tenthPartButton = new JButton("N"); 
		gbc.gridx = 0; 
		gbc.gridy = 10; 
		thirdColumn.add(tenthPartButton, gbc); // adds JButtons to select
		
		
	}
	public JPanel getThirdColumn() {
		return thirdColumn;
	}
	public void setThirdColumn(JPanel thirdColumn) {
		this.thirdColumn = thirdColumn;
	}
	public JLabel getPartSelectTitle() {
		return partSelectTitle;
	}
	public void setPartSelectTitle(JLabel partSelectTitle) {
		this.partSelectTitle = partSelectTitle;
	}
	public JButton getFirstPartButton() {
		return firstPartButton;
	}
	public void setFirstPartButton(JButton firstPartButton) {
		this.firstPartButton = firstPartButton;
	}
	public JButton getSecondPartButton() {
		return secondPartButton;
	}
	public void setSecondPartButton(JButton secondPartButton) {
		this.secondPartButton = secondPartButton;
	}
	public JButton getThirdPartButton() {
		return thirdPartButton;
	}
	public void setThirdPartButton(JButton thirdPartButton) {
		this.thirdPartButton = thirdPartButton;
	}
	public JButton getFourthPartButton() {
		return fourthPartButton;
	}
	public void setFourthPartButton(JButton fourthPartButton) {
		this.fourthPartButton = fourthPartButton;
	}
	public JButton getFifthPartButton() {
		return fifthPartButton;
	}
	public void setFifthPartButton(JButton fifthPartButton) {
		this.fifthPartButton = fifthPartButton;
	}
	public JButton getSixthPartButton() {
		return sixthPartButton;
	}
	public void setSixthPartButton(JButton sixthPartButton) {
		this.sixthPartButton = sixthPartButton;
	}
	public JButton getSeventhPartButton() {
		return seventhPartButton;
	}
	public void setSeventhPartButton(JButton seventhPartButton) {
		this.seventhPartButton = seventhPartButton;
	}
	public JButton getEighthPartButton() {
		return eighthPartButton;
	}
	public void setEighthPartButton(JButton eighthPartButton) {
		this.eighthPartButton = eighthPartButton;
	}
	public JButton getNinthPartButton() {
		return ninthPartButton;
	}
	public void setNinthPartButton(JButton ninthPartButton) {
		this.ninthPartButton = ninthPartButton;
	}
	public JButton getTenthPartButton() {
		return tenthPartButton;
	}
	public void setTenthPartButton(JButton tenthPartButton) {
		this.tenthPartButton = tenthPartButton;
	}
	
}
