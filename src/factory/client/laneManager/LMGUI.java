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

	JPanel panel1, panel2, panel3;
	ArrayList<JCheckBox> laneCheck, nestCheck, dividerCheck, feederCheck;
	JCheckBox camCheck;
	JButton breakButton, laneJump, insertNest, laneJam;
	JComboBox lanes, nests;
	String[] laneStrings, nestStrings;
	LaneManager parent;
	GridBagConstraints	gbc;

	public LMGUI(LaneManager l){

		parent = l;

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		setLayout(new GridLayout(0,1));
		panel1.setLayout(new GridLayout(0,2));
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new GridLayout(1,2));

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

		gbc = new GridBagConstraints();

		laneStrings = new String[8];
		nestStrings = new String[8];
		for(int i=0;i<8;i++){
			laneStrings[i] = "Lane " + (i+1);
			nestStrings[i] = "Nest " + (i+1);
		}

		lanes = new JComboBox(laneStrings);
		nests = new JComboBox(nestStrings);

		gbc.gridx = 1;
		gbc.gridy = 1;
//		panel2.add(lanes,gbc);
		panel2.add(lanes);

		gbc.gridy = 3;
//		panel2.add(nests,gbc);
		panel2.add(nests);

		gbc.gridx = 1;
		gbc.gridy = 2;
		laneJump = new JButton("Part Jump");
		laneJump.addActionListener(this);
//		panel2.add(laneJump,gbc);
		panel2.add(laneJump);

		gbc.gridx++;
		laneJam = new JButton("Jam Lane");
		laneJam.addActionListener(this);
//		panel2.add(laneJam,gbc);
		panel2.add(laneJam);

		gbc.gridx = 1;
		gbc.gridy = 4;
		insertNest = new JButton("Insert Bad Nest Part");
		insertNest.addActionListener(this);
//		panel2.add(insertNest,gbc);
		panel2.add(insertNest);

//		panel2.add(panel3);
		add(panel2);

		setMinimumSize(new Dimension(300,670));
		setMaximumSize(new Dimension(300,670));
		setPreferredSize(new Dimension(300,670));
	}

	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == breakButton){

			if(camCheck.isSelected() == true){
				parent.nb.sendBreakData("camera",3,0);
				camCheck.setSelected(false);
			}

			for(int i=0;i<8;i++){
				if(laneCheck.get(i).isSelected() == true){
					parent.nb.sendBreakData("lane",3,i);
					laneCheck.get(i).setSelected(false);
				}
				if(nestCheck.get(i).isSelected() == true){
					parent.nb.sendBreakData("nest",3,i);
					nestCheck.get(i).setSelected(false);
				}
			}

			for(int i=0;i<4;i++){
				if(dividerCheck.get(i).isSelected() == true){
					parent.nb.sendBreakData("divider",3,i);
					dividerCheck.get(i).setSelected(false);
				}
				if(feederCheck.get(i).isSelected() == true){
					parent.nb.sendBreakData("feeder",3,i);
					feederCheck.get(i).setSelected(false);
				}
			}
		}

		if (ae.getSource() == laneJump){
			String l = (String)lanes.getSelectedItem();
			l = l.substring(5);
			int i = Integer.parseInt(l);
			i--;
			parent.nb.sendBreakData("jump",3,i);
		}
		if (ae.getSource() == laneJam){
			String l = (String)lanes.getSelectedItem();
			l = l.substring(5);
			int i = Integer.parseInt(l);
			i--;
			parent.nb.sendBreakData("jam",3,i);
		}
		if (ae.getSource() == insertNest){
			String l = (String)nests.getSelectedItem();
			l = l.substring(5);
			int i = Integer.parseInt(l);
			i--;
			parent.nb.sendBreakData("insert",3,i);
		}
	}
}