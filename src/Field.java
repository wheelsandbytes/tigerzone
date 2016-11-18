import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Field class implements Region.java:
|  	Contains methods specific to Field Region Type
-------------------------------------------------------------------------------------------------*/

public class Field extends Region {

    public Field() 
    {
        type = GameInfo.FIELD;
        id = -1;
    }
	
	public String toString(){
		return "F" + super.toString();
	}


}