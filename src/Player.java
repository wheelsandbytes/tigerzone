import java.util.*;

/*------------------------------------------------------------------------------
|	Player Class:
|  	Contains information and behaviours of a Player Object
------------------------------------------------------------------------------*/

/*
Player's functions:

Set the move:
	- The player's adapter will call this after having determined
		the details for the Move object
	- This method is meant to be overridden based on the type
		of player that is playing (AI, TCP player, test player, etc)

Make a move:
	- The board will call this method before placing a tile
	- The method will return the Player's Move object

Update the score:
	- The board will send a score to update in the Player
	- The method is void, and takes in an int
	- Adds the int to the existing score

Get the score:
	- The board will call this to retrieve the current score
	- This method returns an int
*/

public abstract class Player {
	private int score;
	private Move move;
	// private List<Meeple> currentMeeples;

	// Move object contains a coordinate, a rotation, and a tile
	// This may be overridden based on the type of player!
	private void setMove(Coor c, Rot r, Tile t)
	{
		move = new Move(c,r,t);
	}

	// The board will call this to get the move from the player
	// The board passes in a Tile object
	public Move makeMove(Tile t);
	{
		setMove(Tile t);
		return move;
	}

	//Any changes to a Players score has to go through this method
	public void updateScore(int n)
	{
		this.score += n;
	}

	// Board can call this to get the current score
	public int  getScore(){
		return score;
	}
}
