
package factory.client.partManager; 
import java.awt.*; 
import factory.global.data.*;
import javax.swing.*; 


public class FirstColumn extends JPanel {

	private JPanel firstColumn;
	private JLabel partsNameTitle, partOneName, partTwoName, partThreeName, partFourName, 
	partFiveName, partSixName, partSevenName, partEightName, partNineName, partTenName;
	public FirstColumn(){
		
		firstColumn = new JPanel(); 
		firstColumn.setLayout(new BoxLayout(firstColumn, BoxLayout.Y_AXIS)); 
		partsNameTitle = new JLabel("Parts Names");		
 
		firstColumn.add(partsNameTitle);  // adds Parts Names title
		JLabel lineSkip = new JLabel("<HTML><BR><BR></HTML>");
		firstColumn.add(lineSkip); 

		// 1
		partOneName = new JLabel("Part 1"); 		
		firstColumn.add(partOneName); // adds Parts Names
		JLabel lineSkip1 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip1);
		// 2
		partTwoName = new JLabel("Part 2"); 
		firstColumn.add(partTwoName); // adds Parts Names
		JLabel lineSkip2 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip2);
		// 3
		partThreeName = new JLabel("Part 3"); 
		firstColumn.add(partThreeName); // adds Parts Names
		JLabel lineSkip3 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip3);
		// 4
		partFourName = new JLabel("Part 4"); 
		firstColumn.add(partFourName); // adds Parts Names
		JLabel lineSkip4 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip4);
		// 5
		partFiveName = new JLabel("Part 5"); 
		firstColumn.add(partFiveName); // adds Parts Names
		JLabel lineSkip5 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip5);
		// 6
		partSixName = new JLabel("Part 6");  
		firstColumn.add(partSixName); // adds Parts Names
		JLabel lineSkip6 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip6);
		// 7
		partSevenName = new JLabel("Part 7"); 		
		firstColumn.add(partSevenName); // adds Parts Names
		JLabel lineSkip7 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip7);
		//8
		partEightName = new JLabel("Part 8");  
		firstColumn.add(partEightName); // adds Parts Names
		JLabel lineSkip8 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip8);
		//9
		partNineName = new JLabel("Part 9"); 
		firstColumn.add(partNineName); // adds Parts Names
		JLabel lineSkip9 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip9);
		//10
		partTenName = new JLabel("Part 10");  
		firstColumn.add(partTenName); // adds Parts Names
		JLabel lineSkip10 = new JLabel("<HTML><BR></HTML>");
		//firstColumn.add(lineSkip10);
		add(firstColumn);
	}
	public JPanel getFirstColumn() {
		return firstColumn;
	}
	public void setFirstColumn(JPanel firstColumn) {
		this.firstColumn = firstColumn;
	}
	public JLabel getPartsNameTitle() {
		return partsNameTitle;
	}
	public void setPartsNameTitle(JLabel partsNameTitle) {
		this.partsNameTitle = partsNameTitle;
	}
	public JLabel getPartOneName() {
		return partOneName;
	}
	public void setPartOneName(JLabel partOneName) {
		this.partOneName = partOneName;
	}
	public JLabel getPartTwoName() {
		return partTwoName;
	}
	public void setPartTwoName(JLabel partTwoName) {
		this.partTwoName = partTwoName;
	}
	public JLabel getPartThreeName() {
		return partThreeName;
	}
	public void setPartThreeName(JLabel partThreeName) {
		this.partThreeName = partThreeName;
	}
	public JLabel getPartFourName() {
		return partFourName;
	}
	public void setPartFourName(JLabel partFourName) {
		this.partFourName = partFourName;
	}
	public JLabel getPartFiveName() {
		return partFiveName;
	}
	public void setPartFiveName(JLabel partFiveName) {
		this.partFiveName = partFiveName;
	}
	public JLabel getPartSixName() {
		return partSixName;
	}
	public void setPartSixName(JLabel partSixName) {
		this.partSixName = partSixName;
	}
	public JLabel getPartSevenName() {
		return partSevenName;
	}
	public void setPartSevenName(JLabel partSevenName) {
		this.partSevenName = partSevenName;
	}
	public JLabel getPartEightName() {
		return partEightName;
	}
	public void setPartEightName(JLabel partEightName) {
		this.partEightName = partEightName;
	}
	public JLabel getPartNineName() {
		return partNineName;
	}
	public void setPartNineName(JLabel partNineName) {
		this.partNineName = partNineName;
	}
	public JLabel getPartTenName() {
		return partTenName;
	}
	public void setPartTenName(JLabel partTenName) {
		this.partTenName = partTenName;
	}


	
	
	
	
}
