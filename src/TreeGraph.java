/*-----------------------------------------------------------------------------------------------	
|	TreeGraph Class:   											
|  	Graph implementation using Tree Structure. Memory wise it is more efficient than MatrixGraph.
|	TreeGraph is also slower due to Complexity and lack of Spatial Locality in CPU cache.
|	Complexity: add(coordinate) O(n), locate(coordinate) O(n), toString() O(n), add(baseTile) O(n)  								
-------------------------------------------------------------------------------------------------*/

public class TreeGraph implements Graph{
	
	//Base container for tiles
	//Offers a better encapsulation for Tile Objects and decouples the system
	private class Node{
		public int x, y;
		public Tile top, right, bottom, left, value;
		
		Node(int x, int y, Tile value){
			this.x = x;
			this.y = y;
			this.value = value;
			top = null; right = null;
			bottom = null; left = null;
		}
	};
	
	private Node root;
	
	TreeGraph(){
		Tile rootTile = new Tile();
		root = new Node(0,0, rootTile);
	}
	
	@Override
	public Tile locate(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Tile base, int side, Tile newTile) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void add(int x, int y, Tile newTile) {
		// TODO Auto-generated method stub		
	}


}
