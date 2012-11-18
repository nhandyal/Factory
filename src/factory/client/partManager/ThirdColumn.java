package factory.client.partManager;
import java.awt.*; 
import factory.global.data.*;
import javax.swing.*;


public class ThirdColumn extends JPanel implements ActionListener{
	private JLabel partSelectTitle; 
	private JButton firstPartButton, secondPartButton, thirdPartButton, fourthPartButton,
	fifthPartButton, sixthPartButton, seventhPartButton, eighthPartButton, ninthPartButton, tenthPartButton;
	PartsManager parent;
	JButton[] deleteButtons = new JButton[10];
	public ThirdColumn(PartsManager parent){
		this.parent = parent;
		JPanel buttonContainer = new JPanel();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		buttonContainer.setLayout(new GridLayout(10,2));
		partSelectTitle = new JLabel("Select Part"); 
		firstPartButton = new JButton("N");
		secondPartButton = new JButton("N"); 
		thirdPartButton = new JButton("N"); 
		fourthPartButton = new JButton("N"); 
		fifthPartButton = new JButton("N"); 
		sixthPartButton = new JButton("N"); 
		seventhPartButton = new JButton("N"); 
		eighthPartButton = new JButton("N"); 
		ninthPartButton = new JButton("N"); 
		tenthPartButton = new JButton("N");
		for(int i = 0; i < 10; i++){
				JButton temp = new JButton("Delete");
				temp.addActionListener(this);
				deleteButtons[i] = temp;
				switch(i){
						case 0:
								buttonContainer.add(firstPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 1:
								buttonContainer.add(secondPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 2:
								buttonContainer.add(thirdPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 3:
								buttonContainer.add(fourthPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 4:
								buttonContainer.add(fifthPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 5:
								buttonContainer.add(sixthPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 6:
								buttonContainer.add(seventhPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 7:
								buttonContainer.add(eighthPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 9:
								buttonContainer.add(ninthPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
						case 10:
								buttonContainer.add(tenthPartButton);
								buttonContainer.add(deleteButtons[i]);
								break;
				}
		}
		
		this.add(partSelectTitle);
		this.add(buttonContainer);
		
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
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
