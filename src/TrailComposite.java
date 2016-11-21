import java.util.ArrayList;
import java.util.List;

public class TrailComposite {
	int compID; 
	int prey;
	int score; //maybe
	List<Trail> components;
	
	public TrailComposite(){
		components = new ArrayList<Trail>();
	}
	
	public TrailComposite(int i){
		components = new ArrayList<Trail>();
		compID = i;
	}
	
	public List<Trail> getList(){
		return components;
	}
	
	public void remove(Trail region){
		components.remove(region);
	}
	
	public void add(Trail region){
		components.add(region);
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(TrailComposite comp){
		int remove = comp.getID();
		for (Trail r : comp.getList()){
			r.setID(this.getID());
			this.add(r);
		}
		
		return remove;
	}
	
	public String toString(){
		String s = "Roads:\n";
		for(Trail c : components){
			s += c.toString();
		}
		return s;
	}
}
