import static org.junit.Assert.*;
import org.junit.Test;
/*-----------------------------------------------------------------------------------------------
|	UnitTests Class:
|  	Contains all Unit Tests for our Program
|	(The better way is to have a Test Class for every actual Class,
|   and a Test Method per actual Method but for now this will do)
-------------------------------------------------------------------------------------------------*/

public class UnitTests {
	@Test
	public void testPlayerClass(){
		Player p = new AI();
		assertTrue(p instanceof Player);
		assertTrue(p instanceof AI);
		assertEquals(p.getScore(), 0);
		p.altScore(2);
		assertEquals(p.getScore(), 2);
	}

	@Test
	public void testBoardClass(){
		Board b = new Board();
		assertEquals(b.checkValid(), false);
	}

	//Continue Testing Components as desired
	//.......
	@Test
	public void testTileClass(){
		Tile t = new Tile();
		assertEquals(null, t.innerRegions);
		assertEquals(null, t.adjacentTiles);
	}

	/*
	@Test
	public void testFieldClass(){
		Region field = new Field();
		assertEquals(field.getType(), GameInfo.FIELD);
		assertTrue(field instanceof Region);
	}

	@Test
	public void testCityClass(){
		Region city = new City();
		assertEquals(city.getType(), GameInfo.CITY);
		assertTrue(city instanceof Region);
	}
	
	@Test
	void testChurchClass(){
		Region church = new Church();
		assertEquals(church.getType(), GameInfo.CHURCH);
		assertTrue(church instanceof Region);
	}
	
	@Test
	public void testRoadClass(){
		Region road = new Road();
		assertEquals(road.getType(), GameInfo.ROAD);
		assertTrue(road instanceof Region);
	}
*/
	
	@Test
	public void testFarmerClass(){
		Meeple farmer = new Farmer();
		assertEquals(farmer.getType(), GameInfo.FIELD);
		assertTrue(farmer instanceof Meeple);
		assertTrue(farmer.isAvailable());
		assertEquals(null, farmer.getRegion());
	}

	@Test
	public void testKnightClass(){
		Meeple knight = new Knight();
		assertEquals(knight.getType(), GameInfo.CITY);
		assertTrue(knight instanceof Meeple);
		assertTrue(knight.isAvailable());
		assertEquals(null, knight.getRegion());
	}
	
	@Test
	public void testMonkClass(){
		Meeple monk = new Monk();
		assertEquals(monk.getType(), GameInfo.CHURCH);
		assertTrue(monk instanceof Meeple);
		assertTrue(monk.isAvailable());
		assertEquals(null, monk.getRegion());
	}
	
	@Test
	public void testThiefClass(){
		Meeple thief = new Thief();
		assertEquals(thief.getType(), GameInfo.ROAD);
		assertTrue(thief instanceof Meeple);
		assertTrue(thief.isAvailable());
		assertEquals(null, thief.getRegion());
	}
	
	public void MeepleTests(){
		testFarmerClass();
		testKnightClass();
		testMonkClass();
		testThiefClass();
	}
	
	public static void main(String args[]){
		UnitTests t = new UnitTests();
		t.MeepleTests();
	}
}
