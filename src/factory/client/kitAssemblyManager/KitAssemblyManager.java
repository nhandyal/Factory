package factory.client.kitAssemblyManager;

import java.awt.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;
import factory.global.network.*;
import factory.global.data.*;

public class KitAssemblyManager extends JPanel implements ActionListener, NetworkManager {
	
		Timer t;
		TreeMap<Integer, FactoryObject> fos = new TreeMap<Integer, FactoryObject>();
		ImageIcon bg = new ImageIcon("bin/factory/global/assets/KMBG.png");
    Image iBuffer;
		ImageArray images = new ImageArray();
    NetworkBridge nb;
		public KitAssemblyManager(){
				t = new Timer(25,this);
        nb = new NetworkBridge(this, "localhost", 8465, 4);
				nb.sync();
				t.start();
		}
	
		public void actionPerformed(ActionEvent ae){
				repaint();
				//Graphics g = this.getGraphics();
				//update(g);
		}
	
		public void paint(Graphics g){
				Graphics2D g2 = (Graphics2D)g;
				bg.paintIcon(this, g2, 0, 0);
                TreeMap<Integer, FactoryObject> to = (TreeMap<Integer, FactoryObject>)fos.clone();
				for (Integer i : to.keySet()){
						FactoryObject t = to.get(i);
						if(t.getIsLine()){
								g2.setColor(Color.WHITE);
								g2.drawLine(t.getPositionX(), t.getPositionY(), t.getPositionXF(), t.getPositionYF());
						}	
						else{
								ImageIcon tmp = images.getIcon(t.getImageIndex());
								tmp.paintIcon(this, g2, t.getPositionX(), t.getPositionY());
						}
				}
		}
	
		public void update(Graphics g){  
				if(iBuffer == null){  
						iBuffer = this.createImage(400, 670);  
				}  
				Graphics gOffScreen = iBuffer.getGraphics();
				paint(gOffScreen);  
				g.drawImage(iBuffer, 0, 0, null);
		}
    
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- Network Manager ---------------------------------- //
		// -------------------------------------------------------------------------------------- //
		
		// server specific
    public void registerClientListener(NetworkBridge newBridge, int cID){}
    public void syncFrame(){}
    public void updatePartData(TreeMap<Integer, Parts> partData){}
    public void updateBuildData(ArrayList<Kits> buildData){}
		public void updateBreakData(String breakCommand, int cID, int x){}
		
    // client specific
    public void mergeChanges(ArrayList<TreeMap<Integer, Boolean>> mapArray, ArrayList<TreeMap<Integer, FactoryObject>> dataArray){
        if(mapArray.size() == 1){
            TreeMap<Integer, Boolean> changeMap = mapArray.get(0);
            TreeMap<Integer, FactoryObject> changeData = dataArray.get(0);
            
            // iterate over all the keys present in changeMap
            // after this loop is complete, the frameAnimationData map will be accurately synced with the server copy
            Set<Integer> t = changeMap.keySet();
            for (Integer i : t){
                if (changeMap.get(i)){
                    fos.put(i, changeData.get(i));
                }
                else{
                    fos.remove(i);
                }
						}
        }
        else{
            System.out.println("Warning: Corrupt frame data");
        }
    }
    
    public void syncChanges(ArrayList<TreeMap<Integer, FactoryObject>> dataArray){
        if (dataArray.size() == 1){
						TreeMap<Integer, FactoryObject> changeData = dataArray.get(0);
						fos.clear();
						fos = changeData;
				}
				else{
						System.out.println("Warning: Corrupt frame data");
				}
    }
    
    // global
    public void closeNetworkBridge(int bridgeID){
        nb.close();
    }
    
    public void updateKitData(TreeMap<Integer, Kits> kitData){}

    
		
		// -------------------------------------------------------------------------------------- //
		// ----------------------------------- End Network Manager ------------------------------ //
		// -------------------------------------------------------------------------------------- //
}
