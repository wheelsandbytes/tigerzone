import java.util.*;
import static org.junit.Assert.*;

/*-----------------------------------------------------------------------------------------------
|	Tile Class:
|  	Contains Tile information, a List of its inner Regions, and linked Tiles (up-to-4)
-------------------------------------------------------------------------------------------------*/

public class Tile {

	// Private fields
	int prey;
	private String type;
	private int rotation;
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
	}
	
	
	//Getters
	String getType(){
		return type;
	}
	
	Tile getAdj(int i){
		return adjacentTiles.get(i);
	}
	
	Region getRegionAt(int i){
		if(i < MAX_REGIONS)
			return regionPositions[i];
		else return null;
	}
	
	void setAdj(int i, Tile t){
		if(t == null) { adjacentTiles.set(i, null); return; }
		adjacentTiles.set(i, t);
	}
	
	//Accounts for a given rotation, currently assuming counterclockwise.
	Edge getEdge(int i){
		return tileEdges.get((rotation+i)%4);
	}
	
	void setRot(int r){
		rotation = r;
	}
	
	public void placeTiger(){
		//Implementation should use getRegionAt(i)
		//Then Tiger or Crocodile can be place in that region
		//NOTE: useful to use RotationalMap class
	}

	boolean checkValid(List<Edge> e){
		for(int i = 0; i < 4; i++){
			if((e.get(i) != null) && (!e.get(i).equals(getEdge(i)))){
				return false;
			}
		}
		return true;
	}
}


//Revise before use
//----------------------------------------------------------------------------------------------------
//tile factory, don't know how sever will pass us the order of the deck. most likely this code istrash
//type of tile represented by string of 5 chars. first char is type of the northen edge, second char is eastern edge ect.
//t stands for trail, l stands for lake and j stands for jungle
//5th char in string either says if it is a den or the type of prey on the tile(dens do not have prey).
//d means it's a den, e there is a deer, o means there is a boar, u for buffalo and n for no prey
//void factory(String tileType){
//	den = false;
//	prey = 0;
//	for(int i = 0; i<= 3; i++){
//		if(tileType.CharAt(i) == 't'){
//			/*create new edge*/
//			tileEdges.at(i) = /* add correct region to first position in edge*/;
//			tileEdges.at(i) = /* add second correct region to edge */;
//			tileEdges.at(i) = /* add third correct region to edge */;
//		}
//		if(tileType.CharAt(i) == 'l'){
//			/*create new edge*/
//			tileEdges.at(i) = /* add correct region to first position in edge*/;
//			tileEdges.at(i) = /* add second correct region to edge */;
//			tileEdges.at(i) = /* add third correct region to edge */;
//		}
//		if(tileType.CharAt(i) == 'j'){
//			/*create new edge*/
//			tileEdges.at(i) = /* add correct region to first position in edge*/;
//			tileEdges.at(i) = /* add second correct region to edge */;
//			tileEdges.at(i) = /* add third correct region to edge */;
//		}
//	}
//	if(tileType.CharAt(4) == 'd'){
//		den = true;
//	}
//	if(tileType.CharAt(4) == 'e'){
//		prey = 1;
//	}
//	if(tileType.CharAt(4) == 'o'){
//		prey = 2;
//	}
//	if(tileType.CharAt(4) == 'u'){
//		prey = 3;
//	}
//
//}


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