package factory.client.kitManager;
import factory.global.data.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.*; 
public class PartSelector extends JPanel{

	JPanel partsPanel; 
	JComboBox boxOfPart1, boxOfPart2, boxOfPart3, boxOfPart4, boxOfPart5, boxOfPart6, boxOfPart7, boxOfPart8; 
	ArrayList<String> partNamesBox1, partNamesBox2, partNamesBox3, partNamesBox4, partNamesBox5, partNamesBox6, partNamesBox7, partNamesBox8;
	public PartSelector(){

		
		partsPanel = new JPanel(); 
		partsPanel.setLayout(new BoxLayout(partsPanel, BoxLayout.Y_AXIS)); 

		partNamesBox1 = new ArrayList<String>(); 
		partNamesBox1.add("Part 1"); 
		boxOfPart1 = new JComboBox(partNamesBox1.toArray());
		partsPanel.add(boxOfPart1); 
		
		partNamesBox2 = new ArrayList<String>();
		partNamesBox2.add("Part 2"); 
		boxOfPart2 = new JComboBox(partNamesBox2.toArray()); 
		partsPanel.add(boxOfPart2); 

		partNamesBox3 = new ArrayList<String>();
		partNamesBox3.add("Part 3"); 
		boxOfPart3 = new JComboBox(partNamesBox3.toArray()); 
		partsPanel.add(boxOfPart3);

		partNamesBox4 = new ArrayList<String>();
		partNamesBox4.add("Part 4"); 
		boxOfPart4 = new JComboBox(partNamesBox4.toArray()); 
		partsPanel.add(boxOfPart4);

		partNamesBox5 = new ArrayList<String>();
		partNamesBox5.add("Part 5"); 
		boxOfPart5 = new JComboBox(partNamesBox5.toArray()); 
		partsPanel.add(boxOfPart5);

		partNamesBox6 = new ArrayList<String>();
		partNamesBox6.add("Part 6"); 
		boxOfPart6 = new JComboBox(partNamesBox6.toArray()); 
		partsPanel.add(boxOfPart6);

		partNamesBox7 = new ArrayList<String>();
		partNamesBox7.add("Part 7"); 
		boxOfPart7 = new JComboBox(partNamesBox7.toArray()); 
		partsPanel.add(boxOfPart7);

		partNamesBox8 = new ArrayList<String>();
		partNamesBox8.add("Part 8"); 
		boxOfPart8 = new JComboBox(partNamesBox8.toArray()); 
		partsPanel.add(boxOfPart8);


		add(partsPanel);



	}






}