package factory.client.kitManager;
import java.awt.CardLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.event.*;
import factory.global.data.*; 
import factory.global.network.*;

import javax.swing.*;
public class KitManager extends JFrame implements ActionListener, NetworkManager
{
	private AddPanel addPanel;
	private EditPanel editPanel;
	private DeletePanel deletePanel;
	JPanel panel = new JPanel();
	CardLayout cl = new CardLayout();
	private TreeMap<Integer, Parts> currentParts;
	private TreeMap<Integer, Kits> currentKits;
	private ArrayList<Integer> indexes = new ArrayList<Integer>();
	private int count;
	JMenuBar menubar = new JMenuBar();
	JMenuItem add = new JMenuItem("Add");
	JMenuItem edit = new JMenuItem("Edit");
	JMenuItem delete = new JMenuItem("Delete");
	boolean isEdit = false;
	String current = "";
	NetworkBridge nb1;
	public KitManager()
	{
		nb1 = new NetworkBridge(this, "localhost", 8465, 1);
	}
	
	public TreeMap<Integer, Parts> getCurrentParts()
	{
		return currentParts;
	}
	
	public TreeMap<Integer, Kits> getCurrentKits()
	{
		return currentKits;
	}
	
	public static void main(String args[])
	{
		KitManager frame = new KitManager();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == addPanel.cp.show)
		{
			ArrayList<JComboBox> boxes = addPanel.cp.getComboBoxes();
			TreeMap<Integer, Parts> selectedParts = new TreeMap<Integer, Parts>();
			int partCount = 0;
			for (int i = 0; i < boxes.size(); i++)
			{
				int t = boxes.get(i).getSelectedIndex();
				if (t > 0)
					selectedParts.put(i, currentParts.get(indexes.get(t - 1)));
			}
			addPanel.kip.setKitParts(selectedParts);
		}
		if (e.getSource() == addPanel.kip.create)
		{
			ArrayList<JComboBox> boxes = addPanel.cp.getComboBoxes();
			TreeMap<Integer, Parts> selectedParts = new TreeMap<Integer, Parts>();
			int partCount = 0;
			for (int i = 0; i < boxes.size(); i++)
			{
				int t = boxes.get(i).getSelectedIndex();
				if (t > 0)
				{
					selectedParts.put(i, currentParts.get(indexes.get(t - 1)));
					partCount++;
				}
			}
			addPanel.kip.setKitParts(selectedParts);
			int ID = -1;
			try
			{
				ID = Integer.parseInt(addPanel.kip.ID.getText());
			}catch(Exception ex)
			{
				ID = -1;
			}
			if (ID >= 0 && partCount >= 4)
			{
				Kits k = new Kits(addPanel.kip.name.getText(), selectedParts, addPanel.kip.desc.getText(), ID);
				if (!isEdit)
				{
					count = -1;
					for (Integer key : currentKits.keySet())
						if (key > count)
							count = key;
					count++;
				}
				else
				{
					addPanel.kip.create.setText("Create Kit");
					isEdit = false;
				}
				System.out.println(count);
				currentKits.put(count, k);
				addPanel.kip.name.setText("Name");
				addPanel.kip.ID.setText("ID");
				addPanel.kip.desc.setText("Brief discription of kit");
				for (int i = 0; i < boxes.size(); i++)
				{
					boxes.get(i).setSelectedIndex(0);
				}
			}
		}
		if (e.getSource() == editPanel.edit)
		{
			Kits k = editPanel.getKits();
			int index = editPanel.getIndex();
			if (index >= 0)
			{
				addPanel.kip.name.setText(k.getName());
				addPanel.kip.ID.setText("" + k.getKitID());
				addPanel.kip.desc.setText(k.getDescription());
				isEdit = true;
				for (int i = 0; i < addPanel.cp.getComboBoxes().size(); i++)
					addPanel.cp.getComboBoxes().get(i).setSelectedIndex(0);
				for (Integer key : k.getListOfParts().keySet())
				{
					int t = k.getListOfParts().get(key).getImageIndex();
					for (int i = 0; i < indexes.size(); i++)
						if (indexes.get(i) == t - 1)
							addPanel.cp.getComboBoxes().get(key).setSelectedIndex(i + 1);
				}
				addPanel.kip.setKitParts(k.getListOfParts());
				addPanel.kip.create.setText("Save Kit");
				count = index;
			}
			else
			{
				for (int i = 0; i < addPanel.cp.getComboBoxes().size(); i++)
					addPanel.cp.getComboBoxes().get(i).setSelectedIndex(0);
				addPanel.kip.name.setText("Name");
				addPanel.kip.ID.setText("ID");
				addPanel.kip.desc.setText("Brief discription of kit");
				addPanel.kip.setKitParts(new TreeMap<Integer, Parts>());
			}
			current = "add";
			cl.show(panel, "add");
		}
		if(e.getSource() == deletePanel.delete)
		{
			if (deletePanel.getIndex() >= 0)
				currentKits.remove(deletePanel.getIndex());
			for (int i = 0; i < addPanel.cp.getComboBoxes().size(); i++)
				addPanel.cp.getComboBoxes().get(i).setSelectedIndex(0);
			addPanel.kip.name.setText("Name");
			addPanel.kip.ID.setText("ID");
			addPanel.kip.desc.setText("Brief discription of kit");
			addPanel.kip.setKitParts(new TreeMap<Integer, Parts>());
			current = "add";
			cl.show(panel, "add");
		}
		if(e.getSource() == add)
		{
			for (int i = 0; i < addPanel.cp.getComboBoxes().size(); i++)
				addPanel.cp.getComboBoxes().get(i).setSelectedIndex(0);
			addPanel.kip.name.setText("Name");
			addPanel.kip.ID.setText("ID");
			addPanel.kip.desc.setText("Brief discription of kit");
			addPanel.kip.setKitParts(new TreeMap<Integer, Parts>());
			current = "add";
			addPanel.kip.create.setText("Create Kit");
			isEdit = false;
			cl.show(panel, "add");
		}
		if(e.getSource() == edit)
		{
			
			if (current != "edit")
				editPanel.initialize();
			current = "edit";
			cl.show(panel, "edit");
			
		}
		if(e.getSource() == delete)
		{
			if (current != "delete")
				deletePanel.initialize();
			current = "delete";
			cl.show(panel, "delete");
		}
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			fos = new FileOutputStream("Kits");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(currentKits);
		}catch(Exception ex)
		{
		}
	}
	public void registerClientListener(NetworkBridge newBridge, int cID){}
	public void syncFrame(){}
	public void updatePartData(TreeMap<Integer, Parts>partData){
		if (partData != null)
			currentParts = partData;
		else
			currentParts = new TreeMap<Integer, Parts>();
	}
	public void updateKitData(TreeMap<Integer, Kits>kitData)
	{
		if (kitData != null)
			currentKits = kitData;
		else
			currentKits = new TreeMap<Integer, Kits>();
		for (Integer key : currentParts.keySet())
			indexes.add(key);
		panel.setLayout(cl);
		addPanel = new AddPanel(this);
		editPanel = new EditPanel(this);
		deletePanel = new DeletePanel(this);
		panel.add(addPanel, "add");
		panel.add(deletePanel, "delete");
		panel.add(editPanel, "edit");
		add(panel);
		add.addActionListener(this);
		edit.addActionListener(this);
		delete.addActionListener(this);
		menubar.add(add);
		menubar.add(edit);
		menubar.add(delete);
		current = "add";
		setJMenuBar(menubar);
		setSize(400, 400);
		setVisible(true);

	}
		
	// client specific
	public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray){}
	
	public void syncChanges(ArrayList<TreeMap<Integer,FactoryObject>> dataArray){}
		
	// global
	public void closeNetworkBridge(int bridgeID)
	{
				nb1.close();
	}
}
