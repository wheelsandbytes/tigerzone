import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Board Class:
|  	Contains information about current board game, its Regions, Players, and a Standard Deck
-------------------------------------------------------------------------------------------------*/

public class Board {
	private Graph board;
	private Player p1, p2;
	private List<Tile> deck;
	
	private List<RegionComposite<Field>> Fields;
	private List<RegionComposite<City>> Cities;
	private List<RegionComposite<Road>> Roads;
	
	private HashMap<Coor, Slot> possibleLocs;  //This could be a Coor to Tile hashmap?

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
		s.setAdjacent(0, board.locate(c.x, c.y+1).getEdge(0));
		s.setAdjacent(1, board.locate(c.x+1, c.y).getEdge(1));
		s.setAdjacent(2, board.locate(c.x, c.y-1).getEdge(2));
		s.setAdjacent(3, board.locate(c.x-1, c.y).getEdge(3));

		return s;
	}

	// public void place(Tile t, Coor c){
	public void place(Move m)
	{
		Coor c = m.getLocation() ; // set the coordinates from the passed-in Move
		Tile t = m.getTile() ; // set the tile from the passed-in Move

		t.setAdj(0, board.locate(c.x, c.y+1));
		t.setAdj(1, board.locate(c.x+1, c.y));
		t.setAdj(2, board.locate(c.x, c.y-1));
		t.setAdj(3, board.locate(c.x-1, c.y));
		
		board.add(c.x, c.y, t);

		possibleLocs.remove(c);

		if(t.getAdj(0) == null){ possibleLocs.putIfAbsent(new Coor(c.x, c.y+1), newSlot(new Coor(c.x, c.y+1))); }

		if(t.getAdj(1) == null){ possibleLocs.putIfAbsent(new Coor(c.x+1, c.y), newSlot(new Coor(c.x+1, c.y))); }
		
		if(t.getAdj(2) == null){ possibleLocs.putIfAbsent(new Coor(c.x, c.y-1), newSlot(new Coor(c.x, c.y-1))); }

		if(t.getAdj(3) == null){ possibleLocs.putIfAbsent(new Coor(c.x-1, c.y), newSlot(new Coor(c.x-1, c.y))); }
		
		mergeRegions(t);
	}
	
	
	//This will work eventually when we get region hierarchy together
	public void mergeRegions(Tile t){
		for(int i =0; i < 4; i++){
			if(t.getAdj(i) == null) {continue;}
			Edge base = t.getAdj(i).getEdge((i+2)%4);
			Edge n = t.getEdge(i);
		
			for(int j = 0; j < 3; j++){
				int bComp = base.getReg(j).getID();
				int nComp = n.getReg(j).getID();
				
				if(bComp != -1 && nComp != -1){
					if(bComp == nComp) {continue;}
					
					switch(n.getReg(j).getType()){
						case GameInfo.FIELD:
							Fields.get(bComp).merge(Fields.get(nComp));
							Fields.remove(nComp);
							break;
						case GameInfo.CITY:
							Cities.get(bComp).merge(Cities.get(nComp));
							Cities.remove(nComp);
							break;
						case GameInfo.ROAD:
							Roads.get(bComp).merge(Roads.get(nComp));
							Roads.remove(nComp);
							break;
					}
				}
				
				else if(bComp != -1 && nComp == -1){
					n.getReg(j).setID(bComp);
					switch(n.getReg(j).getType()){
					case GameInfo.FIELD:
						Fields.get(bComp).add( n.getReg(j));
						break;
					case GameInfo.CITY:
						Cities.get(bComp).add( n.getReg(j));
						break;
					case GameInfo.ROAD:
						Roads.get(bComp).add( n.getReg(j));
						break;
					}
				}
				
				else if(bComp == -1 && nComp != -1){
					base.getReg(j).setID(nComp);
					switch(n.getReg(j).getType()){
					case GameInfo.FIELD:
						Fields.get(nComp).add( base.getReg(j));
						break;
					case GameInfo.CITY:
						Cities.get(nComp).add( base.getReg(j));
						break;
					case GameInfo.ROAD:
						Roads.get(nComp).add( base.getReg(j));
						break;
					}
				}
				
				else{
					n.getReg(j).setID(bComp);
					switch(n.getReg(j).getType()){
					case GameInfo.FIELD:
						RegionComposite<Field> comp = new RegionComposite<Field>(Fields.size()+1);
						
						comp.add(base.getReg(j));
						comp.add(n.getReg(j));
						
						Fields.add(comp);
						break;
						
					case GameInfo.CITY:
						RegionComposite<City> comp1 = new RegionComposite<City>(Cities.size()+1);
						
						comp1.add(base.getReg(j));
						comp1.add(n.getReg(j));
						
						Cities.add(comp1);
						break;
					case GameInfo.ROAD:
						RegionComposite<Road> comp2 = new RegionComposite<Road>(Roads.size()+1);
						
						comp2.add(base.getReg(j));
						comp2.add(n.getReg(j));
						
						Roads.add(comp2);
						break;
					}
				}
			}
		}
	}


}
