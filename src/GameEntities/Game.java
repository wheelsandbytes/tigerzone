package GameEntities;
import java.util.ArrayList;
import java.util.List;

import GameEntities.DataTools.Coor;
import GameEntities.DataTools.Move;
import GameEntities.Factories.TileFactory;
import GameEntities.Tile.Tile;
import Players.AI;
import Players.Player;
import Players.TCPPlayer;

public class Game {
	//Public references
	public Board board;
	public Player player1;
	public Player player2;
	public Deck deck;

	//Private References
	private Drawer drawer;
	private List<Tile> tiles;
	private TileFactory factory;


	//Constructor 1
	public Game(String pid1, String pid2, String startTile, String[] types){
		setGame(pid1, pid2, startTile, types, 0, 0, 0);
	}

	//Constructor 2
	public Game(String pid1, String pid2, String startTile, String[] types, int initX, int initY, int initRot){
		setGame(pid1, pid2, startTile, types, initX, initY, initRot);
	}

	//Makes move directly given Player pid
	public void playMove(String pid, Move move){
		if(pid.equals(player1.name))
			player1.makeMove(move);
		else if(pid.equals(player2.name))
			player2.makeMove(move);
	}

	public Move getMoveFromPlayer(String pid)
	{
		if(pid.equals(player1.name))
			return player1.decideMove();
		else if(pid.equals(player2.name))
			return player2.decideMove();
		else
		return null;
	}

	//Helper Functions
	//----------------------------------------------------------------------------------------------------------------------
	//Setting up the Game
	private void setGame(String pid1, String pid2, String startTile, String[] types, int initX, int initY, int initRot){
		//Object initialization
		tiles = new ArrayList<Tile>();
		factory = new TileFactory();
		deck = new Deck();

		//Continue Object initialization
		board = new Board(null);
		board.place(new Move(new Coor(initX,initY), initRot, factory.create(startTile)));

		//Setup Deck
		for(String type : types){
			tiles.add(factory.create(type));
		}deck.setStandardDeck(tiles);

		//Player setup
		player1 = new AI(board, pid1, deck);
		player2 = new TCPPlayer(board, pid2, deck);

		//Drawer initialization
		//Drawer needs some testing hence commented out for now
//		drawer = new Drawer(board.getGraph(), deck);
	}


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
//		drawer.refresh();
	}
}
