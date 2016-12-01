package GameEntities.Regions;
import GameEntities.Tile.Tiger;
import GlobalRefferences.GameInfo;

/*-----------------------------------------------------------------------------------------------
|	City class implements Region.java:
|  	Contains methods specific to City Region Type
-------------------------------------------------------------------------------------------------*/

public class Lake extends Region {
	 
	private boolean complete;
	private int caps;
	private int tileID;
	private int prey;
	private boolean crocodile;
	LakeComposite myComp = null;
    
    public Lake() 
    {
        type = GameInfo.CITY;
        id = -1;
    }
    
    public Lake(int initialCaps, int tID, boolean croc, int p) 
    {
        type = GameInfo.CITY;
        id = -1;
        caps = initialCaps;
        tileID = tID;
        crocodile = croc;
        prey = p;
    }
    
    public void addCap(){
    	caps++;
    }
    
    public void setComplete(boolean c)
    {
    	complete = c;
    }

	public String toString(){
		return "C" + super.toString();
	}

	public int getCaps(){
		return caps;
	}
	
	public boolean getCompleted() {

		return complete;
	}
	
	public boolean getCrocodile() {

		return crocodile;
	}

	public Integer getTileID() {
		// TODO Auto-generated method stub
		return tileID;
	}
	
	public int getPrey(){
		return prey;
	}
	
	 public LakeComposite getComp(){
	    	return myComp;
	 }
	    
	 public void setComp(LakeComposite j){
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