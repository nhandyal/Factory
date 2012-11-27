package factory.client.kitManager;
import factory.global.data.*; 
import javax.swing.*;
public class AddPanel extends JPanel
{
	KitInfoPanel kip;
	ComboBoxPanel cp;
	KitManager km;
	public AddPanel(KitManager km)
	{
		this.km = km;
		kip = new KitInfoPanel(km);
		cp = new ComboBoxPanel(km);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		kip.setVisible(true);
		add(kip);
		add(cp);
	}

}
