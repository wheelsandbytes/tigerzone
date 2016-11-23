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
		p.updateScore(2);
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
	}

	
	@Test
	public void testFieldClass(){
		Region field = new Jungle();
		assertEquals(field.getType(), GameInfo.FIELD);
		assertTrue(field instanceof Region);
	}

	@Test
	public void testCityClass(){
		Region city = new Lake();
		assertEquals(city.getType(), GameInfo.CITY);
		assertTrue(city instanceof Region);
	}
	
	@Test
	public void testChurchClass(){
		Region church = new Den();
		assertEquals(church.getType(), GameInfo.CHURCH);
		assertTrue(church instanceof Region);
	}
	
	@Test
	public void testRoadClass(){
		Region road = new Trail();
		assertEquals(road.getType(), GameInfo.ROAD);
		assertTrue(road instanceof Region);
	}

	
	
	public static void main(String args[]){
		UnitTests t = new UnitTests();
	}
}
