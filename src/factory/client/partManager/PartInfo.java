package factory.client.partManager;
import javax.swing.*; 
import java.awt.*;
import factory.global.data.*;
public class PartInfo extends JPanel{
	JPanel partInformation; 
	JLabel title; 
	JTextField enterNameHere, partIndexNumber, partDescription; 

	JButton createPart; 
	
	public PartInfo(){
		partInformation = new JPanel(new GridBagLayout()); 
		
		GridBagConstraints gbc = new GridBagConstraints(); 
		
		title = new JLabel("Part Creator");
		gbc.gridx = 0; 
		gbc.gridy = 0; 
		partInformation.add(title, gbc); 
		enterNameHere = new JTextField("Enter Name of Part Here", 20);
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		partInformation.add(enterNameHere, gbc);
		partIndexNumber = new JTextField("Enter Part ID Number Here", 20); 
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		partInformation.add(partIndexNumber, gbc); 		 
		partDescription = new JTextField("Short description of part here", 20); 
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		partInformation.add(partDescription, gbc); 
		createPart = new JButton("Create Part"); 
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		partInformation.add(createPart, gbc); 
		
		add(partInformation); 
		setVisible(false); 
		
		
		
	}

	public JPanel getPartInformation() {
		return partInformation;
	}

	public void setPartInformation(JPanel partInformation) {
		this.partInformation = partInformation;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JTextField getPartIndexNumber() {
		return partIndexNumber;
	}

	public void setPartIndexNumber(JTextField partIndexNumber) {
		this.partIndexNumber = partIndexNumber;
	}

	public JTextField getEnterNameHere() {
		return enterNameHere;
	}

	public void setEnterNameHere(JTextField enterNameHere) {
		this.enterNameHere = enterNameHere;
	}

	public JButton getCreatePart() {
		return createPart;
	}

	public void setCreatePart(JButton createPart) {
		this.createPart = createPart;
	}
	
	public JTextField getPartDescription() {
		return partDescription;
	}

	public void setPartDescription(JTextField partDescription) {
		this.partDescription = partDescription;
	}

}