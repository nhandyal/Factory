package factory.client.factoryManager;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import factory.global.data.*;

public class KASM extends JPanel{
	
		TreeMap<Integer, FactoryObject> fos;
		ImageIcon bg;
		ImageArray images = new ImageArray();
		FactoryManager parent;
    
		public KASM(FactoryManager parent){
				this.parent = parent;
				bg = new ImageIcon("bin/factory/global/assets/KMBG.png");		// Create Backgroud Image
				images = parent.getImageArray();														// Bind image array with factoryManager
				
				
				// set panel properties
				this.setMinimumSize(new Dimension(355,670));
				this.setMaximumSize(new Dimension(355,670));
				this.setPreferredSize(new Dimension(355,670));
		}
	
		public void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D)g;
				refreshAnimationData();
				
				
				bg.paintIcon(this, g2, 0, 0);
				for (Integer i : fos.keySet()){
						FactoryObject t = fos.get(i);
						if(t.getIsLine()){
								g2.setColor(Color.WHITE);
								g2.drawLine(t.getPositionX(), t.getPositionY(), t.getPositionXF(), t.getPositionYF());
						}	
						else{
								if (t.getImageIndex() >= 0){
										ImageIcon tmp = images.getIcon(t.getImageIndex());
										tmp.paintIcon(this, g2, t.getPositionX(), t.getPositionY());
								}
						}
				}
		}
		
		private void refreshAnimationData(){
				fos = parent.getFrameData(0);																// Bind frame animation data with factoryManager
		}
}
