
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class PartsManager extends JFrame implements ActionListener {
 
	JPanel partsGrid; 
	FirstColumn fC; 
	SecondColumn sC; 
	ThirdColumn tC; 
	PartInfo pI; 
	boolean part1Edit, part2Edit, part3Edit, part4Edit, part5Edit, part6Edit, part7Edit, part8Edit, part9Edit, part10Edit; 
	int eightPartCounter; 
	public PartsManager(){

		 
		
		partsGrid = new JPanel(new GridLayout(1, 4)); 	
		partsGrid.setVisible(true); 
		//First Column
		fC = new FirstColumn();		
		partsGrid.add(fC);		
		//Second Column
		sC = new SecondColumn(); 
		partsGrid.add(sC); 		
		//Third Column			
		tC = new ThirdColumn(); 
		partsGrid.add(tC); 	
		
		
		tC.getFirstPartButton().addActionListener(this); 
		tC.getFirstPartButton().setActionCommand("1"); 
		tC.getSecondPartButton().addActionListener(this); 
		tC.getSecondPartButton().setActionCommand("2"); 
		tC.getThirdPartButton().addActionListener(this); 
		tC.getThirdPartButton().setActionCommand("3"); 
		tC.getFourthPartButton().addActionListener(this); 
		tC.getFourthPartButton().setActionCommand("4"); 
		tC.getFifthPartButton().addActionListener(this); 
		tC.getFifthPartButton().setActionCommand("5"); 
		tC.getSixthPartButton().addActionListener(this); 
		tC.getSixthPartButton().setActionCommand("6"); 
		tC.getSeventhPartButton().addActionListener(this); 
		tC.getSeventhPartButton().setActionCommand("7"); 
		tC.getEighthPartButton().addActionListener(this); 
		tC.getEighthPartButton().setActionCommand("8"); 
		tC.getNinthPartButton().addActionListener(this); 
		tC.getNinthPartButton().setActionCommand("9"); 
		tC.getTenthPartButton().addActionListener(this); 
		tC.getTenthPartButton().setActionCommand("10"); 
		
		//main panel
		
		pI = new PartInfo(); 
		pI.getCreatePart().setActionCommand("Create Part"); 
		pI.getCreatePart().addActionListener(this); 
		partsGrid.add(pI);
		add(partsGrid);
		
		part1Edit = false; 
		part2Edit = false; 
		part3Edit = false; 
		part4Edit = false; 
		part5Edit = false; 
		part6Edit = false; 
		part7Edit = false; 
		part8Edit = false; 
		part9Edit = false; 
		part10Edit = false; 
		
		eightPartCounter = 0; 

	}
	
	
	public static void main(String[] args) {
		PartsManager thisPartsManager = new PartsManager(); 
		thisPartsManager.setTitle("Parts Manager");
	    thisPartsManager.setSize(1000, 350);	
	    thisPartsManager.setLocationRelativeTo(null);	    
	    thisPartsManager.setVisible(true); 
	    thisPartsManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

	}
	
	
	public void actionPerformed(ActionEvent ae){
		//First Button
		if("1".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part1Edit = true; 

		}
		if("Create Part".equals(ae.getActionCommand()) && part1Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartOneName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part1Edit = false; 
		}
		
		//Second Button
		if("2".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part2Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part2Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartTwoName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part2Edit = false; 
		}
		
		//Third Button
		if("3".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part3Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part3Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartThreeName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part3Edit = false; 
			
		}
		
		//Fourth Button
		if("4".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part4Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part4Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartFourName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part4Edit = false; 
			
		}
		
		//Fifth Button
		
		if("5".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part5Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part5Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartFiveName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part5Edit = false; 
			
		}
		
		//Sixth Button
		
		if("6".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part6Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part6Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartSixName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part6Edit = false; 
			
		}
		
		//Seventh Button
		if("7".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part7Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part7Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartSevenName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part7Edit = false; 
		}
		
		//Eighth Button
		if("8".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part8Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part8Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartEightName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part8Edit = false; 
			
		}
		//Ninth Button
		if("9".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part9Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part9Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartNineName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false); 
			part9Edit = false; 
			
		}
		
		//Tenth Button
		if("10".equals(ae.getActionCommand())){
			pI.setVisible(true); 
			tC.setVisible(false); 
			part10Edit = true; 
		}
		if("Create Part".equals(ae.getActionCommand()) && part10Edit == true){
			String nameText = pI.getEnterNameHere().getText(); 
			fC.getPartTenName().setText(nameText); 
			fC.setVisible(false);
			tC.setVisible(true); 
			fC.setVisible(true);
			pI.setVisible(false);
			part10Edit = false; 
			
		}
	}
	

}
