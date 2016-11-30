import java.util.*;
import java.util.Random;
/*------------------------------------------------------------------------------
|	AI extends Player Class:
|  	Contains information and behaviours of the AI
------------------------------------------------------------------------------*/

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
			for(Region r : t.getRegions()){
				if(r instanceof Lake && ((Lake) r).getCompleted()) { ((Lake) r).getComp().returnTigers(); }
				
				else if(r instanceof Trail && ((Trail) r).getComp().checkComplete()) { ((Trail) r).getComp().returnTigers(); }
			}
			return null;
		}
		
		if(t.getRegionAt(4) instanceof Den){ return new MeeplePlacement(GameInfo.TIGER, 5); }
		
		boolean croc = false;
		
		for(int i = 1; this.crocodiles > 0 && i < 10; i++){
			System.out.println("CROC CHECK");
			Region temp = t.getRegionAt(GameInfo.TIGERZONE.getZone(t.getRotation(), i));

			if(temp instanceof Lake && ((Lake) temp).getComp().tigerCount.get(this) != null) {
				System.out.println("IT'S OURS");
				croc = false;
				break;
			}
			
			else if(temp instanceof Lake && ((Lake) temp).getComp().crocodiles == 0 && !((Lake) temp).getComp().placedTigers.isEmpty()) {
				System.out.println("LET'S FUCK");	
				croc = true;
			}
		}
		
		if(croc) { 
			for(Region r : t.getRegions()){
				if(r instanceof Lake && ((Lake) r).getCompleted()) { ((Lake) r).getComp().returnTigers(); }
				
				else if(r instanceof Trail && ((Trail) r).getComp().checkComplete()) { ((Trail) r).getComp().returnTigers(); }
			}
			
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
					System.out.println(((Jungle) temp).getComp());
					pScore = ((Jungle) temp).getComp().score();
					if(pScore > optimum){
						optimum = pScore;
						jungle = true;
						pos = i;
					}
				}

				else if(temp instanceof Trail && ((Trail) temp).getComp().placedTigers.isEmpty()) {
					System.out.println("TRAIL BEING CONSIDERED AT " + i + " " + ((Trail) temp).getComp().placedTigers);
					pScore = ((Trail) temp).getComp().score();
					if(pScore > optimum){
						optimum = pScore;
						jungle = false;
						pos = i;
					}
				}
			
		}
		for(Region r : t.getRegions()){
			if(r instanceof Lake && ((Lake) r).getCompleted()) { ((Lake) r).getComp().returnTigers(); }
			
			else if(r instanceof Trail && ((Trail) r).getComp().checkComplete()) { ((Trail) r).getComp().returnTigers(); }
		}
		
		if(pos == -1 || (jungle && (fieldMeeples > 3))) { return null; }
		else { 
			if(jungle) { fieldMeeples++; }
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
