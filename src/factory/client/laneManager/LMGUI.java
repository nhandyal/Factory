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

	JPanel panel1, panel2;
	ArrayList<JCheckBox> laneCheck, nestCheck, dividerCheck, feederCheck;
	JCheckBox camCheck;
	JButton breakButton, laneJump, insertNest;
	JComboBox lanes;
	String[] laneStrings;
	LaneManager parent;

	public LMGUI(LaneManager l){

		parent = l;

		panel1 = new JPanel();
		panel2 = new JPanel();

		setLayout(new GridLayout(0,1));
		panel1.setLayout(new GridLayout(0,2));
		panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));

		laneCheck = new ArrayList<JCheckBox>();
		nestCheck = new ArrayList<JCheckBox>();
		for(int i=0;i<8;i++){
			laneCheck.add(new JCheckBox("Lane "+(i+1)));
			panel1.add(laneCheck.get(i));

			nestCheck.add(new JCheckBox("Nest "+(i+1)));
			panel1.add(nestCheck.get(i));
		}

		dividerCheck = new ArrayList<JCheckBox>();
		feederCheck = new ArrayList<JCheckBox>();
		for(int i=0;i<4;i++){
			dividerCheck.add(new JCheckBox("Divider "+(i+1)));
			panel1.add(dividerCheck.get(i));

			feederCheck.add(new JCheckBox("Feeder "+(i+1)));
			panel1.add(feederCheck.get(i));
		}

		camCheck = new JCheckBox("Camera");
		panel1.add(camCheck);

		breakButton = new JButton("Break");
		breakButton.addActionListener(this);
		panel1.add(breakButton);

		add(panel1);

		laneStrings = new String[8];
		for(int i=0;i<8;i++){
			laneStrings[i] = "Lane " + (i+1);
		}

		lanes = new JComboBox(laneStrings);

		panel2.add(lanes);

		laneJump = new JButton("Part Jump");
		laneJump.addActionListener(this);
		panel2.add(laneJump);

		insertNest = new JButton("Insert Bad Nest Part");
		insertNest.addActionListener(this);
		panel2.add(insertNest);

		add(panel2);

//		setMinimumSize(new Dimension(400,670));
//		setMaximumSize(new Dimension(400,670));
//		setPreferredSize(new Dimension(400,670));
	}

	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == breakButton){
			System.out.println("Break Pressed");

			if(camCheck.isSelected() == true){
				System.out.println("Camera broken");
				parent.nb.sendBreakData("camera",3,0);
				camCheck.setSelected(false);
			}

			for(int i=0;i<8;i++){
				if(laneCheck.get(i).isSelected() == true){
					System.out.println("Lane "+(i+1)+" broken");
					parent.nb.sendBreakData("lane",3,i);
					laneCheck.get(i).setSelected(false);
				}
				if(nestCheck.get(i).isSelected() == true){
					System.out.println("Nest "+(i+1)+" broken");
					parent.nb.sendBreakData("nest",3,i);
					nestCheck.get(i).setSelected(false);
				}
			}

			for(int i=0;i<4;i++){
				if(dividerCheck.get(i).isSelected() == true){
					System.out.println("Divider "+(i+1)+" broken");
					parent.nb.sendBreakData("divider",3,i);
					dividerCheck.get(i).setSelected(false);
				}
				if(feederCheck.get(i).isSelected() == true){
					System.out.println("Feeder "+(i+1)+" broken");
					parent.nb.sendBreakData("feeder",3,i);
					feederCheck.get(i).setSelected(false);
				}
			}
			System.out.println();
		}
	}
}