import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LakeComposite {
	int compID; 
	int prey;
	int score;
	int crocodiles;
	int totalTigers = 0;
	HashMap<Player, Integer> tigerCount = new HashMap<Player, Integer>();
	List<Lake> components;
	List<Integer> tiles;
	List<Tiger> placedTigers;
	boolean[] uniquePrey; //Used to determine if a prey species has been added
	boolean complete;
	
	public LakeComposite(){
		components = new ArrayList<Lake>();
		tiles = new ArrayList<Integer>();
		placedTigers = new ArrayList<Tiger>();
		uniquePrey =new boolean[3];
	}
	
	public LakeComposite(int i){
		components = new ArrayList<Lake>();
		tiles = new ArrayList<Integer>();
		placedTigers = new ArrayList<Tiger>();
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
		region.setID(compID);
		region.setComp(this);
		components.add(region);
		
		if(region.getPrey() != GameInfo.NONE){
			if(!uniquePrey[region.getPrey()]){ uniquePrey[region.getPrey()] = true; this.prey++; }
		}
		
		if(!tiles.contains(region.getTileID())) { tiles.add(region.getTileID()); }
		
		if(region.getCrocodile()) { crocodiles++; }
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(LakeComposite comp){
		int remove = comp.getID();
		for (Lake r : comp.getList()){
			this.add(r);
		}
		
		return remove;
	}
	
	public int score(){
		int tempScore = 0;
		int perTile = 1;
		if(complete){
			perTile = 2;
		}
		
		tempScore = (perTile*tiles.size()) * (1+prey-crocodiles < 1 ? 1 : 1+prey-crocodiles);
		return tempScore;
	}
	
	public boolean checkComplete(){
		for(Lake l : components){
			if(l.getCaps() != 4){
				return false;
			}
		}
		complete = true;
		for(Lake l : components){
			l.setComplete(true);
		}
		returnTigers();
		return true;
	}
	
	public void placeTiger(Tiger t){
		totalTigers++;
		tigerCount.put(t.getPlayer(), tigerCount.get(t.getPlayer()).intValue()+1);
	}
	
	public void returnTigers(){
		for(Tiger t : placedTigers){
			t.getBack();
			placedTigers.remove(t);
		}
	}
	
	public void returnTiger(Tiger t) {
		totalTigers--;
		tigerCount.put(t.getPlayer(), tigerCount.get(t.getPlayer()).intValue()-1);
		placedTigers.get(placedTigers.indexOf(t)).getBack();
	}
	
	public String toString(){
		String s = "Cities:\n";
		for(Lake c : components){
			s += c.toString();
		}
		s += tigerCount.toString();
		return s;
	}
}

