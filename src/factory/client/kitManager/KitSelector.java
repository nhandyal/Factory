package factory.client.kitManager;
import factory.global.data.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.*;

public class KitSelector extends JPanel{
	JButton selectPartForKitButton;
	JPanel kitSelectorPanel;
	JButton createThisKit; 
	GridBagConstraints gbc;
	TreeMap<Integer, Parts> updatableListOfParts; 
	public KitSelector(){
		updatableListOfParts = new TreeMap<Integer, Parts>(); 
		kitSelectorPanel = new JPanel();
		createThisKit = new JButton("Make This Kit"); 
		kitSelectorPanel.add(createThisKit); 
		add(kitSelectorPanel); 		
		selectPartForKitButton = new JButton("No Parts Here Yet");
	}

	public void createAButton(int i){
		i = 0; 
		selectPartForKitButton = new JButton(updatableListOfParts.get(i).getName()); 
		kitSelectorPanel.add(selectPartForKitButton);	
		i++; 	
	}






}