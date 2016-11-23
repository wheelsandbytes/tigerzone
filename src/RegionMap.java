import java.util.ArrayList;
import java.util.List;

public class RegionMap {
	List<LakeComposite> Lakes;
	List<TrailComposite> Trails;
	List<JungleComposite> Jungles;
	
	public RegionMap(){
		Lakes = new ArrayList<LakeComposite>();
		Trails = new ArrayList<TrailComposite>();
		Jungles = new ArrayList<JungleComposite>();
	}
	
	List<LakeComposite> getLakes(){
		return Lakes;
	}
	
	List<TrailComposite> getTrails(){
		return Trails;
	}
	
	List<JungleComposite> getJungles(){
		return Jungles;
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
		Jungles.get(i).add(f);
	}
	
	void add(Lake c, int i){
		c.setID(i);
		Lakes.get(i).add(c);
	}
	
	void add(Trail r, int i){
		r.setID(i);
		Trails.get(i).add(r);
	}
	
	void addNew(Jungle n, Jungle base){
		int id = Jungles.size();
		JungleComposite comp = new JungleComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Jungles.add(comp);
	}
	
	void addNew(Lake n, Lake base){
		int id = Lakes.size();
		LakeComposite comp = new LakeComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Lakes.add(comp);
	}
	
	void addNew(Trail n, Trail base){
		int id = Trails.size();
		TrailComposite comp = new TrailComposite(id);
		
		base.setID(id);
		comp.add(base);
		
		n.setID(id);
		comp.add(n);
		
		Trails.add(comp);
	}
	
	void join(Jungle n, Jungle base){
		System.out.println("Field");
		int removeID = Jungles.get(base.getID()).merge(Jungles.get(n.getID()));
		Jungles.remove(removeID);
	}
	
	void join(Lake n, Lake base){
		int removeID = Lakes.get(base.getID()).merge(Lakes.get(n.getID()));
		Lakes.remove(removeID);
	}
	
	void join(Trail n, Trail base){
		int removeID = Trails.get(base.getID()).merge(Trails.get(n.getID()));
		Trails.remove(removeID);
	}
	
	
	
	public String toString(){
		return Lakes.toString() + "\n" + Jungles.toString() + "\n" + Trails.toString() + "\n";
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
