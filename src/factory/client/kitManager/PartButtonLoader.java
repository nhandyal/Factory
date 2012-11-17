package factory.client.kitManager;
import factory.global.data.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.*;
public class PartButtonLoader extends JButton{
	ArrayList<JButton> partButtons; 
	public PartButtonLoader(){
		partButtons = new ArrayList<JButton>(); 
	}

	public ArrayList<JButton> getPartButtons(){
		return partButtons; 
	}







}