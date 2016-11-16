import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Player Class:
|  	Contains information and behaviours of a Player Object
-------------------------------------------------------------------------------------------------*/

public abstract class Player {
	private int score;
	private List<Meeple> currentMeeples;

	// PLAY MOVE
	// Player sends a move object
	// Move object contains a coordinate, a rotation, and a tile
	public Move playMove(Coor c, Rot r, Tile t);
	{
		// method call to send the move to the Board
		// set the coordinate, rotation first
		// package the tile to be sent into a move object
		// Return a Move object for the TCP adapter to grab
		Move m = new Move(c,r,t);
		return m;
	}

	//Any changes to a Players score has to go through this setter
	public void altScore(int score){
		this.score = score;
	}

	public int  getScore(){
		return score;
	}
}
