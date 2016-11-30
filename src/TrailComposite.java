import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrailComposite {
	int compID; 
	int prey;
	int crocodiles;
	int score; //maybe
	public int ends;
	int totalTigers = 0;
	HashMap<Player, Integer> tigerCount = new HashMap<Player, Integer>();
	List<Trail> components;
	List<Integer> tiles;
	List<Tiger> placedTigers;
	public boolean complete;
	
	public TrailComposite(){
		components = new ArrayList<Trail>();
		tiles = new ArrayList<Integer>();
		placedTigers = new ArrayList<Tiger>();
	}
	
	public TrailComposite(int i){
		components = new ArrayList<Trail>();
		tiles = new ArrayList<Integer>();
		placedTigers = new ArrayList<Tiger>();
		compID = i;
	}
	
	public void add(Trail region){
		placedTigers.addAll(region.placedMeeples);
		region.setID(compID);
		region.setComp(this);
		components.add(region);
		
		if(region.getEnd()){
			ends++;
			if(ends == 2) {
				complete = true;
			}
		}
		
		if(region.getPrey() != GameInfo.NONE){ this.prey++; }
		
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
		
		for(Player p : comp.tigerCount.keySet()){
			tigerCount.put(p, tigerCount.get(p) == null ? comp.tigerCount.get(p).intValue() : tigerCount.get(p).intValue()+comp.tigerCount.get(p));
		}
		
		return remove;
	}
	
	public int score(){
		int tempScore = 0;

		tempScore = tiles.size() + (prey-crocodiles < 0 ? 0 : prey-crocodiles);
		return tempScore;
	}
	
	public boolean checkComplete(){
		return ends == 2;
	}
	
	public void placeTiger(Tiger t){
		placedTigers.add(t);
		totalTigers++;
		tigerCount.put(t.getPlayer(), tigerCount.get(t.getPlayer()) == null ? 1 : tigerCount.get(t.getPlayer()).intValue()+1);
	}
	
	public void returnTigers(){
		Player max = null;
		int m = -1;
		for(Tiger t : placedTigers){
			if(tigerCount.get(t.getPlayer()).intValue() > m) { max = t.getPlayer(); }
			t.getBack();
		}
		placedTigers.clear();
		
		if(max != null) { 
			max.updateScore(this.score()); 
		}
		tigerCount.clear();
	}
	
	public String toString(){
		String s = "Roads:\n";
		for(Trail c : components){
			s += c.toString();
		}
		s += tigerCount.toString();
		return s;
	}

	public void returnTiger(Tiger t) {
		totalTigers--;
		tigerCount.put(t.getPlayer(), tigerCount.get(t.getPlayer()).intValue()-1);
		placedTigers.get(placedTigers.indexOf(t)).getBack();
	}
}
