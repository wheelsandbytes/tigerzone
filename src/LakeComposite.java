import java.util.ArrayList;
import java.util.List;

public class LakeComposite {
	int compID; 
	int prey;
	int score;
	int crocodiles;
	List<Lake> components;
	List<Integer> tiles;
	boolean[] uniquePrey; //Used to determine if a prey species has been added
	boolean complete;
	
	public LakeComposite(){
		components = new ArrayList<Lake>();
		uniquePrey =new boolean[3];
	}
	
	public LakeComposite(int i){
		components = new ArrayList<Lake>();
		compID = i;
		uniquePrey = new boolean[3];
	}
	
	public List<Lake> getList(){
		return components;
	}
	
	public void remove(Lake region){
		components.remove(region);
	}
	
	public void add(Lake region){
		components.add(region);
		
		if(!uniquePrey[region.prey]){ uniquePrey[region.prey] = true; this.prey++; }
		
		if(!tiles.contains(region.getTileID())) { tiles.add(region.getTileID()); }
		
		if(region.getCrocodile()) { crocodiles++; }
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(LakeComposite comp){
		int remove = comp.getID();
		for (Lake r : comp.getList()){
			r.setID(this.getID());
			this.add(r);
		}
		
		return remove;
	}
	
	public void score(){
		int perTile = 1;
		if(complete){
			perTile = 2;
		}
		
		score = (perTile*tiles.size()) * (prey-crocodiles);
	}
	
	public boolean checkComplete(){
		for(Lake l : components){
			if(l.getCaps() != 4){
				return false;
			}
		}
		complete = true;
		return true;
	}
	
	public String toString(){
		String s = "Cities:\n";
		for(Lake c : components){
			s += c.toString();
		}
		return s;
	}
}

