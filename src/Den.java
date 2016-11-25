import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Church class implements Region.java:
|  	Contains methods specific to Church Region Type
-------------------------------------------------------------------------------------------------*/

public class Den extends Region {
	private boolean completed;
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
	
	public String toString(){
		return "Den at " + loc.toString();
	}
}