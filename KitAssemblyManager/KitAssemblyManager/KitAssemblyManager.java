package KitAssemblyManager;

import java.awt.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;

public class KitAssemblyManager extends JFrame implements ActionListener {
	
	Rectangle r = new Rectangle(800,800);
	Timer t;
	UpdateServer us = new UpdateServer();
	TreeMap<Integer, Boolean> ChangeMap;
	TreeMap<Integer, FactoryObject> ChangeData;
	ArrayList<FactoryObject> fos;
	public KitAssemblyManager(){
		t = new Timer(50,this);
		fos = us.getCurrentObjects();
		t.start();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		//fos = us.getCurrentObjects();
		ChangeMap = us.getChangeMap();
		ChangeData = us.getChangeData();
		Set<Integer> t = ChangeMap.keySet();
		for (Integer i : t)
		{
			if (ChangeMap.get(i))
				fos.set(i, ChangeData.get(i));
			else
				fos.remove(i);
		}
		repaint();
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GRAY);
		g2.fillRect(0,0,800,800);
		for (int i = 0; i < fos.size(); i++)
		{
			FactoryObject t = fos.get(i);
			if(t.isLine())
			{
				g2.setColor(Color.WHITE);
				g2.drawLine(t.getPositionX(), t.getPositionY(), t.getXF(), t.getYF());
			}	
			else
				t.getImage().paintIcon(this, g2, t.getPositionX(), t.getPositionY());
		}
	}
}
