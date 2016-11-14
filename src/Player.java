import java.util.*;

/*-----------------------------------------------------------------------------------------------
|	Player Class:
|  	Contains information and behaviours of a Player Object
-------------------------------------------------------------------------------------------------*/

public abstract class Player {
	private int score;
	private List<Meeple> currentMeeples;

	// SEND MOVE
	// Player sends a move object
	// Move object contains a coordinate, a rotation, and a tile
	public void sendMove(Move m);
	{
		// method call to send the move to the Board
	}

	//Any changes to a Players score has to go through this setter
	public void altScore(int score){
		this.score = score;
	}

	public int  getScore(){
		return score;
	}
}
