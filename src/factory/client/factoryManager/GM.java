package factory.client.factoryManager;

// Java packages
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*; 
import java.util.*;

// user packages
import factory.global.data.*;

public class GM extends JPanel{
		TreeMap<Integer,FactoryObject> frameAnimationData;
		ImageIcon background;
		ImageArray images;
		FactoryManager parent;
		
		public GM(FactoryManager parent){
				this.parent = parent;
				background = new ImageIcon("bin/factory/global/assets/GMBG.png");		// Create Backgroud Image
				images = parent.getImageArray();																		// Bind image array with factoryManager
				
				
				// set panel properties
				this.setMinimumSize(new Dimension(400,670));
				this.setMaximumSize(new Dimension(400,670));
				this.setPreferredSize(new Dimension(400,670));
		}
		
    public void paintComponent(Graphics g){
				Graphics2D g2 = (Graphics2D)g;
				refreshAnimationData();
				background.paintIcon(this,g2,0,0);

				// Paint Updated List
				Iterator k = frameAnimationData.keySet().iterator();
				while(k.hasNext()){
						int i = (Integer) k.next();
						if(i != 0 && frameAnimationData.get(i).getIndex()> 0){
								if(frameAnimationData.get(i).getIsLine()== true)	// if object is a line draw a line
									g2.drawLine(frameAnimationData.get(i).getPositionX(),frameAnimationData.get(i).getPositionY(),frameAnimationData.get(i).getPositionXF(),frameAnimationData.get(i).getPositionYF());
								else{ 										//if object is not a line draw an ImageIcon
										int img = frameAnimationData.get(i).getImageIndex();
										images.getIcon(img).paintIcon(this,g2,frameAnimationData.get(i).getPositionX(),frameAnimationData.get(i).getPositionY());
								}
						}
				}	
    }
		
		private void refreshAnimationData(){
				frameAnimationData = parent.getFrameData(2);																// Bind frame animation data with factoryManager
		}
}
