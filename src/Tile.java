import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Tile Class:
|  	Contains Tile information, a List of its inner Regions, and linked Tiles (up-to-4)
-------------------------------------------------------------------------------------------------*/

public class Tile {

	// Top, Bottom, Right, Left definitions
	private int rotation;
	boolean shield;
	// List<Region> innerRegions;
	private List<Tile> adjacentTiles;

	// List of tile edges within the tile?
	private List<Edge> tileEdges;

	// Default constructor
	public Tile(){}
	
	Tile getAdj(int i){
		return adjacentTiles.get(i);
	}
	
	void setAdj(int i, Tile t){
		if(t == null) { adjacentTiles.set(i, null); return; }
		
		adjacentTiles.set(i, t);
		
		getEdge(i).merge(t.getEdge((i+2)%4));
	}
	
	//Accounts for a given rotation, currently assuming counterclockwise.
	Edge getEdge(int i){
		return tileEdges.get((rotation+i)%4);
	}
	
	void setRot(int r){
		rotation = r;
	}
	
	//This feels like trash but it works and might be fine
	boolean[] checkValid(Tile t){
		
		boolean[] sides = new boolean[4];
		
		for(int i = 0; i < 4; i++){
			if((t.getAdj(i) == null) && (t.getEdge(i).equals(getEdge((i+2)%4)))){
				sides[i] = true;
			}
		}
		
		/*  This is an explicit comparison, above is programatic.
		if((t.getAdj(0) == null) && (t.getEdge(0).equals(getEdge(2)))){
			sides[0] = true;
		}
		
		if((t.getAdj(1) == null) && (t.getEdge(1).equals(getEdge(3)))){
			sides[1] = true;
		}
		
		if((t.getAdj(2) == null) && (t.getEdge(2).equals(getEdge(0)))){
			sides[2] = true;
		}
		
		if((t.getAdj(3) == null) && (t.getEdge(3).equals(getEdge(1)))){
			sides[3] = true;
		}
		*/
		
		return sides;
	}
}
