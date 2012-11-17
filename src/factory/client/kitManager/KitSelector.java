package factory.client.kitManager;
import javax.swing.*; 
import java.awt.*; 
import java.util.*;

public class KitSelector extends JPanel{
	JButton selectPartForKitButton;
	JPanel kitSelectorPanel;
	JButton createThisKit; 
	GridBagConstraints gbc;
	public KitSelector(){
		kitSelectorPanel = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints(); 
		gbc.gridx = 1;
		gbc.gridy = 0; 
		createThisKit = new JButton("Make This Kit"); 
		kitSelectorPanel.add(createThisKit, gbc); 
		add(kitSelectorPanel); 		
	}

	public void createAButton(){
		selectPartForKitButton = new JButton("Select Part"); 
		int y = 0; 
		gbc.gridx = 0; 
		gbc.gridy = y;
		y++;
		kitSelectorPanel.add(selectPartForKitButton, gbc);
		
	}






}