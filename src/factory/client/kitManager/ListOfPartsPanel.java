import javax.swing.*; 
import java.awt.*; 
import java.util.*; 

//don't add to list - prebuilt list and editable
public class ListOfPartsPanel extends JPanel {
	JPanel listOfParts; JLabel theList; ArrayList<String> namesOfParts; 
	public ListOfPartsPanel(){
		namesOfParts = new ArrayList<String>(); 
		listOfParts = new JPanel(); 
		for(int i = 0; i<namesOfParts.size(); i++){
			theList = new JLabel(namesOfParts.get(i)); 
			listOfParts.add(theList); 
		}
		add(listOfParts); 
		
	}
	public JPanel getListOfParts() {
		return listOfParts;
	}
	public void setListOfParts(JPanel listOfParts) {
		this.listOfParts = listOfParts;
	}
	public JLabel getTheList() {
		return theList;
	}
	public void setTheList(JLabel theList) {
		this.theList = theList;
	}
	public ArrayList<String> getNamesOfParts() {
		return namesOfParts;
	}
	public void setNamesOfParts(ArrayList<String> namesOfParts) {
		this.namesOfParts = namesOfParts;
	}
}
