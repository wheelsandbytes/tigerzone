import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Board Class:
|  	Contains information about current board game, its Regions, Players, and a Standard Deck
-------------------------------------------------------------------------------------------------*/

public class Board {
	private Graph board;
	private Player p1, p2;
	private List<Tile> deck;
	
	private RegionMap map;
	
	private HashMap<Coor, Slot> possibleLocs;  //Coor to Slot hashmap

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

	//Finds a set of possible placement Coordinates for a tile, for now we don't care about the specific rotation,
	//as long as at least one fits.
	public List<Move> find(Tile t){

		List<Move> locs = new ArrayList<Move>();

		for(Coor c : possibleLocs.keySet()){
			for(int i = 0; i < 4; i++){
				t.setRot(i);
				if(t.checkValid(possibleLocs.get(c).getAdjacent())){ locs.add(new Move(c, i, t)); }
			}
		}

		return locs;

	}

	//Slot Factory goes here, creates a slot with the given coordinates and sets Edges appropriately
	public Slot newSlot(Coor c){
		Slot s = new Slot(c);
		s.setAdjacent(0, board.locate(c.x, c.y+1) == null ? null : board.locate(c.x, c.y+1).getEdge(0));
		s.setAdjacent(1, board.locate(c.x+1, c.y) == null ? null : board.locate(c.x+1, c.y).getEdge(1));
		s.setAdjacent(2, board.locate(c.x+1, c.y) == null ? null : board.locate(c.x, c.y-1).getEdge(2));
		s.setAdjacent(3, board.locate(c.x+1, c.y) == null ? null : board.locate(c.x-1, c.y).getEdge(3));

		return s;
	}

	// public void place(Tile t, Coor c){
	public void place(Move m)
	{
		Coor c = m.getLocation() ; // set the coordinates from the passed-in Move
		Tile t = m.getTile(); // set the tile from the passed-in Move
		t.setRot(m.getRotation());
		
		t.setAdj(0, board.locate(c.x, c.y+1));
		t.setAdj(1, board.locate(c.x+1, c.y));
		t.setAdj(2, board.locate(c.x, c.y-1));
		t.setAdj(3, board.locate(c.x-1, c.y));
		
		board.add(c.x, c.y, t);

		possibleLocs.remove(c);

		if(t.getAdj(0) == null){
			Coor cNew = new Coor(c.x, c.y+1);
			
			if(possibleLocs.get(cNew) != null){
				possibleLocs.get(cNew).setAdjacent(2, t.getEdge(0));
			}
			
			else{
				possibleLocs.put(cNew, newSlot(cNew));
			}
			
		}

		if(t.getAdj(1) == null){
			Coor cNew = new Coor(c.x+1, c.y);
			
			if(possibleLocs.get(cNew) != null){
				possibleLocs.get(cNew).setAdjacent(3, t.getEdge(1));
			}
			
			else{
				possibleLocs.put(cNew, newSlot(cNew));
			}
		}
		
		if(t.getAdj(2) == null){
			Coor cNew = new Coor(c.x, c.y-1);

			if(possibleLocs.get(cNew) != null){
				possibleLocs.get(cNew).setAdjacent(0, t.getEdge(2));
			}
			
			else{
				possibleLocs.put(cNew, newSlot(cNew));
			}
		}

		if(t.getAdj(3) == null){
			Coor cNew = new Coor(c.x-1, c.y);
			
			if(possibleLocs.get(cNew) != null){
				possibleLocs.get(cNew).setAdjacent(1, t.getEdge(3));
			}
			
			else{
				possibleLocs.put(cNew, newSlot(cNew));
			}
		}
		
		
		mergeRegions(t);
	}
	
	
	//This will work eventually when we get region hierarchy together
	public void mergeRegions(Tile t){
		for(int i =0; i < 4; i++){
			if(t.getAdj(i) == null) {continue;}
			Edge base = t.getAdj(i).getEdge((i+2)%4);
			Edge n = t.getEdge(i);
		
			for(int j = 0; j < 3; j++){
				map.mergeRegion(base.getReg(j), n.getReg(j));
			}
		}
	}
}
