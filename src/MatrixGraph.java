/*-----------------------------------------------------------------------------------------------	
|	MatrixGraph Class:   											
|  	Graph implementation using 2D array on a max size. Memory wise this is way more consuming than TreeGraph,
|	until a point (approximate 20MB of memory for this type of allocation), but WAY WAY faster.
|	Complexity: add(coordinate) O(1), locate(coordinate) O(1), toString() O(n), add(baseTile) O(n) 										
-------------------------------------------------------------------------------------------------*/

public class MatrixGraph implements Graph{
	//Center Row and Col, and Matrix with all Tile references
	Coor center;
	private int cRow, cCol;
	private Tile[][] matrix;
	private boolean[][] explored;
	
	//Coordinate Object
	public class Coor{
		public int row, col;
		Coor(int row, int col){this.row = row; this.col = col;};
	};
	
	
	MatrixGraph(Tile root){
		cRow = cCol = GameInfo.MAX_TILES - 1;
		int maxDimension = GameInfo.MAX_TILES*2;
		matrix = new Tile[maxDimension][maxDimension];
		
		//Allocate root
		matrix[cRow][cCol] = root;
		center = new Coor(cRow, cCol);

		//Designed for traversals
		explored = new boolean[maxDimension][maxDimension];
	}
	
	
	@Override
	public void add(Tile base, int side, Tile newTile) {
		
		Coor[] result = new Coor[1];
		resetArray(explored);
		findTile(center, result, base);
		
		//If findFile found the Tile result will not be null
		if(result[0] != null)
			matrix[result[0].row][result[0].col] = newTile;
	}

	
	@Override
	public void add(int x, int y, Tile newTile) {
		Coor c = mapCoordinates(x, y);
		if(validCoordinate(c) && emptyCoordinate(c)){
			//Check there is a neighbor
			if(!emptyCoordinate(mapCoordinates(x+1,y)) || !emptyCoordinate(mapCoordinates(x-1,y)) 
			|| !emptyCoordinate(mapCoordinates(x,y+1)) || !emptyCoordinate(mapCoordinates(x,y-1))){
				matrix[c.row][c.col] = newTile;
			}
			else System.out.println("Error - No neighbor Tiles available");
		}
		else System.out.println("Error - Invalid coordinates");
	}

	
	@Override
	public Tile locate(int x, int y) {
		Coor c = mapCoordinates(x, y);
		if(validCoordinate(c) && !emptyCoordinate(c))
			return matrix[c.row][c.col];
		else
			return new Tile("null");
	}
	
	@Override
	public String toString(){
		StringBuilder graph = new StringBuilder();
		StringBuilder row = new StringBuilder();
		
		for(int i=0; i<matrix.length; i++){
			row.setLength(0);
			for(int j=0; j<matrix[0].length; j++){
				if(matrix[i][j] == null)
					row.append(" ");
				else
					row.append(matrix[i][j].type);
			}
			graph.append(row.toString()+"/n");
		}
		
		return graph.toString();
	}
	
	
	//----------------------------------------------------------------------------------------------
	//Helper functions - not that important - ONLY FOR INTERNAL USE AND TESTING
	//----------------------------------------------------------------------------------------------
	protected Coor mapCoordinates(int x, int y){
		//Mapping from (0,0) coordinates as center
		return new Coor(cRow - y, cCol + x);
	}
	
	protected boolean validCoordinate(Coor c){
		 //Check index out of bounds
		 if(c.row >= 0 && c.row < matrix.length && c.col >= 0 && c.col < matrix[0].length)
			return true; 
		 return false;
	}
	
	protected boolean emptyCoordinate(Coor c){
		//Check there is a tile present
		 if(matrix[c.row][c.col] == null)
			 return true;
		 return false;
	}
	
	protected void findTile(Coor root, Coor[] result, Tile toFind){
		result = new Coor[]{root};
		
//		//Base case for breath first search
//		explored[root.row][root.col] = true;
//		if(emptyCoordinate(root)){
//			return;
//		}
//		//Check self
//		if(matrix[root.row][root.col] == toFind){
//			result = new Coor[]{root};
//			return;
//		}
//		
//		//Check children
//		Coor child = new Coor(0,0);
//		for(int i=0; i<GameInfo.SHIFT.length-1; i+=2){
//			child.row = root.row + GameInfo.SHIFT[i];
//			child.col = root.row + GameInfo.SHIFT[i+1]; 
//			if(!explored[child.row][child.col] && validCoordinate(child))
//				findTile(child, result, toFind);
//		}
	}
	
	protected void resetArray(boolean[][] A){
		for(int i=0; i<A.length; i++){
			for(int j=0; j<A[0].length; j++){
				A[i][j] = false;
			}
		}
	}
	
	protected void resetArray(){
		resetArray(explored);
	}
}
