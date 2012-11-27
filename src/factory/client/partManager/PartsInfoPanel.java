package factory.client.partManager; 
import java.util.*;
import javax.swing.*;
import factory.global.data.*;
public class PartsInfoPanel extends JPanel
{
	TreeMap<Integer, Parts> currentParts;
	public PartsInfoPanel(TreeMap<Integer, Parts> parts)
	{
		currentParts = parts;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		drawLabels();
	}
	
	public void drawLabels()
	{
		this.removeAll();
		add(new JLabel("Parts Name"));
		JLabel lineSkip = new JLabel("<HTML><BR><BR></HTML>");
		add(lineSkip);
		for (int i = 0; i < 10; i++)
			if(currentParts.get(i) != null)
			{
				add(new JLabel("Part " + (i + 1) + ":" + currentParts.get(i).getName()));
			}
			else
			{
				add(new JLabel("Unused"));
			}
	}
	
	public void setCurrentParts(TreeMap<Integer, Parts> parts)
	{
		currentParts = parts;
	}
}
