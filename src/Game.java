import java.util.ArrayList;
import java.util.List;

public class Game {
	public Board board;
	public Player player1;
	public Player player2;
	public Deck deck;
	
	private Drawer drawer;
	private List<Tile> tiles;
	private TileFactory factory;
	private int initX = 0, initY = 0, initRot = 0;
	
	
	public Game(String pid1, String pid2, String[] types){
		//Object initialization
		tiles = new ArrayList<Tile>();
		factory = new TileFactory();
		deck = new Deck();
		
		//Setup Deck
		for(String type : types){
			tiles.add(factory.create(type));
		}deck.setStandardDeck(tiles);
		
		//Continue Object initialization
		board = new Board(null);
		board.place(new Move(new Coor(initX,initY), initRot, deck.getCurrent()));
		deck.next();
		
		//Player setup
		player1 = new AI(board, pid1, deck);
		player2 = new TCPPlayer(board, pid2, deck);
		
		//Drawer initialization
		//Drawer needs some testing hence commented out for now
		drawer = new Drawer(board.getGraph(), deck);
	}
	
	
	
	
	//Helper Functions
	//---------------------------------------------------------------------------------
	public static boolean equalMoves(Move m1, Move m2){
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
	public boolean validMove(Move move){
		for(Move m : board.find(move.getTile())){
			if(equalMoves(move, m))
				return true;
		}
		return false;
	}
	
	
	//Refreshes the Game UI representation
	public void refresh(){
		drawer.refresh();
	}
}
