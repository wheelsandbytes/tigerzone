package GameEntities.Regions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GameEntities.Tile.Tiger;
import Players.Player;

public class JungleComposite {
	int compID; 
	int prey;
	int score;
	int totalTigers = 0;
	HashMap<Player, Integer> tigerCount = new HashMap<Player, Integer>();
	public List<Tiger> placedTigers;
	List<Jungle> components;
	List<Lake> adjLakes;
	List<Den> adjDens;
	
	public JungleComposite(){
		components = new ArrayList<Jungle>();
		placedTigers = new ArrayList<Tiger>();
		adjLakes = new ArrayList<Lake>();
		adjDens = new ArrayList<Den>();
	}
	
	public JungleComposite(int i){
		components = new ArrayList<Jungle>();
		placedTigers = new ArrayList<Tiger>();
		adjLakes = new ArrayList<Lake>();
		adjDens = new ArrayList<Den>();
		compID = i;
	}
	
	public List<Jungle> getList(){
		return components;
	}
	
	public void remove(Jungle region){
		components.remove(region);
	}
	
	public void add(Jungle region){
		placedTigers.addAll(region.placedMeeples);
		region.setID(compID);
		region.setComp(this);
		components.add(region);
		
		adjLakes.addAll(region.getLakes());
		
		if(region.getDen() != null){
			
			if(!adjDens.contains(region.getDen())){
				adjDens.add(region.getDen());
			}
		}
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(JungleComposite comp){
		int remove = comp.getID();
		for (Jungle r : comp.getList()){
			this.add(r);
		}
		
		for(Player p : comp.tigerCount.keySet()){
			tigerCount.put(p, tigerCount.get(p) == null ? comp.tigerCount.get(p).intValue() : tigerCount.get(p).intValue()+comp.tigerCount.get(p).intValue());
		}
		
		return remove;
	}
	
	public int score(){
		int score = 0;
		List<Integer> ids = new ArrayList<Integer>();
		for(Lake l : adjLakes){
			if(!ids.contains(l.getID())){
				if(l.getCompleted()){
					score += 3;
				}
				ids.add(l.getID());
			}
		}
		
		for(Den d : adjDens){
			if(d.getCompleted()){
				score += 5;
			}
		}
		return score;
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
			if(tigerCount.get(t.getPlayer()) > m) { max = t.getPlayer(); }
			t.getBack();
		}
		placedTigers.clear();
		
		if(max != null) { max.updateScore(this.score()); }
		tigerCount.clear();
	}
	
	public String toString(){
		String s = "Fields:\n";
		for(Jungle c : components){
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
