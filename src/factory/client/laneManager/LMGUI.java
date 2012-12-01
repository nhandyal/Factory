package factory.client.laneManager;

// Java packages
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*; 
import javax.swing.Timer;
import java.util.*;

// user packages
import factory.global.network.*;
import factory.global.data.*;

public class LMGUI extends JPanel implements ActionListener{//, NetworkManager{

	ArrayList<JCheckBox> laneCheck, nestCheck, dividerCheck, feederCheck;
	JCheckBox camCheck;
	JButton breakButton;
	LaneManager parent;

	public LMGUI(LaneManager l){

		parent = l;

		setLayout(new GridLayout(0,2));

		laneCheck = new ArrayList<JCheckBox>();
		nestCheck = new ArrayList<JCheckBox>();
		for(int i=0;i<8;i++){
			laneCheck.add(new JCheckBox("Lane "+(i+1)));
			laneCheck.get(i).addActionListener(this);
			add(laneCheck.get(i));

			nestCheck.add(new JCheckBox("Nest "+(i+1)));
			nestCheck.get(i).addActionListener(this);
			add(nestCheck.get(i));
		}

		dividerCheck = new ArrayList<JCheckBox>();
		feederCheck = new ArrayList<JCheckBox>();
		for(int i=0;i<4;i++){
			dividerCheck.add(new JCheckBox("Divider "+(i+1)));
			dividerCheck.get(i).addActionListener(this);
			add(dividerCheck.get(i));

			feederCheck.add(new JCheckBox("Feeder "+(i+1)));
			feederCheck.get(i).addActionListener(this);
			add(feederCheck.get(i));
		}

		camCheck = new JCheckBox("Camera");
		camCheck.addActionListener(this);
		add(camCheck);

		breakButton = new JButton("Break");
		breakButton.addActionListener(this);
		add(breakButton);

//		setMinimumSize(new Dimension(400,670));
//		setMaximumSize(new Dimension(400,670));
//		setPreferredSize(new Dimension(400,670));
	}

	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == breakButton){
			System.out.println("Break Pressed");

			if(camCheck.isSelected() == true){
				System.out.println("Camera broken");
				camCheck.setSelected(false);
			}

			for(int i=0;i<8;i++){
				if(laneCheck.get(i).isSelected() == true){
					System.out.println("Lane "+(i+1)+" broken");
					laneCheck.get(i).setSelected(false);
				}
				if(nestCheck.get(i).isSelected() == true){
					System.out.println("Nest "+(i+1)+" broken");
					nestCheck.get(i).setSelected(false);
				}
			}

			for(int i=0;i<4;i++){
				if(dividerCheck.get(i).isSelected() == true){
					System.out.println("Divider "+(i+1)+" broken");
					dividerCheck.get(i).setSelected(false);
				}
				if(feederCheck.get(i).isSelected() == true){
					System.out.println("Feeder "+(i+1)+" broken");
					feederCheck.get(i).setSelected(false);
				}
			}
			System.out.println();
		}
	}
}