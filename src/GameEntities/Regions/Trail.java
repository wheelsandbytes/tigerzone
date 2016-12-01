package GameEntities.Regions;
import java.util.ArrayList;
import java.util.List;

import GameEntities.Tile.Tiger;
import GlobalRefferences.GameInfo;

/*-----------------------------------------------------------------------------------------------
|	Road class implements Region.java:
|  	Contains methods specific to Road Region Type
-------------------------------------------------------------------------------------------------*/

public class Trail extends Region {

	private int tileID;
	private boolean crocodile;
	private boolean end;
	private int prey;
	private TrailComposite myComp;
	
    public Trail() 
    {
        type = GameInfo.ROAD;
        id = -1;
    }
    
    public Trail(boolean end, int tID, boolean croc, int p) 
    {
        type = GameInfo.CITY;
        id = -1;
        this.end = end;
        tileID = tID;
        crocodile = croc;
        prey = p;
    }
    
	public String toString(){
		return "R" + super.toString();
	}

	public boolean getEnd() {
		return end;
	}
	
	public int getTileID() {
		return tileID;
	}

	public boolean getCrocodile() {
		return crocodile;
	}
	
	public int getPrey(){
		return prey;
	}
	
	 public TrailComposite getComp(){
		 return myComp;
	 }
	    
	 public void setComp(TrailComposite j){
		 myComp = j;
	 }
	 
	 public void setMeeple(Tiger t){
	    	placedMeeples.add(t);
	    	myComp.placeTiger(t);
	 }
	 
	 public void removeMeeple(Tiger t) {
		placedMeeples.remove(placedMeeples.indexOf(t));
		myComp.returnTiger(t);
	}
}