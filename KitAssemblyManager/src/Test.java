import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test extends JFrame implements ActionListener
{

	int vx = 1;
	int vy = 1;
	int x = 0;
	int y = 0;
	private Image iBuffer;
	Rectangle r = new Rectangle(400, 400);
	Test()
	{
		setSize(400, 400);
	}
	public static void main(String[] args) 
	{
		Test frame = new Test();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Timer(20, frame).start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		x += vx;
		y += vy;
		Graphics g = this.getGraphics();
		update(g);
		
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 400, 400);
		g2.setColor(Color.RED);
		g2.fillOval(x, y, 10, 10);
		
	}
	
	public void update(Graphics g) 
	{  
		  if(iBuffer == null)
		  {  
		      iBuffer = this.createImage(800, 800);  
		  }  
		  Graphics gOffScreen = iBuffer.getGraphics();  
		  Color c = gOffScreen.getColor();  
		  gOffScreen.setColor(Color.BLUE);  
		  gOffScreen.fillRect(0, 0, 800, 800);  
		  gOffScreen.setColor(c);  
		  paint(gOffScreen);  
		  g.drawImage(iBuffer, 0, 0, null);  
	} 
	
}
