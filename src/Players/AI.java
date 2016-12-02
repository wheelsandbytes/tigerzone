package Players;
import java.util.*;
import java.util.Random;
/*------------------------------------------------------------------------------
|	AI extends Player Class:
|  	Contains information and behaviours of the AI
------------------------------------------------------------------------------*/

import GameEntities.Board;
import GameEntities.Deck;
import GameEntities.DataTools.Coor;
import GameEntities.DataTools.MeeplePlacement;
import GameEntities.DataTools.Move;
import GameEntities.Regions.Den;
import GameEntities.Regions.Jungle;
import GameEntities.Regions.Lake;
import GameEntities.Regions.Region;
import GameEntities.Regions.Trail;
import GameEntities.Tile.Edge;
import GameEntities.Tile.Tile;
import GlobalRefferences.GameInfo;

/*
AI functions:

Everything the base Player can do plus:

	Calulate the coordinate and rotation of the tile to be placed

*/
public class AI extends Player{
	
	int fieldMeeples = 0;

	public AI (Board board, String pid, Deck deck)
	{
		super(board,pid,deck);
	}

	public AI () {}

	// this method overrides setMove
	// this is where the decision making happens
	 private void setMove(Tile t)
	 {
		 Coor loc;
		 int rot;
		 // decisions to set loc and rot...
		//move = new Move(loc,rot,t);
	 }

	@Override
	public Move decideMove() {
	 	List<Move> possibleMoves = mainBoard.find(globalDeck.getCurrent());
	 	if(possibleMoves.isEmpty()) { return null; }
	 	
	 	Random rand = new Random();
	 	int randomInt = rand.nextInt(possibleMoves.size());
	 	
	 	Move fin = possibleMoves.get(randomInt);
	 	int maxEdges = mainBoard.possibleLocs.get(fin.getLocation().toString()).numEdges();
	 	for(Move m : possibleMoves){
	 		int edges = mainBoard.possibleLocs.get(m.getLocation().toString()).numEdges();
	 		for(Edge f : mainBoard.possibleLocs.get(m.getLocation().toString()).getAdjacent()){
	 			if(f != null){
	 				for(Region r : f.returnAllReg()){
	 					if(r instanceof Lake && !((Lake) r).getComp().tigerCount.isEmpty()){
	 						if(((Lake) r).getComp().tigerCount.get(this) == null){
	 							edges-=2;
	 						}
	 						else{
	 							edges+=2;
	 						}
	 					}
	 					else if(r instanceof Trail && !((Trail) r).getComp().tigerCount.isEmpty()){
		 						if(((Trail) r).getComp().tigerCount.get(this) == null){
		 							edges-=2;
		 						}
		 						else{
		 							edges+=2;
		 						}
		 					}
	 					}
	 				}
	 			}
	 		if(edges > maxEdges){
	 			maxEdges = edges;
	 			fin = m;
	 		}
	 	}
	 	
		return fin;
	}

	@Override
	public MeeplePlacement decideMeeple() {
		Tile t = globalDeck.getCurrent();
		
		if(this.getTiger() == null){
			System.out.println("NO TIGERS FOR" + name);
			return null;
		}
		
		if(t.getRegionAt(4) instanceof Den){ return new MeeplePlacement(GameInfo.TIGER, 5); }
		
		boolean croc = false;
		
		for(int i = 1; this.crocodiles > 0 && i < 10; i++){
			Region temp = t.getRegionAt(GameInfo.TIGERZONE.getZone(t.getRotation(), i));

			if(temp instanceof Lake && ((Lake) temp).getComp().tigerCount.get(this) != null) {
				croc = false;
				break;
			}
			
			else if(temp instanceof Lake && ((Lake) temp).getComp().crocodiles == 0 && !((Lake) temp).getComp().placedTigers.isEmpty()) {
				croc = true;
			}
		}
		
		if(croc) {
			System.out.println("CROC ROCK BOIS " + name);
			return new MeeplePlacement(GameInfo.CROCODILE, -1);
		}

		int optimum = -1;
		int pos = -1;
		boolean jungle = false;
		for(int i = 1; i < 10; i++){
			Region temp = t.getRegionAt(GameInfo.TIGERZONE.getZone(t.getRotation(), i));
				
			int pScore;
				if(temp instanceof Lake && ((Lake) temp).getComp().placedTigers.isEmpty()) {
					pScore = ((Lake) temp).getComp().score();
					if(pScore > optimum){
						optimum = pScore;
						jungle = false;
						pos = i;
					}
				}

				else if(temp instanceof Jungle && ((Jungle) temp).getComp().placedTigers.isEmpty()) {
					pScore = ((Jungle) temp).getComp().score();
					if(pScore > optimum){
						optimum = pScore;
						jungle = true;
						pos = i;
					}
				}

				else if(temp instanceof Trail && ((Trail) temp).getComp().placedTigers.isEmpty()) {
					pScore = ((Trail) temp).getComp().score();
					if(pScore > optimum){
						optimum = pScore;
						jungle = false;
						pos = i;
					}
				}
			
		}
		
		if(pos == -1 || (jungle && (fieldMeeples > 3))) { return null; }
		else {
			if(jungle) { fieldMeeples++; }
			System.out.println("TIGER AT " + pos + " " + name);
			return new MeeplePlacement(GameInfo.TIGER, pos); 
		}
	}
	
	/*
	private Move unplaceableDecide(){
		int c = 0;
		for(LakeComposite l : mainBoard.map.Lakes.values()){
			c = l.tigerCount.get(this) == null ? 0 : l.tigerCount.get(this);
			int j =l.totalTigers - c;
			if(j != l.totalTigers){
				if(j > l.tigerCount.get(this)){
					return new Move(null, -1, null);
				}
			}
		}
		
		for(JungleComposite l : mainBoard.map.Jungles.values()){
			c = l.tigerCount.get(this) == null ? 0 : l.tigerCount.get(this);
			int i = l.totalTigers - c;
			if(i != l.totalTigers){
				
			}
		}
		
		for(TrailComposite l : mainBoard.map.Trails.values()){
			c = l.tigerCount.get(this) == null ? 0 : l.tigerCount.get(this);
			int k =l.totalTigers - c;
			if(k != l.totalTigers){
				
			}
		}
		
	}*/

}
