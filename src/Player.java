import java.util.*;
/*------------------------------------------------------------------------------
|	Player Class:
|  	Contains information and behaviors of a Player Object
|	NOTE: Board does not contain players, players contain Board
|	For more info please visit the UML diagram for the program
------------------------------------------------------------------------------*/


public abstract class Player {
	//Data used by Player object
	public String name;
	protected Deck globalDeck;
	protected Board mainBoard;
	protected int score;
	protected int crocodiles;
	protected List<Tiger> currentTigers;
	
	//Default Constructor
	public Player(){}
	
	//Fancy Constructor
	public Player(Board mainBoard, String name, Deck deck){
		score = 0;
		this.name = name;
		this.globalDeck = deck;
		this.mainBoard = mainBoard;
		crocodiles = GameInfo.MAX_CROCS;
		currentTigers = new LinkedList<Tiger>();
		
		//Create Tigers
		for(int i=0; i<GameInfo.MAX_TIGERS; i++){
			currentTigers.add(new Tiger(this));
		}
	}
	
	
	//Different kinds of Players decide to make their Moves in different ways
	//Tester Player waits for Input, AI decides based on Algorithm, Remote Player (TCP) waits for Server Signal
	public abstract Move decideMove();
	public abstract MeeplePlacement decideMeeple(Tile t);
	
	
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