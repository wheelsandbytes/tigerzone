import org.junit.Test;

/*-----------------------------------------------------------------------------------------------
|	UnitTests Class:
|  	Contains all Unit Tests for our Program
|	(The better way is to have a Test Class for every actual Class,
|   and a Test Method per actual Method but for now this will do)
-------------------------------------------------------------------------------------------------*/

public class UnitTests {
	@Test
	void testPlayerClass(){
		Player p = new AI();
		assertTrue(p instanceof Player);
		assertTrue(p instanceof AI);
		assertEquals(p.getScore, 0);
		p.altScore(2);
		assertEquals(p.getScore(), 2);
	}

	@Test
	void testBoardClass(){
		Board b = new Board();
		assertEquals(b.checkValid(), false);
	}

	//Continue Testing Components as desired
	//.......
	@Test
	void testTileClass(){
		Tile t = new Tile();
		assertEquals(null, t.innerRegions);
		assertEquals(null, t.adjacentTiles);
	}

	@Test
	void testRegionClass(){
		Region field = new Field();
		assertEquals(field.getType(), GameInfo.FIELD);
		assertTrue(field instanceof Region);

		Region city = new City();
		assertEquals(city.getType(), GameInfo.CITY);
		assertTrue(city instanceof Region);

		Region church = new Church();
		assertEquals(church.getType(), GameInfo.CHURCH);
		assertTrue(church instanceof Region);

		Region road = new Road();
		assertEquals(road.getType(), GameInfo.ROAD);
		assertTrue(road instanceof Region);

	}

	@Test
	void testMeepleClass(){
		Meeple farmer = new Farmer();
		assertEquals(farmer.getType(), GameInfo.FIELD);
		assertTrue(field instanceof Meeple);
		assertTrue(farmer.isAvailable());
		assertEquals(null, farmer.getRegion());

		Meeple knight = new Knight();
		assertEquals(field.getType(), GameInfo.CITY);
		assertTrue(knight instanceof Meeple);
		assertTrue(knight.isAvailable());
		assertEquals(null, knight.getRegion());

		Meeple monk = new Monk();
		assertEquals(field.getType(), GameInfo.CHURCH);
		assertTrue(monk instanceof Meeple);
		assertTrue(monk.isAvailable());
		assertEquals(null, monk.getRegion());

		Meeple thief = new Thief();
		assertEquals(field.getType(), GameInfo.ROAD);
		assertTrue(thief instanceof Meeple);
		assertTrue(thief.isAvailable());
		assertEquals(null, thief.getRegion());
	}
}
