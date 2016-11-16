import java.util.List;

/*
 * Possible Tile implementation to account for weird shit in the corners.
 */

public class Edge {
	public List<Region> edges;
	
	public boolean equals(Edge e){
		for (int i = 0; i < edges.size(); i++){
			if(!edges.get(i).equals(e.edges.get(i))) return false;
		}
		
		return true;
	}
	
	public Region getReg(int i){
		return edges.get(i);
	}
	
	public void merge(Edge e){
		for (int i = 0; i < edges.size(); i++){
			
			e.edges.get(i).merge(edges.get(i));
			
			edges.set(i, e.edges.get(i));
		}
	}
}
