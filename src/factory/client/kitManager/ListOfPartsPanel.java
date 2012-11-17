package factory.client.kitManager;
import factory.global.data.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.*; 

//don't add to list - prebuilt list and editable
public class ListOfPartsPanel extends JPanel {
	JPanel listPanel; 
	TreeMap<Integer, Parts> updatableListOfParts; 
	JLabel displayPartsList; 
	public ListOfPartsPanel(){
		updatableListOfParts = new TreeMap<Integer, Parts>(); 
		displayPartsList = new JLabel("<HTML>Part 1 <BR>Part 2</HTML>"); 
		listPanel = new JPanel(); 
		listPanel.add(displayPartsList); 
		add(listPanel); 
	}

	public TreeMap<Integer, Parts> getUpdatableListOfParts(){
		return updatableListOfParts; 
	}
}

