import javax.swing.*;

public class Part extends FactoryObject
{
	public Part(int initialPosX, int initialPosY, String initialImage){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
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
