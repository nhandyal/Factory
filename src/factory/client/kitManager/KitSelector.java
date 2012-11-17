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
		updatableListOfParts.put(0, new Parts(1, "Part 1", "This is Part 1", 0));
		updatableListOfParts.put(1, new Parts(2, "Part 2", "This is Part 2", 1));
	}

	public void createAButton(int i){
		
		selectPartForKitButton = new JButton(updatableListOfParts.get(i).getName()); 
		kitSelectorPanel.add(selectPartForKitButton);	
		i++; 	
	}

	public TreeMap<Integer, Parts> getUpdatableListOfParts(){
		return updatableListOfParts; 
	}




}