import java.util.List;

public class FieldComposite {
	int compID; 
	int prey;
	int score; //maybe
	List<Field> components;
	
	public FieldComposite(int i){
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
}
