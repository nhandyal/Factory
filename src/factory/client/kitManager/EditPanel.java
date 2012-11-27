package factory.client.kitManager;
import factory.global.data.*; 
import java.util.*;

import javax.swing.*;
public class EditPanel extends JPanel
{
	KitManager km;
	private TreeMap<Integer, Kits> currentKits;
	private ArrayList<Integer> indexes = new ArrayList<Integer>();
	JComboBox kits;
	JButton edit = new JButton("Edit");
	public EditPanel(KitManager km)
	{
		this.km = km;
		initialize();
	}
	
	public void initialize()
	{
		edit.removeActionListener(km);
		indexes.removeAll(indexes);
		removeAll();
		currentKits = km.getCurrentKits();
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ArrayList<String> options = new ArrayList<String>();
		options.add("None");
		for (Integer key : currentKits.keySet())
		{
			indexes.add(key);
			options.add(currentKits.get(key).getName());
		}
		kits = new JComboBox(options.toArray());
		add(kits);
		edit.addActionListener(km);
		add(edit);
	}
	public int getIndex()
	{
		if (kits.getSelectedIndex() >= 1)
		{
			//System.out.println(indexes.get(kits.getSelectedIndex() - 1));
			return indexes.get(kits.getSelectedIndex() - 1);
		}
		else
			return -1;
	}
	
	public Kits getKits()
	{
		return currentKits.get(getIndex());
	}
}
