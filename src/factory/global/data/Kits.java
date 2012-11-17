public class Kits{

	String name; 
	TreeMap<Integer, Parts> listOfParts; 
	String description; 
	int kitId; 
	public Kits(String name, TreeMap<Integer, Parts> listOfParts,String description, int kitID){
		this.name = name; 
		this.listOfParts = listOfParts; 
		this.description = description; 
		this.kitId = kitID; 

	}
	


}