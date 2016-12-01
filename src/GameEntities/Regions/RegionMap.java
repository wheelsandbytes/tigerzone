package GameEntities.Regions;
import java.util.HashMap;

public class RegionMap {
	int lakeNum = 0;
	int trailNum = 0;
	int jungleNum = 0;
	HashMap<Integer, LakeComposite> Lakes;
	HashMap<Integer, TrailComposite> Trails;
	HashMap<Integer, JungleComposite> Jungles;
	
	public RegionMap(){
		Lakes = new HashMap<Integer, LakeComposite>();
		Trails = new HashMap<Integer, TrailComposite>();
		Jungles = new HashMap<Integer, JungleComposite>();
	}
	
	HashMap<Integer, LakeComposite> getLakes(){
		return Lakes;
	}
	
	HashMap<Integer, TrailComposite>  getTrails(){
		return Trails;
	}
	
	HashMap<Integer, JungleComposite> getJungles(){
		return Jungles;
	}
	
	
	public void mergeRegion(Region base, Region n){
		int bComp = base.getID();
		int nComp = n.getID();
		
		if(bComp != -1 && nComp != -1){
			
			if(bComp == nComp) {
				
				if(!(base instanceof Trail)) { return; }
				
				Trails.get(bComp).ends = 2;
				Trails.get(bComp).complete = true;
				return;
			}
			
			this.join(n, base);
		}
		
		else if(bComp != -1 && nComp == -1){
			this.add(n, base);
		}
		
		else if(bComp == -1 && nComp != -1){
			this.add(base, n);
		}
		
		else{
			this.addNew(n, base);
		}
	}

	void add(Jungle f, int i){
		Jungles.get(i).add(f);
	}
	
	void add(Lake c, int i){
		Lakes.get(i).add(c);
		if(c.getCaps() == 4) { Lakes.get(i).checkComplete(); }
		
	}
	
	void add(Trail r, int i){
		Trails.get(i).add(r);
	}
	
	private void addNew(Trail n) {
		int id = trailNum;
		TrailComposite comp = new TrailComposite(id);
		
		comp.add(n);
		
		Trails.put(id, comp);
		trailNum++;
	}

	private void addNew(Lake n) {
		int id = lakeNum;
		LakeComposite comp = new LakeComposite(id);
				
		comp.add(n);
		
		if(n.getCaps() == 4) { comp.checkComplete(); }
		
		Lakes.put(id, comp);
		lakeNum++;
	}

	private void addNew(Jungle n) {
		int id = jungleNum;
		JungleComposite comp = new JungleComposite(id);
		
		comp.add(n);
		
		Jungles.put(id, comp);
		jungleNum++;
	}
	
	void addNew(Jungle n, Jungle base){
		int id = jungleNum;
		JungleComposite comp = new JungleComposite(id);
		
		comp.add(base);
		
		comp.add(n);
		
		Jungles.put(id, comp);
		jungleNum++;
	}
	
	void addNew(Lake n, Lake base){
		int id = lakeNum;
		LakeComposite comp = new LakeComposite(id);
		
		comp.add(base);
		
		comp.add(n);
		
		if(base.getCaps() == 4 && n.getCaps() == 4) { comp.checkComplete(); }
		
		Lakes.put(id, comp);
		lakeNum++;
	}
	
	void addNew(Trail n, Trail base){
		int id = trailNum;
		TrailComposite comp = new TrailComposite(id);
		
		comp.add(base);
		
		comp.add(n);
		
		Trails.put(id, comp);
		trailNum++;
	}
	
	void join(Jungle n, Jungle base){
		int removeID = Jungles.get(base.getID()).merge(Jungles.get(n.getID()));
		Jungles.remove(removeID);
	}
	
	void join(Lake n, Lake base){
		int removeID = Lakes.get(base.getID()).merge(Lakes.get(n.getID()));
		Lakes.remove(removeID);
		
		if(base.getCaps() == 4 && n.getCaps() == 4) { Lakes.get(base.getID()).checkComplete(); }
	}
	
	void join(Trail n, Trail base){
		int removeID = Trails.get(base.getID()).merge(Trails.get(n.getID()));
		Trails.remove(removeID);
	}
	
	
	
	public String toString(){
		return Lakes.toString() + "\n" + Jungles.toString() + "\n" + Trails.toString() + "\n";
	}
	
	
	private void addNew(Region n, Region base) {
		if(n instanceof Jungle){
			addNew((Jungle) n, (Jungle) base);
		}
		else if(n instanceof Lake){
			((Lake) n).addCap();
			((Lake) base).addCap();
			addNew((Lake) n, (Lake) base);
		}
			
		else if(n instanceof Trail){
			addNew((Trail) n, (Trail) base);
		}
	}

	private void add(Region n, Region base) {
		int bComp = base.getID();
		if(n instanceof Jungle){
			add((Jungle) n, bComp);
		}
		else if(n instanceof Lake){
			((Lake) n).addCap();
			((Lake) base).addCap();
			add((Lake) n, bComp);
		}
			
		else if(n instanceof Trail){
			add((Trail) n, bComp);
		}
	}
	
	public void add(Region n) {
		if(n instanceof Jungle){
			addNew((Jungle) n);
		}
		else if(n instanceof Lake){
			addNew((Lake) n);
		}
			
		else if(n instanceof Trail){
			addNew((Trail) n);
		}
	}

	private void join(Region n, Region base){
		if(n instanceof Jungle){
			join((Jungle) n, (Jungle) base);
		}
		else if(n instanceof Lake){
			((Lake) n).addCap();
			((Lake) base).addCap();
			join((Lake) n, (Lake) base);
		}
			
		else if(n instanceof Trail){
			join((Trail) n, (Trail) base);
		}
	}
	
	public String getScores(){
		String s = "Lakes: \n";
		for(LakeComposite l : Lakes.values()){
			s += l.getID() + " " + l.score() + "\n";
		}
		
		s += "Trails: \n";
		for(TrailComposite l : Trails.values()){
			s += l.getID() + " " + l.score() + " " + l.ends + "\n";
		}
		
		s += "Jungles: \n";
		for(JungleComposite l : Jungles.values()){
			s += (l.getID() + " " + l.score() + "\n");
		}
		return s;
	}
}
