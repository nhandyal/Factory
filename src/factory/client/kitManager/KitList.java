package factory.client.kitManager;
import factory.global.data.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.*;

public class KitList extends JPanel{
	JPanel kitLister; 
	JLabel listOfKits; 	
	JButton createKit; 
	public KitList(){
		kitLister = new JPanel(); 
		kitLister.setLayout(new BoxLayout(kitLister, BoxLayout.Y_AXIS));
		//listOfKits = new JLabel("<HTML>List Of Kits<BR><BR><BR></HTML>"); 
		createKit = new JButton("Create Kit");  		
		//kitLister.add(listOfKits); 
		kitLister.add(createKit); 
		add(kitLister); 
	}


	public JPanel getKitLister(){
		return kitLister; 
	}

	public void setKitLister(JPanel kitLister){
		this.kitLister = kitLister; 
	}

	public JLabel getListOfKits(){
		return listOfKits; 
	}

	public void setListOfKits(JLabel listOfKits){
		this.listOfKits = listOfKits; 
	}

	public JButton getCreateKit(){
		return createKit; 
	}

	public void setCreateKit(JButton createKit){
		this.createKit = createKit; 
	}

}