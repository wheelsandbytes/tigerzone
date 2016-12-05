package GameEntities;
import static org.junit.Assert.*;

import org.junit.Test;

import GameEntities.DataTools.Coor;
import GameEntities.DataTools.Move;
import GlobalReferences.GameInfo;

public class TestGame {

	@Test
	public void test() {
		Game game = new Game("Blue", "Red", GameInfo.JJJJN, GameInfo.allowedTiles);
		assertNotEquals(null, game.player1);
		assertNotEquals(null, game.player2);
		assertNotEquals(null, game.board);
		assertNotEquals(null, game.deck);
		
		//Try to make Games functions
		assertNotEquals(null, game.getMoveFromPlayer("Blue"));
		game.playMove("Blue", new Move(new Coor(0, 1), 0, game.deck.getCurrent()));
		game.validMove(new Move(new Coor(0, 1), 0, game.deck.getCurrent()));
	}

}
