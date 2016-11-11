import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Tile Class:   											
|  	Contains Tile information, a List of its inner Regions, and linked Tiles (up-to-4)   										
-------------------------------------------------------------------------------------------------*/

public class Tile {
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
	
	
}
