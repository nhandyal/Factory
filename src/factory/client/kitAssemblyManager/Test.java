package factory.client.kitAssemblyManager;

import javax.swing.JFrame;


public class Test{

	public static void main(String[] args) {
		KitAssemblyManager k = new KitAssemblyManager();
		k.setSize(400, 670/*670 - 45*/);
		k.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		k.setVisible(true);
		System.out.println(System.getProperty("user.dir"));
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
