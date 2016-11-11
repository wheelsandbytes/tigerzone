import static org.junit.Assert.*;

import org.junit.Test;
/*-----------------------------------------------------------------------------------------------	
|	TestMatrixGraph Class:   											
|  	Contains Unit Tests for MatrixGraph Representation and all its internal methods.							
-------------------------------------------------------------------------------------------------*/

public class TestMatrixGraph {
	
	protected long getUsedRAM(){
		Runtime runtime = Runtime.getRuntime();
	    runtime.gc();
	    //Used memory is bytes: memory
	    //Used memory is megabytes: memory / (1024L * 1024L))
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    return memory / (1024L * 1024L);
	}
	
	
	@Test
	public void testMapCoordinates() {
		Tile root = new Tile("A");
		MatrixGraph testGraph = new MatrixGraph(root);
		MatrixGraph.Coor test1 = testGraph.new Coor(0, 76);
		MatrixGraph.Coor test2 = testGraph.new Coor(74, 76);
		MatrixGraph.Coor test3 = testGraph.new Coor(3, 4);
		
		assertEquals(test1.row, testGraph.mapCoordinates(0, 76).row);
		assertEquals(test1.col, testGraph.mapCoordinates(0, 76).col);
		assertEquals(test2.row, testGraph.mapCoordinates(0, 2).row);
		assertEquals(test2.col, testGraph.mapCoordinates(0, 2).col);
		assertEquals(test3.row, testGraph.mapCoordinates(-72, 73).row);
		assertEquals(test3.col, testGraph.mapCoordinates(-72, 73).col);
		assertTrue(getUsedRAM() < 10);
	}
	
	
	@Test
	public void testValidCoordinate() {
		Tile root = new Tile("A");
		MatrixGraph testGraph = new MatrixGraph(root);
		MatrixGraph.Coor test1 = testGraph.mapCoordinates(76, -76);
		MatrixGraph.Coor test2 = testGraph.mapCoordinates(4, 77);
		MatrixGraph.Coor test3 = testGraph.mapCoordinates(-76, 5);
		
		assertEquals(true, testGraph.validCoordinate(test1));
		assertEquals(false, testGraph.validCoordinate(test2));
		assertEquals(true, testGraph.validCoordinate(test3));
		assertTrue(getUsedRAM() < 10);
	}

	
	@Test
	public void testLocate() {
		Tile root = new Tile("A");
		MatrixGraph testGraph = new MatrixGraph(root);
		
		assertEquals("A", testGraph.locate(0,0).type);
		assertEquals("null", testGraph.locate(0,1).type);
		assertTrue(getUsedRAM() < 10);
	}
	
	
	@Test
	public void testAddByCoordinate() {
		Tile root = new Tile("A");
		MatrixGraph testGraph = new MatrixGraph(root);
		testGraph.add(0, 1, new Tile("B"));
		testGraph.add(1, 1, new Tile("C"));
		testGraph.add(-1, 1, new Tile("D"));
		
		assertEquals("B", testGraph.locate(0,1).type);
		assertEquals("C", testGraph.locate(1,1).type);
		assertEquals("D", testGraph.locate(-1,1).type);
		assertEquals("null", testGraph.locate(3,3).type);
		assertTrue(getUsedRAM() < 10);
	}
	
	
	@Test
	public void testResetArray() {
		Tile root = new Tile("A");
		MatrixGraph testGraph = new MatrixGraph(root);
		boolean[][] test1 = new boolean[GameInfo.MAX_TILES*2][GameInfo.MAX_TILES*2];
		boolean[][] test2 = new boolean[GameInfo.MAX_TILES*2][GameInfo.MAX_TILES*2];
		test2[5][5] = true; test2[2][53] = true; test2[9][127] = true;
		
		testGraph.resetArray(test2);
		assertArrayEquals(test1, test2);
		assertTrue(getUsedRAM() < 10);
	}
	
	
	@Test
	public void testFindTile(){
		Tile root = new Tile("A"), B = new Tile("B"), C = new Tile("C"), D = new Tile("D");
		MatrixGraph testGraph = new MatrixGraph(root);
		MatrixGraph.Coor center = testGraph.mapCoordinates(0, 0);
		MatrixGraph.Coor testB, testC, testD;
		testGraph.add(0, 1, B);
		testGraph.add(1, 1, C);
		testGraph.add(-1, 1, D);
		testB = testGraph.findTile(center, B);	testGraph.resetArray();
		testC = testGraph.findTile(center, C);	testGraph.resetArray();
		testD = testGraph.findTile(center, D);	testGraph.resetArray();
		
		assertEquals(testGraph.mapCoordinates(0, 1).row, testB.row);
		assertEquals(testGraph.mapCoordinates(0, 1).col, testB.col);
		assertEquals(testGraph.mapCoordinates(1, 1).row, testC.row);
		assertEquals(testGraph.mapCoordinates(1, 1).col, testC.col);
		assertEquals(testGraph.mapCoordinates(-1, 1).row, testD.row);
		assertEquals(testGraph.mapCoordinates(-1, 1).col, testD.col);
		assertTrue(getUsedRAM() < 10);
	}
	
	
	@Test
	public void testAddByTile(){
		Tile root = new Tile("A");
		Tile B = new Tile("B");
		Tile C = new Tile("C");
		Tile D = new Tile("D");
		MatrixGraph testGraph = new MatrixGraph(root);
		testGraph.add(root, GameInfo.UP, B);
		testGraph.add(B, GameInfo.RIGHT, C);
		testGraph.add(C, GameInfo.DOWN, D);
		
		assertEquals("B", testGraph.locate(0,1).type);
		assertEquals("C", testGraph.locate(1,1).type);
		assertEquals("D", testGraph.locate(1,0).type);
		assertTrue(getUsedRAM() < 10);
	}
	
	@Test
	public void testToString() {
		Tile root = new Tile("A");
		MatrixGraph testGraph = new MatrixGraph(root);
		testGraph.add(0, 1, new Tile("B"));
		testGraph.add(1, 1, new Tile("C"));
		testGraph.add(-1, 1, new Tile("D"));
		testGraph.add(1, 0, new Tile("E"));
		assertTrue(testGraph.toString() != "");
	}
}
