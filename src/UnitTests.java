import static org.junit.Assert.*;

import java.util.Scanner;

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
		Scanner s = new Scanner(System.in);
		
		Tile test1 = t.create("TLTJ-");
		Tile test2 = t.create("TLJT-");
		Tile test3 = t.create("TLLL-");
		Tile test4 = t.create("JJTJX");
		Tile test5 = t.create("JLTTB");
		Tile test6 = t.create("JLLJ-");
		Tile test7 = t.create("LJJJ-");
		Tile test8 = t.create("TLTJD");
		Tile test9 = t.create("TJJT-");
		Tile test10 = t.create("TLTJD");
		Tile test11 = t.create("TJJT-");
		Tile test12 = t.create("TJTT-");
		Tile test13 = t.create("TLTJD");
		Tile test14 = t.create("TLJTP");
		b.place(new Move(new Coor(0,0), 0, test1));
		System.out.println(b.map);
		Move m = b.find(test2).get(4);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		m = b.find(test3).get(2);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		m = b.find(test4).get(2);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test5).get(3);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test6).get(0);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test7).get(6);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test8).get(3);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test9).get(6);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test10).get(8);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test11).get(4);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test12).get(3);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test13).get(2);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		m = b.find(test14).get(14);
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		System.out.println(b.dens.get(0).toString());
		
		
		/*
		Tile test15 = t.create("TJJT-");
		Tile test16 = t.create("TJJT-");
		Tile test17 = t.create("TJJT-");
		Tile test18 = t.create("TJJT-");
		b.place(new Move(new Coor(0,0), 0, test15));
		System.out.println(b.map);
		Move m = b.find(test16).get(s.nextInt());
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		m = b.find(test17).get(s.nextInt());
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		m = b.find(test18).get(s.nextInt());
		b.place(m);
		System.out.println(b.map);
		System.out.println(b.map.getScores());
		b.find(test18);
		*/
		
	}

	public static void main(String args[]){
		UnitTests t = new UnitTests();
		
	}
}
