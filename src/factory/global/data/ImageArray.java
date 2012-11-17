/*
** Authors:			Timi Okuboyejo
** Date: 			11/15/12
** Project: 		Cs200-Factory
** Description: 	Contains information for bins(parts, coordinates, etc)
** 					Is Serializable.
** 
** Pre-Conditions: 	None
** Post-Conditions: None
** 
*/
package factory.global.data;

// Java packages
import java.io.*;
import javax.swing.*;
import java.util.*;

public class ImageArray{
	ArrayList<ImageIcon> images;

	public ImageArray(){

		images = new ArrayList<ImageIcon>();
		images.add(new ImageIcon("bin/factory/global/assets/part1.png"));		// index 0		part 1
		images.add(new ImageIcon("bin/factory/global/assets/part2.png"));		// index 1		part 2
		images.add(new ImageIcon("bin/factory/global/assets/part3.png"));		// index 2		part 3
		images.add(new ImageIcon("bin/factory/global/assets/part4.png"));		// index 3		part 4
		images.add(new ImageIcon("bin/factory/global/assets/part5.png"));		// index 4		part 5
		images.add(new ImageIcon("bin/factory/global/assets/part6.png"));		// index 5		part 6
		images.add(new ImageIcon("bin/factory/global/assets/part7.png"));		// index 6		part 7
		images.add(new ImageIcon("bin/factory/global/assets/part8.png"));		// index 7		part 8
		images.add(new ImageIcon("bin/factory/global/assets/part9.png"));		// index 8		part 9
		images.add(new ImageIcon("bin/factory/global/assets/part10.png"));		// index 9		part 10
		images.add(new ImageIcon("bin/factory/global/assets/bin.png"));			// index 10		bin
		images.add(new ImageIcon("bin/factory/global/assets/robot.png"));		// index 11		Kit/Part Picking
		images.add(new ImageIcon("bin/factory/global/assets/kit.png"));			// index 12		Kit
		images.add(new ImageIcon("bin/factory/global/assets/camera.png"));		// index 13		QA/Nest Cam
		images.add(new ImageIcon("bin/factory/global/assets/camflash.png"));	// index 14		Cam Flash
		images.add(new ImageIcon("bin/factory/global/assets/camline.png"));		// index 15		Nest Cam Line
        images.add(new ImageIcon("bin/factory/global/assets/gripper.png"));     // index 16     Gripper
        images.add(new ImageIcon("bin/factory/global/assets/imageChart.png"));  // index 17     Image Chart
        images.add(new ImageIcon("bin/factory/global/assets/gantry.png"));		// index 18		Gantry
	}

	public ImageIcon getIcon(int i){
		return images.get(i);
	}
}