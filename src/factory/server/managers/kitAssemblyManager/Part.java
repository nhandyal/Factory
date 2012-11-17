package factory.server.managers.kitAssemblyManager;

import factory.global.data.*;

import javax.swing.*;

public class Part extends FactoryObject
{
	public Part(int initialPosX, int initialPosY, int initialImage){
		super(initialPosX, initialPosY, initialImage);
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