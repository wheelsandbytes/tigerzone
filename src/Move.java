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

	public Move(Coor c, int r, Tile t){
		loc = c;
		rot = r;
		tile = t;
	}

	public Coor getLocation() { return loc; }

	public int getRotation() { return rot; }

	public Tile getTile() { return tile; }
}
