import javax.swing.*; 
import java.awt.*; 
public class PartInfo extends JPanel{
	JPanel partInformation; 
	JLabel title; 
	JTextField enterNameHere; 
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
		createPart = new JButton("Create Part"); 
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		partInformation.add(createPart, gbc); 
		
		add(partInformation); 
		setVisible(false); 
		
		
		
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
	
}