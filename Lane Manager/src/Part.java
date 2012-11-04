import javax.swing.*;

public class Part
{
	int xcor, ycor;
	ImageIcon image;

	public Part(int x, int y){
		xcor = x;
		ycor = y;
		image = new ImageIcon("image.png");
	}

	public void moveLeft(){
		xcor -= 2;
	}

	public void moveUp(){
		ycor -= 2;
	}

	public void moveDown(){
		ycor += 2;
	}

	public ImageIcon getImage(){
		return image;
	}

	public int getXCor(){
		return xcor;
	}

	public int getYCor(){
		return ycor;
	}
}