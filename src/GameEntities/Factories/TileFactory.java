package GameEntities.Factories;
import java.util.ArrayList;
import java.util.List;

import GameEntities.Regions.Cross;
import GameEntities.Regions.Den;
import GameEntities.Regions.Jungle;
import GameEntities.Regions.Lake;
import GameEntities.Regions.Region;
import GameEntities.Regions.Trail;
import GameEntities.Tile.Edge;
import GameEntities.Tile.Tile;
import GlobalRefferences.GameInfo;

/*-----------------------------------------------------------------------------------------------
|	TileFactory Class:
|  	Helps create different types of Tiles (For more info on possible types check out GameInfo class)
|	This class extends GameInfo in order to have access to all its constants directly
-------------------------------------------------------------------------------------------------*/

//EDGES LIST NUMERATION
/*----------------
|	  0 1 2		 |
|	2       0	 |
|	1       1	 |
|	0       2    |
|  	  2 1 0      |
------------------*/

//REGION MATRIX
/*--------------
  | 1 | 2 | 3 |
  |---|---|---|
  | 4 | 5 | 6 |	__________
  |---|---|---|           |
  | 7 | 8 | 9 |           |
--------------- 		  v
*/
//ARRAY = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ]


public class TileFactory  extends GameInfo implements Factory<Tile>{
	int tileCount = 0;

	@Override
	//NOTE: edges are being initialized TOP, RIGHT, BOTTOM, LEFT 
	public Tile create(String type) {
		List<Edge> edges = new ArrayList<Edge>();
		Region r1, r2, r3, r4, r5, r6, r7, r8, r9;
		switch(type){
		
			//First 7 Tiles
			//------------------------------------------------------------------------------------------
			case JJJJN:
				//Region Construction
				r1 = new Jungle();
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r1, r1, r1, r1, r1});
				
			case JJJJX:	
				//Region Construction
				r5 = new Den();
				r1 = new Jungle((Den) r5);
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r5, r1, r1, r1, r1});
				
			case JJTJX:
				//Region Construction
				r2 = new Den();
				r1 = new Jungle((Den) r2);
				r3 = new Trail(true, tileCount, false, NONE);
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1,r3,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r2, r1, r1, r3, r1});
				
			case TTTTN:
				//Region Construction
				r1 = new Jungle(); r3 = new Jungle(); r7 = new Jungle(); r9 = new Jungle();
				r2 = new Trail(true, tileCount, false, NONE); r4 = new Trail(true, tileCount, false, NONE);
				r6 = new Trail(true, tileCount, false, NONE); r8 = new Trail(true, tileCount, false, NONE);
				r5 = new Cross();
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3,r6,r9));	//RIGHT
				edges.add(new Edge(r9,r8,r7));	//BOTTOM
				edges.add(new Edge(r7,r4,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r4, r5, r6, r7, r8, r9});
				
			case TJTJN:
				//Region Construction
				r1 = new Jungle(); r3 = new Jungle();
				r2 = new Trail(false, tileCount, false, NONE);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3,r2,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r1, r2, r3, r1, r2, r3});
				
			case TJJTN:
				//Region Construction
				r1 = new Jungle(); r3 = new Jungle();
				r2 = new Trail(false, tileCount, false, NONE);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3));		//BOTTOM
				edges.add(new Edge(r3,r2,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r2, r2, r3, r3, r3, r3});
				
			case TJTTN:
				//Region Construction
				r1 = new Jungle(); r3 = new Jungle(); r7 = new Jungle();
				r2 = new Trail(true, tileCount, false, NONE); r4 = new Trail(true, tileCount, false, NONE);
				r8 = new Trail(true, tileCount, false, NONE);
				r5 = new Cross();
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3,r8,r7));	//BOTTOM
				edges.add(new Edge(r7,r4,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r4, r5, r3, r7, r8, r3});
				
				
			//Second 7 Tiles
			//------------------------------------------------------------------------------------------
			case LLLLN:
				//Region Construction
				r1 = new Lake(0, tileCount, false, NONE);
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r1, r1, r1, r1, r1});
			
			case JLLLN:
				//Region Construction
				r4 = new Lake(1, tileCount, false, NONE);
				r1 = new Jungle((Lake) r4);
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r4));		//RIGHT
				edges.add(new Edge(r4));		//BOTTOM
				edges.add(new Edge(r4));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r4, r4, r4, r4, r4, r4});

			case LLJJN:
				//Region Construction
				r2 = new Lake(2, tileCount, false, NONE);
				r1 = new Jungle((Lake) r2);
				edges.add(new Edge(r2));		//TOP
				edges.add(new Edge(r2));		//RIGHT
				edges.add(new Edge(r1));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r2, r1, r1, r2, r1, r1, r1});
				
			case JLJLN:
				//Region Construction
				r4 = new Lake(2, tileCount, false, NONE);
				r1 = new Jungle((Lake) r4);
				r7 = new Jungle((Lake) r4);
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r4));		//RIGHT
				edges.add(new Edge(r7));		//BOTTOM
				edges.add(new Edge(r4));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r4, r4, r4, r7, r7, r7});
				
			case LJLJN:
				//Region Construction
				r2 = new Lake(3, tileCount, false, NONE);
				r8 = new Lake(3, tileCount, false, NONE);
				r1 = new Jungle((Lake) r2, (Lake) r8);
				edges.add(new Edge(r2));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r8));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r1, r1, r1, r1, r1, r8, r1});
				
			case LJJJN:
				//Region Construction
				r2 = new Lake(3, tileCount, false, NONE);
				r1 = new Jungle((Lake) r2);
				edges.add(new Edge(r2));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r1, r1, r1, r1, r1, r1, r1});
				
			case JLLJN:
				//Region Construction				
				r6 = new Lake(3, tileCount, false, NONE);
				r8 = new Lake(3, tileCount, false, NONE);
				r1 = new Jungle((Lake) r6, (Lake) r8);
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r8));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r1, r6, r1, r8, r1});
				
				
			//Third 7 Tiles
			//------------------------------------------------------------------------------------------
			case TLJTN:
				//Region Construction
				r2 = new Trail(false, tileCount, false, NONE);
				r6 = new Lake(3, tileCount, false, NONE);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r3));		//BOTTOM
				edges.add(new Edge(r3,r2,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r2, r2, r6, r3, r3, r3});
				
			case TLJTP:
				//Region Construction
				r2 = new Trail(false, tileCount, false, BOARD);
				r6 = new Lake(3, tileCount, false, BOARD);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r3));		//BOTTOM
				edges.add(new Edge(r3,r2,r1));	//LEFT
				return assemble(type, BOARD, edges, new Region[]{r1, r2, r3, r2, r2, r6, r3, r3, r3});
				
			case JLTTN:
				//Region Construction
				r3 = new Trail(false, tileCount, false, NONE);
				r6 = new Lake(3, tileCount, false, NONE);
				r1 = new Jungle((Lake) r6); r7 = new Jungle();
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r1,r3,r7));	//BOTTOM
				edges.add(new Edge(r7,r3,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r3, r3, r6, r7, r3, r1});
				
			case JLTTB:
				//Region Construction
				r3 = new Trail(false, tileCount, false, BUFFALO);
				r6 = new Lake(3, tileCount, false, BUFFALO);
				r1 = new Jungle((Lake) r6); r7 = new Jungle();
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r1,r3,r7));	//BOTTOM
				edges.add(new Edge(r7,r3,r1));	//LEFT
				return assemble(type, BUFFALO, edges, new Region[]{r1, r1, r1, r3, r3, r6, r7, r3, r1});
				
			case TLTJN:
				//Region Construction
				r2 = new Trail(false, tileCount, false, NONE);
				r6 = new Lake(3, tileCount, false, NONE);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r3,r2,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r1, r2, r6, r1, r2, r3});
				
			case TLTJD:
				//Region Construction
				r2 = new Trail(false, tileCount, false, DEER);
				r6 = new Lake(3, tileCount, false, DEER);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r3,r2,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, DEER, edges, new Region[]{r1, r2, r3, r1, r2, r6, r1, r2, r3});
				
			case TLLLN:
				//Region Construction				
				r2 = new Trail(true, tileCount, false, NONE);
				r4 = new Lake(1, tileCount, false, NONE);
				r1 = new Jungle((Lake) r4); r3 = new Jungle((Lake) r4);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r4));		//RIGHT
				edges.add(new Edge(r4));		//BOTTOM
				edges.add(new Edge(r4));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r4, r4, r4, r4, r4, r4});
				
				
			//Fourth 7 Tiles
			//------------------------------------------------------------------------------------------
			case TLTTN:
				//Region Construction			
				r2 = new Trail(true, tileCount, false, NONE); r4 = new Trail(true, tileCount, false, NONE); 
				r8 = new Trail(true, tileCount, false, NONE);
				r6 = new Lake(3, tileCount, false, NONE);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6); r7 = new Jungle();
				r5 = new Cross();
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r3,r8,r7));	//BOTTOM
				edges.add(new Edge(r7,r4,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r4, r5, r6, r7, r8, r3});
			
			case TLTTP:
				//Region Construction
				r2 = new Trail(true, tileCount, false, BOARD); r4 = new Trail(true, tileCount, false, BOARD); 
				r8 = new Trail(true, tileCount, false, BOARD);
				r6 = new Lake(3, tileCount, false, BOARD);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6); r7 = new Jungle();
				r5 = new Cross();
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r3,r8,r7));	//BOTTOM
				edges.add(new Edge(r7,r4,r1));	//LEFT
				return assemble(type, BOARD, edges, new Region[]{r1, r2, r3, r4, r5, r6, r7, r8, r3});
				
			case TLLTN:
				//Region Construction				
				r2 = new Trail(false, tileCount, false, NONE);
				r6 = new Lake(2, tileCount, false, NONE);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r6));		//BOTTOM
				edges.add(new Edge(r3,r2,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r2, r2, r6, r3, r6, r6});
				
			case TLLTB:
				//Region Construction
				r2 = new Trail(false, tileCount, false, BUFFALO);
				r6 = new Lake(2, tileCount, false, BUFFALO);
				r1 = new Jungle(); r3 = new Jungle((Lake) r6);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r6));		//RIGHT
				edges.add(new Edge(r6));		//BOTTOM
				edges.add(new Edge(r3,r2,r1));	//LEFT
				return assemble(type, BUFFALO, edges, new Region[]{r1, r2, r3, r2, r2, r6, r3, r6, r6});
				
			case LJTJN:
				//Region Construction				
				r2 = new Lake(3, tileCount, false, NONE);
				r5 = new Trail(true, tileCount, false, NONE);
				r1 = new Jungle((Lake) r2); r3 = new Jungle((Lake) r2);
				edges.add(new Edge(r2));		//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3,r5,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r1, r5, r3, r1, r5, r3});
				
			case LJTJD:
				//Region Construction
				r2 = new Lake(3, tileCount, false, DEER);
				r5 = new Trail(true, tileCount, false, DEER);
				r1 = new Jungle((Lake) r2); r3 = new Jungle((Lake) r2);
				edges.add(new Edge(r2));		//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3,r5,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, DEER, edges, new Region[]{r1, r2, r3, r1, r5, r3, r1, r5, r3});
					
			case TLLLC:
				//Region Construction
				r2 = new Trail(true, tileCount, true, NONE);
				r4 = new Lake(1, tileCount, true, NONE);
				r1 = new Jungle((Lake) r4); r3 = new Jungle((Lake) r4);
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r4));		//RIGHT
				edges.add(new Edge(r4));		//BOTTOM
				edges.add(new Edge(r4));		//LEFT
				return assemble(type, CROCODILE, edges, new Region[]{r1, r2, r3, r4, r4, r4, r4, r4, r4});
		}
		return null;
	}
	

	protected Tile assemble(String type, int prey, List<Edge>  tileEdges, Region...regions){
		tileCount++;
		
		List<Tile> adjacentTiles = new ArrayList<Tile>();
		//This adjacent list may not be accurate with the rotation
		adjacentTiles.add(null);	//TOP
		adjacentTiles.add(null);	//RIGHT
		adjacentTiles.add(null);	//DOWN
		adjacentTiles.add(null);	//LEFT
		
		//Return a new Tile with specifications
		return new Tile(type, prey, adjacentTiles, tileEdges, regions);
	}
}
