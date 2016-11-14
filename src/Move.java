
public class Move {
	public Coor loc;
	public int rot;
	public Tile tile;

	public Move(Coor c, int r, Tile t){
		loc = c;
		rot = r;
		tile = t;
	}
}
