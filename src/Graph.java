/*-----------------------------------------------------------------------------------------------	
|	Graph Interface:   											
|  	Contains methods common to all types of Graphs			  										
-------------------------------------------------------------------------------------------------*/

public interface Graph {
	//Function should add a new Tile based on two Tile references and their joining sides
	//For valid joining sides check GameInfo.java
	void add(Tile base, int side, Tile newTile);
	
	//Function should add a new Tile based on its reference and board coordinates
	void add( int x, int y, Tile newTile);
	
	//Returns reference to a tile in position (x,y) respective to root tile (0,0) 
	Tile locate(int x, int y);
	
	//Return a String representation of the Board
	String toString();
}
