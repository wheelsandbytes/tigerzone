import java.util.ArrayList;
import java.util.List;

public class LakeComposite {
	int compID; 
	int prey;
	int score; //maybe
	List<Lake> components;
	
	public LakeComposite(){
		components = new ArrayList<Lake>();
	}
	
	public LakeComposite(int i){
		components = new ArrayList<Lake>();
		compID = i;
	}
	
	public List<Lake> getList(){
		return components;
	}
	
	public void remove(Lake region){
		components.remove(region);
	}
	
	public void add(Lake region){
		components.add(region);
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
	
	public String toString(){
		String s = "Cities:\n";
		for(Lake c : components){
			s += c.toString();
		}
		return s;
	}
}

