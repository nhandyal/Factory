import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class KitAssemblyManager extends JFrame implements ActionListener {
	
	Rectangle r = new Rectangle(800,800);
	InspectionCamera cam;
	Conveyor conv;
	KitRobot robot;
	PartRobot probot;
	ArrayList<KitStand> stands;
	ArrayList<Kit> kits;
	ArrayList<Part> parts;
	Timer t;
	
	public KitAssemblyManager(){
		cam = new InspectionCamera(100,700,"src/camera.jpg",this);
		conv = new Conveyor(0,50,"src/conveyor.jpg",this);
		robot = new KitRobot(200,400,"src/kitrobot.png",this);
		probot = new PartRobot(400,400,"src/partrobot.png",this);
		stands = new ArrayList<KitStand>();
		kits = new ArrayList<Kit>();
		parts = new ArrayList<Part>();
		KitStand ks = new KitStand(100,200,"src/kitstand.jpg",this);
		KitStand ks2 = new KitStand(100,400,"src/kitstand.jpg",this);
		KitStand ks3 = new KitStand(100,600,"src/kitstand.jpg",this);
		stands.add(ks);
		stands.add(ks2);
		stands.add(ks3);
		Kit k = new Kit(100,400,"src/kit.png");
		ks2.setCurrentKit(k);
		kits.add(k);
		t = new Timer(50,this);
		t.start();
	}
	
	public void actionPerformed(ActionEvent ae){
		if (conv.getInKit() == null)
			conv.bringKit();
		if (conv.getOutKit() != null)
			conv.takeKit();
		if (!robot.getIsMoving() && emptyStand() && conv.kitArrived()){
			for (int i = 0; i < 2; i++){
				if (stands.get(i).getKit() == null){
					robot.moveFromConveyorToStand(conv, stands.get(i), conv.getInKit());
					break;
				}
			}
		}
		if (!robot.getIsMoving() && stands.get(2).getKit() == null){
			for (int i = 0; i < 2; i++){
				if (stands.get(i).getKit() != null){
					robot.moveFromStandToStand(stands.get(i),stands.get(2), stands.get(i).getKit());
					break;
				}
			}
		}
		if (!robot.getIsMoving() && stands.get(2).getKit() != null && !stands.get(2).getKit().getPicTaken() && !cam.isMoving()){
			cam.takePicture(stands.get(2));
		}
		if (!robot.getIsMoving() && stands.get(2).getKit() != null && stands.get(2).getKit().getPicTaken()){
			robot.moveFromStandToConveyor(stands.get(2), conv, stands.get(2).getKit());
		}
	}
	
	public boolean emptyStand(){
		for (int i = 0; i < 2; i++){
			if (stands.get(i).getKit() == null)
				return true;
		}
		return false;
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.GRAY);
		g2.fillRect(0,0,800,800);
		conv.getImage().paintIcon(this,g2,conv.getPositionX(),conv.getPositionY());
		robot.getImage().paintIcon(this, g2, robot.getPositionX(), robot.getPositionY());
		probot.getImage().paintIcon(this, g2, probot.getPositionX(), probot.getPositionY());
		for (int i = 0; i < stands.size(); i++)
			stands.get(i).getImage().paintIcon(this, g2, stands.get(i).getPositionX(), stands.get(i).getPositionY());
		for (int i = 0; i < kits.size(); i++)
			kits.get(i).getImage().paintIcon(this, g2, kits.get(i).getPositionX(), kits.get(i).getPositionY());
		for (int i = 0; i < parts.size(); i++)
			parts.get(i).getImage().paintIcon(this, g2, parts.get(i).getPositionX(), parts.get(i).getPositionY());
		g2.setColor(Color.BLACK);
		g2.drawLine((int)robot.getX1(),(int)robot.getY1(),(int)robot.getX2(),(int)robot.getY2());
		g2.drawLine((int)probot.getX1(),(int)probot.getY1(),(int)probot.getX2(),(int)probot.getY2());
		if (cam.takePicture){
			g2.setColor(Color.YELLOW);
			g2.fillOval(cam.getPositionX()-5,cam.getPositionY()-5, cam.getImage().getIconWidth()+10, cam.getImage().getIconWidth()+10);
		}
		cam.getImage().paintIcon(this,g2,cam.getPositionX(),cam.getPositionY());
	}
	
	public ArrayList<Kit> getKits(){
		return kits;
	}
	
	public int getWindowWidth(){
		return r.width;
	}
	
	public void removeKit(Kit k){
		for (int i = 0; i < kits.size(); i++){
			if (k.equals(kits.get(i)))
			{
				Kit k1 = kits.remove(i);
				System.out.println("Removed kit at" + k1.getPositionX() + " " + k1.getPositionY());
				break;
			}
		}
	}
}
