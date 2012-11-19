package factory.server;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import factory.global.data.*;
import factory.server.managers.GuiManager;
import factory.server.managers.laneManager.*;
import factory.server.managers.kitAssemblyManager.*;
import factory.server.managers.gantryManager.*;
import factory.server.managers.factoryState.*;

public class ServerControl extends JPanel implements ActionListener{
	
	UpdateServer KitASM;
	LaneManager LM;
	GantryManager GM;
	FactoryState fs;
	
	JPanel overPanel = new JPanel();
	JPanel laneControl = new JPanel();
	JPanel kitAssemblyControl = new JPanel();
	JPanel lComboBoxPanel = new JPanel();
	JPanel[] kComboBoxPanel = new JPanel[4];
	JPanel laneButtonPanel = new JPanel();
	JPanel factoryControl = new JPanel();
	JPanel gantryControl = new JPanel();
	JPanel gComboBoxPanel = new JPanel();
	
	JButton conveyorToStand = new JButton("Move from Conveyor to Stand");
	JButton build1 = new JButton("Build Kit 1");
	JButton build2 = new JButton("Build Kit 2");
	JButton inspectionPicture = new JButton("Take Picture");
	JButton inspection = new JButton("Move Kit 1 to Inspection Stand");
    JButton inspection2 = new JButton("Move Kit 2 to Inspection Stand");
	JButton standToConveyor = new JButton("Move from Inspection Stand to Conveyor");
	JLabel kitAssemblyDesc = new JLabel("Kit Assembly Manager");
	JComboBox[] indexChooser = new JComboBox[4];
	String[] indexStrings = new String[9];
	JComboBox[] nestChooser = new JComboBox[4];
	String[] nestStrings = new String[9];
	JButton bringKit = new JButton("Bring Kit");
	JButton takeKit = new JButton("Take Kit");
	
	JLabel laneDesc = new JLabel("Lane Manager");
	String[] laneStrings = new String[8];
	JComboBox laneChooser;
	JComboBox feederChooser2;
	JButton toggleLane = new JButton("Turn Lane On/Off");
	JButton toggleDivider = new JButton("Toggle Divider Up/Down");
	JButton nestPicture = new JButton("Picture of Nest");
	JButton purgeNest = new JButton("Purge Nest");
	JButton purgeLane = new JButton("Purge Lane");
	JButton purgeFeeder = new JButton("Purge Feeder");
	JButton addBin = new JButton("Add Bin");
	
	JLabel factoryControlDesc = new JLabel("Factory Production Manager");
	ArrayList<JLabel> kitLabels = new ArrayList<JLabel>();
	
	JLabel gantryControlDesc = new JLabel("Gantry Manager");
	JComboBox binChooser;
	JComboBox feederChooser;
	String[] binStrings = new String[10];
	String[] feederStrings = new String[4];
	JButton moveToBin = new JButton("Move to Bin");
	JButton moveToFeeder = new JButton("Move to Feeder");
	Server server;
	
	public ServerControl(GuiManager kit, GuiManager LM, GuiManager GM, FactoryState fs, Server serv){
		server = serv;
		
		this.KitASM = (UpdateServer)kit;
		this.LM = (LaneManager)LM;
		this.GM = (GantryManager)GM;
		this.fs = fs;
		
		for (int i = 1; i < laneStrings.length+1; i++){
				laneStrings[i-1] = "Lane " + i;
		}
		for (int i = 0; i < indexStrings.length; i++){
			if (i == 0)
				indexStrings[i] = "Section -";
			else
				indexStrings[i] = "Section " + i;
		}
		for (int i = 0; i < nestStrings.length; i++){
			if (i == 0)
				nestStrings[i] = "Nest -";
			else
				nestStrings[i] = "Nest " + i;
		}
		for (int i = 1; i < binStrings.length+1; i++){
			binStrings[i-1] = "Bin " + i;
		}
		for (int i = 1; i < feederStrings.length+1; i++){
			feederStrings[i-1] = "Feeder " + i;
		}
		laneChooser = new JComboBox(laneStrings);
		for (int i = 0; i < nestChooser.length; i++)
			nestChooser[i] = new JComboBox(nestStrings);
		for (int i = 0; i < indexChooser.length; i++)
			indexChooser[i] = new JComboBox(indexStrings);
		for (int i = 0; i < kComboBoxPanel.length; i++)
			kComboBoxPanel[i] = new JPanel();
		binChooser = new JComboBox(binStrings);
		feederChooser = new JComboBox(feederStrings);
		feederChooser2 = new JComboBox(feederStrings);
		
		overPanel.setLayout(new BoxLayout(overPanel,BoxLayout.X_AXIS));
		kitAssemblyControl.setLayout(new BoxLayout(kitAssemblyControl,BoxLayout.Y_AXIS));
		for (int i = 0; i < kComboBoxPanel.length; i++)
			kComboBoxPanel[i].setLayout(new FlowLayout());
		laneControl.setLayout(new BoxLayout(laneControl,BoxLayout.Y_AXIS));
		lComboBoxPanel.setLayout(new FlowLayout());
		laneButtonPanel.setLayout(new BoxLayout(laneButtonPanel,BoxLayout.Y_AXIS));
		factoryControl.setLayout(new BoxLayout(factoryControl,BoxLayout.Y_AXIS));
		gantryControl.setLayout(new BoxLayout(gantryControl,BoxLayout.Y_AXIS));
		gComboBoxPanel.setLayout(new FlowLayout());
		
		
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
		toggleDivider.addActionListener(this);
		nestPicture.addActionListener(this);
		purgeNest.addActionListener(this);
		purgeLane.addActionListener(this);
		purgeFeeder.addActionListener(this);
		addBin.addActionListener(this);
		
		moveToBin.addActionListener(this);
		moveToFeeder.addActionListener(this);
		
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
		lComboBoxPanel.add(feederChooser2);
		laneControl.add(laneDesc);
		laneControl.add(lComboBoxPanel);
		laneButtonPanel.add(toggleLane);
		laneButtonPanel.add(toggleDivider);
		laneButtonPanel.add(nestPicture);
		laneButtonPanel.add(purgeNest);
		laneButtonPanel.add(purgeLane);
		laneControl.add(laneButtonPanel);
		
		factoryControl.add(factoryControlDesc);
		
		gComboBoxPanel.add(binChooser);
		gComboBoxPanel.add(feederChooser);
		gantryControl.add(gantryControlDesc);
		gantryControl.add(gComboBoxPanel);
		gantryControl.add(moveToBin);
		gantryControl.add(moveToFeeder);
		gantryControl.add(purgeFeeder);
		//gantryControl.add(addBin);
		
		overPanel.add(kitAssemblyControl);
		overPanel.add(laneControl);
		overPanel.add(factoryControl);
		overPanel.add(gantryControl);
		
		add(overPanel);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == bringKit){
			if (KitASM.isFinished()){
				KitASM.bringKit();
			}
		}
		if (e.getSource() == conveyorToStand){
			if (KitASM.isFinished()){

				for (int i = 0; i < 2; i++){
					if (KitASM.getStands().get(i).getKit() == null){
						KitASM.moveToStand(i);
						break;
					}
				}
			}
		}
		
		if (e.getSource() == build1){
			if (KitASM.isFinished() ){
				int[] nests = new int[4];
				int[] indexes = new int[4];
				for (int i = 0; i < 4; i++){
					nests[i] = -1;
					indexes[i] = -1;
				}
				for (int i = 0; i < nestChooser.length; i++){
					String nest = (String)nestChooser[i].getSelectedItem();
					nest = nest.substring(5);
					if (!nest.equals("-")){
						int j = Integer.parseInt(nest);
						j -= 1;
						nests[i] = j;
					}
				}
				for (int i = 0; i < indexChooser.length; i++){
					String index = (String)indexChooser[i].getSelectedItem();
					index = index.substring(8);
					if (!index.equals("-")){
						int j = Integer.parseInt(index);
						j -= 1;
						indexes[i] = j;
					}
				}
				KitASM.movePartstoStand(0, 0, nests, indexes);
			}
		}
		if (e.getSource() == build2){
			if (KitASM.isFinished() ){
				int[] nests = new int[4];
				int[] indexes = new int[4];
				for (int i = 0; i < 4; i++){
					nests[i] = -1;
					indexes[i] = -1;
				}
				for (int i = 0; i < nestChooser.length; i++){
					String nest = (String)nestChooser[i].getSelectedItem();
					nest = nest.substring(5);
					if (!nest.equals("-")){
						int j = Integer.parseInt(nest);
						j -= 1;
						nests[i] = j;
					}
				}
				for (int i = 0; i < indexChooser.length; i++){
					String index = (String)indexChooser[i].getSelectedItem();
					index = index.substring(8);
					if (!index.equals("-")){
						int j = Integer.parseInt(index);
						j -= 1;
						indexes[i] = j;
					}
				}
				KitASM.movePartstoStand(0, 1, nests, indexes);
			}
		}
		
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
			server.sync = true;
			String l = (String)laneChooser.getSelectedItem();
			l = l.substring(5);
			int lane = Integer.parseInt(l);
			lane -= 1;
			LM.laneToggle(lane);
		}
		if (e.getSource() == nestPicture){
			server.sync = true;
			String n = (String)laneChooser.getSelectedItem();
			n = n.substring(5);
			int nest = Integer.parseInt(n);
			nest -= 1;
			LM.takePicture(nest);
		}
		if (e.getSource() == moveToBin){
			String b = (String)binChooser.getSelectedItem();
			b = b.substring(4);
			int bin = Integer.parseInt(b);
			bin -= 1;
			GM.robot.moveToBin(bin);
		}
		if (e.getSource() == moveToFeeder){
			String f = (String)feederChooser.getSelectedItem();
			f = f.substring(7);
			int feeder = Integer.parseInt(f);
			feeder -= 1;
			GM.robot.moveToFeeder(feeder);
		}
		if (e.getSource() == purgeNest){
			server.sync = true;
			String n = (String)laneChooser.getSelectedItem();
			n = n.substring(5);

			if (!n.equals("-")){
				int nest = Integer.parseInt(n);
				nest -= 1;
				LM.purgeNest(nest);
			}
			
		}
		if (e.getSource() == purgeLane){
			server.sync = true;
			String l = (String)laneChooser.getSelectedItem();
			l = l.substring(5);
			int lane = Integer.parseInt(l);
			lane -= 1;
			LM.purgeLane(lane);
		}
		if (e.getSource() == purgeFeeder){
			server.sync = true;
			String f = (String)feederChooser2.getSelectedItem();
			f = f.substring(7);
			int feeder = Integer.parseInt(f);
			feeder -= 1;
			GM.robot.removeBin(feeder);
			LM.removeBin(feeder);
		}
		if (e.getSource() == toggleDivider){
			server.sync = true;
			String f = (String)feederChooser2.getSelectedItem();
			f = f.substring(7);
			int feeder = Integer.parseInt(f);
			feeder -= 1;
			LM.dividerToggle(feeder);
		}
		/*
		if (e.getSource() == addBin){
			String f = (String)feederChooser.getSelectedItem();
			f = f.substring(7);
			int feeder = Integer.parseInt(f);
			feeder -= 1;
			String b = (String)binChooser.getSelectedItem();
			b = b.substring(4);
			int bin = Integer.parseInt(b);
			bin -= 1;
			LM.addBin2(feeder,bin,36);
		}
		*/
	}
		
}
