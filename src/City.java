import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	City class implements Region.java:
|  	Contains methods specific to City Region Type
-------------------------------------------------------------------------------------------------*/

public class City extends Region {
	 
    
    public City() 
    {
        type = GameInfo.CITY;
        id = -1;
    }

	public String toString(){
		return "C" + super.toString();
	}
}