//package factory.client.partsManager;
import java.util.*;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
//import factory.global.network.*;
//import factory.global.data.*;

public class PartsManager extends JFrame implements ActionListener {
	
	JPanel partsGrid; 
	FirstColumn fC; 
	SecondColumn sC; 
	ThirdColumn tC; 
	PartInfo pI; 
	boolean part1Edit, part2Edit, part3Edit, part4Edit, part5Edit, part6Edit, part7Edit, part8Edit, part9Edit, part10Edit; 
	int eightPartCounter; 
	JButton newKit; int partIndexNumber; 
	ArrayList<Parts> listOfParts; 
	JMenuBar optionMenuBar; JMenu optionMenu; JMenuItem resetOption, deletePartOption; 
	public PartsManager(){	

		optionMenuBar = new JMenuBar(); 
		optionMenu = new JMenu("Options"); 
		resetOption = new JMenuItem("Reset"); 
		deletePartOption = new JMenuItem("Delete a Part");
		optionMenu.add(resetOption); 
		optionMenu.add(deletePartOption);
		optionMenuBar.add(optionMenu); 
		add(optionMenuBar, BorderLayout.NORTH); 

		partsGrid = new JPanel(new GridLayout(1, 5)); 	
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
		partIndexNumber = 0; 
		/*
		newKit = new JButton("New Kit"); 
		newKit.setActionCommand("Reset"); 
		newKit.addActionListener(this); 
		partsGrid.add(newKit); 
		*/
		listOfParts = new ArrayList<Parts>(); 
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 1"); 
				return; 
			}

			Parts newPart1 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part1.png"));
			listOfParts.add(newPart1); 
			partIndexNumber++;
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 2"); 
				return; 
			} 			
			Parts newPart2 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part2.png"));
			listOfParts.add(newPart2); 
			partIndexNumber++;
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
			
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 3"); 
				return; 
			} 			
			Parts newPart3 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part3.png"));
			listOfParts.add(newPart3); 
			partIndexNumber++;
			
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 4"); 
				return; 
			} 			
			Parts newPart4 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part4.png"));
			listOfParts.add(newPart4); 
			partIndexNumber++;
			
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 5"); 
				return; 
			} 			
			Parts newPart5 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part5.png"));
			listOfParts.add(newPart5); 
			partIndexNumber++;
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 6"); 
				return; 
			}			
			Parts newPart6 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part6.png"));
			listOfParts.add(newPart6); 
			partIndexNumber++;
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 7"); 
				return; 
			} 			
			Parts newPart7 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part7.png"));
			listOfParts.add(newPart7); 
			partIndexNumber++;
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here");
				fC.getPartOneName().setText("Part 8"); 
				return; 
			} 			
			Parts newPart8 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part8.png"));
			listOfParts.add(newPart8); 
			partIndexNumber++;
			
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 9"); 
				return; 
			} 			
			Parts newPart9 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part9.png"));
			listOfParts.add(newPart9); 
			partIndexNumber++;
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
			String stringPartIndex = pI.getPartIndexNumber().getText(); 
			int intPartIndex = 0; 
			try{
				intPartIndex = Integer.parseInt(stringPartIndex);
			}
			catch(Exception z){
				System.out.println("Exception found - did not enter in an integer");
				pI.getPartIndexNumber().setText("Enter Part ID Number Here"); 
				fC.getPartOneName().setText("Part 10"); 
				return; 
			} 			
			Parts newPart10 = new Parts(intPartIndex, nameText, pI.getPartDescription().getText(), new ImageIcon("part10.png"));
			listOfParts.add(newPart10); 
			partIndexNumber++; 
		}
		
		if("Reset".equals(ae.getActionCommand())){
			//everything resets and a new kit can be created
			eightPartCounter = 0; 
		}
	}
	

}
