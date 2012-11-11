import javax.swing.*;

public class Line extends FactoryObject
{
	public Line(int initialPosX, int initialPosY, int xFixed, int yFixed){
		x = initialPosX;
		y = initialPosY;
		xf = xFixed;
		yf = yFixed;
		isLine = true;
	}

	public void moveLeft(){
		x -= 2;
		xf = x;
	}
	
	public void reset(){
		x += 300;
		xf = x;
	}

	public void dividerUp(){
		y = yf - 35;
	}

	public void dividerNeutral(){
		y = yf;
	}

	public void dividerDown(){
		y = yf + 35;
	}
}
