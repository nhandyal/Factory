import java.awt.event.*;
import javax.swing.*;

public class Conveyor extends FactoryObject implements ActionListener{
	
	Kit inKit;
	Kit outKit;
	int inCount = 0;
	int outCount = 0;
	KitAssemblyManager m;
	Timer inTimer;
	Timer outTimer;
	int inStartx = 700;
	int inStarty = 50;
	int inFinishx = 400;
	int inFinishy = 50;
	int outStartx = 300;
	int outStarty = 50;
	int outFinishx = -100;
	int outFinishy = 50;
	
	public Conveyor(int xpos, int ypos, String image, KitAssemblyManager k){
		x = xpos;
		y = ypos;
		setImage(image);
		m = k;
		inTimer = new Timer(50,this);
		outTimer = new Timer(50,this);
	}
	
	public void bringKit(){
		inKit = new Kit(inStartx,inStarty,"src/kit.png");
		inCount = 0;
		inTimer.start();
		m.getKits().add(inKit);
	}
	
	public void takeKit(){
		outCount = 0;
		outTimer.start();
	}
	
	public Kit getInKit(){
		return inKit;
	}
	
	public void setInKit(Kit k){
		inKit = k;
	}
	
	public Kit getOutKit(){
		return outKit;
	}
	
	public void setOutKit(Kit k){
		outKit = k;
	}
	
	public int getInFinishX(){
		return inFinishx;
	}
	
	public int getInFinishY(){
		return inFinishy;
	}
	
	public int getOutStartX(){
		return outStartx;
	}
	
	public int getOutStartY(){
		return outStarty;
	}
	
	public boolean kitArrived(){
		if (inKit.getPositionX() == inFinishx && inKit.getPositionY() == inFinishy)
			return true;
		else
			return false;
	}
	
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource() == inTimer){
			inKit.setPosition(inKit.getPositionX() + (inFinishx - inStartx)/25, inKit.getPositionY() + (inFinishy - inStarty)/25);
			inCount++;
			m.repaint();
			if (inCount == 25){
				inTimer.stop();
			}
		}
		if (ae.getSource() == outTimer){
			outKit.setPosition(outKit.getPositionX() + (outFinishx - outStartx)/25, outKit.getPositionY() + (outFinishy - outStarty)/25);
			outCount++;
			m.repaint();
			if (outCount == 25){
				outTimer.stop();
				outKit = null;
				m.removeKit(outKit);
			}
		}
	}
	
}
