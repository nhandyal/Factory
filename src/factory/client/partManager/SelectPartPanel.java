package factory.client.partManager; 
import javax.swing.*;
import java.util.*;
import factory.global.data.*;
public class SelectPartPanel extends JPanel
{
	PartsManager pm;
	ArrayList<JButton> newButtons = new ArrayList<JButton>();
	ArrayList<JButton> editButtons = new ArrayList<JButton>();
	ArrayList<JButton> deleteButtons = new ArrayList<JButton>();
	public SelectPartPanel(PartsManager pm)
	{
		this.pm = pm;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel("Select Part"));
		for (int i = 0; i < 10; i++)
		{
			JPanel temp = new JPanel();
			temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
			temp.add(new JLabel("Part" + (i + 1) + "-->      "));
			newButtons.add(new JButton("New"));
			editButtons.add(new JButton("Edit"));
			deleteButtons.add(new JButton("Delete"));
			newButtons.get(i).addActionListener(pm);
			editButtons.get(i).addActionListener(pm);
			deleteButtons.get(i).addActionListener(pm);
			if (pm.currentParts.get(i) == null)
			{
				deleteButtons.get(i).setEnabled(false);
				editButtons.get(i).setEnabled(false);
			}
			else
			{
				newButtons.get(i).setEnabled(false);
			}
			temp.add(newButtons.get(i));
			temp.add(editButtons.get(i));
			temp.add(deleteButtons.get(i));
			add(temp);
		}
	}
	public ArrayList<JButton> getNewButtons()
	{
		return newButtons;
	}
	
	public ArrayList<JButton> getEditButtons()
	{
		return editButtons;
	}
	
	public ArrayList<JButton> getDeleteButtons()
	{
		return deleteButtons;
	}
}
