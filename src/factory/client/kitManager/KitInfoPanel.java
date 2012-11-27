package factory.client.kitManager;
import java.awt.Dimension;
import java.util.TreeMap;
import factory.global.data.*; 

import javax.swing.*;
public class KitInfoPanel extends JPanel
{
	TreeMap<Integer, Parts> parts;
	KitManager km;
	JTextField name = new JTextField("Name");
	JTextField ID = new JTextField("ID");
	JTextField desc = new JTextField("Brief discription of kit");
	JButton create = new JButton("Create Kit");
	KitChart chart = new KitChart(new TreeMap<Integer, Parts>());
	public KitInfoPanel(KitManager km)
	{
		this.km = km;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(name);
		add(ID);
		add(desc);
		chart.setPreferredSize(new Dimension(169, 104));
		chart.setMinimumSize(new Dimension(169, 104));
		chart.setMaximumSize(new Dimension(169, 104));
		add(chart);
		add(new JLabel("<HTML><BR><BR></HTML>"));
		create.setAlignmentX(CENTER_ALIGNMENT);
		create.addActionListener(km);
		add(create);
	}
	
	public void setKitParts(TreeMap<Integer, Parts> parts)
	{
		this.parts = parts;
		chart.setParts(parts);
		chart.repaint();
	}
}
