import java.util.*;
import static org.junit.Assert.*;
/*-----------------------------------------------------------------------------------------------
|	Tile Class:
|  	Contains Tile information, a List of its inner Regions, and linked Tiles (up-to-4)
-------------------------------------------------------------------------------------------------*/

public class Tile {

	// Fields
	public int prey;
	public boolean hasCrocodile = false;
	public boolean hasTiger = false;
	private int rotation = 0;
	private int angle = 0;
	private String type;
	private Region[] regionPositions;
	private List<Tile> adjacentTiles;
	private List<Edge> tileEdges;
	private final int MAX_REGIONS = 9;

	// Default constructor
	public Tile(){}
	
	// Constructor takes in the type
	public Tile(String type){
		this.type = type;
	}
	
	// Constructor takes care of setup
	public Tile(String type, int prey, List<Tile> adjacentTiles, List<Edge> tileEdges, Region[] regionPositions){
		rotation = 0;
		this.type = type;
		this.prey = prey;
		this.adjacentTiles = adjacentTiles;
		this.tileEdges = tileEdges;
		assertEquals(MAX_REGIONS, regionPositions.length);
		this.regionPositions = regionPositions;
		if(prey == GameInfo.CROCODILE)
			hasCrocodile = true;
	}
	
	
	//Getters
	public String getType(){
		return type;
	}
	
	public Tile getAdj(int i){
		return adjacentTiles.get(i);
	}
	
	public Region getRegionAt(int i){
		if(i < MAX_REGIONS)
			return regionPositions[i];
		else return null;
	}
	
	//Accounts for a given rotation, currently assuming counterclockwise.
	public Edge getEdge(int i){
		return tileEdges.get(Math.abs((rotation+i)%4));
	}
	
	public int getRotation(){
		return rotation;
	}
	
	public int getAngle(){
		return angle;
	}
	
	
	
	//Setters
	public void setAdj(int i, Tile t){
		if(t == null) { adjacentTiles.set(i, null); return; }
		adjacentTiles.set(i, t);
	}
	
	public void setRot(int r){
		angle = r*90;
		rotation = r;
	}
	
	public void placeTiger(int pos, Tiger t){
		hasTiger = true;
		getRegionAt(pos).setMeeple(t);
	}
	
	public void placeCrocodile(){
		for(Region r : regionPositions){
			if(r instanceof Lake) { ((Lake) r).getComp().crocodiles++; }
			
			else if(r instanceof Trail) { ((Trail) r).getComp().crocodiles++; }
		}
		
		hasCrocodile = true;
	}

	boolean checkValid(List<Edge> e){
		for(int i = 0; i < 4; i++){
			if(e.get(i) == null) {continue;}
			
			if(!e.get(i).equals(getEdge(i))){
				return false;
			}
		}
		return true;
	}

	public Region[] getRegions() {
		return regionPositions;
	}
}