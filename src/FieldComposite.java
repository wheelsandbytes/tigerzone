import java.util.ArrayList;
import java.util.List;

public class FieldComposite {
	int compID; 
	int prey;
	int score; //maybe
	List<Field> components;
	
	public FieldComposite(){
		components = new ArrayList<Field>();
	}
	
	public FieldComposite(int i){
		components = new ArrayList<Field>();
		compID = i;
	}
	
	public List<Field> getList(){
		return components;
	}
	
	public void remove(Field region){
		components.remove(region);
	}
	
	public void add(Field region){
		components.add(region);
	}
	
	public int getID(){
		return compID;
	}
	
	public int merge(FieldComposite comp){
		int remove = comp.getID();
		for (Field r : comp.getList()){
			r.setID(this.getID());
			this.add(r);
		}
		
		return remove;
	}
	
	public String toString(){
		String s = "Fields:\n";
		for(Field c : components){
			s += c.toString();
		}
		return s;
	}
}
