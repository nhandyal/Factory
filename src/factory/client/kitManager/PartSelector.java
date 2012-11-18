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
	public JPanel getPartsPanel() {
		return partsPanel;
	}
	public void setPartsPanel(JPanel partsPanel) {
		this.partsPanel = partsPanel;
	}
	public JComboBox getBoxOfPart1() {
		return boxOfPart1;
	}
	public void setBoxOfPart1(JComboBox boxOfPart1) {
		this.boxOfPart1 = boxOfPart1;
	}
	public JComboBox getBoxOfPart2() {
		return boxOfPart2;
	}
	public void setBoxOfPart2(JComboBox boxOfPart2) {
		this.boxOfPart2 = boxOfPart2;
	}
	public JComboBox getBoxOfPart3() {
		return boxOfPart3;
	}
	public void setBoxOfPart3(JComboBox boxOfPart3) {
		this.boxOfPart3 = boxOfPart3;
	}
	public JComboBox getBoxOfPart4() {
		return boxOfPart4;
	}
	public void setBoxOfPart4(JComboBox boxOfPart4) {
		this.boxOfPart4 = boxOfPart4;
	}
	public JComboBox getBoxOfPart5() {
		return boxOfPart5;
	}
	public void setBoxOfPart5(JComboBox boxOfPart5) {
		this.boxOfPart5 = boxOfPart5;
	}
	public JComboBox getBoxOfPart6() {
		return boxOfPart6;
	}
	public void setBoxOfPart6(JComboBox boxOfPart6) {
		this.boxOfPart6 = boxOfPart6;
	}
	public JComboBox getBoxOfPart7() {
		return boxOfPart7;
	}
	public void setBoxOfPart7(JComboBox boxOfPart7) {
		this.boxOfPart7 = boxOfPart7;
	}
	public JComboBox getBoxOfPart8() {
		return boxOfPart8;
	}
	public void setBoxOfPart8(JComboBox boxOfPart8) {
		this.boxOfPart8 = boxOfPart8;
	}
	public ArrayList<String> getPartNamesBox1() {
		return partNamesBox1;
	}
	public void setPartNamesBox1(ArrayList<String> partNamesBox1) {
		this.partNamesBox1 = partNamesBox1;
	}
	public ArrayList<String> getPartNamesBox2() {
		return partNamesBox2;
	}
	public void setPartNamesBox2(ArrayList<String> partNamesBox2) {
		this.partNamesBox2 = partNamesBox2;
	}
	public ArrayList<String> getPartNamesBox3() {
		return partNamesBox3;
	}
	public void setPartNamesBox3(ArrayList<String> partNamesBox3) {
		this.partNamesBox3 = partNamesBox3;
	}
	public ArrayList<String> getPartNamesBox4() {
		return partNamesBox4;
	}
	public void setPartNamesBox4(ArrayList<String> partNamesBox4) {
		this.partNamesBox4 = partNamesBox4;
	}
	public ArrayList<String> getPartNamesBox5() {
		return partNamesBox5;
	}
	public void setPartNamesBox5(ArrayList<String> partNamesBox5) {
		this.partNamesBox5 = partNamesBox5;
	}
	public ArrayList<String> getPartNamesBox6() {
		return partNamesBox6;
	}
	public void setPartNamesBox6(ArrayList<String> partNamesBox6) {
		this.partNamesBox6 = partNamesBox6;
	}
	public ArrayList<String> getPartNamesBox7() {
		return partNamesBox7;
	}
	public void setPartNamesBox7(ArrayList<String> partNamesBox7) {
		this.partNamesBox7 = partNamesBox7;
	}
	public ArrayList<String> getPartNamesBox8() {
		return partNamesBox8;
	}
	public void setPartNamesBox8(ArrayList<String> partNamesBox8) {
		this.partNamesBox8 = partNamesBox8;
	}






}