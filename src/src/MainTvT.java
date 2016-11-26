/*-----------------------------------------------------------------------------------------------	
|	Main Class:   											
|  	Contains Game Loop and helper functions for a Tester vs Tester simulation of the Game
|	NOTE: Helper functions and structure may be reusable for other simulations  										
 --------------------------------------------------------------------------------------------------*/

public class MainTvT {
	public static void main(String[] args) {
		
		//Instantiate Deck
		Deck deck = new Deck();
		deck.generateDeck();
		
		//Instantiate Board
		Tile root = deck.getCurrent();
		Board board = new Board(root);
		deck.next();
		
		//Instantiate Players
		Player p1 = new TesterPlayer(board, "Tester 1", deck);
		Player p2 = new TesterPlayer(board, "Tester 2", deck);
		Tile t1; Move m1; MeeplePlacement mp1;
		Tile t2; Move m2; MeeplePlacement mp2;
		
		//The Drawer will give a Real Tile Representation of theBoard
		//When the Graph changes, or the Deck changes it will update itself
		Drawer drawer = new Drawer(board.getGraph(), deck);
		
		
		//Game Loop --> NOTE: I had to comment out the validating of Moves bc it was not matching
		//Validation of Meeple placements not implemented yet
		while(!deck.isDone()){
			
			//Player1's Turn
			//----------------------------------------------------------------------
			t1 = deck.getCurrent();
			
//			do{	//Validate Move
				m1 = p1.decideMove();
//			}while(!validMove(m1, board));
			
			//If valid move
			p1.makeMove(m1);
			deck.next();
			
			do{	//Validate Meeple placement
				mp1 = p1.decideMeeple();
			}while(!validMeeple(mp1, board));
			
			//If valid Meeple placement
			p1.placeMeeple(mp1, t1);
			System.out.println("\n");
			
			
			
			//Player2's Turn
			//-------------------------------------------------------------------
			t2 = deck.getCurrent();
			
//			do{	//Validate Move
				m2 = p2.decideMove();
//			}while(!validMove(m2, board));
			
			//If valid move
			p2.makeMove(m2);
			deck.next();
			
			do{	//Validate Meeple placement
				mp2 = p2.decideMeeple();
			}while(!validMeeple(mp2, board));
			
			//If valid Meeple placement
			p2.placeMeeple(mp2, t2);
			System.out.println("\n");
		}
	}
	
	
	
	//Helper Functions
	//---------------------------------------------------------------------------------
	static boolean equalMoves(Move m1, Move m2){
		//Check Tiles to be equals
		if(!m1.getTile().getType().equals(m2.getTile().getType()))
			return false;
		//Check rotation to be equals
		if(m1.getRotation() != m1.getRotation())
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
