/*-----------------------------------------------------------------------------------------------	
|	Main Class:   											
|  	Contains Game Loop and helper functions for a Tester vs Tester simulation of the Game
|	NOTE: Helper functions and structure may be reusable for other simulations  										
 --------------------------------------------------------------------------------------------------*/

public class MainTvT {
	public static void main(String[] args) {
		
		Drawer d = new Drawer();
		
//		//Instantiate Deck
//		Deck deck = new Deck();
//		deck.generateDeck();
//		
//		
//		//Instantiate Board
//		Tile root = deck.getCurrent();
//		Board board = new Board(root);
//		Graph graph = board.getGraph();
//		deck.next();
//		
//		
//		//Instantiate Players
//		Player p1 = new TesterPlayer(board, "Tester 1", deck);
//		Player p2 = new TesterPlayer(board, "Tester 2", deck);
//		Tile t1; Move m1; MeeplePlacement mp1;
//		Tile t2; Move m2; MeeplePlacement mp2;
//		
//		
//		while(!deck.isDone()){
//			System.out.println(graph);
//			
//			//Player1's Turn
//			//----------------------------------------------------------------------
//			t1 = deck.getCurrent();
//			System.out.println("Current Tile: "+t1.getType());
//			
//			do{	//Validate Move
//				m1 = p1.decideMove();
//			}while(!validMove(m1, board));
//			
//			//If valid move
//			p1.makeMove(m1);
//			
//			do{	//Validate Meeple placement
//				mp1 = p1.decideMeeple();
//			}while(!validMeeple(mp1, board));
//			
//			//If valid Meeple placement
//			p1.placeMeeple(mp1, t1);
//			
//			
//			//Change Turns
//			//-------------------------------------------------------------------
//			deck.next();
//			System.out.println(graph);
//			
//			
//			
//			//Player2's Turn
//			//-------------------------------------------------------------------
//			t2 = deck.getCurrent();
//			System.out.println("Current Tile: "+t2.getType());
//			
//			do{	//Validate Move
//				m2 = p2.decideMove();
//			}while(!validMove(m2, board));
//			
//			//If valid move
//			p2.makeMove(m2);
//			
//			do{	//Validate Meeple placement
//				mp2 = p2.decideMeeple();
//			}while(!validMeeple(mp2, board));
//			
//			//If valid Meeple placement
//			p2.placeMeeple(mp2, t2);
//		}
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
