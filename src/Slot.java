import java.util.List;
import java.util.ArrayList;

public class Slot {
	private Coor loc;				//Location upon which we will place a tile
	private List<Edge> connections; //The edges (null if not present) that the incoming tile will need to match.

	public Slot(Coor loc){
		connections = new ArrayList<Edge>();
		connections.add(null);
		connections.add(null);
		connections.add(null);
		connections.add(null);
		this.loc = loc;
	}

	public Slot(int x, int y){
		connections = new ArrayList<Edge>(4);
		loc = new Coor(x, y);
	}

	public List<Edge> getAdjacent(){
		return connections;
	}

	public void setAdjacent(int i, Edge t){
		connections.set(i, t);
	}

	public void setAdjacent(List<Edge> t){
		connections = t;
	}
	
	public Coor getCoor(){
		return loc;
	}
}
