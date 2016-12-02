package MainSimulations;
import java.util.Scanner;

import GameEntities.Board;
import GameEntities.Deck;
import GameEntities.Drawer;
import GameEntities.DataTools.Coor;
import GameEntities.DataTools.MeeplePlacement;
import GameEntities.DataTools.Move;
import GameEntities.Tile.Tile;
import GlobalReferences.GameInfo;
import Players.AI;
import Players.Player;

public class MainAvP {
public static void main(String[] args) {
		
		//Instantiate Deck
		Deck deck = new Deck();
		deck.generateDeck();
		
		//Instantiate Board
		Tile root = deck.getCurrent();
		Board board = new Board();
		board.place(new Move(new Coor(0,0), 0, root));
		deck.next();
		
		//Instantiate Players
		Player p1 = new AI(board, "AI", deck);
		Player p2 = new AI(board, "Tester", deck);
		Tile t1; Move m1; MeeplePlacement mp1;
		Tile t2; Move m2; MeeplePlacement mp2;
		
		//The Drawer will give a Real Tile Representation of theBoard
		//When the Graph changes, or the Deck changes it will update itself
		Drawer drawer = new Drawer(board.getGraph(), deck);
		
		
		//Game Loop --> NOTE: Validation of Meeple placements not implemented yet
		while(!deck.isDone()){
			Scanner s = new Scanner(System.in);

			//Player1's Turn
			//----------------------------------------------------------------------
			t1 = deck.getCurrent();
			
			mp1=null;
			mp2=null;
			
			m1 = p1.decideMove();
		
			
			//If valid move
			if(m1 != null){ p1.makeMove(m1);
			
			
			drawer.refresh();
						
			mp1 = p1.decideMeeple();
			p1.placeMeeple(mp1, t1);
			}
			
			String placement1 = "NOTHING";
			
			if(mp1 != null){
				if(mp1.type == GameInfo.CROCODILE){
					placement1 = "CROCODILE";
				}
				else{
					placement1 = "TIGER AT " + mp1.pos;
				}
			}
			
			deck.next();
			drawer.refresh();
			System.out.println(board.getMap());
			System.out.println(board.getMap().getScores());
			System.out.println("Player " + p1.name + " Placed " + placement1);
			System.out.println(p1.toString() + ": " + p1.getScore() + " " + p2.toString() + ": " + p2.getScore());
			s.next();
			
			//Player2's Turn
			//-------------------------------------------------------------------
			t2 = deck.getCurrent();
			
			m2 = p2.decideMove();
			
			//If valid move
			if(m2 != null){ p2.makeMove(m2);
			
			
			drawer.refresh();
						
			mp2 = p2.decideMeeple();
			p2.placeMeeple(mp2, t2);
			}
			
			String placement2 = "NOTHING";
			
			if(mp2 != null){
				if(mp2.type == GameInfo.CROCODILE){
					placement2 = "CROCODILE";
				}
				else{
					placement2 = "TIGER AT " + mp2.pos;
				}
			}			
			deck.next();
			drawer.refresh();
			System.out.println(board.getMap());
			System.out.println(board.getMap().getScores());
			System.out.println("Player " + p1.name + " Placed " + placement2);
			System.out.println(p1.toString() + ": " + p1.getScore() + " " + p2.toString() + ": " + p2.getScore());
			s.next();
		}
	}
	
	
	
	//Helper Functions
	//---------------------------------------------------------------------------------
	static boolean equalMoves(Move m1, Move m2){
		//Check Tiles to be equals
		if(!m1.getTile().getType().equals(m2.getTile().getType()))
			return false;
			
		//Check rotation to be equals
		if(m1.getRotation() != m2.getRotation())
			return false;
			
		//Check Coordinate
		if(!m1.getLocation().equals(m2.getLocation()))
			return false;
			
		//If all else failed, the Move is the same as far as we are concerned
		return true;
	}
	
	
	//Makes sure the move is valid
	static boolean validMove(Move move, Board board){
		for(Move m : board.find(move.getTile())){
			if(equalMoves(move, m))
				return true;
		}
		return false;
	}
	
	//Makes sure the Meeple placement is valid
	static boolean validMeeple(MeeplePlacement mp, Board board){
		//Needs implementation
		return true;
	}
}
