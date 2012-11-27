package factory.client.partManager; 
import javax.swing.*;
import java.awt.event.*;
import factory.global.data.*;
public class PartsCreatorPanel extends JPanel
{
	JTextField partName = new JTextField("Please enter part name");
	JTextField partID = new JTextField("Please enter part ID");
	JTextField partDes = new JTextField("Please enter part description");
	JButton CreatePart = new JButton("Create Part");
	PartsManager pm;
	public PartsCreatorPanel(PartsManager pm)
	{
		this.pm = pm;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JLabel("Part Creator"));
		add(partName);
		add(partID);
		add(partDes);
		CreatePart.addActionListener(pm);
		add(CreatePart);
	}
	
}
