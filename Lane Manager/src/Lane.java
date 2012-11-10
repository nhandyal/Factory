import javax.swing.*;
import java.util.*;

public class Lane extends FactoryObject
{

	ArrayList<Part> lane, nest;
	ArrayList<LaneLine> lines;
	boolean laneActive;
	String imageName;

	public Lane(int initialPosX, int initialPosY, String image){
		x = initialPosX;
		y = initialPosY;
		imageName = image;
		
		lane = new ArrayList<Part>();
		nest = new ArrayList<Part>();
		lines = new ArrayList<LaneLine>();
		
		lines.add(new LaneLine((x+80),(y+1)));
		lines.add(new LaneLine((x+180),(y+1)));
		lines.add(new LaneLine((x+280),(y+1)));
		lines.add(new LaneLine((x+380),(y+1)));
	}

	public void setActive(int i){
		if(i==0)
			laneActive = true;
		else
			laneActive = false;
	}
	
	public boolean getActive(){
		return laneActive;
	}
	
	public void addPart(){
		lane.add(new Part(x+410,y+16,imageName));
	}
	
	public Part getLanePart(int i){
		return lane.get(i);
	}
	
	public Part getNestPart(int i){
		return nest.get(i);
	}

	public int getLaneSize(){
		return lane.size();
	}
	
	public int getNestSize(){
		return nest.size();
	}
	
	public LaneLine getLaneLine(int i){
		return lines.get(i);
	}

	public void moveParts(){
		if(laneActive == true){
			for(int i=0;i<lane.size();i++){
				if(nest.size()<9){
					if(lane.get(i).getPositionX()<=(x+40)){
						nest.add(lane.get(i));
						System.out.println("Part Added to Nest\nNest Size: "+nest.size());
						lane.remove(i);
					}
					lane.get(i).moveLeft();
				}
				if(nest.size()==9){
					if(lane.get(i).getPositionX()<=((x+50)+((i/3)*10))){
						if(i%3 == 0 && lane.get(i).getPositionY()>=(y+6))
							lane.get(i).moveUp();
						else if(i%3 == 1 && lane.get(i).getPositionY()<=(y+26))
							lane.get(i).moveDown();
					}
					else
						lane.get(i).moveLeft();
				}
			}
			for(int i=0;i<nest.size();i++){
				if(nest.get(i).getPositionX()<=((x+5)+((i/3)*15))){
					if(i%3 == 0 && nest.get(i).getPositionY()>=(y+6))
						nest.get(i).moveUp();
					else if(i%3 == 1 && nest.get(i).getPositionY()<=(y+26))
						nest.get(i).moveDown();
				}
				else
					nest.get(i).moveLeft();
			}
			for(int i=0;i<4;i++){
				lines.get(i).moveLeft();
				if(lines.get(i).getPositionX()<=(x+43))
					lines.get(i).reset();
			}
		}
	}
}
