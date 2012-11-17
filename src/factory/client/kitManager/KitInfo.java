package factory.client.kitManager;
import factory.global.data.*; 
import javax.swing.*; 
import java.awt.*; 
import java.util.*;

public class KitInfo extends JPanel{
	JPanel kitInfo;
	JTextField kitNameText;
	JTextField kitIDNumber; 
	JTextField kitDescription;
	JLabel kitChart; 
	ImageArray iA; 
	public KitInfo(){
		kitInfo = new JPanel();		
		kitInfo.setLayout(new BoxLayout(kitInfo, BoxLayout.Y_AXIS));
		kitNameText = new JTextField("Name", 10);
		kitInfo.add(kitNameText); 
		kitIDNumber = new JTextField("ID", 10);
		kitInfo.add(kitIDNumber); 
		kitDescription = new JTextField("Brief Description of Kit", 10);
		kitInfo.add(kitDescription); 
		iA = new ImageArray(); 
		kitChart = new JLabel(iA.getIcon(17));
		kitInfo.add(kitChart); 
		add(kitInfo);

	}


	public JPanel getKitInfo(){
		return kitInfo; 
	}

	public JTextField getKitNameText(){
		return kitNameText; 
	}

	public JTextField getKitIDNumber(){
		return kitIDNumber; 
	}

	public JTextField getKitDescription(){
		return kitDescription;
	}

	public JLabel getKitChart(){
		return kitChart;
	}

	public void setKitInfo(JPanel kitInfo){
		this.kitInfo = kitInfo; 
	}

	public void setKitNameText(JTextField kitNameText){
		this.kitNameText = kitNameText; 
	}

	public void setKitIDNumber(JTextField kitIDNumber){
		this.kitIDNumber = kitIDNumber; 
	}

	public void setKitDescription(JTextField kitDescription){
		this.kitDescription = kitDescription; 
	}

	public void setKitChart(JLabel kitChart){
		this.kitChart = kitChart; 
	}
}