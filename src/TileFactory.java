import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	TileFactory Class:
|  	Helps create different types of Tiles (For more info on possible types check out GameInfo class)
|	This class extends GameInfo in order to have access to all its constants directly
-------------------------------------------------------------------------------------------------*/

//EDGES LIST NUMERATION
/*----------------
|	  0 1 2		 |
|	2		0	 |
|	1		1	 |
|	0		2    |
|  	  2 1 0      |
------------------*/

//REGION MATRIX
/*--------------
  | 1 | 2 | 3 |
  |---|---|---|
  | 4 | 5 | 6 |	__________
  |---|---|---|			  |
  | 7 | 8 | 9 |		 	  |
--------------- 		  v
*/
//ARRAY = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ]


public class TileFactory  extends GameInfo implements Factory<Tile>{
	int tileCount = 0;

	@Override
	//NOTE: edges are being initialized TOP, RIGHT, BOTTOM, LEFT 
	public Tile create(String type) {
		List<Edge> edges;
		Region r1, r2, r3, r4, r5, r6, r7, r8, r9;
		switch(type){
		
			case JJJJN:
				 edges = new ArrayList<Edge>();
				//Region Construction
				//---------------------------------------------------------------
				r1 = new Jungle();
				//---------------------------------------------------------------
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r1, r1, r1, r1, r1});
				
			case JJJJX:
				edges = new ArrayList<Edge>();
				//Region Construction
				//---------------------------------------------------------------
				r5 = new Den();
				r1 = new Jungle((Den) r5);
				//---------------------------------------------------------------
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1));		//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r5, r1, r1, r1, r1});
				
			case JJTJX:
				edges = new ArrayList<Edge>();
				//Region Construction
				//---------------------------------------------------------------
				r2 = new Den();
				r1 = new Jungle((Den) r2);
				r3 = new Trail(true, tileCount, false, -1);
				//---------------------------------------------------------------
				edges.add(new Edge(r1));		//TOP
				edges.add(new Edge(r1));		//RIGHT
				edges.add(new Edge(r1,r3,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r1, r1, r1, r2, r1, r1, r3, r1});
				
			case TTTTN:
				edges = new ArrayList<Edge>();
				//Region Construction
				//------------------------------------------------------------------------
				r1 = new Jungle(); r3 = new Jungle(); r7 = new Jungle(); r9 = new Jungle();
				r2 = new Trail(true, tileCount, false, -1); r4 = new Trail(true, tileCount, false, -1);
				r6 = new Trail(true, tileCount, false, -1); r8 = new Trail(true, tileCount, false, -1);
				r5 = new Cross();
				//------------------------------------------------------------------------
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3,r6,r9));	//RIGHT
				edges.add(new Edge(r9,r8,r7));	//BOTTOM
				edges.add(new Edge(r7,r4,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r4, r5, r6, r7, r8, r9});
				
			case TJTJN:
				edges = new ArrayList<Edge>();
				//Region Construction
				//------------------------------------------------------------------------
				r1 = new Jungle(); r3 = new Jungle();
				r2 = new Trail(false, tileCount, false, -1);
				//------------------------------------------------------------------------
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3,r2,r1));	//BOTTOM
				edges.add(new Edge(r1));		//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r1, r2, r3, r1, r2, r3});
				
			case TJJTN:
				edges = new ArrayList<Edge>();
				//Region Construction
				//------------------------------------------------------------------------
				r1 = new Jungle(); r3 = new Jungle();
				r2 = new Trail(false, tileCount, false, -1);
				//------------------------------------------------------------------------
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3));		//BOTTOM
				edges.add(new Edge(r3,r2,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r2, r2, r3, r3, r3, r3});
				
			case TJTTN:
				edges = new ArrayList<Edge>();
				//Region Construction
				//------------------------------------------------------------------------
				r1 = new Jungle(); r3 = new Jungle(); r7 = new Jungle();
				r2 = new Trail(true, tileCount, false, -1); r4 = new Trail(true, tileCount, false, -1);
				r8 = new Trail(true, tileCount, false, -1);
				r5 = new Cross();
				//------------------------------------------------------------------------
				edges.add(new Edge(r1,r2,r3));	//TOP
				edges.add(new Edge(r3));		//RIGHT
				edges.add(new Edge(r3,r8,r7));	//BOTTOM
				edges.add(new Edge(r7,r4,r1));	//LEFT
				return assemble(type, NONE, edges, new Region[]{r1, r2, r3, r4, r5, r3, r7, r8, r3});
				
		}
		return null;
	}
//	JJJJN="JJJJ-", JJJJX="JJJJX", JJTJX="JJTJX", TTTTN="TTTT-", TJTJN="TJTJ-", TJJTN="TJJT-", TJTTN="TJTT-";
//	public static final String LLLLN="LLLL-", JLLLN="JLLL-", LLJJN="LLJJ-", JLJLN="JLJL-", LJLJN="LJLJ-", LJJJN="LJJJ-", JLLJN="JLLJ-";
//	public static final String TLJTN="TLJT-", TLJTP="TLJTP", JLTTB="JLTTB", JLTTN="JLTT-", TLTJN="TLTJ-", TLTJD="TLTJD", TLLLN="TLLL-";
//	public static final String TLTTN="TLTT-", TLTTP="TLTTP", TLLTN="TLLT-", TLLTB="TLLTB", LJTJN="LJTJ-", LJTJD="LJTJD", TLLLC="TLLLC";
	
	

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
