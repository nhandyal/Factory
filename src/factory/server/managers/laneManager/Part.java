import javax.swing.*;

public class Part extends FactoryObject
{
	public Part(int initialPosX, int initialPosY, int initialImage, int indx){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
		index = indx;
	}

	public void moveLeft(){
		x -= 2;
	}

	public void moveUp(){
		y -= 2;
	}

	public void moveDown(){
		y += 2;
	}
}
