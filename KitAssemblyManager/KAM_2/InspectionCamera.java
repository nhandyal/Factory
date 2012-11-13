import java.awt.event.*;
import javax.swing.*;

public class InspectionCamera extends FactoryObject implements ActionListener{
	
	int count = 0;
	Timer t;
	int defaultx;
	int defaulty;
	int initialx;
	int initialy;
	int finalx;
	int finaly;
	KitAssemblyManager m;
	Kit k;
	boolean takePicture;
	boolean isMoving = false;
	
	public InspectionCamera(int xpos, int ypos, String image, KitAssemblyManager k){
		x = xpos;
		y = ypos;
		defaultx = x;
		defaulty = y;
		setImage(image);
		t = new Timer(50,this);
		m = k;
	}
	
	public void takePicture(KitStand s){
		isMoving = true;
		initialx = x;
		initialy = y;
		finalx = s.getPositionX();
		finaly = s.getPositionY();
		count = 0;
		k = s.getKit();
		t.start();
	}
	
	public void reset(){
		initialx = x;
		initialy = y;
		finalx = defaultx;
		finaly = defaulty;
	}
	
	public boolean isMoving(){
		return isMoving;
	}
	
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == t)
		{
			if (count < 25){
				x += (finalx - initialx)/25;
				y += (finaly - initialy)/25;
				count++;
				m.repaint();
			}
			if (count >= 25 && count < 30)
			{	
				takePicture = true;
				count++;
				m.repaint();
			}
			else if (count == 30)
			{
				reset();
				takePicture = false;
				k.setPicTaken(true);
				count++;
				m.repaint();
			}
			else if (count >= 30 && count < 56){
				x += (finalx - initialx)/25;
				y += (finaly - initialy)/25;
				count++;
				m.repaint();
			}
			else if (count == 56)
			{
				isMoving = false;
				t.stop();
			}
		}
	}
}
