package global.data;
import java.util.ArrayList;

public class FactoryObject {

	int x;
	int y;

	ImageIcon factoryObjectImage;

	public void FactoryObject (int initialPosX, int initialPosY, String initialImage){
		x = initialPosX;
		y = initialPosY;
		setImage(initialImage);
	}

	public  void setPosition(int newPosX, int newPosY){
		x = newPosX;
		y = newPosY;
	}

	public int getPositionX(){
		return x;
	}

	public int getPositionY(){
		return y;
	}

	public void setImage(String newImage){
		factoryObjectImage = new ImageIcon(newImage);
	}

	public ImageIcon getImage(){
		return factoryObjectImage;
	}

}