import java.util.ArrayList;
import java.util.List;

public class RegionMap {
	List<LakeComposite> Cities;
	List<TrailComposite> Roads;
	List<JungleComposite> Fields;
	
	public RegionMap(){
		Cities = new ArrayList<LakeComposite>();
		Roads = new ArrayList<TrailComposite>();
		Fields = new ArrayList<JungleComposite>();
	}
	
	List<LakeComposite> getCities(){
		return Cities;
	}
	
	List<TrailComposite> getRoads(){
		return Roads;
	}
	
	List<JungleComposite> getFields(){
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

	void add(Jungle f, int i){
		f.setID(i);
		Fields.get(i).add(f);
	}
	
	void add(Lake c, int i){
		c.setID(i);
		Cities.get(i).add(c);
	}
	
	void add(Trail r, int i){
		r.setID(i);
		Roads.get(i).add(r);
	}
	
	void addNew(Jungle n, Jungle base){
		int id = Fields.size();
		JungleComposite comp = new JungleComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Fields.add(comp);
	}
	
	void addNew(Lake n, Lake base){
		int id = Cities.size();
		LakeComposite comp = new LakeComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Cities.add(comp);
	}
	
	void addNew(Trail n, Trail base){
		int id = Roads.size();
		TrailComposite comp = new TrailComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Roads.add(comp);
	}
	
	void join(Jungle n, Jungle base){
		System.out.println("Field");
		int removeID = Fields.get(base.getID()).merge(Fields.get(n.getID()));
		Fields.remove(removeID);
	}
	
	void join(Lake n, Lake base){
		int removeID = Cities.get(base.getID()).merge(Cities.get(n.getID()));
		Cities.remove(removeID);
	}
	
	void join(Trail n, Trail base){
		int removeID = Roads.get(base.getID()).merge(Roads.get(n.getID()));
		Roads.remove(removeID);
	}
	
	
	
	public String toString(){
		return Cities.toString() + "\n" + Fields.toString() + "\n" + Roads.toString() + "\n";
	}
	
	
	//Methods to make Java happy, hope these don't fuck anything up.
	private void addNew(Region n, Region base) {
		if(n instanceof Jungle){
			addNew((Jungle) n, (Jungle) base);
		}
		else if(n instanceof Lake){
			addNew((Lake) n, (Lake) base);
		}
			
		else if(n instanceof Trail){
			addNew((Trail) n, (Trail) base);
		}
	}

	private void add(Region n, int bComp) {
		if(n instanceof Jungle){
			add((Jungle) n, bComp);
		}
		else if(n instanceof Lake){
			add((Lake) n, bComp);
		}
			
		else if(n instanceof Trail){
			add((Trail) n, bComp);
		}
	}
			
	private void join(Region n, Region base){
		if(n instanceof Jungle){
			join((Jungle) n, (Jungle) base);
		}
		else if(n instanceof Lake){
			join((Lake) n, (Lake) base);
		}
			
		else if(n instanceof Trail){
			join((Trail) n, (Trail) base);
		}
	}
}
