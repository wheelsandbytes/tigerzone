import java.util.*;
/*------------------------------------------------------------------------------
|	Player Class:
|  	Contains information and behaviors of a Player Object
|	NOTE: Board does not contain players, players contain Board
|	For more info please visit the UML diagram for the program
------------------------------------------------------------------------------*/


public abstract class Player {
	private Board mainBoard;
	private int score;
	private Move move;
	private int crocodiles = GameInfo.MAX_CROCS;
	private List<Tiger> currentTigers;
	
	public class MeeplePlacement{
		public int type;
		public int pos;
		MeeplePlacement(int type, int pos){
			this.type = type;
			this.pos = pos;
		}
	}
	
	//Different kinds of Players decide to make their Moves in different ways
	//Tester Player waits for Input, AI decides based on Algorithm, Remote Player (TCP) waits for Server Signal
	public abstract Move decideMove();
	public abstract MeeplePlacement decideMeeple();
	
	
	//Makes the Move after it's been decided
	public void makeMove(Move m){
		mainBoard.place(m);
	}
	
	
	//Places the Meeple after it's been decided
	public void placeMeeple(MeeplePlacement mp, Tile tile){
		
		//If Player wants to place a Tiger
		if(mp.type == GameInfo.TIGER && !tile.hasTiger){
			
			//Get the next available Tiger
			for(Tiger t : currentTigers){
				if(!t.placed){
					tile.placeTiger(mp.pos, t);
					t.placed = true;
					break;
				}
			}
		}
		//If Player wants to place a Crocodile
		else if(mp.type == GameInfo.CROCODILE && !tile.hasCrocodile){
			if(crocodiles != 0)
				tile.placeCrocodile();
		}
	}

	
	//Any changes to a Players score has to go through this method
	public void updateScore(int n){
		this.score += n;
	}

	
	//Returns score
	public int  getScore(){
		return score;
	}
}