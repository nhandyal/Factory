package factory.client.laneManager;

// Java packages
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*; 
import javax.swing.Timer;
import java.util.*;

// user packages
import factory.global.network.*;
import factory.global.data.*;

public class LMANIM extends JPanel{// implements ActionListener, NetworkManager{

	TreeMap<Integer,FactoryObject> frameAnimationData;

	ImageIcon background;
	ImageArray images;
	LaneManager parent;

	public LMANIM(LaneManager l){

		parent = l;

		// Create Backgroud Image
		background = new ImageIcon("bin/factory/global/assets/LMBG.png");
		
		images = new ImageArray();
		frameAnimationData = new TreeMap<Integer,FactoryObject>();

		setMinimumSize(new Dimension(400,670));
		setMaximumSize(new Dimension(400,670));
		setPreferredSize(new Dimension(400,670));
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		background.paintIcon(this,g2,0,0);

		frameAnimationData = parent.getMap();

		// Paint Updated List
		Iterator k = frameAnimationData.keySet().iterator();
		while(k.hasNext()){
			int i = (Integer) k.next();
			if(i != 0 && frameAnimationData.get(i).getIndex()> 0){
				if(frameAnimationData.get(i).getIsLine()== true){	// if object is a line draw a line
					if(frameAnimationData.get(i).getPositionX() == frameAnimationData.get(i).getPositionXF())
						g2.setColor(Color.black);
					else
						g2.setColor(Color.gray);
					g2.drawLine(frameAnimationData.get(i).getPositionX(),frameAnimationData.get(i).getPositionY(),frameAnimationData.get(i).getPositionXF(),frameAnimationData.get(i).getPositionYF());
				}
				else{ 										//if object is not a line draw an ImageIcon
					int img = frameAnimationData.get(i).getImageIndex();
					images.getIcon(img).paintIcon(this,g2,frameAnimationData.get(i).getPositionX(),frameAnimationData.get(i).getPositionY());
				}
			}
		}
	}
}