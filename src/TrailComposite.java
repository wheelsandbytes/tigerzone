import java.util.ArrayList;
import java.util.List;

public class TrailComposite {
	int compID; 
	int prey;
	int crocodiles;
	int score; //maybe
	int ends;
	List<Trail> components;
	List<Integer> tiles;
	boolean complete;
	
	public TrailComposite(){
		components = new ArrayList<Trail>();
	}
	
	public TrailComposite(int i){
		components = new ArrayList<Trail>();
		compID = i;
	}
	
	public void add(Trail region){
		components.add(region);
		
		if(region.getEnd()){ 
			ends++;
			if(ends == 2) {
				complete = true;
			}
		}
		
		if(region.prey != -1){ this.prey++; }
		
		if(!tiles.contains(region.getTileID())) { tiles.add(region.getTileID()); }
		
		if(region.getCrocodile()) { crocodiles++; }
	}
	
	public List<Trail> getList(){
		return components;
	}
	
	public void remove(Trail region){
		components.remove(region);
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
