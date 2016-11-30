/*-----------------------------------------------------------------------------------------------	
|	MatrixGraph Class:   											
|  	Graph implementation using 2D array on a max size. Memory wise this is way more consuming than TreeGraph,
|	until a point (approximate 20MB of memory for this type of allocation), but WAY WAY faster.
|	Complexity: add(coordinate) O(1), locate(coordinate) O(1), toString() O(n^2), add(baseTile) O(n) 										
-------------------------------------------------------------------------------------------------*/

public class MatrixGraph implements Graph{
	//Center Row and Col, and Matrix with all Tile references
	Coor center;
	private int cRow, cCol, rootX, rootY;
	private Tile[][] matrix;
	private boolean[][] explored;
	
	//Coordinate Object
	protected class Coor{
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
		
		rootX = 0;
		rootY = 0;
		//Designed for traversals
		explored = new boolean[maxDimension][maxDimension];
	}
	
	
	@Override
	public void add(Tile base, int side, Tile newTile) {
		resetArray(explored);
		Coor result = findTile(center, base);
		//If findFile found the Tile result will not be null
		if(result == null) return;
		
		//Get the coordinate being inserted
		switch(side){
			case GameInfo.UP:
				result.row--; 
				break;
			case GameInfo.RIGHT:
				result.col++;
				break;
			case GameInfo.DOWN:
				result.row++;
				break;
			case GameInfo.LEFT:
				result.col--;
				break;
			default:return;
		}
		
		//If valid then add the new tile
		if(validCoordinate(result) && emptyCoordinate(result))
			matrix[result.row][result.col] = newTile;
	}

	
	@Override
	public void add(int x, int y, Tile newTile) {
		Coor c = mapCoordinates(x, y);
		if(validCoordinate(c) && emptyCoordinate(c)){
			//Check there is a neighbor
			//if(!emptyCoordinate(mapCoordinates(x+1,y)) || !emptyCoordinate(mapCoordinates(x-1,y)) 
			//|| !emptyCoordinate(mapCoordinates(x,y+1)) || !emptyCoordinate(mapCoordinates(x,y-1))){
				matrix[c.row][c.col] = newTile;
			//}
			//else System.out.println("Error - No neighbor Tiles available");
		}
	}

	
	@Override
	public Tile locate(int x, int y) {
		//Get Coordinate and validate it
		Coor c = mapCoordinates(x, y);
		if(validCoordinate(c) && !emptyCoordinate(c))
			return matrix[c.row][c.col];
		//If nothing found return null Tile
		else return null;
	}
	
	@Override
	public String toString(){
		//Adjust the board screen
		int leftCut = 1, rightCut = 2, base = 3;
		//Build Board representation
		StringBuilder graph = new StringBuilder();
		StringBuilder row = new StringBuilder();
		
		//Find all Tiles that are present in the Graph
		for(int i=0; i<matrix.length; i++){
			row.setLength(0);
			for(int j=matrix[0].length*leftCut/base; j<matrix[0].length*rightCut/base; j++){
				if(matrix[i][j] == null)
					row.append("  ");
				else
					row.append("-"+matrix[i][j].getType());
			}
			if(row.toString().trim().length() > 0)
				graph.append(row.toString()+"\n");
		}
		//Return the String representation
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
	
	
	protected Coor findTile(Coor root, Tile toFind){
		//Base case for breath first search
		explored[root.row][root.col] = true;
		if(emptyCoordinate(root))
			return null;
		
		//Check self
		if(matrix[root.row][root.col] == toFind){
			return root;
		}
		
		//Check children
		Coor child = new Coor(cRow,cCol), temp = null;
		for(int i=0; i<GameInfo.SHIFT.length; i+=2){
			
			//Visit all children top, right, bot, left
			child.row = root.row + GameInfo.SHIFT[i];
			child.col = root.col + GameInfo.SHIFT[i+1];
			
			//If current Child is not out of bounds and has not been visited, go check it
			if(!explored[child.row][child.col] && validCoordinate(child)){
				temp = findTile(child, toFind);
				//If something was found, return the coordinates
				if(temp != null)
					return temp;
			}
		}
		return null;
	}
	
	
	protected void resetArray(boolean[][] A){
		//Simple array reset
		for(int i=0; i<A.length; i++){
			for(int j=0; j<A[0].length; j++){
				A[i][j] = false;
			}
		}
	}
	
	//Overloaded version
	protected void resetArray(){
		resetArray(explored);
	}
}
