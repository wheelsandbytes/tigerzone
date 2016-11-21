import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	City class implements Region.java:
|  	Contains methods specific to City Region Type
-------------------------------------------------------------------------------------------------*/

public class Lake extends Region {
	 
    
    public Lake() 
    {
        type = GameInfo.CITY;
        id = -1;
    }

	public String toString(){
		return "C" + super.toString();
	}
}