import java.util.ArrayList;
import java.util.List;

public class RegionMap {
	List<CityComposite> Cities;
	List<RoadComposite> Roads;
	List<FieldComposite> Fields;
	
	public RegionMap(){
		Cities = new ArrayList<CityComposite>();
		Roads = new ArrayList<RoadComposite>();
		Fields = new ArrayList<FieldComposite>();
	}
	
	List<CityComposite> getCities(){
		return Cities;
	}
	
	List<RoadComposite> getRoads(){
		return Roads;
	}
	
	List<FieldComposite> getFields(){
		return Fields;
	}
	
	void add(Field f, int i){
		f.setID(i);
		Fields.get(i).add(f);
	}
	
	void add(City c, int i){
		c.setID(i);
		Cities.get(i).add(c);
	}
	
	void add(Road r, int i){
		r.setID(i);
		Roads.get(i).add(r);
	}
	
	void addNew(Field n, Field base){
		int id = Fields.size()+1;
		FieldComposite comp = new FieldComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Fields.add(comp);
	}
	
	void addNew(City n, City base){
		int id = Cities.size()+1;
		CityComposite comp = new CityComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Cities.add(comp);
	}
	
	void addNew(Road n, Road base){
		int id = Roads.size()+1;
		RoadComposite comp = new RoadComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Roads.add(comp);
	}
	
	void join(Field n, Field base){
		int removeID = Fields.get(base.getID()).merge(Fields.get(n.getID()));
		Fields.remove(removeID);
	}
	
	void join(City n, City base){
		int removeID = Cities.get(base.getID()).merge(Cities.get(n.getID()));
		Cities.remove(removeID);
	}
	
	void join(Road n, Road base){
		int removeID = Roads.get(base.getID()).merge(Roads.get(n.getID()));
		Roads.remove(removeID);
	}
	
	
	
	
	
	public void mergeRegion(Region base, Region n){
		int bComp = base.getID();
		int nComp = n.getID();
		
		if(bComp != -1 && nComp != -1){
			if(bComp == nComp) {return;}
			this.join(n, base);
		}
		
		else if(bComp != -1 && nComp == -1){
			
			this.add(n, bComp);
		}
		
		else if(bComp == -1 && nComp != -1){
			
			this.add(base, nComp);
		}
		
		else{
			this.addNew(n, base);
		}
	}

	
	
	
	
	//Methods to make Java happy, hope these don't fuck anything up.
	private void addNew(Region n, Region base) {
		
	}

	private void add(Region n, int bComp) {
		
	}
	
	private void join(Region n, Region base){
		
	}
}
