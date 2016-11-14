import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Tile Class:
|  	Contains Tile information, a List of its inner Regions, and linked Tiles (up-to-4)
-------------------------------------------------------------------------------------------------*/

public class Tile {
<<<<<<< HEAD

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
	
	/*
	//This feels like trash but it works and might be fine
	//The input tile would come from a Slot object, containing the edges that we would need to match to fit.
	boolean checkValid(Tile t){
				
		for(int i = 0; i < 4; i++){
			if((t.getEdge(i) != null) && (!t.getEdge(i).equals(getEdge(i)))){
				return false;
			}
		}
		
		return true;
	}
	*/
	
	//Same as above only just passing in an edge list
	boolean checkValid(List<Edge> e){
		
		for(int i = 0; i < 4; i++){
			if((e.get(i) != null) && (!e.get(i).equals(getEdge(i)))){
				return false;
			}
		}
		
		return true;
	}
=======
	public String type;
	boolean shield;
	List<Region> innerRegions;
	List<Tile> adjacentTiles;
	
	Tile(){
		//Empty..  :(
	}
	
	Tile(String type){
		this.type = type;
	}
	
	
>>>>>>> master
}
