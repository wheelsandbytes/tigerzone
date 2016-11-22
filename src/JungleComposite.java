import java.util.ArrayList;
import java.util.List;

public class JungleComposite {
	int compID; 
	int prey;
	int score;
	List<Jungle> components;
	List<Lake> adjLakes;
	List<Den> adjDens;
	
	public JungleComposite(){
		components = new ArrayList<Jungle>();
	}
	
	public JungleComposite(int i){
		components = new ArrayList<Jungle>();
		compID = i;
	}
	
	public List<Jungle> getList(){
		return components;
	}
	
	public void remove(Jungle region){
		components.remove(region);
	}
	
	public void add(Jungle region){
		components.add(region);
		
		adjLakes.addAll(region.getLakes());
		
		if(region.getDen() != null){ adjDens.add(region.getDen()); }
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(JungleComposite comp){
		int remove = comp.getID();
		for (Jungle r : comp.getList()){
			r.setID(this.getID());
			this.add(r);
		}
		
		return remove;
	}
	
	public void score(){
		List<Integer> ids = new ArrayList<Integer>();
		for(Lake l : adjLakes){
			if(ids.contains(l.getID())){
				if(l.getCompleted()){
					score += 2;
				}
				else{
					score++;
				}
				
				ids.add(l.getID());
			}
		}
		score *= (1+prey);
	}
	
	public String toString(){
		String s = "Fields:\n";
		for(Jungle c : components){
			s += c.toString();
		}
		return s;
	}
}
