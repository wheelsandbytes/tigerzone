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
		tiles = new ArrayList<Integer>();
	}
	
	public TrailComposite(int i){
		components = new ArrayList<Trail>();
		tiles = new ArrayList<Integer>();
		compID = i;
	}
	
	public void add(Trail region){
		region.setID(compID);
		
		components.add(region);
		
		if(region.getEnd()){ 
			ends++;
			if(ends == 2) {
				complete = true;
				//tiger.getBack(); ???
			}
		}
		
		if(region.getPrey() != -1){ this.prey++; }
		
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
			this.add(r);
		}
		
		return remove;
	}
	
	public int score(){
		int tempScore = 0;

		tempScore = tiles.size() + (prey-crocodiles < 0 ? 0 : prey-crocodiles);
		return tempScore;
	}
	
	public String toString(){
		String s = "Roads:\n";
		for(Trail c : components){
			s += c.toString();
		}
		return s;
	}
}
