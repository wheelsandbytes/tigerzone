import static org.junit.Assert.*;

import org.junit.Test;

public class TestTileFactory{

	@Test
	public void testCreate() {
		Tile t;
		TileFactory tf = new TileFactory();
		
		//First 7 Tiles
		t = tf.create(GameInfo.JJJJN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.JJJJX);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.JJTJX);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TTTTN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TJTJN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TJJTN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TJTTN);
		assertNotEquals(null, t);
		
		
		//Second 7 Tiles
		t = tf.create(GameInfo.LLLLN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.JLLLN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.LLJJN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.JLJLN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.LJLJN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.LJJJN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.JLLJN);
		assertNotEquals(null, t);
		
		
		//Third 7 Tiles
		t = tf.create(GameInfo.TLJTN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLJTP);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.JLTTN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.JLTTB);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLTJN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLTJD);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLLLN);
		assertNotEquals(null, t);
		
		
		//Fourth 7 Tiles
		t = tf.create(GameInfo.TLTTN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLTTP);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLLTN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLLTB);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.LJTJN);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.LJTJD);
		assertNotEquals(null, t);
		t = tf.create(GameInfo.TLLLC);
		assertNotEquals(null, t);
	}

}
