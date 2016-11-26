import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Road class implements Region.java:
|  	Contains methods specific to Road Region Type
-------------------------------------------------------------------------------------------------*/

public class Trail extends Region {

	private int tileID;
	private boolean crocodile;
	private boolean end;
	private int prey;
	
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
}