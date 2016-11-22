import java.util.ArrayList;
import java.util.List;

/*
 * Possible Tile implementation to account for weird shit in the corners.
 */

public class Edge {
	public List<Region> edges;
	
	public Edge(Region r){
		edges = new ArrayList<Region>();
		edges.add(r);
		edges.add(r);
		edges.add(r);
	}
	
	public Edge(Region r0, Region r1, Region r2){
		edges = new ArrayList<Region>();
		edges.add(r0);
		edges.add(r1);
		edges.add(r2);
	}
	
	public Edge(){
		edges = new ArrayList<Region>(3);
	}
	
	public boolean equals(Edge e){
		for (int i = 0; i < 3; i++){
			if(!edges.get(i).equals(e.edges.get((i+2)%3))) return false;
		}
		
		return true;
	}
	
	public Region getReg(int i){
		return edges.get(i);
	}
}
