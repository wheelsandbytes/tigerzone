package GameEntities.Regions;
import java.util.List;

import GameEntities.Tile.Tiger;

public class RegionComposite<T extends Region> {
	int compID; 
	int prey;
	int score; //maybe
	List<T> components;
	List<Tiger> tigers;
	int[] tigerCounts = {0, 0};
	
	public RegionComposite(int i){
		compID = i;
	}
	
	public List<T> getList(){
		return components;
	}
	
	public void remove(T region){
		components.remove(region);
	}
	
	public void add(T region){
		components.add(region);
	}
	
	public int getID(){
		return compID;
	}
	
	public void merge(RegionComposite<T> comp){
		for (T r : comp.getList()){
			r.setID(this.getID());
			this.add(r);
		}
	}
}
