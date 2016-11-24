import static org.junit.Assert.*;
import org.junit.Test;
/*-----------------------------------------------------------------------------------------------
|	UnitTests Class:
|  	Contains all Unit Tests for our Program
|	(The better way is to have a Test Class for every actual Class,
|   and a Test Method per actual Method but for now this will do)
-------------------------------------------------------------------------------------------------*/

public class UnitTests {
	TileFactory t = new TileFactory();
	
	@Test
	public void testPlayerClass(){
		Player p = new AI();
		assertTrue(p instanceof Player);
		assertTrue(p instanceof AI);
		assertEquals(p.getScore(), 0);
		p.updateScore(2);
		assertEquals(p.getScore(), 2);
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
	
	@Test
	public void placeTest(){
		Board b = new Board();
		Tile test1 = t.create("TJTJ-");
		Tile test2 = t.create("TTTT-");
		Tile test3 = t.create("TJJT-");
		Tile test4 = t.create("JJJJ-");
		b.place(new Move(new Coor(0,0), 0, test1));
		Move m = b.find(test2).get(0);
		b.place(m);
		m = b.find(test3).get(2);
		b.place(m);
		System.out.println(b.map.Trails.get(0).score());
		m = b.find(test4).get(0);
		b.place(m);
		b.find(test4);
	}

	public static void main(String args[]){
		UnitTests t = new UnitTests();
	}
}
