package factory.server;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import factory.global.data.*;
import factory.server.managers.GuiManager;
import factory.server.managers.laneManager.*;
import factory.server.managers.kitAssemblyManager.*;

public class ServerControl extends JPanel implements ActionListener{
	
	UpdateServer KitASM;
	LaneManager LM;
	
	JPanel overPanel = new JPanel();
	JPanel laneGantryControl = new JPanel();
	JPanel kitAssemblyControl = new JPanel();
	JPanel lgComboBoxPanel = new JPanel();
	JPanel[] kComboBoxPanel = new JPanel[4];
	JPanel laneGantryButtonPanel = new JPanel();
	JPanel factoryControl = new JPanel();
	JPanel fComboBoxPanel = new JPanel();
	JPanel factoryButtonPanel = new JPanel();
	
	JButton conveyorToStand = new JButton("Move from Conveyor to Stand");
	JButton build1 = new JButton("Build Kit 1");
	JButton build2 = new JButton("Build Kit 2");
	JButton inspectionPicture = new JButton("Take Picture");
	JButton inspection = new JButton("Move to Inspection Stand");
	JButton standToConveyor = new JButton("Move from Inspection Stand to Conveyor");
	JLabel kitAssemblyDesc = new JLabel("Kit Assembly Manager");
	JComboBox[] indexChooser = new JComboBox[4];
	String[] indexStrings = new String[8];
	JComboBox[] nestChooser = new JComboBox[4];
	JButton updateParts2 = new JButton("Update Part List");
	JButton bringKit = new JButton("Bring Kit");
	JButton takeKit = new JButton("Take Kit");
	
	JLabel laneGantryDesc = new JLabel("Lane and Gantry Manager");
	String[] laneStrings = new String[8];
	ArrayList<String> partStrings = new ArrayList<String>();
	JComboBox laneChooser;
	JComboBox partChooser;
	JButton toggleLane = new JButton("Turn Lane On/Off");
	JButton nestPicture = new JButton("Picture of Nest");
	JButton gantry = new JButton("Add Part to Lane");
	JButton updateParts = new JButton("Update Part List");
	
	JLabel factoryControlDesc = new JLabel("Factory Production Manager");
	ArrayList<String> kitStrings = new ArrayList<String>();
	JComboBox kitChooser;
	JTextField kitQuantity = new JTextField(3);
	JButton makeKits = new JButton("Make Kits");
	JButton updateKits = new JButton("Update Kit List");
	
	public ServerControl(GuiManager kit, GuiManager LM){
		
		this.KitASM = (UpdateServer)kit;
		this.LM = (LaneManager)LM;
		
		for (int i = 1; i < laneStrings.length+1; i++){
			laneStrings[i-1] = "Lane " + i;
		}
		for (int i = 1; i < indexStrings.length+1; i++){
			indexStrings[i-1] = "Section " + i;
		}
		for (int i = 1; i < 11; i++){
			String s = "Part " + i;
			partStrings.add(s);
		}
		for (int i = 1; i < 6; i++){
			String s = "Kit " + i;
			kitStrings.add(s);
		}
		laneChooser = new JComboBox(laneStrings);
		partChooser = new JComboBox(partStrings.toArray());
		for (int i = 0; i < nestChooser.length; i++)
			nestChooser[i] = new JComboBox(partStrings.toArray());
		kitChooser = new JComboBox(kitStrings.toArray());
		for (int i = 0; i < indexChooser.length; i++)
			indexChooser[i] = new JComboBox(indexStrings);
		for (int i = 0; i < kComboBoxPanel.length; i++)
			kComboBoxPanel[i] = new JPanel();
		
		overPanel.setLayout(new BoxLayout(overPanel,BoxLayout.X_AXIS));
		kitAssemblyControl.setLayout(new BoxLayout(kitAssemblyControl,BoxLayout.Y_AXIS));
		for (int i = 0; i < kComboBoxPanel.length; i++)
			kComboBoxPanel[i].setLayout(new FlowLayout());
		laneGantryControl.setLayout(new BoxLayout(laneGantryControl,BoxLayout.Y_AXIS));
		lgComboBoxPanel.setLayout(new FlowLayout());
		laneGantryButtonPanel.setLayout(new BoxLayout(laneGantryButtonPanel,BoxLayout.Y_AXIS));
		fComboBoxPanel.setLayout(new FlowLayout());
		factoryButtonPanel.setLayout(new BoxLayout(factoryButtonPanel,BoxLayout.Y_AXIS));
		factoryControl.setLayout(new BoxLayout(factoryControl,BoxLayout.Y_AXIS));
		
		
		conveyorToStand.addActionListener(this);
		build1.addActionListener(this);
		build2.addActionListener(this);
		inspectionPicture.addActionListener(this);
		inspection.addActionListener(this);
		standToConveyor.addActionListener(this);
		updateParts2.addActionListener(this);
		bringKit.addActionListener(this);
		takeKit.addActionListener(this);
		
		toggleLane.addActionListener(this);
		nestPicture.addActionListener(this);
		updateParts.addActionListener(this);
		
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
		kitAssemblyControl.add(inspectionPicture);
		kitAssemblyControl.add(standToConveyor);
		kitAssemblyControl.add(takeKit);
		kitAssemblyControl.add(updateParts2);
		
		lgComboBoxPanel.add(partChooser);
		lgComboBoxPanel.add(laneChooser);
		laneGantryControl.add(laneGantryDesc);
		laneGantryControl.add(lgComboBoxPanel);
		laneGantryButtonPanel.add(toggleLane);
		laneGantryButtonPanel.add(nestPicture);
		laneGantryButtonPanel.add(gantry);
		laneGantryButtonPanel.add(updateParts);
		laneGantryControl.add(laneGantryButtonPanel);
		
		fComboBoxPanel.add(kitChooser);
		fComboBoxPanel.add(kitQuantity);
		factoryButtonPanel.add(makeKits);
		factoryButtonPanel.add(updateKits);
		factoryControl.add(factoryControlDesc);
		factoryControl.add(fComboBoxPanel);
		factoryControl.add(factoryButtonPanel);
		
		overPanel.add(kitAssemblyControl);
		overPanel.add(laneGantryControl);
		overPanel.add(factoryControl);
		
		add(overPanel);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == bringKit){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
				KitASM.bringKit();
			}
		}
		if (e.getSource() == conveyorToStand){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
				for (int i = 0; i < 2; i++){
					if (KitASM.stands.get(i).getKit() == null){
						KitASM.moveToStand(i);
						break;
					}
				}
			}
		}
		/*
		if (e.getSource() == build1){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
				int[] nests = new Part[4];
				int[] indexes = new int[4];
				for (int i = 0; i < nestChooser.length; i++){
					String name = nestChooser[i].getSelectedItem();
					if (index != "Unused"){
						//convert name to part (call it part p)
						parts[i] = p;
					}
				}
				for (int i = 0; i < indexChooser.length; i++){
					String index = indexChooser[i].getSelectedItem().substring(9);
					if (index != "Unused"){
						int j = Integer.parseInt(index);
						indexes[i] = j;
					}
				}
				movePartsToStand(nest, KitASM.stands.get(0), indexes);
			}
		}
		if (e.getSource() == build2){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
			
			}
		}
		*/
		if (e.getSource() == inspectionPicture){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
				KitASM.takePic();
			}
		}
		if (e.getSource() == inspection){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
				KitASM.moveToInspection();
			}
		}
		if (e.getSource() == standToConveyor){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
				KitASM.takeToConveyor();
			}
		}
		if (e.getSource() == takeKit){
			if (!KitASM.cam.isMoving() && !KitASM.robot.isMoving() && !KitASM.probot.isMoving() && !KitASM.conv.getOutKit().getIsMoving()){
				KitASM.takeKit();
			}
		}
		if (e.getSource() == toggleLane){
		
		}
		if (e.getSource() == nestPicture){
		
		}
		if (e.getSource() == updateParts || e.getSource() == updateParts2){
			//Get parts list from server, set it to partStrings
			partChooser.removeAllItems();
			for (int i = 0; i < partStrings.size(); i++)
				partChooser.addItem(partStrings.get(i));
			for (int i = 0; i < nestChooser.length; i++){
				nestChooser[i].removeAllItems();
				for (int j = 0; j < partStrings.size(); j++)
					nestChooser[i].addItem(partStrings.get(j));
			}
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