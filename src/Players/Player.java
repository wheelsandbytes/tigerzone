package Players;
import java.util.*;
/*------------------------------------------------------------------------------
|	Player Class:
|  	Contains information and behaviors of a Player Object
|	NOTE: Board does not contain players, players contain Board
|	For more info please visit the UML diagram for the program
------------------------------------------------------------------------------*/

import GameEntities.Board;
import GameEntities.Deck;
import GameEntities.DataTools.Coor;
import GameEntities.DataTools.MeeplePlacement;
import GameEntities.DataTools.Move;
import GameEntities.Regions.Lake;
import GameEntities.Regions.Region;
import GameEntities.Regions.Trail;
import GameEntities.Tile.Tiger;
import GameEntities.Tile.Tile;
import GlobalReferences.GameInfo;


public abstract class Player {
	//Data used by Player object
	public String name;
	protected Deck globalDeck;
	protected Board mainBoard;
	protected int score = 0;
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
	public abstract MeeplePlacement decideMeeple();
	
	public void placeTiger(Tiger t, Coor c){
		
		for(Region r : mainBoard.getGraph().locate(c.x, c.y).getRegions()){
			if(r.getMeeples() != null){
				r.setMeeple(t);
				return;
			}
		}
	}
	
	public Tiger getTiger(){
		for(Tiger t : currentTigers){
			if(!t.placed){
				return t;
			}
		}
		return null;
	}
	
	public void removeTiger(Coor c){
		for(Region r : mainBoard.getGraph().locate(c.x, c.y).getRegions()){
			if(!r.getMeeples().isEmpty()){
				r.removeMeeple(r.getMeeples().get(0));
				return;
			}
		}
	}
	
	//Makes the Move after it's been decided
	public void makeMove(Move m){
		mainBoard.place(m);
	}
	
	
	//Places the Meeple after it's been decided
	public void placeMeeple(MeeplePlacement mp, Tile tile){
		//If Player wants to place a Tiger
		if(mp != null && mp.type == GameInfo.TIGER && !tile.hasTiger){
			
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
		else if(mp != null && mp.type == GameInfo.CROCODILE && !tile.hasCrocodile){
			if(crocodiles != 0)
				tile.placeCrocodile();
				crocodiles--;
		}
		
		for(Region r : tile.getRegions()){
			if(r instanceof Lake && ((Lake) r).getCompleted()) { ((Lake) r).getComp().returnTigers(); }
			
			else if(r instanceof Trail && ((Trail) r).getComp().checkComplete()) { ((Trail) r).getComp().returnTigers(); }
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
	
	public String toString(){
		return this.name;
	}
}