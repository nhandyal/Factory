package factory.client.kitAssemblyManager;
import javax.swing.*;
class KAMNonNormFrame extends JFrame
{
	public KAMNonNormFrame()
	{
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
		KitAssemblyManager k = new KitAssemblyManager();
		KAMNonNormPanel kn = new KAMNonNormPanel();
		p.add(k);
		p.add(kn);
		add(p);
	}
	public static void main(String[] args)
	{
		KAMNonNormFrame frame = new KAMNonNormFrame();
		frame.setSize(530, 670);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
