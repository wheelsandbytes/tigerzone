import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Road class implements Region.java:
|  	Contains methods specific to Road Region Type
-------------------------------------------------------------------------------------------------*/

public class Trail extends Region {

    public Trail() 
    {
        type = GameInfo.ROAD;
        id = -1;
    }
    
	public String toString(){
		return "R" + super.toString();
	}
}