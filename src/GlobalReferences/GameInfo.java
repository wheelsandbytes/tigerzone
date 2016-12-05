package GlobalReferences;
/*-----------------------------------------------------------------------------------------------	
|	GameInfo Class:   											
|  	Contains constant information about the Business Objects accessible publicly
|	(Feel free to add more, or modify existing constants)		  										
 --------------------------------------------------------------------------------------------------*/

public class GameInfo {
	
	//Sides of a Tile... 
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	
	//Types of Regions...
	public static final int ROAD = 1, CITY = 2, FIELD = 3, CHURCH = 4, CROSS = 5;
	
	//Total tiles
	public static final int MAX_TILES = 77;
	
	//Shift coordinates to visit children of root(0,0)
	public static final int[] SHIFT = {1,0,  -1,0,  0,1,  0,-1};
	
	//Preys, zero if it has no prey, 1 if deer, 2 if boar and 3 if buffalo 
	public static final int NONE = -1, DEER = 0, BOARD = 1, BUFFALO = 2, CROCODILE = 3, TIGER = 4;
	
	
	//Maximum number of Meeples allowed per type
	public static final int MAX_CROCS = 2, MAX_TIGERS = 7, MAX_TYPES = 28, MAX_ZONES = 9;
	
	//Frame's proportions for UI
	public static final int WIDTH = 800, HEIGHT = 800;
	
	//RotationalMap for Tiger placement
	public static final RotationalMap TIGERZONE = new RotationalMap();
	
	//TILES
	//----------------------------------------------------------------------------------------------------------------------------------
	//Types of Tiles based on TigerZone rules and Tournament
	public static final String JJJJN="JJJJ-", JJJJX="JJJJX", JJTJX="JJTJX", TTTTN="TTTT-", TJTJN="TJTJ-", TJJTN="TJJT-", TJTTN="TJTT-";
	public static final String LLLLN="LLLL-", JLLLN="JLLL-", LLJJN="LLJJ-", JLJLN="JLJL-", LJLJN="LJLJ-", LJJJN="LJJJ-", JLLJN="JLLJ-";
	public static final String TLJTN="TLJT-", TLJTP="TLJTP", JLTTB="JLTTB", JLTTN="JLTT-", TLTJN="TLTJ-", TLTJD="TLTJD", TLLLN="TLLL-";
	public static final String TLTTN="TLTT-", TLTTP="TLTTP", TLLTN="TLLT-", TLLTB="TLLTB", LJTJN="LJTJ-", LJTJD="LJTJD", TLLLC="TLLLC";
	
	//Array Representation of Tiles
	public static final String[] allowedTiles = new String[]{
			"JJJJ-", "JJJJX", "JJTJX", "TTTT-", "TJTJ-", "TJJT-", "TJTT-", "LLLL-", "JLLL-", "LLJJ-", "JLJL-", "LJLJ-", "LJJJ-", "JLLJ-",
			"TLJT-", "TLJTP", "JLTTB", "JLTT-", "TLTJ-", "TLTJD", "TLLL-", "TLTT-", "TLTTP", "TLLT-", "TLLTB", "LJTJ-", "LJTJD", "TLLLC"
	};
}

