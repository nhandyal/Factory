package factory.client.partManager; 
import javax.swing.*;
import factory.global.data.*;
public class PartsIconPanel extends JPanel
{
	public PartsIconPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel("Parts Icon"));
		JLabel lineSkip = new JLabel("<HTML><BR></HTML>");
		add(lineSkip);
		for (int i = 1; i <= 10; i++)
		{
			add(new JLabel(new ImageIcon("bin/factory/global/assets/part" + i + ".png")));
			JLabel t = new JLabel("<HTML><BR></HTML>");
			add(t);
		}
	}
}
