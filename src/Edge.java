import java.util.List;

/*
 * Possible Tile implementation to account for weird shit in the corners.
 */

public class Edge {
	public List<Region> edges;
	
	public boolean equals(Edge e){
		for (int i = 0; i < edges.size(); i++){
			if(edges.get(i).getType() == e.edges.get(i).getType()) return false;
		}
		
		return true;
	}
}
