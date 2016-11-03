import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Board Class:   											
|  	Contains information about current board game, its Regions, Players, and a Standard Deck 			   										
-------------------------------------------------------------------------------------------------*/

public class Board {
	private Graph<Tile> board;
	private Player p1, p2;
	private List<Tile> deck;
	private List<Region> regions;
	
	//Function places a new Tile on a VALID side of a VALID tile
	public void placeTile(Tile hostTile, Tile newTile, int side){
		//Implementation ....
	}
	
	//We may want to input a Move object or some coordinate information
	//Move object may have this info: Tile hostTile, Tile newTile, int side
	public boolean checkValid(){
		//Implementation ....
		return false;
	}
}
