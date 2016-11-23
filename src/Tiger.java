/*-----------------------------------------------------------------------------------------------
|	Meeple abstract class
|  	Contains methods specific to Meeples
-------------------------------------------------------------------------------------------------*/

public class Tiger {

    boolean placed;
    int score;
    Player p;
    
    public Tiger(){
    	placed = false;
    	score = 0;
    }
    
    public Tiger(Player p){
    	placed = false;
    	score = 0;
    	this.p = p;
    }
    
	public void getBack(){
		//Method to dereference a meeple and add it's stored score to a player.
		//Called from within a RegionComposite when completed.
		//Called from within a player on game end.
		placed = false;
		p.updateScore(score);
		score = 0;
	}

    public boolean isAvailable() { return placed; }
    
    public void setScore(int i){
    	score = i;
    }
    
    public Player getPlayer(){ return p; }

}
