import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Church class implements Region.java:
|  	Contains methods specific to Church Region Type
-------------------------------------------------------------------------------------------------*/

public class Den extends Region {
	private boolean completed;
	private int score;
	private Coor loc;
	List<Tiger> placedTigers;
    
	public Den()
    {
		completed = false;
        type = GameInfo.CHURCH;
    }
	
	public boolean getCompleted(){
		return completed;
	}
	
	public void setCompleted(boolean b){
		completed = b;
	}
	
	public Coor getLoc(){
		return loc;
	}
	
	public void setLoc(Coor c){
		loc = c;
	}
	
	public void setScore(int i){
		score = i;
	}
	
	public int score(){
		return score;
	}
	
	public void placeTiger(Tiger t){
		
	}
	
	public void returnTigers(){
		for(Tiger t : placedTigers){
			t.getBack();
			placedTigers.remove(t);
		}
	}
	
	public String toString(){
		return "Den at " + loc.toString() + " Score: " + score;
	}
}