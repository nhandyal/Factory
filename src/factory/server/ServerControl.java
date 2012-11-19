package factory.server;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import factory.global.data.*;
import factory.server.managers.GuiManager;
import factory.server.managers.laneManager.*;
import factory.server.managers.kitAssemblyManager.*;
import factory.server.managers.factoryState.*;

public class ServerControl extends JPanel implements ActionListener{
	
	UpdateServer KitASM;
	LaneManager LM;
	FactoryState fs;
	
	JPanel overPanel = new JPanel();
	JPanel laneControl = new JPanel();
	JPanel kitAssemblyControl = new JPanel();
	JPanel lComboBoxPanel = new JPanel();
	JPanel[] kComboBoxPanel = new JPanel[4];
	JPanel laneButtonPanel = new JPanel();
	JPanel factoryControl = new JPanel();
	JPanel fComboBoxPanel = new JPanel();
	JPanel factoryButtonPanel = new JPanel();
	
	JButton conveyorToStand = new JButton("Move from Conveyor to Stand");
	JButton build1 = new JButton("Build Kit 1");
	JButton build2 = new JButton("Build Kit 2");
	JButton inspectionPicture = new JButton("Take Picture");
	JButton inspection = new JButton("Move Kit 1 to Inspection Stand");
    JButton inspection2 = new JButton("Move Kit 2 to Inspection Stand");
	JButton standToConveyor = new JButton("Move from Inspection Stand to Conveyor");
	JLabel kitAssemblyDesc = new JLabel("Kit Assembly Manager");
	JComboBox[] indexChooser = new JComboBox[4];
	String[] indexStrings = new String[8];
	JComboBox[] nestChooser = new JComboBox[4];
	String[] nestStrings = new String[8];
	JButton bringKit = new JButton("Bring Kit");
	JButton takeKit = new JButton("Take Kit");
	
	JLabel laneDesc = new JLabel("Lane Manager");
	String[] laneStrings = new String[8];
	JComboBox laneChooser;
	JButton toggleLane = new JButton("Turn Lane On/Off");
	JButton nestPicture = new JButton("Picture of Nest");
	
	JLabel factoryControlDesc = new JLabel("Factory Production Manager");
	ArrayList<String> kitStrings = new ArrayList<String>();
	JComboBox kitChooser;
	JTextField kitQuantity = new JTextField(3);
	JButton makeKits = new JButton("Make Kits");
	JButton updateKits = new JButton("Update Kit List");
	
	public ServerControl(GuiManager kit, GuiManager LM, FactoryState fs){
		
		System.out.println(kit);
		this.KitASM = (UpdateServer)kit;
		this.LM = (LaneManager)LM;
		this.fs = fs;
		System.out.println(KitASM);
		for (int i = 1; i < laneStrings.length+1; i++){
			laneStrings[i-1] = "Lane " + i;
		}
		for (int i = 0; i < indexStrings.length+1; i++){
			if (i == 0)
				indexStrings[i] = "Section -";
			else
				indexStrings[i-1] = "Section " + i;
		}
		for (int i = 0; i < nestStrings.length+1; i++){
			if (i == 0)
				nestStrings[i] = "Nest -";
			else
				nestStrings[i-1] = "Nest " + i;
		}
		for (int i = 1; i < 6; i++){
			String s = "Kit " + i;
			kitStrings.add(s);
		}
		laneChooser = new JComboBox(laneStrings);
		for (int i = 0; i < nestChooser.length; i++)
			nestChooser[i] = new JComboBox(nestStrings);
		kitChooser = new JComboBox(kitStrings.toArray());
		for (int i = 0; i < indexChooser.length; i++)
			indexChooser[i] = new JComboBox(indexStrings);
		for (int i = 0; i < kComboBoxPanel.length; i++)
			kComboBoxPanel[i] = new JPanel();
		
		overPanel.setLayout(new BoxLayout(overPanel,BoxLayout.X_AXIS));
		kitAssemblyControl.setLayout(new BoxLayout(kitAssemblyControl,BoxLayout.Y_AXIS));
		for (int i = 0; i < kComboBoxPanel.length; i++)
			kComboBoxPanel[i].setLayout(new FlowLayout());
		laneControl.setLayout(new BoxLayout(laneControl,BoxLayout.Y_AXIS));
		lComboBoxPanel.setLayout(new FlowLayout());
		laneButtonPanel.setLayout(new BoxLayout(laneButtonPanel,BoxLayout.Y_AXIS));
		fComboBoxPanel.setLayout(new FlowLayout());
		factoryButtonPanel.setLayout(new BoxLayout(factoryButtonPanel,BoxLayout.Y_AXIS));
		factoryControl.setLayout(new BoxLayout(factoryControl,BoxLayout.Y_AXIS));
		
		
		conveyorToStand.addActionListener(this);
		build1.addActionListener(this);
		build2.addActionListener(this);
		inspectionPicture.addActionListener(this);
		inspection.addActionListener(this);
        inspection2.addActionListener(this);
		standToConveyor.addActionListener(this);
		bringKit.addActionListener(this);
		takeKit.addActionListener(this);
		
		toggleLane.addActionListener(this);
		nestPicture.addActionListener(this);
		
		makeKits.addActionListener(this);
		updateKits.addActionListener(this);
		
		for (int i = 0; i < kComboBoxPanel.length; i++){
			kComboBoxPanel[i].add(nestChooser[i]);
			kComboBoxPanel[i].add(indexChooser[i]);
		}
		kitAssemblyControl.add(kitAssemblyDesc);
		for (int i = 0; i < kComboBoxPanel.length; i++)
			kitAssemblyControl.add(kComboBoxPanel[i]);
		kitAssemblyControl.add(bringKit);
		kitAssemblyControl.add(conveyorToStand);
		kitAssemblyControl.add(build1);
		kitAssemblyControl.add(build2);
		kitAssemblyControl.add(inspection);
        kitAssemblyControl.add(inspection2);
		kitAssemblyControl.add(inspectionPicture);
		kitAssemblyControl.add(standToConveyor);
		kitAssemblyControl.add(takeKit);
		
		lComboBoxPanel.add(laneChooser);
		laneControl.add(laneDesc);
		laneControl.add(lComboBoxPanel);
		laneButtonPanel.add(toggleLane);
		laneButtonPanel.add(nestPicture);
		laneControl.add(laneButtonPanel);
		
		fComboBoxPanel.add(kitChooser);
		fComboBoxPanel.add(kitQuantity);
		factoryButtonPanel.add(makeKits);
		factoryButtonPanel.add(updateKits);
		factoryControl.add(factoryControlDesc);
		factoryControl.add(fComboBoxPanel);
		factoryControl.add(factoryButtonPanel);
		
		overPanel.add(kitAssemblyControl);
		overPanel.add(laneControl);
		overPanel.add(factoryControl);
		
		add(overPanel);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == bringKit){
			if (KitASM.isFinished()){
				KitASM.bringKit();
			}
		}
		if (e.getSource() == conveyorToStand){
			if (KitASM.isFinished() /**/){

				for (int i = 0; i < 2; i++){
					if (KitASM.getStands().get(i).getKit() == null){
						KitASM.moveToStand(i);
						break;
					}
				}
			}
		}
		/*
		if (e.getSource() == build1){
			if (KitASM.isFinished() ){
				int[] nests = new Part[4];
				int[] indexes = new int[4];
				for (int i = 0; i < nestChooser.length; i++){
					String nest = nestChooser[i].getSelectedItem(5);
					if (index != "-"){
						int j = Integer.parseInt(nest);
						nests[i] = j;
					}
				}
				for (int i = 0; i < indexChooser.length; i++){
					String index = indexChooser[i].getSelectedItem().substring(8);
					if (index != "-"){
						int j = Integer.parseInt(index);
						indexes[i] = j;
					}
				}
				movePartsToStand(0, KitASM.stands.get(0), nests, indexes);
			}
		}
		if (e.getSource() == build2){
			if (KitASM.isFinished() ){
				int[] nests = new Part[4];
				int[] indexes = new int[4];
				for (int i = 0; i < nestChooser.length; i++){
					String nest = nestChooser[i].getSelectedItem(5);
					if (index != "-"){
						int j = Integer.parseInt(nest);
						nests[i] = j;
					}
				}
				for (int i = 0; i < indexChooser.length; i++){
					String index = indexChooser[i].getSelectedItem().substring(8);
					if (index != "-"){
						int j = Integer.parseInt(index);
						indexes[i] = j;
					}
				}
				movePartsToStand(0, KitASM.stands.get(1), nests, indexes);
			}
		}
		*/
		if (e.getSource() == inspectionPicture){
			if (KitASM.isFinished() ){
				KitASM.takePic();
			}
		}
		if (e.getSource() == inspection){
			if (KitASM.isFinished() ){
				KitASM.moveToInspection(0);
            }
			
		}
        if (e.getSource() == inspection2){
                if (KitASM.isFinished() ){
                    KitASM.moveToInspection(1);
                    
                }
        }
		if (e.getSource() == standToConveyor){
			if (KitASM.isFinished() ){
				KitASM.takeToConveyor();
			}
		}
		if (e.getSource() == takeKit){
			if (KitASM.isFinished() ){
				KitASM.takeKit();
			}
		}
		if (e.getSource() == toggleLane){
		
		}
		if (e.getSource() == nestPicture){
		
		}
		if (e.getSource() == makeKits){
		
		}
		if (e.getSource() == updateKits){
			//Get kits list from server, set it to kitStrings
			kitChooser.removeAllItems();
			for (int i = 0; i < kitStrings.size(); i++)
				kitChooser.addItem(kitStrings.get(i));
		}
	}
		
}
