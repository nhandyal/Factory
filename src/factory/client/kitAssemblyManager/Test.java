package factory.client.kitAssemblyManager;

import javax.swing.JFrame;


public class Test extends JFrame{

	public Test()
	{
		KitAssemblyManager k = new KitAssemblyManager();
		add(k);
	}
	public static void main(String[] args) {
		Test frame = new Test();
		frame.setSize(400, 670/*670 - 45*/);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		/*int i = 0;
		while (i < 8)
		{
			if (!k.probot.isMoving()){
				Part p = new Part(860,300,"images/part2.png");
				k.parts.add(p);
				k.probot.moveFromNest(200,k.stands.get(1),p,i);
				i++;
			}
		}*/
		

	}

}
