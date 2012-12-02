package factory.client.kitAssemblyManager;
import javax.swing.*;
import java.awt.event.*;
import factory.global.network.*;
class KAMNonNormPanel extends JPanel implements ActionListener
{
	JButton partMissing = new JButton("Parts Missing");
    NetworkBridge nb;
	KAMNonNormPanel(NetworkBridge nb)
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		partMissing.addActionListener(this);
		add(partMissing);
        this.nb = nb;
	}

	public void actionPerformed(ActionEvent e)
	{
        nb.sendBreakData("BRKSM", 4, 0);
	}
}
