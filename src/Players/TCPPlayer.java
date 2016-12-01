package Players;
/*
    This class extends Player and provides explicit functionality
    for communicating with the TCP server
*/
import java.util.*;

import GameEntities.Board;
import GameEntities.Deck;
import GameEntities.DataTools.MeeplePlacement;
import GameEntities.DataTools.Move;

public class TCPPlayer extends Player {

	public TCPPlayer (Board board, String pid, Deck deck)
	{
		super(board,pid,deck);
	}
	
	public MeeplePlacement decideMeeple ()
    {
		return null;
	}

	@Override
	public Move decideMove() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public MeeplePlacement decideMeeple(Tile t) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
