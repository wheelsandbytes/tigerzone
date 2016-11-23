/*
Move object contains:
	Coor object (x and y coordinate)
	A rotation integer
	The Tile object that is handed from the server
*/

public class Move {
	private Coor loc;
	private int rot;
	private Tile tile;
	private int tigerZone = 0;
	private boolean crocodile = false;
	private Tiger tiger;

	public Move(Coor c, int r, Tile t){
		loc = c;
		rot = r;
		tile = t;
	}
	
	public Move(Coor c, int r, Tile t, int tc, Tiger tig){
		loc = c;
		rot = r;
		tile = t;
		tigerZone = tc;
		tiger = tig;
	}
	
	public Move(Coor c, int r, Tile t, boolean croc){
		loc = c;
		rot = r;
		tile = t;
		crocodile = croc;
	}
	
	public void setZone(int i){
		tigerZone = i;
	}
	
	public void setCroc(boolean croc){
		crocodile = croc;
	}
	
	public Coor getLocation() { return loc; }

	public int getRotation() { return rot; }

	public Tile getTile() { return tile; }
	
	public Tiger getTiger() { return tiger; }
}
