import java.util.List;

public class RoadComposite {
	int compID; 
	int prey;
	int score; //maybe
	List<Road> components;
	
	public RoadComposite(int i){
		compID = i;
	}
	
	public List<Road> getList(){
		return components;
	}
	
	public void remove(Road region){
		components.remove(region);
	}
	
	public void add(Road region){
		components.add(region);
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(RoadComposite comp){
		int remove = comp.getID();
		for (Road r : comp.getList()){
			r.setID(this.getID());
			this.add(r);
		}
		
		return remove;
	}
}
