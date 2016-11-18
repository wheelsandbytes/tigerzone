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
	
	
	public void mergeRegion(Region base, Region n){
		int bComp = base.getID();
		int nComp = n.getID();
		
		if(bComp != -1 && nComp != -1){
			
			if(bComp == nComp) {return;}
			
			System.out.println("Join");
			this.join(n, base);
		}
		
		else if(bComp != -1 && nComp == -1){
			System.out.println("n into b");
			this.add(n, bComp);
		}
		
		else if(bComp == -1 && nComp != -1){
			System.out.println("b into n");
			this.add(base, nComp);
		}
		
		else{
			System.out.println("Brand new");
			this.addNew(n, base);
		}
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
		int id = Fields.size();
		FieldComposite comp = new FieldComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Fields.add(comp);
	}
	
	void addNew(City n, City base){
		int id = Cities.size();
		CityComposite comp = new CityComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Cities.add(comp);
	}
	
	void addNew(Road n, Road base){
		int id = Roads.size();
		RoadComposite comp = new RoadComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Roads.add(comp);
	}
	
	void join(Field n, Field base){
		System.out.println("Field");
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
	
	
	
	public String toString(){
		return Cities.toString() + "\n" + Fields.toString() + "\n" + Roads.toString() + "\n";
	}
	
	
	//Methods to make Java happy, hope these don't fuck anything up.
	private void addNew(Region n, Region base) {
		if(n instanceof Field){
			addNew((Field) n, (Field) base);
		}
		else if(n instanceof City){
			addNew((City) n, (City) base);
		}
			
		else if(n instanceof Road){
			addNew((Road) n, (Road) base);
		}
	}

	private void add(Region n, int bComp) {
		if(n instanceof Field){
			add((Field) n, bComp);
		}
		else if(n instanceof City){
			add((City) n, bComp);
		}
			
		else if(n instanceof Road){
			add((Road) n, bComp);
		}
	}
			
	private void join(Region n, Region base){
		if(n instanceof Field){
			join((Field) n, (Field) base);
		}
		else if(n instanceof City){
			join((City) n, (City) base);
		}
			
		else if(n instanceof Road){
			join((Road) n, (Road) base);
		}
	}
}
