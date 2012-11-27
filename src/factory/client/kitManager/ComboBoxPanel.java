package factory.client.kitManager;
import factory.global.data.*; 
import java.util.*;
import javax.swing.*;

public class ComboBoxPanel extends JPanel
{
	KitManager km;
	TreeMap<Integer, Parts> parts;
	ArrayList<JComboBox> comboboxes = new ArrayList<JComboBox>();
	JButton show = new JButton("Show");
	public ComboBoxPanel(KitManager km)
	{
		this.km = km;
		parts = km.getCurrentParts();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel("Kit Config"));
		add(new JLabel("<HTML><BR><BR></HTML>"));
		for (int i = 0; i < 8; i++)
		{
			ArrayList<String> options = new ArrayList<String>();
			options.add("None");
			for (Integer key : parts.keySet())
			{
				options.add(parts.get(key).getName());
			}
			comboboxes.add(new JComboBox(options.toArray()));
			add(comboboxes.get(i));
			
		}
		show.addActionListener(km);
		add(show);
	}
	
	public ArrayList<JComboBox> getComboBoxes()
	{
		return comboboxes;
	}
}
