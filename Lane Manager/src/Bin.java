import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

import java.util.*;

public class Bin extends FactoryObject
{

	ArrayList<Part> parts;

	public Bin(int initialPosX, int initialPosY, String initialImage){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		parts = new ArrayList<Part>();
	}

	public void fillBin(int partNum, int partType){
		if(partType == 1)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part" ));
			}
		}
		else if(partType == 2)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part2" ));
			}
		}
		else if(partType == 3)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part3" ));
			}
		}
		else if(partType == 4)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part4" ));
			}
		}
		else if(partType == 5)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part5" ));
			}
		}
		else if(partType == 6)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part6" ));
			}
		}
		else if(partType == 7)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part7" ));
			}
		}
		else if(partType == 8)
		{
			for(int i = 0; i < partNum; i++){
				parts.add(new Part(x, y, "part8" ));
			}
		}
	}


}