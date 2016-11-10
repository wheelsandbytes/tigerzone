import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Tile Class:
|  	Contains Tile information, a List of its inner Regions, and linked Tiles (up-to-4)
-------------------------------------------------------------------------------------------------*/

public class Tile {

	// Top, Bottom, Right, Left definitions
	int TOP,RIGHT,BOTTOM,LEFT;
	boolean shield;
	// List<Region> innerRegions;
	List<Tile> adjacentTiles;

	// List of tile edges within the tile?
	List<Edge> tileEdges;

	// Default constructor
	public Tile
	{
		// DEFAULT rotation state is ZERO (0) for new Tile
		setTileRotationState(0);
	}

	// Define rotation states
	public void setTileRotationState(int ROT_STATE)
	{
		if (ROT_STATE == 0) // ROTATION STATE 0
		{
			TOP = 0;
			RIGHT = 1;
			BOTTOM = 2;
			LEFT = 3;
		}
		if (ROT_STATE == 1) // ROTATION STATE 1
		{
			TOP = 1;
			RIGHT = 2;
			BOTTOM = 3;
			LEFT = 0;
		}
		if (ROT_STATE == 2) // ROTATION STATE 2
		{
			TOP = 2;
			RIGHT = 3;
			BOTTOM = 0;
			LEFT = 1;
		}
		if (ROT_STATE == 3) // ROTATION STATE 3
		{
			TOP = 3;
			RIGHT = 0;
			BOTTOM = 1;
			LEFT = 2;
		}
	}
}
