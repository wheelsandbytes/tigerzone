import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Tile Class:   											
|  	Contains Tile information, a List of its inner Regions, and linked Tiles (up-to-4)   										
-------------------------------------------------------------------------------------------------*/

public class Tile {
	boolean shield;
	List<Region> innerRegions;
	List<Tile> adjacentTiles;
}
