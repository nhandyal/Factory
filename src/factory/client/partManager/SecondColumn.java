package factory.client.partManager;
import java.awt.*; 
import factory.global.data.*;
import javax.swing.*; 


public class SecondColumn extends JPanel {
	private JPanel secondColumn; 
	private JLabel partsIconTitle, partOneIcon, partTwoIcon, partThreeIcon, partFourIcon, partFiveIcon, partSixIcon,
	partSevenIcon, partEightIcon, partNineIcon, partTenIcon; 
	ImageArray iA; 
	public SecondColumn(){
		iA = new ImageArray(); 
	JPanel secondColumn = new JPanel(); 
	secondColumn.setLayout(new BoxLayout(secondColumn, BoxLayout.Y_AXIS)); 
	//0
	partsIconTitle = new JLabel("Parts Icon"); 	
	secondColumn.add(partsIconTitle);  // adds Parts Icon title
	JLabel lineSkip = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip); 
	//1
	
	partOneIcon = new JLabel(iA.getIcon(0)); 	
	secondColumn.add(partOneIcon); // adds Parts Icons 
	JLabel lineSkip1 = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip1); 
		//2
	partTwoIcon = new JLabel(iA.getIcon(1)); 	
	secondColumn.add(partTwoIcon); // adds Parts Icons 
	JLabel lineSkip2 = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip2);
	//3
	partThreeIcon = new JLabel(iA.getIcon(2));  
	secondColumn.add(partThreeIcon); // adds Parts Icons 
	JLabel lineSkip3 = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip3);
	//4
	partFourIcon = new JLabel(iA.getIcon(3)); 
	secondColumn.add(partFourIcon); // adds Parts Icons 
	JLabel lineSkip4 = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip4);
	//5
	partFiveIcon = new JLabel(iA.getIcon(4)); 
	secondColumn.add(partFiveIcon); // adds Parts Icons 
	JLabel lineSkip5 = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip5);
	//6
	partSixIcon = new JLabel(iA.getIcon(5)); 
	secondColumn.add(partSixIcon); // adds Parts Icons 
	JLabel lineSkip6 = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip6);
	//7
	partSevenIcon = new JLabel(iA.getIcon(6)); 
	secondColumn.add(partSevenIcon); // adds Parts Icons 
	JLabel lineSkip7 = new JLabel("<HTML><BR><BR></HTML>");
	secondColumn.add(lineSkip7);
	//8
	partEightIcon = new JLabel(iA.getIcon(7)); 	
	secondColumn.add(partEightIcon); // adds Parts Icons 
	JLabel lineSkip8 = new JLabel("<HTML><BR><BR></HTML>");
	secondColumn.add(lineSkip8);
	//9
	partNineIcon = new JLabel(iA.getIcon(8)); 
	secondColumn.add(partNineIcon); // adds Parts Icons 
	JLabel lineSkip9 = new JLabel("<HTML><BR><BR></HTML>");
	secondColumn.add(lineSkip9);
	//10
	partTenIcon = new JLabel(iA.getIcon(9)); 
	secondColumn.add(partTenIcon); // adds Parts Icons 
	JLabel lineSkip10 = new JLabel("<HTML><BR></HTML>");
	secondColumn.add(lineSkip10);
	
	add(secondColumn); 
	}
	
	
	
	public JPanel getSecondColumn() {
		return secondColumn;
	}
	public void setSecondColumn(JPanel secondColumn) {
		this.secondColumn = secondColumn;
	}
	public JLabel getPartsIconTitle() {
		return partsIconTitle;
	}
	public void setPartsIconTitle(JLabel partsIconTitle) {
		this.partsIconTitle = partsIconTitle;
	}
	public JLabel getPartOneIcon() {
		return partOneIcon;
	}
	public void setPartOneIcon(JLabel partOneIcon) {
		this.partOneIcon = partOneIcon;
	}
	public JLabel getPartTwoIcon() {
		return partTwoIcon;
	}
	public void setPartTwoIcon(JLabel partTwoIcon) {
		this.partTwoIcon = partTwoIcon;
	}
	public JLabel getPartThreeIcon() {
		return partThreeIcon;
	}
	public void setPartThreeIcon(JLabel partThreeIcon) {
		this.partThreeIcon = partThreeIcon;
	}
	public JLabel getPartFourIcon() {
		return partFourIcon;
	}
	public void setPartFourIcon(JLabel partFourIcon) {
		this.partFourIcon = partFourIcon;
	}
	public JLabel getPartFiveIcon() {
		return partFiveIcon;
	}
	public void setPartFiveIcon(JLabel partFiveIcon) {
		this.partFiveIcon = partFiveIcon;
	}
	public JLabel getPartSixIcon() {
		return partSixIcon;
	}
	public void setPartSixIcon(JLabel partSixIcon) {
		this.partSixIcon = partSixIcon;
	}
	public JLabel getPartSevenIcon() {
		return partSevenIcon;
	}
	public void setPartSevenIcon(JLabel partSevenIcon) {
		this.partSevenIcon = partSevenIcon;
	}
	public JLabel getPartEightIcon() {
		return partEightIcon;
	}
	public void setPartEightIcon(JLabel partEightIcon) {
		this.partEightIcon = partEightIcon;
	}
	public JLabel getPartNineIcon() {
		return partNineIcon;
	}
	public void setPartNineIcon(JLabel partNineIcon) {
		this.partNineIcon = partNineIcon;
	}
	public JLabel getPartTenIcon() {
		return partTenIcon;
	}
	public void setPartTenIcon(JLabel partTenIcon) {
		this.partTenIcon = partTenIcon;
	}
	
	
}