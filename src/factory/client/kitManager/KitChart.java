package factory.client.kitManager;
import factory.global.data.*; 
import java.awt.*;
import java.util.*;
import javax.swing.*;
public class KitChart extends JComponent
{
	private TreeMap<Integer, Parts> currentParts;
	private static ImageIcon kit = new ImageIcon("bin/factory/global/assets/imageChart.png");
	private static double width = 169 / 4.0;
	private static double height = 104 / 2.0;
	public KitChart(TreeMap<Integer, Parts> parts)
	{
		currentParts = parts;
	}
	
	public void setParts(TreeMap<Integer, Parts> parts)
	{
		currentParts = parts;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		kit.paintIcon(this, g2, 0, 0);
		for (Integer key : currentParts.keySet())
		{
			ImageIcon parts = new ImageIcon("bin/factory/global/assets/part" + currentParts.get(key).getImageIndex() + ".png");
			parts.paintIcon(this, g2, (int)(key % 4 * width + width / 2), (int)(key / 4 * height + height / 4));
		}
	}
}
