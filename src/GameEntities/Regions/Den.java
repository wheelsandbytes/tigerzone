package GameEntities.Regions;
import java.util.List;

import GameEntities.DataTools.Coor;
import GameEntities.Tile.Tiger;
import GlobalRefferences.GameInfo;

/*-----------------------------------------------------------------------------------------------
|	Church class implements Region.java:
|  	Contains methods specific to Church Region Type
-------------------------------------------------------------------------------------------------*/

public class Den extends Region {
	private boolean completed;
	private int score;
	private Coor loc;
    
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
		placedMeeples.add(t);
	}
	
	public void returnTigers(){
		Tiger temp = null;
		for(Tiger t : placedMeeples){
			temp = t;
			t.getBack();
			
		}
		placedMeeples.clear();
		if(temp != null){
			temp.getPlayer().updateScore(score);
		}
	}
	
	public void setMeeple(Tiger t){
    	placeTiger(t);
	}
	
	public void removeMeeple(Tiger t) {
		placedMeeples.remove(placedMeeples.indexOf(t));
	}
	
	public String toString(){
		return "Den at " + loc.toString() + " Score: " + score + " " + placedMeeples;
	}
}