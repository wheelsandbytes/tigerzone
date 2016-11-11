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
	
	private List<Coor> possibleTiles;  //This could be a Coor to Tile hashmap?
	
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
	
	//Finds a set of possible placement Coordinates for a tile.
	public List<Coor> find(Tile t){
		
		List<Coor> slots;
		
		for(int i = 0; i < 4; i++){
			t.setRot(i);
			for(Coor c : possibleTiles){
				
			}
		}
	}
	
	public void place(Tile t, Coor c, int rot){
		t.setRot(rot);
		t.setAdj(0, board.getTile(c.x, c.y+1));
		t.setAdj(1, board.getTile(c.x+1, c.y));
		t.setAdj(2, board.getTile(c.x, c.y-1));
		t.setAdj(3, board.getTile(c.x-1, c.y));
		board.add(c.x, c.y);
	}
}
