import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Board Class:
|  	Contains information about current board game, its Regions, Players, and a Standard Deck
-------------------------------------------------------------------------------------------------*/

public class Board {
//	Board does not contain players, players contain Board
//	For more info please visit the UML diagram for the program
//	private Player p1, p2;
	private Graph board;
	private List<Tile> deck;
	
	RegionMap map;
	List<Den> dens;
	
	HashMap<String, Slot> possibleLocs;  //Coor to Slot hashmap
	
	public Board(){
		board = new MatrixGraph(null);
		possibleLocs = new HashMap<String, Slot>();
		map = new RegionMap();
		dens = new ArrayList<Den>();
	}
	
	public Board(Tile t){
		board = new MatrixGraph(t);
		possibleLocs = new HashMap<String, Slot>();
		map = new RegionMap();
		dens = new ArrayList<Den>();
	}
	
	
	//Temporal for testing output
	//Later we will replace this with a Drawer object
	Graph getGraph(){
		return board;
	}
	
	
	//Finds a set of possible placement Coordinates for a tile, for now we don't care about the specific rotation,
	//as long as at least one fits.
	public List<Move> find(Tile t){

		
		List<Move> locs = new ArrayList<Move>();
		for(String c : possibleLocs.keySet()){
			for(int i = 0; i < 4; i++){
				t.setRot(i);
				if(t.checkValid(possibleLocs.get(c).getAdjacent())){ locs.add(new Move(possibleLocs.get(c).getCoor(), i, t)); }
			}
		}
		
		for(Move m : locs){
		}
		return locs;

	}

	//Slot Factory goes here, creates a slot with the given coordinates and sets Edges appropriately
	public Slot newSlot(Coor c){
		Slot s = new Slot(c);
		s.setAdjacent(0, board.locate(c.x, c.y+1) == null ? null : board.locate(c.x, c.y+1).getEdge(2));
		s.setAdjacent(1, board.locate(c.x+1, c.y) == null ? null : board.locate(c.x+1, c.y).getEdge(3));
		s.setAdjacent(2, board.locate(c.x, c.y-1) == null ? null : board.locate(c.x, c.y-1).getEdge(0));
		s.setAdjacent(3, board.locate(c.x-1, c.y) == null ? null : board.locate(c.x-1, c.y).getEdge(1));

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

		possibleLocs.remove(c.toString());

		if(t.getAdj(0) == null){
			Coor cNew = new Coor(c.x, c.y+1);
			String cString = cNew.toString();
			
			if(possibleLocs.get(cString) != null){
				Slot tSlot = possibleLocs.get(cString);
				tSlot.setAdjacent(2, t.getEdge(0));
				possibleLocs.put(cString, tSlot);
				
			}
			
			else{
				possibleLocs.put(cString, newSlot(cNew));
			}
			
		}

		if(t.getAdj(1) == null){
			Coor cNew = new Coor(c.x+1, c.y);
			String cString = cNew.toString();
			
			if(possibleLocs.get(cString) != null){
				Slot tSlot = possibleLocs.get(cString);
				tSlot.setAdjacent(3, t.getEdge(1));
				possibleLocs.put(cString, tSlot);
				
			}
			
			else{
				possibleLocs.put(cString, newSlot(cNew));
			}
		}
		
		if(t.getAdj(2) == null){
			Coor cNew = new Coor(c.x, c.y-1);
			String cString = cNew.toString();

			if(possibleLocs.get(cString) != null){
				Slot tSlot = possibleLocs.get(cString);
				tSlot.setAdjacent(0, t.getEdge(2));
				possibleLocs.put(cString, tSlot);
				
			}
			
			else{
				possibleLocs.put(cString, newSlot(cNew));
			}
		}

		if(t.getAdj(3) == null){
			Coor cNew = new Coor(c.x-1, c.y);
			String cString = cNew.toString();
			
			if(possibleLocs.get(cString) != null){
				Slot tSlot = possibleLocs.get(cString);
				tSlot.setAdjacent(1, t.getEdge(3));
				possibleLocs.put(cString, tSlot);
			}
			
			else{
				possibleLocs.put(cString, newSlot(cNew));
			}
		}
		
		//Place meeple here?
		
		mergeRegions(t);
		
		//Place meeple here?
		
		if(t.getRegionAt(4).getType() == GameInfo.CHURCH){
			((Den) t.getRegionAt(4)).setLoc(m.getLocation());
			dens.add((Den) t.getRegionAt(4));
		}
		
		//Place meeple here?
		
		this.checkDens();
		
		//Place meeple here?
	}
	
	
	//This will work eventually when we get region hierarchy together
	public void mergeRegions(Tile t){
		for(int i =0; i < 4; i++){
			if(t.getAdj(i) == null) {
				Edge n = t.getEdge(i);
				for(int j = 0; j < 3; j++){
					if(n.getReg(j).getID() == -1){
						map.add(n.getReg(j));
					}
				}
			}
			
			else{
				Edge base = t.getAdj(i).getEdge((i+2)%4);
				Edge n = t.getEdge(i);
				
				map.mergeRegion(base.getReg(0), n.getReg(2));
				map.mergeRegion(base.getReg(1), n.getReg(1));
				map.mergeRegion(base.getReg(2), n.getReg(0));
				
				/*
				for(int j = 0; j < 3; j++){
					map.mergeRegion(base.getReg((j+2)%2), n.getReg(j));
				}
				*/
			}
		}
	}
	
	public void placeTiger(Tiger t,  Coor c){
		for(Region r : board.locate(c.x, c.y).getRegions()){
			if(r.getMeeples() != null){
				r.setMeeple(t);
				return;
			}
		}
	}
	
	public void removeTiger(Coor c){
		for(Region r : board.locate(c.x, c.y).getRegions()){
			if(!r.getMeeples().isEmpty()){
				r.removeMeeple(r.getMeeples().get(0));
				return;
			}
		}
	}
	
	//Used for den scoring, it's a bit shit but something that should work for now.
	private void checkDens(){
		for(Den d : dens){
			if(d.getCompleted()) { continue; }
			int tempScore = 1;
			Coor c = d.getLoc();
			
			if(board.locate(c.x-1, c.y+1) != null) { tempScore++; }
			if(board.locate(c.x, c.y+1) != null) { tempScore++; }
			if(board.locate(c.x+1, c.y+1) != null) { tempScore++; }
			
			if(board.locate(c.x+1, c.y) != null) { tempScore++; }
			if(board.locate(c.x-1, c.y) != null) { tempScore++; }
			
			if(board.locate(c.x-1, c.y-1) != null) { tempScore++; }
			if(board.locate(c.x, c.y-1) != null) { tempScore++; }
			if(board.locate(c.x+1, c.y-1) != null) { tempScore++; }
			
			d.setScore(tempScore);
			if(tempScore == 9){
				d.setCompleted(true);
				d.returnTigers();
			}
		}
	}
}
