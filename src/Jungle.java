import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Field class implements Region.java:
|  	Contains methods specific to Field Region Type
-------------------------------------------------------------------------------------------------*/

public class Jungle extends Region {

    public Jungle() 
    {
        type = GameInfo.FIELD;
        id = -1;
    }
	
	public String toString(){
		return "F" + super.toString();
	}


}