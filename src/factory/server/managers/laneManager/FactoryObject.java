public class FactoryObject {

	int x, y, xf, yf, index = -5;
	boolean isLine = false;
	int imageIndex;
	
	public FactoryObject(){
		x = 3;
		y = 4;
	}

	public  void setPosition(int newPosX, int newPosY){
		x = newPosX;
		y = newPosY;
	}
	
	public  void setPositionF(int newPosXF, int newPosYF){
		xf = newPosXF;
		yf = newPosYF;
	}

	public int getPositionX(){
		return x;
	}

	public int getPositionY(){
		return y;
	}

	public int getPositionXF(){
		return xf;
	}

	public int getPositionYF(){
		return yf;
	}
	
	public void setIsLine(boolean b){
		isLine = b;
	}

	public boolean getIsLine(){
		return isLine;
	}

	public void setImage(int newImageIndex){
		imageIndex = newImageIndex;
	}

	public int getImageIndex(){
		return imageIndex;
	}
	
	public void setIndex(int i){
		index = i;
	}
	
	public int getIndex(){
		return index;
	}
}
