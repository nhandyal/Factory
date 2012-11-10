import javax.swing.*;

public class LaneLine extends FactoryObject
{
	public LaneLine(int initialPosX, int initialPosY){
		x = initialPosX;
		y = initialPosY;
		setImage("line.png");
	}

	public void moveLeft(){
		x -= 2;
	}
	
	public void reset(){
		x += 400;
	}
}
