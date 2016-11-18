import java.util.ArrayList;
import java.util.List;

public class CityComposite {
	int compID; 
	int prey;
	int score; //maybe
	List<City> components;
	
	public CityComposite(){
		components = new ArrayList<City>();
	}
	
	public CityComposite(int i){
		components = new ArrayList<City>();
		compID = i;
	}
	
	public List<City> getList(){
		return components;
	}
	
	public void remove(City region){
		components.remove(region);
	}
	
	public void add(City region){
		components.add(region);
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(CityComposite comp){
		int remove = comp.getID();
		for (City r : comp.getList()){
			r.setID(this.getID());
			this.add(r);
		}
		
		return remove;
	}
	
	public String toString(){
		String s = "Cities:\n";
		for(City c : components){
			s += c.toString();
		}
		return s;
	}
}

