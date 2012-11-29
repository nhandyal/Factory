package factory.client.kitAssemblyManager;
import javax.swing.*;
import java.awt.event.*;
class KAMNonNormPanel extends JPanel implements ActionListener
{
	JButton partMissing = new JButton("Parts Missing");
	KAMNonNormPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		partMissing.addActionListener(this);
		add(partMissing);
	}

	public void actionPerformed(ActionEvent e)
	{	
	}
}
